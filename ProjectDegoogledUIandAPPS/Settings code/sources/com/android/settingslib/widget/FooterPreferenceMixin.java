package com.android.settingslib.widget;

import androidx.preference.PreferenceScreen;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.SetPreferenceScreen;

@Deprecated
public class FooterPreferenceMixin implements LifecycleObserver, SetPreferenceScreen {
    private FooterPreference mFooterPreference;

    public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
        FooterPreference footerPreference = this.mFooterPreference;
        if (footerPreference != null) {
            preferenceScreen.addPreference(footerPreference);
        }
    }
}
