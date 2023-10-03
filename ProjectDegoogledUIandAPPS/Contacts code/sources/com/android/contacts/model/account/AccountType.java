package com.android.contacts.model.account;

import android.accounts.AuthenticatorDescription;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import com.android.contacts.R;
import com.android.contacts.model.dataitem.DataKind;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public abstract class AccountType {
    private static final String TAG = "AccountType";
    private static Comparator<DataKind> sWeightComparator = new Comparator<DataKind>() {
        public int compare(DataKind dataKind, DataKind dataKind2) {
            return dataKind.weight - dataKind2.weight;
        }
    };
    public String accountType = null;
    public String dataSet = null;
    public int iconRes;
    protected boolean mIsInitialized;
    private ArrayList<DataKind> mKinds = Lists.newArrayList();
    private HashMap<String, DataKind> mMimeKinds = Maps.newHashMap();
    public String resourcePackageName;
    public String syncAdapterPackageName;
    public int titleRes;

    public interface StringInflater {
        CharSequence inflateUsing(Context context, ContentValues contentValues);
    }

    public abstract boolean areContactsWritable();

    /* access modifiers changed from: protected */
    public int getInviteContactActionResId() {
        return -1;
    }

    public String getInviteContactActivityClassName() {
        return null;
    }

    public String getViewContactNotifyServiceClassName() {
        return null;
    }

    public String getViewGroupActivity() {
        return null;
    }

    /* access modifiers changed from: protected */
    public int getViewGroupLabelResId() {
        return -1;
    }

    public boolean isEmbedded() {
        return true;
    }

    public boolean isExtension() {
        return false;
    }

    public abstract boolean isGroupMembershipEditable();

    protected static class DefinitionException extends Exception {
        public DefinitionException(String str) {
            super(str);
        }

        public DefinitionException(String str, Exception exc) {
            super(str, exc);
        }
    }

    public final boolean isInitialized() {
        return this.mIsInitialized;
    }

    public String getViewContactNotifyServicePackageName() {
        return this.syncAdapterPackageName;
    }

    public CharSequence getDisplayLabel(Context context) {
        return getResourceText(context, this.syncAdapterPackageName, this.titleRes, this.accountType);
    }

    public AccountInfo wrapAccount(Context context, AccountWithDataSet accountWithDataSet) {
        Preconditions.checkArgument(Objects.equal(accountWithDataSet.type, this.accountType), "Account types must match: account.type=%s but accountType=%s", accountWithDataSet.type, this.accountType);
        return new AccountInfo(new AccountDisplayInfo(accountWithDataSet, accountWithDataSet.name, getDisplayLabel(context), getDisplayIcon(context), false), this);
    }

    public AccountTypeWithDataSet getAccountTypeAndDataSet() {
        return AccountTypeWithDataSet.get(this.accountType, this.dataSet);
    }

    public List<String> getExtensionPackageNames() {
        return new ArrayList();
    }

    public CharSequence getInviteContactActionLabel(Context context) {
        return getResourceText(context, this.syncAdapterPackageName, getInviteContactActionResId(), "");
    }

    public CharSequence getViewGroupLabel(Context context) {
        CharSequence resourceText = getResourceText(context, this.syncAdapterPackageName, getViewGroupLabelResId(), (String) null);
        return resourceText == null ? context.getText(R.string.view_updates_from_group) : resourceText;
    }

    static CharSequence getResourceText(Context context, String str, int i, String str2) {
        if (i == -1 || str == null) {
            return i != -1 ? context.getText(i) : str2;
        }
        return context.getPackageManager().getText(str, i, (ApplicationInfo) null);
    }

    public Drawable getDisplayIcon(Context context) {
        return getDisplayIcon(context, this.titleRes, this.iconRes, this.syncAdapterPackageName);
    }

    public static Drawable getDisplayIcon(Context context, int i, int i2, String str) {
        if (i != -1 && str != null) {
            return context.getPackageManager().getDrawable(str, i2, (ApplicationInfo) null);
        }
        if (i != -1) {
            return context.getResources().getDrawable(i2);
        }
        return null;
    }

    public ArrayList<DataKind> getSortedDataKinds() {
        Collections.sort(this.mKinds, sWeightComparator);
        return this.mKinds;
    }

    public DataKind getKindForMimetype(String str) {
        return this.mMimeKinds.get(str);
    }

    public void initializeFieldsFromAuthenticator(AuthenticatorDescription authenticatorDescription) {
        this.accountType = authenticatorDescription.type;
        this.titleRes = authenticatorDescription.labelId;
        this.iconRes = authenticatorDescription.iconId;
    }

    public DataKind addKind(DataKind dataKind) throws DefinitionException {
        String str = dataKind.mimeType;
        if (str == null) {
            throw new DefinitionException("null is not a valid mime type");
        } else if (this.mMimeKinds.get(str) == null) {
            dataKind.resourcePackageName = this.resourcePackageName;
            this.mKinds.add(dataKind);
            this.mMimeKinds.put(dataKind.mimeType, dataKind);
            return dataKind;
        } else {
            throw new DefinitionException("mime type '" + dataKind.mimeType + "' is already registered");
        }
    }

    public static class EditType {
        public String customColumn;
        public int labelRes;
        public int rawValue;
        public boolean secondary;
        public int specificMax = -1;

        public EditType(int i, int i2) {
            this.rawValue = i;
            this.labelRes = i2;
        }

        public EditType setSecondary(boolean z) {
            this.secondary = z;
            return this;
        }

        public EditType setSpecificMax(int i) {
            this.specificMax = i;
            return this;
        }

        public EditType setCustomColumn(String str) {
            this.customColumn = str;
            return this;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof EditType) || ((EditType) obj).rawValue != this.rawValue) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.rawValue;
        }

        public String toString() {
            return getClass().getSimpleName() + " rawValue=" + this.rawValue + " labelRes=" + this.labelRes + " secondary=" + this.secondary + " specificMax=" + this.specificMax + " customColumn=" + this.customColumn;
        }
    }

    public static class EventEditType extends EditType {
        private boolean mYearOptional;

        public EventEditType(int i, int i2) {
            super(i, i2);
        }

        public boolean isYearOptional() {
            return this.mYearOptional;
        }

        public EventEditType setYearOptional(boolean z) {
            this.mYearOptional = z;
            return this;
        }

        public String toString() {
            return super.toString() + " mYearOptional=" + this.mYearOptional;
        }
    }

    public static final class EditField {
        public String column;
        public int inputType;
        public boolean longForm;
        public int minLines;
        public boolean optional;
        public String phoneticsColumn;
        public boolean shortForm;
        public int titleRes;

        public EditField(String str, int i) {
            this.column = str;
            this.titleRes = i;
        }

        public EditField(String str, int i, int i2) {
            this(str, i);
            this.inputType = i2;
        }

        public EditField setOptional(boolean z) {
            this.optional = z;
            return this;
        }

        public EditField setShortForm(boolean z) {
            this.shortForm = z;
            return this;
        }

        public EditField setLongForm(boolean z) {
            this.longForm = z;
            return this;
        }

        public EditField setPhoneticsColumn(String str) {
            this.phoneticsColumn = str;
            return this;
        }

        public EditField setMinLines(int i) {
            this.minLines = i;
            return this;
        }

        public boolean isMultiLine() {
            return (this.inputType & 131072) != 0;
        }

        public String toString() {
            return EditField.class.getSimpleName() + ": column=" + this.column + " titleRes=" + this.titleRes + " inputType=" + this.inputType + " minLines=" + this.minLines + " optional=" + this.optional + " shortForm=" + this.shortForm + " longForm=" + this.longForm;
        }
    }

    public static class DisplayLabelComparator implements Comparator<AccountType> {
        private final Collator mCollator = Collator.getInstance();
        private final Context mContext;

        public DisplayLabelComparator(Context context) {
            this.mContext = context;
        }

        private String getDisplayLabel(AccountType accountType) {
            CharSequence displayLabel = accountType.getDisplayLabel(this.mContext);
            if (displayLabel == null) {
                return "";
            }
            return displayLabel.toString();
        }

        public int compare(AccountType accountType, AccountType accountType2) {
            return this.mCollator.compare(getDisplayLabel(accountType), getDisplayLabel(accountType2));
        }
    }
}
