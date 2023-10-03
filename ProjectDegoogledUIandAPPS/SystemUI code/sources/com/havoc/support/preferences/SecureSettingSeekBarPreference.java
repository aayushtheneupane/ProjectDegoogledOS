package com.havoc.support.preferences;

import android.content.Context;
import android.util.AttributeSet;

public class SecureSettingSeekBarPreference extends CustomSeekBarPreference {
    public SecureSettingSeekBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new SecureSettingsStore(context.getContentResolver()));
    }
}
