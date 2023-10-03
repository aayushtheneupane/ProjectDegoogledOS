package com.android.settings.network.telephony;

import android.content.Context;
import android.os.Looper;
import android.os.PersistableBundle;
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
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;
import java.util.ArrayList;
import java.util.List;

public class Enhanced4gLtePreferenceController extends TelephonyTogglePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private final int VARIANT_TITLE_4G_CALLING = 2;
    private final int VARIANT_TITLE_ADVANCED_CALL = 1;
    private final int VARIANT_TITLE_VOLTE = 0;
    private final List<On4gLteUpdateListener> m4gLteListeners;
    private PersistableBundle mCarrierConfig;
    private CarrierConfigManager mCarrierConfigManager;
    ImsManager mImsManager;
    private PhoneCallStateListener mPhoneStateListener;
    /* access modifiers changed from: private */
    public Preference mPreference;
    /* access modifiers changed from: private */
    public TelephonyManager mTelephonyManager;
    private final CharSequence[] mVariantSumaries;
    private final CharSequence[] mVariantTitles;

    public interface On4gLteUpdateListener {
        void on4gLteUpdated();
    }

    public Enhanced4gLtePreferenceController(Context context, String str) {
        super(context, str);
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
        this.m4gLteListeners = new ArrayList();
        this.mPhoneStateListener = new PhoneCallStateListener(Looper.getMainLooper());
        this.mVariantTitles = context.getResources().getTextArray(C1715R.array.enhanced_4g_lte_mode_title_variant);
        this.mVariantSumaries = context.getResources().getTextArray(C1715R.array.enhanced_4g_lte_mode_sumary_variant);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000b, code lost:
        r5 = r4.mImsManager;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getAvailabilityStatus(int r5) {
        /*
            r4 = this;
            android.telephony.CarrierConfigManager r0 = r4.mCarrierConfigManager
            android.os.PersistableBundle r0 = r0.getConfigForSubId(r5)
            r1 = 1
            r2 = 0
            r3 = -1
            if (r5 == r3) goto L_0x0031
            com.android.ims.ImsManager r5 = r4.mImsManager
            if (r5 == 0) goto L_0x0031
            if (r0 == 0) goto L_0x0031
            boolean r5 = r5.isVolteEnabledByPlatform()
            if (r5 == 0) goto L_0x0031
            com.android.ims.ImsManager r5 = r4.mImsManager
            boolean r5 = r5.isVolteProvisionedOnDevice()
            if (r5 == 0) goto L_0x0031
            com.android.ims.ImsManager r5 = r4.mImsManager
            boolean r5 = com.android.settings.network.telephony.MobileNetworkUtils.isImsServiceStateReady(r5)
            if (r5 == 0) goto L_0x0031
            java.lang.String r5 = "hide_enhanced_4g_lte_bool"
            boolean r5 = r0.getBoolean(r5)
            if (r5 != 0) goto L_0x0031
            r5 = r1
            goto L_0x0032
        L_0x0031:
            r5 = r2
        L_0x0032:
            if (r5 == 0) goto L_0x003c
            boolean r4 = r4.is4gLtePrefEnabled()
            if (r4 == 0) goto L_0x003d
            r1 = r2
            goto L_0x003d
        L_0x003c:
            r1 = 2
        L_0x003d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.network.telephony.Enhanced4gLtePreferenceController.getAvailabilityStatus(int):int");
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    public void onStart() {
        this.mPhoneStateListener.register(this.mSubId);
    }

    public void onStop() {
        this.mPhoneStateListener.unregister();
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        SwitchPreference switchPreference = (SwitchPreference) preference;
        boolean z = this.mCarrierConfig.getBoolean("show_4g_for_lte_data_icon_bool");
        int i = this.mCarrierConfig.getInt("enhanced_4g_lte_title_variant_int");
        boolean z2 = false;
        if (i != 1) {
            i = z ? 2 : 0;
        }
        switchPreference.setTitle(this.mVariantTitles[i]);
        switchPreference.setSummary(this.mVariantSumaries[i]);
        switchPreference.setEnabled(is4gLtePrefEnabled());
        if (this.mImsManager.isEnhanced4gLteModeSettingEnabledByUser() && this.mImsManager.isNonTtyOrTtyOnVolteEnabled()) {
            z2 = true;
        }
        switchPreference.setChecked(z2);
    }

    public boolean setChecked(boolean z) {
        this.mImsManager.setEnhanced4gLteModeSetting(z);
        for (On4gLteUpdateListener on4gLteUpdated : this.m4gLteListeners) {
            on4gLteUpdated.on4gLteUpdated();
        }
        return true;
    }

    public boolean isChecked() {
        return this.mImsManager.isEnhanced4gLteModeSettingEnabledByUser();
    }

    public Enhanced4gLtePreferenceController init(int i) {
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
        this.mCarrierConfig = this.mCarrierConfigManager.getConfigForSubId(this.mSubId);
        int i2 = this.mSubId;
        if (i2 != -1) {
            this.mImsManager = ImsManager.getInstance(this.mContext, SubscriptionManager.getPhoneId(i2));
        }
        return this;
    }

    public Enhanced4gLtePreferenceController addListener(On4gLteUpdateListener on4gLteUpdateListener) {
        this.m4gLteListeners.add(on4gLteUpdateListener);
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000d, code lost:
        r0 = r2.mImsManager;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean is4gLtePrefEnabled() {
        /*
            r2 = this;
            int r0 = r2.mSubId
            r1 = -1
            if (r0 == r1) goto L_0x0023
            android.telephony.TelephonyManager r1 = r2.mTelephonyManager
            int r0 = r1.getCallState(r0)
            if (r0 != 0) goto L_0x0023
            com.android.ims.ImsManager r0 = r2.mImsManager
            if (r0 == 0) goto L_0x0023
            boolean r0 = r0.isNonTtyOrTtyOnVolteEnabled()
            if (r0 == 0) goto L_0x0023
            android.os.PersistableBundle r2 = r2.mCarrierConfig
            java.lang.String r0 = "editable_enhanced_4g_lte_bool"
            boolean r2 = r2.getBoolean(r0)
            if (r2 == 0) goto L_0x0023
            r2 = 1
            goto L_0x0024
        L_0x0023:
            r2 = 0
        L_0x0024:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.network.telephony.Enhanced4gLtePreferenceController.is4gLtePrefEnabled():boolean");
    }

    private class PhoneCallStateListener extends PhoneStateListener {
        public PhoneCallStateListener(Looper looper) {
            super(looper);
        }

        public void onCallStateChanged(int i, String str) {
            Enhanced4gLtePreferenceController enhanced4gLtePreferenceController = Enhanced4gLtePreferenceController.this;
            enhanced4gLtePreferenceController.updateState(enhanced4gLtePreferenceController.mPreference);
        }

        public void register(int i) {
            this.mSubId = Integer.valueOf(i);
            Enhanced4gLtePreferenceController.this.mTelephonyManager.listen(this, 32);
        }

        public void unregister() {
            Enhanced4gLtePreferenceController.this.mTelephonyManager.listen(this, 0);
        }
    }
}
