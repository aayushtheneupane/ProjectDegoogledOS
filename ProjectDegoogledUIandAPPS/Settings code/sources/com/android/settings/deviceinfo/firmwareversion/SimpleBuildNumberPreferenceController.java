package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.Build;
import android.text.BidiFormatter;
import com.android.settings.core.BasePreferenceController;

public class SimpleBuildNumberPreferenceController extends BasePreferenceController {
    public int getAvailabilityStatus() {
        return 1;
    }

    public SimpleBuildNumberPreferenceController(Context context, String str) {
        super(context, str);
    }

    public CharSequence getSummary() {
        return BidiFormatter.getInstance().unicodeWrap(Build.DISPLAY);
    }
}
