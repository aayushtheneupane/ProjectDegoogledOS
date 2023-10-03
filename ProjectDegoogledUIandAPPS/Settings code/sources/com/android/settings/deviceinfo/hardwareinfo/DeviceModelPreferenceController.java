package com.android.settings.deviceinfo.hardwareinfo;

import android.content.Context;
import com.android.settings.deviceinfo.HardwareInfoPreferenceController;

public class DeviceModelPreferenceController extends HardwareInfoPreferenceController {
    public boolean isSliceable() {
        return true;
    }

    public boolean useDynamicSliceSummary() {
        return true;
    }

    public DeviceModelPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        int availabilityStatus = super.getAvailabilityStatus();
        if (availabilityStatus == 1) {
            return 0;
        }
        return availabilityStatus;
    }

    public CharSequence getSummary() {
        return HardwareInfoPreferenceController.getDeviceModel();
    }
}
