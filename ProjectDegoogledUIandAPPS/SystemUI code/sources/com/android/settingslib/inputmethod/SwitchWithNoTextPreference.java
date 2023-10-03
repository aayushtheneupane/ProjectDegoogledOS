package com.android.settingslib.inputmethod;

import android.content.Context;
import com.havoc.support.preferences.SwitchPreference;

public class SwitchWithNoTextPreference extends SwitchPreference {
    public SwitchWithNoTextPreference(Context context) {
        super(context);
        setSwitchTextOn("");
        setSwitchTextOff("");
    }
}
