package com.android.systemui.tuner;

import android.os.Bundle;
import androidx.preference.PreferenceFragment;
import com.android.systemui.C1786R$xml;

public class OtherPrefs extends PreferenceFragment {
    public void onCreatePreferences(Bundle bundle, String str) {
        addPreferencesFromResource(C1786R$xml.other_settings);
    }
}
