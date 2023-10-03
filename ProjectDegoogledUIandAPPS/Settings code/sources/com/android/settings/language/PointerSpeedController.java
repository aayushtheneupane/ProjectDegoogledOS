package com.android.settings.language;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class PointerSpeedController extends BasePreferenceController {
    static final String KEY_POINTER_SPEED = "pointer_speed";

    public PointerSpeedController(Context context) {
        super(context, KEY_POINTER_SPEED);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_pointer_speed) ? 0 : 3;
    }
}
