package com.android.settings.display;

import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.SliderPreferenceController;
import com.android.settings.widget.SeekBarPreference;

public class NightDisplayIntensityPreferenceController extends SliderPreferenceController {
    private ColorDisplayManager mColorDisplayManager;

    public int getMin() {
        return 0;
    }

    public NightDisplayIntensityPreferenceController(Context context, String str) {
        super(context, str);
        this.mColorDisplayManager = (ColorDisplayManager) context.getSystemService(ColorDisplayManager.class);
    }

    public int getAvailabilityStatus() {
        if (!ColorDisplayManager.isNightDisplayAvailable(this.mContext)) {
            return 3;
        }
        return !this.mColorDisplayManager.isNightDisplayActivated() ? 5 : 0;
    }

    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), "night_display_temperature");
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        SeekBarPreference seekBarPreference = (SeekBarPreference) preferenceScreen.findPreference(getPreferenceKey());
        seekBarPreference.setContinuousUpdates(true);
        seekBarPreference.setMax(getMax());
        seekBarPreference.setMin(getMin());
    }

    public final void updateState(Preference preference) {
        super.updateState(preference);
        preference.setEnabled(this.mColorDisplayManager.isNightDisplayActivated());
    }

    public int getSliderPosition() {
        return convertTemperature(this.mColorDisplayManager.getNightDisplayColorTemperature());
    }

    public boolean setSliderPosition(int i) {
        return this.mColorDisplayManager.setNightDisplayColorTemperature(convertTemperature(i));
    }

    public int getMax() {
        return convertTemperature(ColorDisplayManager.getMinimumColorTemperature(this.mContext));
    }

    private int convertTemperature(int i) {
        return ColorDisplayManager.getMaximumColorTemperature(this.mContext) - i;
    }
}
