package com.android.settings.core;

import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.widget.SeekBarPreference;

public abstract class SliderPreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener {
    public abstract int getMax();

    public abstract int getMin();

    public int getSliceType() {
        return 2;
    }

    public abstract int getSliderPosition();

    public abstract boolean setSliderPosition(int i);

    public SliderPreferenceController(Context context, String str) {
        super(context, str);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        return setSliderPosition(((Integer) obj).intValue());
    }

    public void updateState(Preference preference) {
        if (preference instanceof SeekBarPreference) {
            ((SeekBarPreference) preference).setProgress(getSliderPosition());
        } else if (preference instanceof androidx.preference.SeekBarPreference) {
            ((androidx.preference.SeekBarPreference) preference).setValue(getSliderPosition());
        }
    }
}
