package com.android.settings.gestures;

import android.content.Context;
import com.android.internal.custom.hardware.LineageHardwareManager;
import com.android.settings.core.BasePreferenceController;

public class TouchGestureSettingsPreferenceController extends BasePreferenceController {
    public static final String KEY = "touchscreen_gesture_settings";
    private final LineageHardwareManager mHardware;

    public TouchGestureSettingsPreferenceController(Context context, String str) {
        super(context, str);
        this.mHardware = LineageHardwareManager.getInstance(context);
    }

    public TouchGestureSettingsPreferenceController(Context context) {
        this(context, KEY);
    }

    public int getAvailabilityStatus() {
        return !this.mHardware.isSupported(524288) ? 3 : 0;
    }
}
