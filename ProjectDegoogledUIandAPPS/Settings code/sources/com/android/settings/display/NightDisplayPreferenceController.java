package com.android.settings.display;

import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;

public class NightDisplayPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    public String getPreferenceKey() {
        return "night_display";
    }

    public NightDisplayPreferenceController(Context context) {
        super(context);
    }

    public static boolean isSuggestionComplete(Context context) {
        if (context.getResources().getBoolean(C1715R.bool.config_night_light_suggestion_enabled) && ((ColorDisplayManager) context.getSystemService(ColorDisplayManager.class)).getNightDisplayAutoMode() == 0) {
            return false;
        }
        return true;
    }

    public boolean isAvailable() {
        return ColorDisplayManager.isNightDisplayAvailable(this.mContext);
    }
}
