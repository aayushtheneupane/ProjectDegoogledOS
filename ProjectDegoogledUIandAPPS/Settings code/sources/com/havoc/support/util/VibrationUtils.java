package com.havoc.support.util;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;

public class VibrationUtils {
    public static void doHapticFeedback(Context context, int i) {
        Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
        boolean z = true;
        if (Settings.System.getInt(context.getContentResolver(), "haptic_feedback_enabled", 1) == 0) {
            z = false;
        }
        if (vibrator != null && vibrator.hasVibrator() && z) {
            vibrator.vibrate(VibrationEffect.get(i));
        }
    }
}
