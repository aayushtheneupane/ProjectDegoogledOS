package com.havoc.config.center.fragments;

import android.content.ContentResolver;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.CustomSeekBarPreference;

public class ButtonBrightness extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private CustomSeekBarPreference mButtonTimoutBar;
    private CustomSeekBarPreference mManualButtonBrightness;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.button_brightness);
        getPreferenceScreen();
        ContentResolver contentResolver = getActivity().getContentResolver();
        this.mManualButtonBrightness = (CustomSeekBarPreference) findPreference("button_manual_brightness_new");
        int i = Settings.System.getInt(contentResolver, "custom_button_brightness", getResources().getInteger(17694760));
        this.mManualButtonBrightness.setMax(((PowerManager) getActivity().getSystemService("power")).getMaximumScreenBrightnessSetting());
        this.mManualButtonBrightness.setValue(i);
        this.mManualButtonBrightness.setOnPreferenceChangeListener(this);
        this.mButtonTimoutBar = (CustomSeekBarPreference) findPreference("button_timeout");
        this.mButtonTimoutBar.setValue(Settings.System.getInt(contentResolver, "button_backlight_timeout", 0));
        this.mButtonTimoutBar.setOnPreferenceChangeListener(this);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mButtonTimoutBar) {
            Settings.System.putInt(getContentResolver(), "button_backlight_timeout", ((Integer) obj).intValue());
            return true;
        } else if (preference != this.mManualButtonBrightness) {
            return false;
        } else {
            Settings.System.putInt(getContentResolver(), "custom_button_brightness", ((Integer) obj).intValue());
            return true;
        }
    }
}
