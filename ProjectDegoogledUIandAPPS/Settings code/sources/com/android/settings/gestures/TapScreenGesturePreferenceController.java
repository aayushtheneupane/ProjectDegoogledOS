package com.android.settings.gestures;

import android.content.Context;
import android.hardware.display.AmbientDisplayConfiguration;
import android.os.UserHandle;
import android.provider.Settings;

public class TapScreenGesturePreferenceController extends GesturePreferenceController {
    private static final String PREF_KEY_VIDEO = "gesture_tap_screen_video";
    private AmbientDisplayConfiguration mAmbientConfig;
    private final int mUserId = UserHandle.myUserId();

    /* access modifiers changed from: protected */
    public String getVideoPrefKey() {
        return PREF_KEY_VIDEO;
    }

    public boolean isSliceable() {
        return true;
    }

    public TapScreenGesturePreferenceController(Context context, String str) {
        super(context, str);
    }

    public TapScreenGesturePreferenceController setConfig(AmbientDisplayConfiguration ambientDisplayConfiguration) {
        this.mAmbientConfig = ambientDisplayConfiguration;
        return this;
    }

    public int getAvailabilityStatus() {
        return !getAmbientConfig().tapSensorAvailable() ? 3 : 0;
    }

    public CharSequence getSummary() {
        return super.getSummary();
    }

    public boolean isChecked() {
        return getAmbientConfig().tapGestureEnabled(this.mUserId);
    }

    public boolean setChecked(boolean z) {
        return Settings.Secure.putInt(this.mContext.getContentResolver(), "doze_tap_gesture", z ? 1 : 0);
    }

    private AmbientDisplayConfiguration getAmbientConfig() {
        if (this.mAmbientConfig == null) {
            this.mAmbientConfig = new AmbientDisplayConfiguration(this.mContext);
        }
        return this.mAmbientConfig;
    }
}
