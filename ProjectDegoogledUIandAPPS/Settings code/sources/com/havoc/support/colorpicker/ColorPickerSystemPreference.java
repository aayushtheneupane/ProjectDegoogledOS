package com.havoc.support.colorpicker;

import android.content.Context;
import android.util.AttributeSet;
import com.havoc.support.preferences.SystemSettingsStore;

public class ColorPickerSystemPreference extends ColorPickerPreference {
    public ColorPickerSystemPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }

    public ColorPickerSystemPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }
}
