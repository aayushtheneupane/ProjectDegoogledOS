package com.havoc.support.colorpicker;

import android.content.Context;
import android.util.AttributeSet;
import com.havoc.support.preferences.SecureSettingsStore;

public class ColorPickerSecurePreference extends ColorPickerPreference {
    public ColorPickerSecurePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new SecureSettingsStore(context.getContentResolver()));
    }
}
