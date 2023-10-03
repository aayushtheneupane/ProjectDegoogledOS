package com.android.settings.deviceinfo.hardwareinfo;

import android.content.Context;
import android.os.Build;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.slices.Sliceable;
import com.havoc.config.center.C1715R;

public class SerialNumberPreferenceController extends BasePreferenceController {
    public boolean isCopyableSlice() {
        return true;
    }

    public boolean isSliceable() {
        return true;
    }

    public boolean useDynamicSliceSummary() {
        return true;
    }

    public SerialNumberPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_device_model) ? 0 : 3;
    }

    public void copy() {
        Sliceable.setCopyContent(this.mContext, getSummary(), this.mContext.getText(C1715R.string.status_serial_number));
    }

    public CharSequence getSummary() {
        return Build.getSerial();
    }
}
