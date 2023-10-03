package com.android.settings.deviceinfo.hardwareinfo;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.slices.Sliceable;
import com.havoc.config.center.C1715R;

public class HardwareRevisionPreferenceController extends BasePreferenceController {
    public boolean isCopyableSlice() {
        return true;
    }

    public boolean isSliceable() {
        return true;
    }

    public boolean useDynamicSliceSummary() {
        return true;
    }

    public HardwareRevisionPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return (!this.mContext.getResources().getBoolean(C1715R.bool.config_show_device_model) || TextUtils.isEmpty(getSummary())) ? 3 : 0;
    }

    public void copy() {
        Sliceable.setCopyContent(this.mContext, getSummary(), this.mContext.getText(C1715R.string.hardware_revision));
    }

    public CharSequence getSummary() {
        return SystemProperties.get("ro.boot.hardware.revision");
    }
}
