package com.android.settings.connecteddevice;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class TopLevelConnectedDevicesPreferenceController extends BasePreferenceController {
    public TopLevelConnectedDevicesPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_top_level_connected_devices) ? 1 : 3;
    }

    public CharSequence getSummary() {
        Context context = this.mContext;
        return context.getText(AdvancedConnectedDeviceController.getConnectedDevicesSummaryResourceId(context));
    }
}
