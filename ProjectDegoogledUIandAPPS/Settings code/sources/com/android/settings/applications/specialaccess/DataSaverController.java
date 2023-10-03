package com.android.settings.applications.specialaccess;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class DataSaverController extends BasePreferenceController {
    public DataSaverController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_data_saver) ? 1 : 3;
    }
}
