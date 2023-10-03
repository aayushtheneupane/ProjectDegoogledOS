package com.android.settings.notification;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.service.notification.ZenPolicy;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.notification.ZenCustomRadioButtonPreference;
import com.android.settingslib.core.lifecycle.Lifecycle;

public class ZenRuleCustomPolicyPreferenceController extends AbstractZenCustomRulePreferenceController {
    private ZenCustomRadioButtonPreference mPreference;

    public /* bridge */ /* synthetic */ boolean isAvailable() {
        return super.isAvailable();
    }

    public /* bridge */ /* synthetic */ void onResume(AutomaticZenRule automaticZenRule, String str) {
        super.onResume(automaticZenRule, str);
    }

    public ZenRuleCustomPolicyPreferenceController(Context context, Lifecycle lifecycle, String str) {
        super(context, str, lifecycle);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (ZenCustomRadioButtonPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference.setOnGearClickListener(new ZenCustomRadioButtonPreference.OnGearClickListener() {
            public final void onGearClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
                ZenRuleCustomPolicyPreferenceController.this.mo11229xc0759b41(zenCustomRadioButtonPreference);
            }
        });
        this.mPreference.setOnRadioButtonClickListener(new ZenCustomRadioButtonPreference.OnRadioButtonClickListener() {
            public final void onRadioButtonClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
                ZenRuleCustomPolicyPreferenceController.this.mo11230x4d15c642(zenCustomRadioButtonPreference);
            }
        });
    }

    /* renamed from: lambda$displayPreference$0$ZenRuleCustomPolicyPreferenceController */
    public /* synthetic */ void mo11229xc0759b41(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
        setCustomPolicy();
        launchCustomSettings();
    }

    /* renamed from: lambda$displayPreference$1$ZenRuleCustomPolicyPreferenceController */
    public /* synthetic */ void mo11230x4d15c642(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
        setCustomPolicy();
        launchCustomSettings();
    }

    public void updateState(Preference preference) {
        AutomaticZenRule automaticZenRule;
        super.updateState(preference);
        if (this.mId != null && (automaticZenRule = this.mRule) != null) {
            this.mPreference.setChecked(automaticZenRule.getZenPolicy() != null);
        }
    }

    private void setCustomPolicy() {
        if (this.mRule.getZenPolicy() == null) {
            this.mRule.setZenPolicy(this.mBackend.setDefaultZenPolicy(new ZenPolicy()));
            this.mBackend.updateZenRule(this.mId, this.mRule);
        }
    }

    private void launchCustomSettings() {
        new SubSettingLauncher(this.mContext).setDestination(ZenCustomRuleConfigSettings.class.getName()).setArguments(createBundle()).setSourceMetricsCategory(1605).launch();
    }
}
