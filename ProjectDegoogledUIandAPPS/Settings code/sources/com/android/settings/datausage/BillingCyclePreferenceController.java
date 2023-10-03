package com.android.settings.datausage;

import android.content.Context;
import android.net.INetworkStatsService;
import android.net.NetworkPolicyManager;
import android.os.INetworkManagementService;
import android.os.ServiceManager;
import android.os.UserManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.datausage.TemplatePreference;
import com.android.settingslib.NetworkPolicyEditor;
import com.android.settingslib.net.DataUsageUtils;

public class BillingCyclePreferenceController extends BasePreferenceController {
    private int mSubscriptionId;

    public int getAvailabilityStatus() {
        return 0;
    }

    public BillingCyclePreferenceController(Context context, String str) {
        super(context, str);
    }

    public void init(int i) {
        this.mSubscriptionId = i;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        TemplatePreference.NetworkServices networkServices = new TemplatePreference.NetworkServices();
        networkServices.mNetworkService = INetworkManagementService.Stub.asInterface(ServiceManager.getService("network_management"));
        networkServices.mStatsService = INetworkStatsService.Stub.asInterface(ServiceManager.getService("netstats"));
        networkServices.mPolicyManager = (NetworkPolicyManager) this.mContext.getSystemService(NetworkPolicyManager.class);
        networkServices.mPolicyEditor = new NetworkPolicyEditor(networkServices.mPolicyManager);
        networkServices.mTelephonyManager = (TelephonyManager) this.mContext.getSystemService(TelephonyManager.class);
        networkServices.mSubscriptionManager = (SubscriptionManager) this.mContext.getSystemService(SubscriptionManager.class);
        networkServices.mUserManager = (UserManager) this.mContext.getSystemService(UserManager.class);
        ((BillingCyclePreference) preferenceScreen.findPreference(getPreferenceKey())).setTemplate(DataUsageUtils.getMobileTemplate(this.mContext, this.mSubscriptionId), this.mSubscriptionId, networkServices);
    }
}
