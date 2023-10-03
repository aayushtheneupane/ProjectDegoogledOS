package com.android.settings.notification;

import android.app.NotificationManager;
import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.notification.ZenCustomRadioButtonPreference;
import com.android.settingslib.core.lifecycle.Lifecycle;

public class ZenModeVisEffectsAllPreferenceController extends AbstractZenModePreferenceController implements ZenCustomRadioButtonPreference.OnRadioButtonClickListener {
    private ZenCustomRadioButtonPreference mPreference;

    public boolean isAvailable() {
        return true;
    }

    public ZenModeVisEffectsAllPreferenceController(Context context, Lifecycle lifecycle, String str) {
        super(context, str, lifecycle);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (ZenCustomRadioButtonPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference.setOnRadioButtonClickListener(this);
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        this.mPreference.setChecked(NotificationManager.Policy.areAllVisualEffectsSuppressed(this.mBackend.mPolicy.suppressedVisualEffects));
    }

    public void onRadioButtonClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
        this.mMetricsFeatureProvider.action(this.mContext, 1397, true);
        this.mBackend.saveVisualEffectsPolicy(511, true);
    }
}
