package com.android.settings.deviceinfo.hardwareinfo;

import android.app.ActivityManager;
import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.slices.Sliceable;
import com.havoc.config.center.C1715R;
import java.text.DecimalFormat;

public class TotalRAMPreferenceController extends BasePreferenceController {
    public boolean isCopyableSlice() {
        return true;
    }

    public boolean isSliceable() {
        return true;
    }

    public boolean useDynamicSliceSummary() {
        return true;
    }

    public TotalRAMPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_device_model) ? 0 : 3;
    }

    public void copy() {
        Sliceable.setCopyContent(this.mContext, getSummary(), this.mContext.getText(C1715R.string.total_ram));
    }

    public CharSequence getSummary() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) this.mContext.getSystemService("activity")).getMemoryInfo(memoryInfo);
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        double d = (double) memoryInfo.totalMem;
        double d2 = d / 1024.0d;
        double d3 = d / 1048576.0d;
        double d4 = d / 1.073741824E9d;
        if (d4 > 1.0d) {
            return decimalFormat.format(d4).concat(" GB");
        }
        if (d3 > 1.0d) {
            return decimalFormat.format(d3).concat(" MB");
        }
        return decimalFormat.format(d2).concat(" KB");
    }
}
