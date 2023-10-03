package com.android.settings.accessibility;

import android.os.Vibrator;
import com.havoc.config.center.C1715R;

public class RingVibrationPreferenceFragment extends VibrationPreferenceFragment {
    public int getMetricsCategory() {
        return 1620;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.accessibility_ring_vibration_settings;
    }

    /* access modifiers changed from: protected */
    public int getPreviewVibrationAudioAttributesUsage() {
        return 6;
    }

    /* access modifiers changed from: protected */
    public String getVibrationIntensitySetting() {
        return "ring_vibration_intensity";
    }

    /* access modifiers changed from: protected */
    public String getVibrationEnabledSetting() {
        return AccessibilitySettings.isRampingRingerEnabled(getContext()) ? "apply_ramping_ringer" : "vibrate_when_ringing";
    }

    /* access modifiers changed from: protected */
    public int getDefaultVibrationIntensity() {
        return ((Vibrator) getContext().getSystemService(Vibrator.class)).getDefaultRingVibrationIntensity();
    }
}
