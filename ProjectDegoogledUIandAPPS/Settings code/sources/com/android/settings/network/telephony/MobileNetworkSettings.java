package com.android.settings.network.telephony;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserManager;
import android.provider.SearchIndexableResource;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.settings.dashboard.RestrictedDashboardFragment;
import com.android.settings.datausage.BillingCyclePreferenceController;
import com.android.settings.datausage.DataUsageSummaryPreferenceController;
import com.android.settings.development.featureflags.FeatureFlagPersistent;
import com.android.settings.network.telephony.cdma.CdmaSubscriptionPreferenceController;
import com.android.settings.network.telephony.cdma.CdmaSystemSelectPreferenceController;
import com.android.settings.network.telephony.gsm.AutoSelectPreferenceController;
import com.android.settings.network.telephony.gsm.OpenNetworkSelectPagePreferenceController;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.widget.PreferenceCategoryController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MobileNetworkSettings extends RestrictedDashboardFragment {
    static final String KEY_CLICKED_PREF = "key_clicked_pref";
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.mobile_network_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }

        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            return ((UserManager) context.getSystemService(UserManager.class)).isAdminUser();
        }
    };
    private CdmaSubscriptionPreferenceController mCdmaSubscriptionPreferenceController;
    private CdmaSystemSelectPreferenceController mCdmaSystemSelectPreferenceController;
    private String mClickedPrefKey;
    private int mSubId = -1;
    private TelephonyManager mTelephonyManager;
    private UserManager mUserManager;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "NetworkSettings";
    }

    public int getMetricsCategory() {
        return 1571;
    }

    public MobileNetworkSettings() {
        super("no_config_mobile_networks");
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        if (super.onPreferenceTreeClick(preference)) {
            return true;
        }
        String key = preference.getKey();
        if (!TextUtils.equals(key, "cdma_system_select_key") && !TextUtils.equals(key, "cdma_subscription_key")) {
            return false;
        }
        if (this.mTelephonyManager.getEmergencyCallbackMode()) {
            startActivityForResult(new Intent("com.android.internal.intent.action.ACTION_SHOW_NOTICE_ECM_BLOCK_OTHERS", (Uri) null), 17);
            this.mClickedPrefKey = key;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        this.mSubId = getArguments().getInt("android.provider.extra.SUB_ID", MobileNetworkUtils.getSearchableSubscriptionId(context));
        if (!FeatureFlagPersistent.isEnabled(getContext(), "settings_network_and_internet_v2") || this.mSubId == -1) {
            return Arrays.asList(new AbstractPreferenceController[0]);
        }
        return Arrays.asList(new AbstractPreferenceController[]{new DataUsageSummaryPreferenceController(getActivity(), getSettingsLifecycle(), this, this.mSubId)});
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (FeatureFlagPersistent.isEnabled(getContext(), "settings_network_and_internet_v2")) {
            ((CallsDefaultSubscriptionController) use(CallsDefaultSubscriptionController.class)).init(getLifecycle());
            ((SmsDefaultSubscriptionController) use(SmsDefaultSubscriptionController.class)).init(getLifecycle());
            ((MobileNetworkSwitchController) use(MobileNetworkSwitchController.class)).init(getLifecycle(), this.mSubId);
            ((CarrierSettingsVersionPreferenceController) use(CarrierSettingsVersionPreferenceController.class)).init(this.mSubId);
            ((BillingCyclePreferenceController) use(BillingCyclePreferenceController.class)).init(this.mSubId);
            ((MmsMessagePreferenceController) use(MmsMessagePreferenceController.class)).init(this.mSubId);
            ((DataDuringCallsPreferenceController) use(DataDuringCallsPreferenceController.class)).init(getLifecycle(), this.mSubId);
            ((DisabledSubscriptionController) use(DisabledSubscriptionController.class)).init(getLifecycle(), this.mSubId);
            ((DeleteSimProfilePreferenceController) use(DeleteSimProfilePreferenceController.class)).init(this.mSubId, this, 18);
        }
        ((MobileDataPreferenceController) use(MobileDataPreferenceController.class)).init(getFragmentManager(), this.mSubId);
        ((RoamingPreferenceController) use(RoamingPreferenceController.class)).init(getFragmentManager(), this.mSubId);
        ((ApnPreferenceController) use(ApnPreferenceController.class)).init(this.mSubId);
        ((CarrierPreferenceController) use(CarrierPreferenceController.class)).init(this.mSubId);
        ((DataUsagePreferenceController) use(DataUsagePreferenceController.class)).init(this.mSubId);
        ((PreferredNetworkModePreferenceController) use(PreferredNetworkModePreferenceController.class)).init(this.mSubId);
        ((EnabledNetworkModePreferenceController) use(EnabledNetworkModePreferenceController.class)).init(getLifecycle(), this.mSubId);
        ((DataServiceSetupPreferenceController) use(DataServiceSetupPreferenceController.class)).init(this.mSubId);
        if (!FeatureFlagPersistent.isEnabled(getContext(), "settings_network_and_internet_v2")) {
            ((EuiccPreferenceController) use(EuiccPreferenceController.class)).init(this.mSubId);
        }
        ((WifiCallingPreferenceController) use(WifiCallingPreferenceController.class)).init(this.mSubId);
        ((PreferenceCategoryController) use(PreferenceCategoryController.class)).setChildren(Arrays.asList(new AbstractPreferenceController[]{((AutoSelectPreferenceController) use(AutoSelectPreferenceController.class)).init(this.mSubId).addListener(((OpenNetworkSelectPagePreferenceController) use(OpenNetworkSelectPagePreferenceController.class)).init(this.mSubId))}));
        this.mCdmaSystemSelectPreferenceController = (CdmaSystemSelectPreferenceController) use(CdmaSystemSelectPreferenceController.class);
        this.mCdmaSystemSelectPreferenceController.init(getPreferenceManager(), this.mSubId);
        this.mCdmaSubscriptionPreferenceController = (CdmaSubscriptionPreferenceController) use(CdmaSubscriptionPreferenceController.class);
        this.mCdmaSubscriptionPreferenceController.init(getPreferenceManager(), this.mSubId);
        ((Enhanced4gLtePreferenceController) use(Enhanced4gLtePreferenceController.class)).init(this.mSubId).addListener(((VideoCallingPreferenceController) use(VideoCallingPreferenceController.class)).init(this.mSubId));
    }

    public void onCreate(Bundle bundle) {
        Log.i("NetworkSettings", "onCreate:+");
        super.onCreate(bundle);
        Context context = getContext();
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mTelephonyManager = TelephonyManager.from(context).createForSubscriptionId(this.mSubId);
        onRestoreInstance(bundle);
    }

    /* access modifiers changed from: package-private */
    public void onRestoreInstance(Bundle bundle) {
        if (bundle != null) {
            this.mClickedPrefKey = bundle.getString(KEY_CLICKED_PREF);
        }
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return FeatureFlagPersistent.isEnabled(getContext(), "settings_network_and_internet_v2") ? C1715R.xml.mobile_network_settings_v2 : C1715R.xml.mobile_network_settings;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(KEY_CLICKED_PREF, this.mClickedPrefKey);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Preference findPreference;
        FragmentActivity activity;
        if (i != 17) {
            if (i == 18 && (activity = getActivity()) != null && !activity.isFinishing()) {
                activity.finish();
            }
        } else if (i2 != 0 && (findPreference = getPreferenceScreen().findPreference(this.mClickedPrefKey)) != null) {
            findPreference.performClick();
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (FeatureFlagPersistent.isEnabled(getContext(), "settings_network_and_internet_v2") && this.mSubId != -1) {
            MenuItem add = menu.add(0, C1715R.C1718id.edit_sim_name, 0, C1715R.string.mobile_network_sim_name);
            add.setIcon(17302742);
            add.setShowAsAction(2);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (!FeatureFlagPersistent.isEnabled(getContext(), "settings_network_and_internet_v2") || this.mSubId == -1 || menuItem.getItemId() != C1715R.C1718id.edit_sim_name) {
            return super.onOptionsItemSelected(menuItem);
        }
        RenameMobileNetworkDialogFragment.newInstance(this.mSubId).show(getFragmentManager(), "RenameMobileNetwork");
        return true;
    }
}
