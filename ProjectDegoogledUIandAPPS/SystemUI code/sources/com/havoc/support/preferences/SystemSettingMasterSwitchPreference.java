package com.havoc.support.preferences;

import android.content.Context;
import android.util.AttributeSet;

public class SystemSettingMasterSwitchPreference extends MasterSwitchPreference {
    public SystemSettingMasterSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }
}
