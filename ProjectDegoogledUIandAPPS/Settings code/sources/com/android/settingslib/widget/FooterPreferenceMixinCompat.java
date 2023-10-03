package com.android.settingslib.widget;

import android.content.Context;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.SetPreferenceScreen;

public class FooterPreferenceMixinCompat implements LifecycleObserver, SetPreferenceScreen {
    private FooterPreference mFooterPreference;
    private final PreferenceFragmentCompat mFragment;

    public FooterPreferenceMixinCompat(PreferenceFragmentCompat preferenceFragmentCompat, Lifecycle lifecycle) {
        this.mFragment = preferenceFragmentCompat;
        lifecycle.addObserver(this);
    }

    public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
        FooterPreference footerPreference = this.mFooterPreference;
        if (footerPreference != null) {
            preferenceScreen.addPreference(footerPreference);
        }
    }

    public FooterPreference createFooterPreference() {
        PreferenceScreen preferenceScreen = this.mFragment.getPreferenceScreen();
        FooterPreference footerPreference = this.mFooterPreference;
        if (!(footerPreference == null || preferenceScreen == null)) {
            preferenceScreen.removePreference(footerPreference);
        }
        this.mFooterPreference = new FooterPreference(getPrefContext());
        if (preferenceScreen != null) {
            preferenceScreen.addPreference(this.mFooterPreference);
        }
        return this.mFooterPreference;
    }

    private Context getPrefContext() {
        return this.mFragment.getPreferenceManager().getContext();
    }

    public boolean hasFooter() {
        return this.mFooterPreference != null;
    }
}
