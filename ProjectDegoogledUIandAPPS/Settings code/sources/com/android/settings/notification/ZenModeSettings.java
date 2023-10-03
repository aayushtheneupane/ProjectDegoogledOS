package com.android.settings.notification;

import android.app.AutomaticZenRule;
import android.app.NotificationManager;
import android.content.Context;
import android.provider.SearchIndexableResource;
import android.service.notification.ZenModeConfig;
import androidx.fragment.app.FragmentManager;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class ZenModeSettings extends ZenModeSettingsBase {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.zen_mode_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        public List<String> getNonIndexableKeys(Context context) {
            List<String> nonIndexableKeys = super.getNonIndexableKeys(context);
            nonIndexableKeys.add("zen_mode_duration_settings");
            return nonIndexableKeys;
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return ZenModeSettings.buildPreferenceControllers(context, (Lifecycle) null, (FragmentManager) null);
        }
    };

    public int getHelpResource() {
        return C1715R.string.help_uri_interruptions;
    }

    public int getMetricsCategory() {
        return 76;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.zen_mode_settings;
    }

    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle(), getFragmentManager());
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle, FragmentManager fragmentManager) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ZenModeCallsPreferenceController(context, lifecycle, "zen_mode_behavior_calls"));
        arrayList.add(new ZenModeMessagesPreferenceController(context, lifecycle, "zen_mode_behavior_messages"));
        arrayList.add(new ZenModeBlockedEffectsPreferenceController(context, lifecycle));
        arrayList.add(new ZenModeDurationPreferenceController(context, lifecycle));
        arrayList.add(new ZenModeAutomationPreferenceController(context));
        arrayList.add(new ZenModeButtonPreferenceController(context, lifecycle, fragmentManager));
        arrayList.add(new ZenModeSettingsFooterPreferenceController(context, lifecycle, fragmentManager));
        return arrayList;
    }

    public static class SummaryBuilder {
        private static final int[] ALL_PRIORITY_CATEGORIES = {32, 64, 128, 4, 2, 1, 8, 16};
        private Context mContext;

        public SummaryBuilder(Context context) {
            this.mContext = context;
        }

        /* access modifiers changed from: package-private */
        public String getCallsSettingSummary(NotificationManager.Policy policy) {
            List<String> enabledCategories = getEnabledCategories(policy, C1009xa9365482.INSTANCE, false);
            int size = enabledCategories.size();
            if (size == 0) {
                return this.mContext.getString(C1715R.string.zen_mode_from_none_calls);
            }
            if (size == 1) {
                return this.mContext.getString(C1715R.string.zen_mode_calls_summary_one, new Object[]{enabledCategories.get(0)});
            }
            return this.mContext.getString(C1715R.string.zen_mode_calls_summary_two, new Object[]{enabledCategories.get(0), enabledCategories.get(1)});
        }

        static /* synthetic */ boolean lambda$getCallsSettingSummary$1(Integer num) {
            return 8 == num.intValue() || 16 == num.intValue();
        }

        /* access modifiers changed from: package-private */
        public String getMessagesSettingSummary(NotificationManager.Policy policy) {
            List<String> enabledCategories = getEnabledCategories(policy, C1008x58e87a3.INSTANCE, false);
            if (enabledCategories.size() == 0) {
                return this.mContext.getString(C1715R.string.zen_mode_from_none_messages);
            }
            return enabledCategories.get(0);
        }

        static /* synthetic */ boolean lambda$getMessagesSettingSummary$2(Integer num) {
            return 4 == num.intValue();
        }

        /* access modifiers changed from: package-private */
        public String getSoundSummary() {
            if (NotificationManager.from(this.mContext).getZenMode() != 0) {
                String description = ZenModeConfig.getDescription(this.mContext, true, NotificationManager.from(this.mContext).getZenModeConfig(), false);
                if (description == null) {
                    return this.mContext.getString(C1715R.string.zen_mode_sound_summary_on);
                }
                return this.mContext.getString(C1715R.string.zen_mode_sound_summary_on_with_info, new Object[]{description});
            }
            int enabledAutomaticRulesCount = getEnabledAutomaticRulesCount();
            if (enabledAutomaticRulesCount <= 0) {
                return this.mContext.getString(C1715R.string.zen_mode_sound_summary_off);
            }
            Context context = this.mContext;
            return context.getString(C1715R.string.zen_mode_sound_summary_off_with_info, new Object[]{context.getResources().getQuantityString(C1715R.plurals.zen_mode_sound_summary_summary_off_info, enabledAutomaticRulesCount, new Object[]{Integer.valueOf(enabledAutomaticRulesCount)})});
        }

        /* access modifiers changed from: package-private */
        public String getBlockedEffectsSummary(NotificationManager.Policy policy) {
            int i = policy.suppressedVisualEffects;
            if (i == 0) {
                return this.mContext.getResources().getString(C1715R.string.zen_mode_restrict_notifications_summary_muted);
            }
            if (NotificationManager.Policy.areAllVisualEffectsSuppressed(i)) {
                return this.mContext.getResources().getString(C1715R.string.zen_mode_restrict_notifications_summary_hidden);
            }
            return this.mContext.getResources().getString(C1715R.string.zen_mode_restrict_notifications_summary_custom);
        }

        /* access modifiers changed from: package-private */
        public String getAutomaticRulesSummary() {
            int enabledAutomaticRulesCount = getEnabledAutomaticRulesCount();
            if (enabledAutomaticRulesCount == 0) {
                return this.mContext.getString(C1715R.string.zen_mode_settings_summary_off);
            }
            return this.mContext.getResources().getQuantityString(C1715R.plurals.zen_mode_settings_summary_on, enabledAutomaticRulesCount, new Object[]{Integer.valueOf(enabledAutomaticRulesCount)});
        }

        /* access modifiers changed from: package-private */
        public int getEnabledAutomaticRulesCount() {
            Map<String, AutomaticZenRule> automaticZenRules = NotificationManager.from(this.mContext).getAutomaticZenRules();
            int i = 0;
            if (automaticZenRules != null) {
                for (Map.Entry<String, AutomaticZenRule> value : automaticZenRules.entrySet()) {
                    AutomaticZenRule automaticZenRule = (AutomaticZenRule) value.getValue();
                    if (automaticZenRule != null && automaticZenRule.isEnabled()) {
                        i++;
                    }
                }
            }
            return i;
        }

        private List<String> getEnabledCategories(NotificationManager.Policy policy, Predicate<Integer> predicate, boolean z) {
            ArrayList arrayList = new ArrayList();
            for (int i : ALL_PRIORITY_CATEGORIES) {
                boolean z2 = z && arrayList.isEmpty();
                if (predicate.test(Integer.valueOf(i)) && isCategoryEnabled(policy, i) && !(i == 16 && isCategoryEnabled(policy, 8) && policy.priorityCallSenders == 0)) {
                    arrayList.add(getCategory(i, policy, z2));
                }
            }
            return arrayList;
        }

        private boolean isCategoryEnabled(NotificationManager.Policy policy, int i) {
            return (policy.priorityCategories & i) != 0;
        }

        private String getCategory(int i, NotificationManager.Policy policy, boolean z) {
            if (i == 32) {
                if (z) {
                    return this.mContext.getString(C1715R.string.zen_mode_alarms);
                }
                return this.mContext.getString(C1715R.string.zen_mode_alarms_list);
            } else if (i == 64) {
                if (z) {
                    return this.mContext.getString(C1715R.string.zen_mode_media);
                }
                return this.mContext.getString(C1715R.string.zen_mode_media_list);
            } else if (i == 128) {
                if (z) {
                    return this.mContext.getString(C1715R.string.zen_mode_system);
                }
                return this.mContext.getString(C1715R.string.zen_mode_system_list);
            } else if (i == 4) {
                int i2 = policy.priorityMessageSenders;
                if (i2 == 0) {
                    return this.mContext.getString(C1715R.string.zen_mode_from_anyone);
                }
                if (i2 == 1) {
                    return this.mContext.getString(C1715R.string.zen_mode_from_contacts);
                }
                return this.mContext.getString(C1715R.string.zen_mode_from_starred);
            } else if (i == 2) {
                if (z) {
                    return this.mContext.getString(C1715R.string.zen_mode_events);
                }
                return this.mContext.getString(C1715R.string.zen_mode_events_list);
            } else if (i == 1) {
                if (z) {
                    return this.mContext.getString(C1715R.string.zen_mode_reminders);
                }
                return this.mContext.getString(C1715R.string.zen_mode_reminders_list);
            } else if (i == 8) {
                int i3 = policy.priorityCallSenders;
                if (i3 == 0) {
                    return this.mContext.getString(C1715R.string.zen_mode_all_callers);
                }
                if (i3 == 1) {
                    return this.mContext.getString(C1715R.string.zen_mode_contacts_callers);
                }
                return this.mContext.getString(C1715R.string.zen_mode_starred_callers);
            } else if (i != 16) {
                return "";
            } else {
                if (z) {
                    return this.mContext.getString(C1715R.string.zen_mode_repeat_callers);
                }
                return this.mContext.getString(C1715R.string.zen_mode_repeat_callers_list);
            }
        }
    }
}
