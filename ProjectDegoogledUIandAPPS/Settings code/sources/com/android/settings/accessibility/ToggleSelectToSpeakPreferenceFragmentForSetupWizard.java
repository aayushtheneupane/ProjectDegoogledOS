package com.android.settings.accessibility;

import android.os.Bundle;

public class ToggleSelectToSpeakPreferenceFragmentForSetupWizard extends ToggleAccessibilityServicePreferenceFragment {
    private boolean mToggleSwitchWasInitiallyChecked;

    public int getMetricsCategory() {
        return 371;
    }

    /* access modifiers changed from: protected */
    public void onProcessArguments(Bundle bundle) {
        super.onProcessArguments(bundle);
        this.mToggleSwitchWasInitiallyChecked = this.mToggleSwitch.isChecked();
    }

    public void onStop() {
        if (this.mToggleSwitch.isChecked() != this.mToggleSwitchWasInitiallyChecked) {
            this.mMetricsFeatureProvider.action(getContext(), 817, this.mToggleSwitch.isChecked());
        }
        super.onStop();
    }
}
