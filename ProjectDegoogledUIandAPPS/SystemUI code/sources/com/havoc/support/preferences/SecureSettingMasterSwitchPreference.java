package com.havoc.support.preferences;

import android.content.Context;
import android.util.AttributeSet;

public class SecureSettingMasterSwitchPreference extends MasterSwitchPreference {
    public SecureSettingMasterSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new SecureSettingsStore(context.getContentResolver()));
    }
}
