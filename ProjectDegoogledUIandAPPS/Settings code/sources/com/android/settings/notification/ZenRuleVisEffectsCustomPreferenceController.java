package com.android.settings.notification;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.util.Pair;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.notification.ZenCustomRadioButtonPreference;
import com.android.settingslib.core.lifecycle.Lifecycle;

public class ZenRuleVisEffectsCustomPreferenceController extends AbstractZenCustomRulePreferenceController implements PreferenceControllerMixin {
    private ZenCustomRadioButtonPreference mPreference;

    public /* bridge */ /* synthetic */ boolean isAvailable() {
        return super.isAvailable();
    }

    public /* bridge */ /* synthetic */ void onResume(AutomaticZenRule automaticZenRule, String str) {
        super.onResume(automaticZenRule, str);
    }

    public ZenRuleVisEffectsCustomPreferenceController(Context context, Lifecycle lifecycle, String str) {
        super(context, str, lifecycle);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (ZenCustomRadioButtonPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference.setOnGearClickListener(new ZenCustomRadioButtonPreference.OnGearClickListener() {
            public final void onGearClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
                ZenRuleVisEffectsCustomPreferenceController.this.mo11248x1e211691(zenCustomRadioButtonPreference);
            }
        });
        this.mPreference.setOnRadioButtonClickListener(new ZenCustomRadioButtonPreference.OnRadioButtonClickListener() {
            public final void onRadioButtonClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
                ZenRuleVisEffectsCustomPreferenceController.this.mo11249xb8c1d912(zenCustomRadioButtonPreference);
            }
        });
    }

    /* renamed from: lambda$displayPreference$0$ZenRuleVisEffectsCustomPreferenceController */
    public /* synthetic */ void mo11248x1e211691(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
        launchCustomSettings();
    }

    /* renamed from: lambda$displayPreference$1$ZenRuleVisEffectsCustomPreferenceController */
    public /* synthetic */ void mo11249xb8c1d912(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
        launchCustomSettings();
    }

    public void updateState(Preference preference) {
        AutomaticZenRule automaticZenRule;
        super.updateState(preference);
        if (this.mId != null && (automaticZenRule = this.mRule) != null && automaticZenRule.getZenPolicy() != null) {
            this.mPreference.setChecked(!this.mRule.getZenPolicy().shouldHideAllVisualEffects() && !this.mRule.getZenPolicy().shouldShowAllVisualEffects());
        }
    }

    private void launchCustomSettings() {
        this.mMetricsFeatureProvider.action(this.mContext, 1398, (Pair<Integer, Object>[]) new Pair[]{Pair.create(1603, this.mId)});
        new SubSettingLauncher(this.mContext).setDestination(ZenCustomRuleBlockedEffectsSettings.class.getName()).setArguments(createBundle()).setSourceMetricsCategory(1609).launch();
    }
}
