package com.android.settings.notification;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class ZenCustomRuleSettingsBase extends ZenModeSettingsBase {
    List<AbstractPreferenceController> mControllers = new ArrayList();
    String mId;
    AutomaticZenRule mRule;

    public int getHelpResource() {
        return C1715R.string.help_uri_interruptions;
    }

    /* access modifiers changed from: package-private */
    public abstract String getPreferenceCategoryKey();

    ZenCustomRuleSettingsBase() {
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments == null || !arguments.containsKey("RULE_ID")) {
            Log.d("ZenCustomRuleSettings", "Rule id required to set custom dnd rule config settings");
            finish();
            return;
        }
        this.mId = arguments.getString("RULE_ID");
        this.mRule = this.mBackend.getAutomaticZenRule(this.mId);
    }

    public void onZenModeConfigChanged() {
        super.onZenModeConfigChanged();
        updatePreferences();
    }

    public void updatePreferences() {
        Preference findPreference;
        this.mRule = this.mBackend.getAutomaticZenRule(this.mId);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        String preferenceCategoryKey = getPreferenceCategoryKey();
        if (!(preferenceCategoryKey == null || (findPreference = preferenceScreen.findPreference(preferenceCategoryKey)) == null)) {
            findPreference.setTitle((CharSequence) this.mContext.getResources().getString(C1715R.string.zen_mode_custom_behavior_category_title, new Object[]{this.mRule.getName()}));
        }
        Iterator<AbstractPreferenceController> it = this.mControllers.iterator();
        while (it.hasNext()) {
            AbstractZenCustomRulePreferenceController abstractZenCustomRulePreferenceController = (AbstractZenCustomRulePreferenceController) it.next();
            abstractZenCustomRulePreferenceController.onResume(this.mRule, this.mId);
            abstractZenCustomRulePreferenceController.displayPreference(preferenceScreen);
            updatePreference(abstractZenCustomRulePreferenceController);
        }
    }

    public void onResume() {
        super.onResume();
        updatePreferences();
    }

    /* access modifiers changed from: package-private */
    public Bundle createZenRuleBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("RULE_ID", this.mId);
        return bundle;
    }
}
