package com.android.settings.network.telephony;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneStateListener;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.ims.ImsManager;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

public class WifiCallingPreferenceController extends TelephonyBasePreferenceController implements LifecycleObserver, OnStart, OnStop {
    static final String KEY_PREFERENCE_CATEGORY = "calling_category";
    CarrierConfigManager mCarrierConfigManager;
    ImsManager mImsManager;
    private PhoneCallStateListener mPhoneStateListener = new PhoneCallStateListener(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public Preference mPreference;
    PhoneAccountHandle mSimCallManager;
    /* access modifiers changed from: private */
    public TelephonyManager mTelephonyManager;

    public WifiCallingPreferenceController(Context context, String str) {
        super(context, str);
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
    }

    public int getAvailabilityStatus(int i) {
        return (i == -1 || !MobileNetworkUtils.isWifiCallingEnabled(this.mContext, i)) ? 3 : 0;
    }

    public void onStart() {
        this.mPhoneStateListener.register(this.mSubId);
    }

    public void onStop() {
        this.mPhoneStateListener.unregister();
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        Preference findPreference;
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
        Intent intent = this.mPreference.getIntent();
        if (intent != null) {
            intent.putExtra("android.provider.extra.SUB_ID", this.mSubId);
        }
        if (!isAvailable() && (findPreference = preferenceScreen.findPreference(KEY_PREFERENCE_CATEGORY)) != null) {
            findPreference.setVisible(false);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0052, code lost:
        r3 = r3.getConfigForSubId(r6.mSubId);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateState(androidx.preference.Preference r7) {
        /*
            r6 = this;
            super.updateState(r7)
            android.telecom.PhoneAccountHandle r0 = r6.mSimCallManager
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0031
            android.content.Context r3 = r6.mContext
            android.content.Intent r0 = com.android.settings.network.telephony.MobileNetworkUtils.buildPhoneAccountConfigureIntent(r3, r0)
            if (r0 != 0) goto L_0x0012
            return
        L_0x0012:
            android.content.Context r3 = r6.mContext
            android.content.pm.PackageManager r3 = r3.getPackageManager()
            java.util.List r4 = r3.queryIntentActivities(r0, r2)
            java.lang.Object r4 = r4.get(r2)
            android.content.pm.ResolveInfo r4 = (android.content.pm.ResolveInfo) r4
            java.lang.CharSequence r3 = r4.loadLabel(r3)
            r7.setTitle((java.lang.CharSequence) r3)
            r3 = 0
            r7.setSummary((java.lang.CharSequence) r3)
            r7.setIntent(r0)
            goto L_0x008b
        L_0x0031:
            android.content.Context r0 = r6.mContext
            int r3 = r6.mSubId
            android.content.res.Resources r0 = android.telephony.SubscriptionManager.getResourcesForSubId(r0, r3)
            r3 = 2131891525(0x7f121545, float:1.9417773E38)
            java.lang.String r0 = r0.getString(r3)
            r7.setTitle((java.lang.CharSequence) r0)
            r0 = 17041368(0x10407d8, float:2.42502E-38)
            com.android.ims.ImsManager r3 = r6.mImsManager
            boolean r3 = r3.isWfcEnabledByUser()
            if (r3 == 0) goto L_0x0088
            android.telephony.CarrierConfigManager r3 = r6.mCarrierConfigManager
            if (r3 == 0) goto L_0x0061
            int r4 = r6.mSubId
            android.os.PersistableBundle r3 = r3.getConfigForSubId(r4)
            if (r3 == 0) goto L_0x0061
            java.lang.String r4 = "use_wfc_home_network_mode_in_roaming_network_bool"
            boolean r3 = r3.getBoolean(r4)
            goto L_0x0062
        L_0x0061:
            r3 = r2
        L_0x0062:
            android.telephony.TelephonyManager r4 = r6.mTelephonyManager
            boolean r4 = r4.isNetworkRoaming()
            com.android.ims.ImsManager r5 = r6.mImsManager
            if (r4 == 0) goto L_0x0070
            if (r3 != 0) goto L_0x0070
            r3 = r1
            goto L_0x0071
        L_0x0070:
            r3 = r2
        L_0x0071:
            int r3 = r5.getWfcMode(r3)
            if (r3 == 0) goto L_0x0085
            if (r3 == r1) goto L_0x0081
            r4 = 2
            if (r3 == r4) goto L_0x007d
            goto L_0x0088
        L_0x007d:
            r0 = 17041330(0x10407b2, float:2.4250092E-38)
            goto L_0x0088
        L_0x0081:
            r0 = 17041327(0x10407af, float:2.4250084E-38)
            goto L_0x0088
        L_0x0085:
            r0 = 17041329(0x10407b1, float:2.425009E-38)
        L_0x0088:
            r7.setSummary((int) r0)
        L_0x008b:
            android.telephony.TelephonyManager r0 = r6.mTelephonyManager
            int r6 = r6.mSubId
            int r6 = r0.getCallState(r6)
            if (r6 != 0) goto L_0x0096
            goto L_0x0097
        L_0x0096:
            r1 = r2
        L_0x0097:
            r7.setEnabled(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.network.telephony.WifiCallingPreferenceController.updateState(androidx.preference.Preference):void");
    }

    public void init(int i) {
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
        this.mImsManager = ImsManager.getInstance(this.mContext, SubscriptionManager.getPhoneId(this.mSubId));
        this.mSimCallManager = ((TelecomManager) this.mContext.getSystemService(TelecomManager.class)).getSimCallManagerForSubscription(this.mSubId);
    }

    private class PhoneCallStateListener extends PhoneStateListener {
        public PhoneCallStateListener(Looper looper) {
            super(looper);
        }

        public void onCallStateChanged(int i, String str) {
            WifiCallingPreferenceController wifiCallingPreferenceController = WifiCallingPreferenceController.this;
            wifiCallingPreferenceController.updateState(wifiCallingPreferenceController.mPreference);
        }

        public void register(int i) {
            this.mSubId = Integer.valueOf(i);
            WifiCallingPreferenceController.this.mTelephonyManager.listen(this, 32);
        }

        public void unregister() {
            WifiCallingPreferenceController.this.mTelephonyManager.listen(this, 0);
        }
    }
}
