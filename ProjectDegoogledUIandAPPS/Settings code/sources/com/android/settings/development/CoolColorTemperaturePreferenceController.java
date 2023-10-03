package com.android.settings.development;

import android.content.Context;
import android.os.SystemProperties;
import android.widget.Toast;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import com.android.settingslib.development.SystemPropPoker;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;

public class CoolColorTemperaturePreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    static final String COLOR_TEMPERATURE_PROPERTY = "persist.sys.debug.color_temp";

    public String getPreferenceKey() {
        return "color_temperature";
    }

    public CoolColorTemperaturePreferenceController(Context context) {
        super(context);
    }

    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_enableColorTemperature);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        SystemProperties.set(COLOR_TEMPERATURE_PROPERTY, Boolean.toString(((Boolean) obj).booleanValue()));
        SystemPropPoker.getInstance().poke();
        displayColorTemperatureToast();
        return true;
    }

    public void updateState(Preference preference) {
        ((SwitchPreference) this.mPreference).setChecked(SystemProperties.getBoolean(COLOR_TEMPERATURE_PROPERTY, false));
    }

    /* access modifiers changed from: protected */
    public void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        SystemProperties.set(COLOR_TEMPERATURE_PROPERTY, Boolean.toString(false));
        ((SwitchPreference) this.mPreference).setChecked(false);
    }

    /* access modifiers changed from: package-private */
    public void displayColorTemperatureToast() {
        Toast.makeText(this.mContext, C1715R.string.color_temperature_toast, 1).show();
    }
}
