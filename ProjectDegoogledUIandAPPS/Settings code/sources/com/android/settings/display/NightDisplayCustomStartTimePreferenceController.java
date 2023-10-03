package com.android.settings.display;

import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;
import java.time.LocalTime;

public class NightDisplayCustomStartTimePreferenceController extends BasePreferenceController {
    private ColorDisplayManager mColorDisplayManager;
    private NightDisplayTimeFormatter mTimeFormatter;

    public NightDisplayCustomStartTimePreferenceController(Context context, String str) {
        super(context, str);
        this.mColorDisplayManager = (ColorDisplayManager) context.getSystemService(ColorDisplayManager.class);
        this.mTimeFormatter = new NightDisplayTimeFormatter(context);
    }

    public int getAvailabilityStatus() {
        return ColorDisplayManager.isNightDisplayAvailable(this.mContext) ? 0 : 3;
    }

    public final void updateState(Preference preference) {
        int nightDisplayAutoMode = this.mColorDisplayManager.getNightDisplayAutoMode();
        preference.setVisible(nightDisplayAutoMode == 1 || nightDisplayAutoMode == 2);
        preference.setSelectable(true);
        if (nightDisplayAutoMode == 1) {
            preference.setSummary((CharSequence) this.mTimeFormatter.getFormattedTimeString(this.mColorDisplayManager.getNightDisplayCustomStartTime()));
        } else if (nightDisplayAutoMode == 2) {
            LocalTime nightDisplayAutoStartTime = this.mColorDisplayManager.getNightDisplayAutoStartTime();
            if (nightDisplayAutoStartTime != null) {
                preference.setSummary((CharSequence) this.mTimeFormatter.getFormattedTimeString(nightDisplayAutoStartTime));
                preference.setSelectable(false);
                return;
            }
            preference.setSummary((int) C1715R.string.unknown_night_light_time);
        }
    }
}
