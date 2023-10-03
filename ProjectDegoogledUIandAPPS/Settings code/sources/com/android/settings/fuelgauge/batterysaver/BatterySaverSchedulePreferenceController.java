package com.android.settings.fuelgauge.batterysaver;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.Utils;
import com.android.settingslib.fuelgauge.BatterySaverUtils;
import com.havoc.config.center.C1715R;

public class BatterySaverSchedulePreferenceController extends BasePreferenceController {
    public static final String KEY_BATTERY_SAVER_SCHEDULE = "battery_saver_schedule";
    Preference mBatterySaverSchedulePreference;

    public int getAvailabilityStatus() {
        return 0;
    }

    public String getPreferenceKey() {
        return KEY_BATTERY_SAVER_SCHEDULE;
    }

    public BatterySaverSchedulePreferenceController(Context context) {
        super(context, KEY_BATTERY_SAVER_SCHEDULE);
        BatterySaverUtils.revertScheduleToNoneIfNeeded(context);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mBatterySaverSchedulePreference = preferenceScreen.findPreference(KEY_BATTERY_SAVER_SCHEDULE);
    }

    public CharSequence getSummary() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        if (Settings.Global.getInt(contentResolver, "automatic_power_save_mode", 0) != 0) {
            return this.mContext.getText(C1715R.string.battery_saver_auto_routine);
        }
        int i = Settings.Global.getInt(contentResolver, "low_power_trigger_level", 0);
        if (i <= 0) {
            return this.mContext.getText(C1715R.string.battery_saver_auto_no_schedule);
        }
        return this.mContext.getString(C1715R.string.battery_saver_auto_percentage_summary, new Object[]{Utils.formatPercentage(i)});
    }
}
