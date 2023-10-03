package com.android.settings.display;

import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;
import java.time.LocalTime;

public class NightDisplayCustomEndTimePreferenceController extends BasePreferenceController {
    private ColorDisplayManager mColorDisplayManager;
    private NightDisplayTimeFormatter mTimeFormatter;

    public NightDisplayCustomEndTimePreferenceController(Context context, String str) {
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
            preference.setSummary((CharSequence) this.mTimeFormatter.getFormattedTimeString(this.mColorDisplayManager.getNightDisplayCustomEndTime()));
        } else if (nightDisplayAutoMode == 2) {
            LocalTime nightDisplayAutoEndTime = this.mColorDisplayManager.getNightDisplayAutoEndTime();
            if (nightDisplayAutoEndTime != null) {
                preference.setSummary((CharSequence) this.mTimeFormatter.getFormattedTimeString(nightDisplayAutoEndTime));
                preference.setSelectable(false);
                return;
            }
            preference.setSummary((int) C1715R.string.unknown_night_light_time);
        }
    }
}
