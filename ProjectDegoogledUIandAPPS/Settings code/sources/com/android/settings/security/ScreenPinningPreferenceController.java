package com.android.settings.security;

import android.content.Context;
import android.provider.Settings;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class ScreenPinningPreferenceController extends BasePreferenceController {
    private static final String KEY_SCREEN_PINNING = "screen_pinning_settings";

    public ScreenPinningPreferenceController(Context context) {
        super(context, KEY_SCREEN_PINNING);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_screen_pinning_settings) ? 0 : 3;
    }

    public CharSequence getSummary() {
        if (Settings.System.getInt(this.mContext.getContentResolver(), "lock_to_app_enabled", 0) != 0) {
            return this.mContext.getText(C1715R.string.switch_on_text);
        }
        return this.mContext.getText(C1715R.string.switch_off_text);
    }
}
