package com.havoc.support.preferences;

import android.content.Context;
import android.util.AttributeSet;

public class GlobalSettingSeekBarPreference extends CustomSeekBarPreference {
    public GlobalSettingSeekBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new GlobalSettingsStore(context.getContentResolver()));
    }
}
