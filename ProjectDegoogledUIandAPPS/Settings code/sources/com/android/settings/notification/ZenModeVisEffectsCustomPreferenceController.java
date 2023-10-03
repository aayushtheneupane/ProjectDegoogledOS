package com.android.settings.notification;

import android.app.NotificationManager;
import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.notification.ZenCustomRadioButtonPreference;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;

public class ZenModeVisEffectsCustomPreferenceController extends AbstractZenModePreferenceController {
    private ZenCustomRadioButtonPreference mPreference;

    public boolean isAvailable() {
        return true;
    }

    public ZenModeVisEffectsCustomPreferenceController(Context context, Lifecycle lifecycle, String str) {
        super(context, str, lifecycle);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (ZenCustomRadioButtonPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference.setOnGearClickListener(new ZenCustomRadioButtonPreference.OnGearClickListener() {
            public final void onGearClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
                ZenModeVisEffectsCustomPreferenceController.this.mo11214xa9f0b1b8(zenCustomRadioButtonPreference);
            }
        });
        this.mPreference.setOnRadioButtonClickListener(new ZenCustomRadioButtonPreference.OnRadioButtonClickListener() {
            public final void onRadioButtonClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
                ZenModeVisEffectsCustomPreferenceController.this.mo11215x44917439(zenCustomRadioButtonPreference);
            }
        });
    }

    /* renamed from: lambda$displayPreference$0$ZenModeVisEffectsCustomPreferenceController */
    public /* synthetic */ void mo11214xa9f0b1b8(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
        launchCustomSettings();
    }

    /* renamed from: lambda$displayPreference$1$ZenModeVisEffectsCustomPreferenceController */
    public /* synthetic */ void mo11215x44917439(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
        launchCustomSettings();
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        this.mPreference.setChecked(areCustomOptionsSelected());
    }

    /* access modifiers changed from: protected */
    public boolean areCustomOptionsSelected() {
        return !NotificationManager.Policy.areAllVisualEffectsSuppressed(this.mBackend.mPolicy.suppressedVisualEffects) && !(this.mBackend.mPolicy.suppressedVisualEffects == 0);
    }

    /* access modifiers changed from: protected */
    public void select() {
        this.mMetricsFeatureProvider.action(this.mContext, 1399, true);
    }

    private void launchCustomSettings() {
        select();
        new SubSettingLauncher(this.mContext).setDestination(ZenModeBlockedEffectsSettings.class.getName()).setTitleRes(C1715R.string.zen_mode_what_to_block_title).setSourceMetricsCategory(1400).launch();
    }
}
