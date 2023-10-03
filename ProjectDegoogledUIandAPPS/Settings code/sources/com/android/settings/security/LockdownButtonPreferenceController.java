package com.android.settings.security;

import android.content.Context;
import android.provider.Settings;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.core.TogglePreferenceController;

public class LockdownButtonPreferenceController extends TogglePreferenceController {
    private final LockPatternUtils mLockPatternUtils;

    public int getAvailabilityStatus() {
        return 4;
    }

    public LockdownButtonPreferenceController(Context context, String str) {
        super(context, str);
        this.mLockPatternUtils = new LockPatternUtils(context);
    }

    public boolean isChecked() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "lockdown_in_power_menu", 0) != 0;
    }

    public boolean setChecked(boolean z) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "lockdown_in_power_menu", z ? 1 : 0);
        return true;
    }
}
