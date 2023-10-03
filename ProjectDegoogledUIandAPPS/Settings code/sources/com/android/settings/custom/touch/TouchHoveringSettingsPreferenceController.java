package com.android.settings.custom.touch;

import android.content.Context;
import com.android.internal.custom.hardware.LineageHardwareManager;
import com.android.settings.core.BasePreferenceController;

public class TouchHoveringSettingsPreferenceController extends BasePreferenceController {
    public static final String KEY = "feature_touch_hovering";
    private final LineageHardwareManager mHardware;

    public TouchHoveringSettingsPreferenceController(Context context, String str) {
        super(context, str);
        this.mHardware = LineageHardwareManager.getInstance(context);
    }

    public TouchHoveringSettingsPreferenceController(Context context) {
        this(context, KEY);
    }

    public int getAvailabilityStatus() {
        return !this.mHardware.isSupported(2048) ? 3 : 0;
    }
}
