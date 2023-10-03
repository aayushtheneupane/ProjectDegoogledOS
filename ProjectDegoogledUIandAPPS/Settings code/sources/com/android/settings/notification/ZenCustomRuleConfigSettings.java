package com.android.settings.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import androidx.preference.Preference;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.notification.ZenModeSettings;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class ZenCustomRuleConfigSettings extends ZenCustomRuleSettingsBase {
    private Preference mCallsPreference;
    private Preference mMessagesPreference;
    private Preference mNotificationsPreference;
    private ZenModeSettings.SummaryBuilder mSummaryBuilder;

    public int getMetricsCategory() {
        return 1605;
    }

    /* access modifiers changed from: package-private */
    public String getPreferenceCategoryKey() {
        return "zen_custom_rule_configuration_category";
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.zen_mode_custom_rule_configuration;
    }

    public /* bridge */ /* synthetic */ int getHelpResource() {
        return super.getHelpResource();
    }

    public /* bridge */ /* synthetic */ void onAttach(Context context) {
        super.onAttach(context);
    }

    public /* bridge */ /* synthetic */ void updatePreferences() {
        super.updatePreferences();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSummaryBuilder = new ZenModeSettings.SummaryBuilder(this.mContext);
        this.mCallsPreference = getPreferenceScreen().findPreference("zen_rule_calls_settings");
        this.mCallsPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                new SubSettingLauncher(ZenCustomRuleConfigSettings.this.mContext).setDestination(ZenCustomRuleCallsSettings.class.getName()).setArguments(ZenCustomRuleConfigSettings.this.createZenRuleBundle()).setSourceMetricsCategory(1611).launch();
                return true;
            }
        });
        this.mMessagesPreference = getPreferenceScreen().findPreference("zen_rule_messages_settings");
        this.mMessagesPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                new SubSettingLauncher(ZenCustomRuleConfigSettings.this.mContext).setDestination(ZenCustomRuleMessagesSettings.class.getName()).setArguments(ZenCustomRuleConfigSettings.this.createZenRuleBundle()).setSourceMetricsCategory(1610).launch();
                return true;
            }
        });
        this.mNotificationsPreference = getPreferenceScreen().findPreference("zen_rule_notifications");
        this.mNotificationsPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                new SubSettingLauncher(ZenCustomRuleConfigSettings.this.mContext).setDestination(ZenCustomRuleNotificationsSettings.class.getName()).setArguments(ZenCustomRuleConfigSettings.this.createZenRuleBundle()).setSourceMetricsCategory(1608).launch();
                return true;
            }
        });
        updateSummaries();
    }

    public void onZenModeConfigChanged() {
        super.onZenModeConfigChanged();
        updateSummaries();
    }

    private void updateSummaries() {
        NotificationManager.Policy notificationPolicy = this.mBackend.toNotificationPolicy(this.mRule.getZenPolicy());
        this.mCallsPreference.setSummary((CharSequence) this.mSummaryBuilder.getCallsSettingSummary(notificationPolicy));
        this.mMessagesPreference.setSummary((CharSequence) this.mSummaryBuilder.getMessagesSettingSummary(notificationPolicy));
        this.mNotificationsPreference.setSummary((CharSequence) this.mSummaryBuilder.getBlockedEffectsSummary(notificationPolicy));
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        this.mControllers = new ArrayList();
        Context context2 = context;
        this.mControllers.add(new ZenRuleCustomSwitchPreferenceController(context2, getSettingsLifecycle(), "zen_rule_alarms", 5, 1226));
        this.mControllers.add(new ZenRuleCustomSwitchPreferenceController(context2, getSettingsLifecycle(), "zen_rule_media", 6, 1227));
        this.mControllers.add(new ZenRuleCustomSwitchPreferenceController(context2, getSettingsLifecycle(), "zen_rule_system", 7, 1340));
        this.mControllers.add(new ZenRuleCustomSwitchPreferenceController(context2, getSettingsLifecycle(), "zen_rule_reminders", 0, 167));
        this.mControllers.add(new ZenRuleCustomSwitchPreferenceController(context2, getSettingsLifecycle(), "zen_rule_events", 1, 168));
        return this.mControllers;
    }

    public void onResume() {
        super.onResume();
        updateSummaries();
    }
}
