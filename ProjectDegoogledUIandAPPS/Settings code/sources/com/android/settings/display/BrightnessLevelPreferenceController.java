package com.android.settings.display;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.service.vr.IVrManager;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.display.BrightnessUtils;
import java.text.NumberFormat;

public class BrightnessLevelPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnStart, OnStop {
    private static final Uri BRIGHTNESS_ADJ_URI = Settings.System.getUriFor("screen_auto_brightness_adj");
    private static final Uri BRIGHTNESS_FOR_VR_URI = Settings.System.getUriFor("screen_brightness_for_vr");
    private static final Uri BRIGHTNESS_URI = Settings.System.getUriFor("screen_brightness");
    private ContentObserver mBrightnessObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
        public void onChange(boolean z) {
            BrightnessLevelPreferenceController brightnessLevelPreferenceController = BrightnessLevelPreferenceController.this;
            brightnessLevelPreferenceController.updatedSummary(brightnessLevelPreferenceController.mPreference);
        }
    };
    private final ContentResolver mContentResolver;
    private final int mMaxBrightness;
    private final int mMaxVrBrightness;
    private final int mMinBrightness;
    private final int mMinVrBrightness;
    /* access modifiers changed from: private */
    public Preference mPreference;

    private double getPercentage(double d, int i, int i2) {
        if (d > ((double) i2)) {
            return 1.0d;
        }
        double d2 = (double) i;
        if (d < d2) {
            return 0.0d;
        }
        return (d - d2) / ((double) (i2 - i));
    }

    public String getPreferenceKey() {
        return "brightness";
    }

    public boolean isAvailable() {
        return true;
    }

    public BrightnessLevelPreferenceController(Context context, Lifecycle lifecycle) {
        super(context);
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        this.mMinBrightness = powerManager.getMinimumScreenBrightnessSetting();
        this.mMaxBrightness = powerManager.getMaximumScreenBrightnessSetting();
        this.mMinVrBrightness = powerManager.getMinimumScreenBrightnessForVrSetting();
        this.mMaxVrBrightness = powerManager.getMaximumScreenBrightnessForVrSetting();
        this.mContentResolver = this.mContext.getContentResolver();
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference("brightness");
    }

    public void updateState(Preference preference) {
        updatedSummary(preference);
    }

    public void onStart() {
        this.mContentResolver.registerContentObserver(BRIGHTNESS_URI, false, this.mBrightnessObserver);
        this.mContentResolver.registerContentObserver(BRIGHTNESS_FOR_VR_URI, false, this.mBrightnessObserver);
        this.mContentResolver.registerContentObserver(BRIGHTNESS_ADJ_URI, false, this.mBrightnessObserver);
    }

    public void onStop() {
        this.mContentResolver.unregisterContentObserver(this.mBrightnessObserver);
    }

    /* access modifiers changed from: private */
    public void updatedSummary(Preference preference) {
        if (preference != null) {
            preference.setSummary((CharSequence) NumberFormat.getPercentInstance().format(getCurrentBrightness()));
        }
    }

    private double getCurrentBrightness() {
        int i;
        if (isInVrMode()) {
            i = BrightnessUtils.convertLinearToGamma(Settings.System.getInt(this.mContentResolver, "screen_brightness_for_vr", this.mMaxBrightness), this.mMinVrBrightness, this.mMaxVrBrightness);
        } else {
            i = BrightnessUtils.convertLinearToGamma(Settings.System.getInt(this.mContentResolver, "screen_brightness", this.mMinBrightness), this.mMinBrightness, this.mMaxBrightness);
        }
        return getPercentage((double) i, 0, 1023);
    }

    /* access modifiers changed from: package-private */
    public IVrManager safeGetVrManager() {
        return IVrManager.Stub.asInterface(ServiceManager.getService("vrmanager"));
    }

    /* access modifiers changed from: package-private */
    public boolean isInVrMode() {
        IVrManager safeGetVrManager = safeGetVrManager();
        if (safeGetVrManager == null) {
            return false;
        }
        try {
            return safeGetVrManager.getVrModeState();
        } catch (RemoteException e) {
            Log.e("BrightnessPrefCtrl", "Failed to check vr mode!", e);
            return false;
        }
    }
}
