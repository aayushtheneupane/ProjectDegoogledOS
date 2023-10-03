package com.android.settings.deviceinfo.aboutphone;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.deviceinfo.DeviceNamePreferenceController;

public class TopLevelAboutDevicePreferenceController extends BasePreferenceController {
    public int getAvailabilityStatus() {
        return 0;
    }

    public TopLevelAboutDevicePreferenceController(Context context, String str) {
        super(context, str);
    }

    public CharSequence getSummary() {
        return new DeviceNamePreferenceController(this.mContext, "dummy_key").getSummary();
    }
}
