package com.android.settings.display;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.SliderPreferenceController;
import com.android.settings.widget.SeekBarPreference;

public class AutoBrightnessMinPreferenceController extends SliderPreferenceController {
    final int mScreenBrightnessSettingMaximum = this.mContext.getResources().getInteger(17694912);
    final int mScreenBrightnessSettingMinimum = this.mContext.getResources().getInteger(17694913);

    public AutoBrightnessMinPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return !this.mContext.getResources().getBoolean(17891369) ? 3 : 0;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        SeekBarPreference seekBarPreference = (SeekBarPreference) preferenceScreen.findPreference(getPreferenceKey());
        seekBarPreference.setContinuousUpdates(true);
        seekBarPreference.setMax(getMax());
        seekBarPreference.setMin(getMin());
    }

    public int getSliderPosition() {
        int i = Settings.System.getInt(this.mContext.getContentResolver(), "auto_brightness_min_value", -1);
        return i == -1 ? this.mScreenBrightnessSettingMinimum : i;
    }

    public boolean setSliderPosition(int i) {
        Settings.System.putInt(this.mContext.getContentResolver(), "auto_brightness_min_value", i);
        return true;
    }

    public int getMax() {
        return this.mScreenBrightnessSettingMaximum;
    }

    public int getMin() {
        return this.mScreenBrightnessSettingMinimum;
    }
}
