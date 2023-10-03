package com.havoc.support.preferences;

import android.content.Context;
import android.util.AttributeSet;

public class SecureSettingSeekBarPreference extends CustomSeekBarPreference {
    public SecureSettingSeekBarPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setPreferenceDataStore(new SecureSettingsStore(context.getContentResolver()));
    }

    public SecureSettingSeekBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new SecureSettingsStore(context.getContentResolver()));
    }

    public SecureSettingSeekBarPreference(Context context) {
        super(context, (AttributeSet) null);
        setPreferenceDataStore(new SecureSettingsStore(context.getContentResolver()));
    }
}
