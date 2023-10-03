package com.android.settings.notification;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.widget.Switch;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.widget.SwitchBar;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;

public class ZenAutomaticRuleSwitchPreferenceController extends AbstractZenModeAutomaticRulePreferenceController implements SwitchBar.OnSwitchChangeListener {
    private String mId;
    private AutomaticZenRule mRule;
    private SwitchBar mSwitchBar;

    public String getPreferenceKey() {
        return "zen_automatic_rule_switch";
    }

    public ZenAutomaticRuleSwitchPreferenceController(Context context, Fragment fragment, Lifecycle lifecycle) {
        super(context, "zen_automatic_rule_switch", fragment, lifecycle);
    }

    public boolean isAvailable() {
        return (this.mRule == null || this.mId == null) ? false : true;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        LayoutPreference layoutPreference = (LayoutPreference) preferenceScreen.findPreference("zen_automatic_rule_switch");
        this.mSwitchBar = (SwitchBar) layoutPreference.findViewById(C1715R.C1718id.switch_bar);
        SwitchBar switchBar = this.mSwitchBar;
        if (switchBar != null) {
            switchBar.setSwitchBarText(C1715R.string.zen_mode_use_automatic_rule, C1715R.string.zen_mode_use_automatic_rule);
            try {
                layoutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    public final boolean onPreferenceClick(Preference preference) {
                        return ZenAutomaticRuleSwitchPreferenceController.this.mo11120x5eca0cc7(preference);
                    }
                });
                this.mSwitchBar.addOnSwitchChangeListener(this);
            } catch (IllegalStateException unused) {
            }
            this.mSwitchBar.show();
        }
    }

    /* renamed from: lambda$displayPreference$0$ZenAutomaticRuleSwitchPreferenceController */
    public /* synthetic */ boolean mo11120x5eca0cc7(Preference preference) {
        AutomaticZenRule automaticZenRule = this.mRule;
        automaticZenRule.setEnabled(!automaticZenRule.isEnabled());
        this.mBackend.updateZenRule(this.mId, this.mRule);
        return true;
    }

    public void onResume(AutomaticZenRule automaticZenRule, String str) {
        this.mRule = automaticZenRule;
        this.mId = str;
    }

    public void updateState(Preference preference) {
        AutomaticZenRule automaticZenRule = this.mRule;
        if (automaticZenRule != null) {
            this.mSwitchBar.setChecked(automaticZenRule.isEnabled());
        }
    }

    public void onSwitchChanged(Switch switchR, boolean z) {
        if (z != this.mRule.isEnabled()) {
            this.mRule.setEnabled(z);
            this.mBackend.updateZenRule(this.mId, this.mRule);
        }
    }
}
