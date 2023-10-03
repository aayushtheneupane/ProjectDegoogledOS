package com.android.settings.accessibility;

import android.content.Context;
import android.provider.Settings;
import com.android.settings.core.TogglePreferenceController;

public class ColorInversionPreferenceController extends TogglePreferenceController {
    static final int OFF = 0;

    /* renamed from: ON */
    static final int f20ON = 1;

    public int getAvailabilityStatus() {
        return 0;
    }

    public ColorInversionPreferenceController(Context context, String str) {
        super(context, str);
    }

    public boolean isChecked() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "accessibility_display_inversion_enabled", 0) == 1;
    }

    public boolean setChecked(boolean z) {
        return Settings.Secure.putInt(this.mContext.getContentResolver(), "accessibility_display_inversion_enabled", z ? 1 : 0);
    }
}
