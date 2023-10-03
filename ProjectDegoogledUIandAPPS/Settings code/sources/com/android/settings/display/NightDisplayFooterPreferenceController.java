package com.android.settings.display;

import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class NightDisplayFooterPreferenceController extends BasePreferenceController {
    public NightDisplayFooterPreferenceController(Context context) {
        super(context, "footer_preference");
    }

    public int getAvailabilityStatus() {
        return ColorDisplayManager.isNightDisplayAvailable(this.mContext) ? 0 : 3;
    }

    public void updateState(Preference preference) {
        String string = this.mContext.getResources().getString(C1715R.string.night_display_text);
        preference.setTitle((CharSequence) string + this.mContext.getResources().getString(C1715R.string.night_display_location_text));
    }
}
