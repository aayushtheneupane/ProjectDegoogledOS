package com.android.settings.applications.specialaccess.vrlistener;

import android.app.ActivityManager;
import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class VrListenerScreenPreferenceController extends BasePreferenceController {
    private final ActivityManager mActivityManager = ((ActivityManager) this.mContext.getSystemService(ActivityManager.class));

    public VrListenerScreenPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return (!this.mContext.getResources().getBoolean(C1715R.bool.config_show_enabled_vr_listeners) || this.mActivityManager.isLowRamDevice()) ? 3 : 0;
    }
}
