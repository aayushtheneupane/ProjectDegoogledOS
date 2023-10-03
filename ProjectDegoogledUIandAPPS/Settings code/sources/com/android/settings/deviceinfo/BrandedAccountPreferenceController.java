package com.android.settings.deviceinfo;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.accounts.AccountDetailDashboardFragment;
import com.android.settings.accounts.AccountFeatureProvider;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.overlay.FeatureFactory;
import com.havoc.config.center.C1715R;

public class BrandedAccountPreferenceController extends BasePreferenceController {
    private final Account[] mAccounts = FeatureFactory.getFactory(this.mContext).getAccountFeatureProvider().getAccounts(this.mContext);

    public BrandedAccountPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        if (!this.mContext.getResources().getBoolean(C1715R.bool.config_show_branded_account_in_device_info)) {
            return 3;
        }
        Account[] accountArr = this.mAccounts;
        return (accountArr == null || accountArr.length <= 0) ? 4 : 0;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        Account[] accountArr;
        super.displayPreference(preferenceScreen);
        AccountFeatureProvider accountFeatureProvider = FeatureFactory.getFactory(this.mContext).getAccountFeatureProvider();
        Preference findPreference = preferenceScreen.findPreference(getPreferenceKey());
        if (findPreference == null || !((accountArr = this.mAccounts) == null || accountArr.length == 0)) {
            findPreference.setSummary((CharSequence) this.mAccounts[0].name);
            findPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(accountFeatureProvider) {
                private final /* synthetic */ AccountFeatureProvider f$1;

                {
                    this.f$1 = r2;
                }

                public final boolean onPreferenceClick(Preference preference) {
                    return BrandedAccountPreferenceController.this.lambda$displayPreference$0$BrandedAccountPreferenceController(this.f$1, preference);
                }
            });
            return;
        }
        preferenceScreen.removePreference(findPreference);
    }

    public /* synthetic */ boolean lambda$displayPreference$0$BrandedAccountPreferenceController(AccountFeatureProvider accountFeatureProvider, Preference preference) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("account", this.mAccounts[0]);
        bundle.putParcelable("user_handle", Process.myUserHandle());
        bundle.putString("account_type", accountFeatureProvider.getAccountType());
        new SubSettingLauncher(this.mContext).setDestination(AccountDetailDashboardFragment.class.getName()).setTitleRes(C1715R.string.account_sync_title).setArguments(bundle).setSourceMetricsCategory(40).launch();
        return true;
    }
}
