package com.android.settings.custom.touch;

import android.content.Context;
import com.android.internal.custom.hardware.LineageHardwareManager;
import com.android.settings.core.BasePreferenceController;

public class HighTouchSensitivitySettingsPreferenceController extends BasePreferenceController {
    public static final String KEY = "high_touch_sensitivity_enable";
    private final LineageHardwareManager mHardware;

    public HighTouchSensitivitySettingsPreferenceController(Context context, String str) {
        super(context, str);
        this.mHardware = LineageHardwareManager.getInstance(context);
    }

    public HighTouchSensitivitySettingsPreferenceController(Context context) {
        this(context, KEY);
    }

    public int getAvailabilityStatus() {
        return !this.mHardware.isSupported(16) ? 3 : 0;
    }
}
