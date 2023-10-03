package com.android.settings.applications.specialaccess;

import android.content.Context;
import com.android.settings.Utils;
import com.android.settings.core.BasePreferenceController;

public class SystemAlertWindowPreferenceController extends BasePreferenceController {
    public SystemAlertWindowPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return Utils.isSystemAlertWindowEnabled(this.mContext) ? 1 : 3;
    }
}
