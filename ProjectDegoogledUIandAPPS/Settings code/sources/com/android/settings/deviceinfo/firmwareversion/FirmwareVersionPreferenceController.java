package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.Build;
import com.android.settings.core.BasePreferenceController;

public class FirmwareVersionPreferenceController extends BasePreferenceController {
    public int getAvailabilityStatus() {
        return 1;
    }

    public FirmwareVersionPreferenceController(Context context, String str) {
        super(context, str);
    }

    public CharSequence getSummary() {
        return Build.VERSION.RELEASE;
    }
}
