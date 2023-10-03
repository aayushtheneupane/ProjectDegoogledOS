package com.android.settings.accessibility;

import android.content.Context;

public class RingVibrationIntensityPreferenceController extends VibrationIntensityPreferenceController {
    static final String PREF_KEY = "ring_vibration_preference_screen";

    public int getAvailabilityStatus() {
        return 0;
    }

    public RingVibrationIntensityPreferenceController(Context context) {
        super(context, PREF_KEY, "ring_vibration_intensity", "vibrate_when_ringing", true);
    }

    /* access modifiers changed from: protected */
    public int getDefaultIntensity() {
        return this.mVibrator.getDefaultRingVibrationIntensity();
    }
}
