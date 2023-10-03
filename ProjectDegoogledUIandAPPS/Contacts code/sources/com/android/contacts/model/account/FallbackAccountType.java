package com.android.contacts.model.account;

import android.accounts.AuthenticatorDescription;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import com.android.contacts.R;
import com.android.contacts.model.account.AccountType;
import com.android.contactsbind.FeedbackHelper;

public class FallbackAccountType extends BaseAccountType {
    private static final String TAG = "FallbackAccountType";

    public boolean areContactsWritable() {
        return true;
    }

    public void initializeFieldsFromAuthenticator(AuthenticatorDescription authenticatorDescription) {
    }

    private FallbackAccountType(Context context, String str) {
        this.accountType = null;
        this.dataSet = null;
        this.titleRes = R.string.account_phone;
        this.iconRes = R.drawable.quantum_ic_smartphone_vd_theme_24;
        this.resourcePackageName = str;
        this.syncAdapterPackageName = str;
        try {
            addDataKindStructuredName(context);
            addDataKindName(context);
            addDataKindPhoneticName(context);
            addDataKindNickname(context);
            addDataKindPhone(context);
            addDataKindEmail(context);
            addDataKindStructuredPostal(context);
            addDataKindIm(context);
            addDataKindOrganization(context);
            addDataKindPhoto(context);
            addDataKindNote(context);
            addDataKindWebsite(context);
            addDataKindSipAddress(context);
            addDataKindGroupMembership(context);
            this.mIsInitialized = true;
        } catch (AccountType.DefinitionException e) {
            FeedbackHelper.sendFeedback(context, TAG, "Failed to build fallback account type", e);
        }
    }

    public Drawable getDisplayIcon(Context context) {
        Drawable drawable = ResourcesCompat.getDrawable(context.getResources(), this.iconRes, (Resources.Theme) null);
        drawable.mutate().setColorFilter(ContextCompat.getColor(context, R.color.actionbar_icon_color_grey), PorterDuff.Mode.SRC_ATOP);
        return drawable;
    }

    public FallbackAccountType(Context context) {
        this(context, (String) null);
    }

    static AccountType createWithPackageNameForTest(Context context, String str) {
        return new FallbackAccountType(context, str);
    }

    public AccountInfo wrapAccount(Context context, AccountWithDataSet accountWithDataSet) {
        return new AccountInfo(new AccountDisplayInfo(accountWithDataSet, accountWithDataSet.name, getDisplayLabel(context), getDisplayIcon(context), false), this);
    }
}
