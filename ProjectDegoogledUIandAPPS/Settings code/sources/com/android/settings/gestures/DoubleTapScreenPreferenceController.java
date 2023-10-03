package com.android.settings.gestures;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.display.AmbientDisplayConfiguration;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;

public class DoubleTapScreenPreferenceController extends GesturePreferenceController {
    private static final String PREF_KEY_VIDEO = "gesture_double_tap_screen_video";
    private final int OFF = 0;

    /* renamed from: ON */
    private final int f34ON = 1;
    private final String SECURE_KEY = "doze_pulse_on_double_tap";
    private AmbientDisplayConfiguration mAmbientConfig;
    private final int mUserId = UserHandle.myUserId();

    /* access modifiers changed from: protected */
    public String getVideoPrefKey() {
        return PREF_KEY_VIDEO;
    }

    public DoubleTapScreenPreferenceController(Context context, String str) {
        super(context, str);
    }

    public DoubleTapScreenPreferenceController setConfig(AmbientDisplayConfiguration ambientDisplayConfiguration) {
        this.mAmbientConfig = ambientDisplayConfiguration;
        return this;
    }

    public static boolean isSuggestionComplete(Context context, SharedPreferences sharedPreferences) {
        return isSuggestionComplete(new AmbientDisplayConfiguration(context), sharedPreferences);
    }

    static boolean isSuggestionComplete(AmbientDisplayConfiguration ambientDisplayConfiguration, SharedPreferences sharedPreferences) {
        if (!ambientDisplayConfiguration.doubleTapSensorAvailable() || sharedPreferences.getBoolean("pref_double_tap_screen_suggestion_complete", false)) {
            return true;
        }
        return false;
    }

    public int getAvailabilityStatus() {
        return !getAmbientConfig().doubleTapSensorAvailable() ? 3 : 0;
    }

    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), "gesture_double_tap_screen");
    }

    public boolean setChecked(boolean z) {
        return Settings.Secure.putInt(this.mContext.getContentResolver(), "doze_pulse_on_double_tap", z ? 1 : 0);
    }

    public boolean isChecked() {
        return getAmbientConfig().doubleTapGestureEnabled(this.mUserId);
    }

    private AmbientDisplayConfiguration getAmbientConfig() {
        if (this.mAmbientConfig == null) {
            this.mAmbientConfig = new AmbientDisplayConfiguration(this.mContext);
        }
        return this.mAmbientConfig;
    }
}
