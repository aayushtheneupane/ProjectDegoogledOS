package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.DeviceInfoUtils;

public class KernelVersionPreferenceController extends BasePreferenceController {
    public int getAvailabilityStatus() {
        return 0;
    }

    public KernelVersionPreferenceController(Context context, String str) {
        super(context, str);
    }

    public CharSequence getSummary() {
        return DeviceInfoUtils.getFormattedKernelVersion(this.mContext);
    }
}
