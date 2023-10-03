package com.android.settings.notification;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.service.notification.ZenPolicy;
import android.util.Pair;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.notification.ZenCustomRadioButtonPreference;
import com.android.settingslib.core.lifecycle.Lifecycle;

public class ZenRuleDefaultPolicyPreferenceController extends AbstractZenCustomRulePreferenceController implements PreferenceControllerMixin {
    private ZenCustomRadioButtonPreference mPreference;

    public /* bridge */ /* synthetic */ boolean isAvailable() {
        return super.isAvailable();
    }

    public /* bridge */ /* synthetic */ void onResume(AutomaticZenRule automaticZenRule, String str) {
        super.onResume(automaticZenRule, str);
    }

    public ZenRuleDefaultPolicyPreferenceController(Context context, Lifecycle lifecycle, String str) {
        super(context, str, lifecycle);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (ZenCustomRadioButtonPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference.setOnRadioButtonClickListener(new ZenCustomRadioButtonPreference.OnRadioButtonClickListener() {
            public final void onRadioButtonClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
                ZenRuleDefaultPolicyPreferenceController.this.mo11231xaf944363(zenCustomRadioButtonPreference);
            }
        });
    }

    /* renamed from: lambda$displayPreference$0$ZenRuleDefaultPolicyPreferenceController */
    public /* synthetic */ void mo11231xaf944363(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference) {
        this.mRule.setZenPolicy((ZenPolicy) null);
        this.mBackend.updateZenRule(this.mId, this.mRule);
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        if (this.mId != null && this.mRule != null) {
            boolean z = true;
            this.mMetricsFeatureProvider.action(this.mContext, 1606, (Pair<Integer, Object>[]) new Pair[]{Pair.create(1603, this.mId)});
            ZenCustomRadioButtonPreference zenCustomRadioButtonPreference = this.mPreference;
            if (this.mRule.getZenPolicy() != null) {
                z = false;
            }
            zenCustomRadioButtonPreference.setChecked(z);
        }
    }
}
