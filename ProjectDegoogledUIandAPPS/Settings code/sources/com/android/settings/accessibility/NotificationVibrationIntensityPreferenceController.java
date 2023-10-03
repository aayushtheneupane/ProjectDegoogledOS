package com.android.settings.accessibility;

import android.content.Context;

public class NotificationVibrationIntensityPreferenceController extends VibrationIntensityPreferenceController {
    static final String PREF_KEY = "notification_vibration_preference_screen";

    public int getAvailabilityStatus() {
        return 0;
    }

    public NotificationVibrationIntensityPreferenceController(Context context) {
        super(context, PREF_KEY, "notification_vibration_intensity", "");
    }

    /* access modifiers changed from: protected */
    public int getDefaultIntensity() {
        return this.mVibrator.getDefaultNotificationVibrationIntensity();
    }
}
