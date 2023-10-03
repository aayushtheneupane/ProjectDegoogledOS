package com.android.contacts.common.model.account;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.ArrayMap;
import com.android.contacts.common.model.dataitem.DataKind;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public abstract class AccountType {
    public String accountType = null;
    public String dataSet = null;
    protected boolean mIsInitialized;
    private ArrayList<DataKind> mKinds = new ArrayList<>();
    private Map<String, DataKind> mMimeKinds = new ArrayMap();
    public String resourcePackageName;
    public String syncAdapterPackageName;

    protected static class DefinitionException extends Exception {
        public DefinitionException(String str) {
            super(str);
        }

        public DefinitionException(String str, Exception exc) {
            super(str, exc);
        }
    }

    public static final class EditField {
        public String column;
        public int inputType;
        public boolean longForm;
        public int minLines;
        public boolean optional;
        public boolean shortForm;
        public int titleRes;

        public EditField(String str, int i, int i2) {
            this.column = str;
            this.titleRes = i;
            this.inputType = i2;
        }

        public String toString() {
            return EditField.class.getSimpleName() + ": column=" + this.column + " titleRes=" + this.titleRes + " inputType=" + this.inputType + " minLines=" + this.minLines + " optional=" + this.optional + " shortForm=" + this.shortForm + " longForm=" + this.longForm;
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

        public EventEditType setYearOptional(boolean z) {
            this.mYearOptional = z;
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(EventEditType.class.getSimpleName() + " rawValue=" + this.rawValue + " labelRes=" + this.labelRes + " secondary=" + this.secondary + " specificMax=" + this.specificMax + " customColumn=" + this.customColumn);
            sb.append(" mYearOptional=");
            sb.append(this.mYearOptional);
            return sb.toString();
        }
    }

    public interface StringInflater {
        CharSequence inflateUsing(Context context, ContentValues contentValues);
    }

    static {
        new Comparator<DataKind>() {
            public int compare(Object obj, Object obj2) {
                return ((DataKind) obj).weight - ((DataKind) obj2).weight;
            }
        };
    }

    static CharSequence getResourceText(Context context, String str, int i, String str2) {
        if (i == -1 || str == null) {
            return i != -1 ? context.getText(i) : str2;
        }
        return context.getPackageManager().getText(str, i, (ApplicationInfo) null);
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
            throw new DefinitionException(GeneratedOutlineSupport.outline12(GeneratedOutlineSupport.outline13("mime type '"), dataKind.mimeType, "' is already registered"));
        }
    }

    public abstract boolean areContactsWritable();

    public List<String> getExtensionPackageNames() {
        return new ArrayList();
    }

    public String getInviteContactActivityClassName() {
        return null;
    }

    public DataKind getKindForMimetype(String str) {
        return this.mMimeKinds.get(str);
    }

    public String getViewContactNotifyServiceClassName() {
        return null;
    }

    public String getViewContactNotifyServicePackageName() {
        return this.syncAdapterPackageName;
    }

    public boolean isEmbedded() {
        return true;
    }

    public abstract boolean isGroupMembershipEditable();

    public final boolean isInitialized() {
        return this.mIsInitialized;
    }
}
