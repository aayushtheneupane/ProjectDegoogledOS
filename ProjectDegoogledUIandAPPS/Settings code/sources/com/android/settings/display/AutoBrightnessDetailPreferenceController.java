package com.android.settings.display;

import android.content.Context;

public class AutoBrightnessDetailPreferenceController extends AutoBrightnessPreferenceController {
    public boolean isSliceable() {
        return true;
    }

    public AutoBrightnessDetailPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(17891369) ? 0 : 3;
    }
}
