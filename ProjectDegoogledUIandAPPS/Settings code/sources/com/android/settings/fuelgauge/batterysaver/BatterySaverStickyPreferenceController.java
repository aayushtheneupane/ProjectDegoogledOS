package com.android.settings.fuelgauge.batterysaver;

import android.content.Context;
import android.icu.text.NumberFormat;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.core.TogglePreferenceController;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;

public class BatterySaverStickyPreferenceController extends TogglePreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private Context mContext;

    public int getAvailabilityStatus() {
        return 0;
    }

    public BatterySaverStickyPreferenceController(Context context, String str) {
        super(context, str);
        this.mContext = context;
    }

    public boolean isChecked() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "low_power_sticky_auto_disable_enabled", 1) == 1;
    }

    public boolean setChecked(boolean z) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "low_power_sticky_auto_disable_enabled", z ? 1 : 0);
        return true;
    }

    /* access modifiers changed from: protected */
    public void refreshSummary(Preference preference) {
        super.refreshSummary(preference);
        String format = NumberFormat.getPercentInstance().format(((double) Settings.Global.getInt(this.mContext.getContentResolver(), "low_power_sticky_auto_disable_level", 90)) / 100.0d);
        preference.setSummary((CharSequence) this.mContext.getString(C1715R.string.battery_saver_sticky_description_new, new Object[]{format}));
    }

    public void updateState(Preference preference) {
        boolean z = true;
        SwitchPreference switchPreference = (SwitchPreference) preference;
        if (Settings.Global.getInt(this.mContext.getContentResolver(), "low_power_sticky_auto_disable_enabled", 1) != 1) {
            z = false;
        }
        switchPreference.setChecked(z);
        refreshSummary(preference);
    }
}
