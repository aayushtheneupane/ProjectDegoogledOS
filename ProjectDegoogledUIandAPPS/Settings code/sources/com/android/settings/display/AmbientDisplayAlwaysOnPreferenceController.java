package com.android.settings.display;

import android.content.Context;
import android.hardware.display.AmbientDisplayConfiguration;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.settings.core.TogglePreferenceController;

public class AmbientDisplayAlwaysOnPreferenceController extends TogglePreferenceController {
    private static final int MY_USER = UserHandle.myUserId();
    private static final String PROP_AWARE_AVAILABLE = "ro.vendor.aware_available";
    private final int OFF = 0;

    /* renamed from: ON */
    private final int f26ON = 1;
    private OnPreferenceChangedCallback mCallback;
    private AmbientDisplayConfiguration mConfig;

    public interface OnPreferenceChangedCallback {
        void onPreferenceChanged();
    }

    public AmbientDisplayAlwaysOnPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        if (!isAvailable(getConfig()) || SystemProperties.getBoolean(PROP_AWARE_AVAILABLE, false)) {
            return 3;
        }
        return 0;
    }

    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), "ambient_display_always_on");
    }

    public boolean isChecked() {
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "screen_off_fod", 0, -2) != 0) {
            return !getConfig().alwaysOnEnabled(MY_USER);
        }
        return getConfig().alwaysOnEnabled(MY_USER);
    }

    public boolean setChecked(boolean z) {
        try {
            if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "screen_off_fod", 0) != 0 && z) {
                Settings.System.putInt(this.mContext.getContentResolver(), "screen_off_fod", 0);
            }
        } catch (Settings.SettingNotFoundException unused) {
        }
        Settings.Secure.putInt(this.mContext.getContentResolver(), "doze_always_on", z ? 1 : 0);
        OnPreferenceChangedCallback onPreferenceChangedCallback = this.mCallback;
        if (onPreferenceChangedCallback != null) {
            onPreferenceChangedCallback.onPreferenceChanged();
        }
        return true;
    }

    public AmbientDisplayAlwaysOnPreferenceController setConfig(AmbientDisplayConfiguration ambientDisplayConfiguration) {
        this.mConfig = ambientDisplayConfiguration;
        return this;
    }

    public AmbientDisplayAlwaysOnPreferenceController setCallback(OnPreferenceChangedCallback onPreferenceChangedCallback) {
        this.mCallback = onPreferenceChangedCallback;
        return this;
    }

    public static boolean isAvailable(AmbientDisplayConfiguration ambientDisplayConfiguration) {
        return ambientDisplayConfiguration.alwaysOnAvailableForUser(MY_USER);
    }

    private AmbientDisplayConfiguration getConfig() {
        if (this.mConfig == null) {
            this.mConfig = new AmbientDisplayConfiguration(this.mContext);
        }
        return this.mConfig;
    }
}
