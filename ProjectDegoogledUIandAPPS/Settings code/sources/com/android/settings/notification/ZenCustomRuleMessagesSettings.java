package com.android.settings.notification;

import android.content.Context;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class ZenCustomRuleMessagesSettings extends ZenCustomRuleSettingsBase {
    public int getMetricsCategory() {
        return 1610;
    }

    /* access modifiers changed from: package-private */
    public String getPreferenceCategoryKey() {
        return "zen_mode_settings_category_messages";
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.zen_mode_messages_settings;
    }

    public /* bridge */ /* synthetic */ int getHelpResource() {
        return super.getHelpResource();
    }

    public /* bridge */ /* synthetic */ void onAttach(Context context) {
        super.onAttach(context);
    }

    public /* bridge */ /* synthetic */ void onResume() {
        super.onResume();
    }

    public /* bridge */ /* synthetic */ void onZenModeConfigChanged() {
        super.onZenModeConfigChanged();
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        this.mControllers = new ArrayList();
        this.mControllers.add(new ZenRuleMessagesPreferenceController(context, "zen_mode_messages", getSettingsLifecycle()));
        this.mControllers.add(new ZenRuleStarredContactsPreferenceController(context, getSettingsLifecycle(), 2, "zen_mode_starred_contacts_messages"));
        return this.mControllers;
    }

    public void updatePreferences() {
        super.updatePreferences();
        getPreferenceScreen().findPreference("footer_preference").setTitle((CharSequence) this.mContext.getResources().getString(C1715R.string.zen_mode_custom_messages_footer, new Object[]{this.mRule.getName()}));
    }
}
