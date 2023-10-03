package com.android.settings.network.telephony;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneStateListener;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.ims.ImsManager;
import com.android.settings.network.telephony.Enhanced4gLtePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.havoc.support.preferences.SwitchPreference;

public class VideoCallingPreferenceController extends TelephonyTogglePreferenceController implements LifecycleObserver, OnStart, OnStop, Enhanced4gLtePreferenceController.On4gLteUpdateListener {
    private CarrierConfigManager mCarrierConfigManager;
    private DataContentObserver mDataContentObserver = new DataContentObserver(new Handler(Looper.getMainLooper()));
    ImsManager mImsManager;
    private PhoneCallStateListener mPhoneStateListener = new PhoneCallStateListener(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public Preference mPreference;
    /* access modifiers changed from: private */
    public TelephonyManager mTelephonyManager;

    public VideoCallingPreferenceController(Context context, String str) {
        super(context, str);
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
    }

    public int getAvailabilityStatus(int i) {
        return (i == -1 || !MobileNetworkUtils.isWifiCallingEnabled(this.mContext, i) || !isVideoCallEnabled(i)) ? 2 : 0;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    public void onStart() {
        this.mPhoneStateListener.register(this.mSubId);
        this.mDataContentObserver.register(this.mContext, this.mSubId);
    }

    public void onStop() {
        this.mPhoneStateListener.unregister();
        this.mDataContentObserver.unRegister(this.mContext);
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        SwitchPreference switchPreference = (SwitchPreference) preference;
        boolean isVideoCallEnabled = isVideoCallEnabled(this.mSubId, this.mImsManager);
        switchPreference.setVisible(isVideoCallEnabled);
        if (isVideoCallEnabled) {
            boolean z = true;
            boolean z2 = this.mImsManager.isEnhanced4gLteModeSettingEnabledByUser() && this.mImsManager.isNonTtyOrTtyOnVolteEnabled();
            preference.setEnabled(z2 && this.mTelephonyManager.getCallState(this.mSubId) == 0);
            if (!z2 || !this.mImsManager.isVtEnabledByUser()) {
                z = false;
            }
            switchPreference.setChecked(z);
        }
    }

    public boolean setChecked(boolean z) {
        this.mImsManager.setVtSetting(z);
        return true;
    }

    public boolean isChecked() {
        return this.mImsManager.isVtEnabledByUser();
    }

    public VideoCallingPreferenceController init(int i) {
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
        int i2 = this.mSubId;
        if (i2 != -1) {
            this.mImsManager = ImsManager.getInstance(this.mContext, SubscriptionManager.getPhoneId(i2));
        }
        return this;
    }

    private boolean isVideoCallEnabled(int i) {
        return isVideoCallEnabled(i, i != -1 ? ImsManager.getInstance(this.mContext, SubscriptionManager.getPhoneId(i)) : null);
    }

    /* access modifiers changed from: package-private */
    public boolean isVideoCallEnabled(int i, ImsManager imsManager) {
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(i);
        return configForSubId != null && imsManager != null && imsManager.isVtEnabledByPlatform() && imsManager.isVtProvisionedOnDevice() && MobileNetworkUtils.isImsServiceStateReady(imsManager) && (configForSubId.getBoolean("ignore_data_enabled_changed_for_video_calls") || TelephonyManager.from(this.mContext).createForSubscriptionId(i).isDataEnabled());
    }

    public void on4gLteUpdated() {
        updateState(this.mPreference);
    }

    private class PhoneCallStateListener extends PhoneStateListener {
        public PhoneCallStateListener(Looper looper) {
            super(looper);
        }

        public void onCallStateChanged(int i, String str) {
            VideoCallingPreferenceController videoCallingPreferenceController = VideoCallingPreferenceController.this;
            videoCallingPreferenceController.updateState(videoCallingPreferenceController.mPreference);
        }

        public void register(int i) {
            this.mSubId = Integer.valueOf(i);
            VideoCallingPreferenceController.this.mTelephonyManager.listen(this, 32);
        }

        public void unregister() {
            VideoCallingPreferenceController.this.mTelephonyManager.listen(this, 0);
        }
    }

    public class DataContentObserver extends ContentObserver {
        public DataContentObserver(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z) {
            super.onChange(z);
            VideoCallingPreferenceController videoCallingPreferenceController = VideoCallingPreferenceController.this;
            videoCallingPreferenceController.updateState(videoCallingPreferenceController.mPreference);
        }

        public void register(Context context, int i) {
            Uri uriFor = Settings.Global.getUriFor("mobile_data");
            if (TelephonyManager.getDefault().getSimCount() != 1) {
                uriFor = Settings.Global.getUriFor("mobile_data" + i);
            }
            context.getContentResolver().registerContentObserver(uriFor, false, this);
        }

        public void unRegister(Context context) {
            context.getContentResolver().unregisterContentObserver(this);
        }
    }
}
