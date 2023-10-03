package com.android.settings.fuelgauge;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.overlay.FeatureFactory;
import com.havoc.support.preferences.SwitchPreference;

public class AutoRestrictionPreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener {
    private static final String KEY_SMART_BATTERY = "auto_restriction";
    private static final int OFF = 0;

    /* renamed from: ON */
    private static final int f29ON = 1;
    private PowerUsageFeatureProvider mPowerUsageFeatureProvider;

    public AutoRestrictionPreferenceController(Context context) {
        super(context, KEY_SMART_BATTERY);
        this.mPowerUsageFeatureProvider = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context);
    }

    public int getAvailabilityStatus() {
        return this.mPowerUsageFeatureProvider.isSmartBatterySupported() ? 3 : 0;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        boolean z = true;
        if (Settings.Global.getInt(this.mContext.getContentResolver(), "app_auto_restriction_enabled", 1) != 1) {
            z = false;
        }
        ((SwitchPreference) preference).setChecked(z);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "app_auto_restriction_enabled", ((Boolean) obj).booleanValue() ? 1 : 0);
        return true;
    }
}
