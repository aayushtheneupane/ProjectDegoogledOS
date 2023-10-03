package com.havoc.support.preferences;

import android.content.Context;
import android.util.AttributeSet;

public class SystemSettingColorBlendPreference extends ColorBlendPreference {
    public SystemSettingColorBlendPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }
}
