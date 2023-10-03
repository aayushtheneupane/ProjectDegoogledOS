package com.android.settings.wifi.p2p;

import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;

public abstract class P2pCategoryPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    protected PreferenceGroup mCategory;

    public boolean isAvailable() {
        return true;
    }

    public P2pCategoryPreferenceController(Context context) {
        super(context);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mCategory = (PreferenceGroup) preferenceScreen.findPreference(getPreferenceKey());
    }

    public void removeAllChildren() {
        PreferenceGroup preferenceGroup = this.mCategory;
        if (preferenceGroup != null) {
            preferenceGroup.removeAll();
            this.mCategory.setVisible(false);
        }
    }

    public void addChild(Preference preference) {
        PreferenceGroup preferenceGroup = this.mCategory;
        if (preferenceGroup != null) {
            preferenceGroup.addPreference(preference);
            this.mCategory.setVisible(true);
        }
    }

    public void setEnabled(boolean z) {
        PreferenceGroup preferenceGroup = this.mCategory;
        if (preferenceGroup != null) {
            preferenceGroup.setEnabled(z);
        }
    }
}
