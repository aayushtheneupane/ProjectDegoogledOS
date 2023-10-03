package com.android.settings.widget;

import androidx.preference.Preference;
import com.android.settings.widget.SwitchWidgetController;
import com.android.settingslib.RestrictedLockUtils;

public class MasterSwitchController extends SwitchWidgetController implements Preference.OnPreferenceChangeListener {
    private final MasterSwitchPreference mPreference;

    public void updateTitle(boolean z) {
    }

    public MasterSwitchController(MasterSwitchPreference masterSwitchPreference) {
        this.mPreference = masterSwitchPreference;
    }

    public void startListening() {
        this.mPreference.setOnPreferenceChangeListener(this);
    }

    public void stopListening() {
        this.mPreference.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener) null);
    }

    public void setChecked(boolean z) {
        this.mPreference.setChecked(z);
    }

    public boolean isChecked() {
        return this.mPreference.isChecked();
    }

    public void setEnabled(boolean z) {
        this.mPreference.setSwitchEnabled(z);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        SwitchWidgetController.OnSwitchChangeListener onSwitchChangeListener = this.mListener;
        if (onSwitchChangeListener != null) {
            return onSwitchChangeListener.onSwitchToggled(((Boolean) obj).booleanValue());
        }
        return false;
    }

    public void setDisabledByAdmin(RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        this.mPreference.setDisabledByAdmin(enforcedAdmin);
    }
}
