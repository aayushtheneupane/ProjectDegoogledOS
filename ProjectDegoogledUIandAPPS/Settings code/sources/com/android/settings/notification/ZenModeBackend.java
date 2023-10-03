package com.android.settings.notification;

import android.app.ActivityManager;
import android.app.AutomaticZenRule;
import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import android.icu.text.ListFormatter;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.service.notification.ZenModeConfig;
import android.service.notification.ZenPolicy;
import android.util.Log;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ZenModeBackend {
    public static final Comparator<Map.Entry<String, AutomaticZenRule>> RULE_COMPARATOR = new Comparator<Map.Entry<String, AutomaticZenRule>>() {
        public int compare(Map.Entry<String, AutomaticZenRule> entry, Map.Entry<String, AutomaticZenRule> entry2) {
            boolean contains = ZenModeBackend.getDefaultRuleIds().contains(entry.getKey());
            if (contains != ZenModeBackend.getDefaultRuleIds().contains(entry2.getKey())) {
                return contains ? -1 : 1;
            }
            int compare = Long.compare(entry.getValue().getCreationTime(), entry2.getValue().getCreationTime());
            if (compare != 0) {
                return compare;
            }
            return key(entry.getValue()).compareTo(key(entry2.getValue()));
        }

        private String key(AutomaticZenRule automaticZenRule) {
            int i;
            if (ZenModeConfig.isValidScheduleConditionId(automaticZenRule.getConditionId())) {
                i = 1;
            } else {
                i = ZenModeConfig.isValidEventConditionId(automaticZenRule.getConditionId()) ? 2 : 3;
            }
            return i + automaticZenRule.getName().toString();
        }
    };
    protected static final String ZEN_MODE_FROM_ANYONE = "zen_mode_from_anyone";
    protected static final String ZEN_MODE_FROM_CONTACTS = "zen_mode_from_contacts";
    protected static final String ZEN_MODE_FROM_NONE = "zen_mode_from_none";
    protected static final String ZEN_MODE_FROM_STARRED = "zen_mode_from_starred";
    private static List<String> mDefaultRuleIds;
    private static ZenModeBackend sInstance;
    private String TAG = "ZenModeSettingsBackend";
    private final Context mContext;
    private final NotificationManager mNotificationManager;
    protected NotificationManager.Policy mPolicy;
    protected int mZenMode;

    private int clearDeprecatedEffects(int i) {
        return i & -4;
    }

    protected static String getKeyFromSetting(int i) {
        return i != 0 ? i != 1 ? i != 2 ? ZEN_MODE_FROM_NONE : ZEN_MODE_FROM_STARRED : ZEN_MODE_FROM_CONTACTS : ZEN_MODE_FROM_ANYONE;
    }

    protected static String getKeyFromZenPolicySetting(int i) {
        return i != 1 ? i != 2 ? i != 3 ? ZEN_MODE_FROM_NONE : ZEN_MODE_FROM_STARRED : ZEN_MODE_FROM_CONTACTS : ZEN_MODE_FROM_ANYONE;
    }

    /* access modifiers changed from: protected */
    public int getAlarmsTotalSilenceCallsMessagesSummary(int i) {
        if (i == 4) {
            return C1715R.string.zen_mode_from_none_messages;
        }
        if (i == 8) {
            return C1715R.string.zen_mode_from_none_calls;
        }
        return 0;
    }

    public static ZenModeBackend getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ZenModeBackend(context);
        }
        return sInstance;
    }

    public ZenModeBackend(Context context) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
        updateZenMode();
        updatePolicy();
    }

    /* access modifiers changed from: protected */
    public void updatePolicy() {
        NotificationManager notificationManager = this.mNotificationManager;
        if (notificationManager != null) {
            this.mPolicy = notificationManager.getNotificationPolicy();
        }
    }

    /* access modifiers changed from: protected */
    public void updateZenMode() {
        this.mZenMode = Settings.Global.getInt(this.mContext.getContentResolver(), "zen_mode", this.mZenMode);
    }

    /* access modifiers changed from: protected */
    public boolean updateZenRule(String str, AutomaticZenRule automaticZenRule) {
        return NotificationManager.from(this.mContext).updateAutomaticZenRule(str, automaticZenRule);
    }

    /* access modifiers changed from: protected */
    public void setZenMode(int i) {
        NotificationManager.from(this.mContext).setZenMode(i, (Uri) null, this.TAG);
        this.mZenMode = getZenMode();
    }

    /* access modifiers changed from: protected */
    public void setZenModeForDuration(int i) {
        this.mNotificationManager.setZenMode(1, ZenModeConfig.toTimeCondition(this.mContext, i, ActivityManager.getCurrentUser(), true).id, this.TAG);
        this.mZenMode = getZenMode();
    }

    /* access modifiers changed from: protected */
    public int getZenMode() {
        this.mZenMode = Settings.Global.getInt(this.mContext.getContentResolver(), "zen_mode", this.mZenMode);
        return this.mZenMode;
    }

    /* access modifiers changed from: protected */
    public boolean isVisualEffectSuppressed(int i) {
        return (this.mPolicy.suppressedVisualEffects & i) != 0;
    }

    /* access modifiers changed from: protected */
    public boolean isPriorityCategoryEnabled(int i) {
        return (this.mPolicy.priorityCategories & i) != 0;
    }

    /* access modifiers changed from: protected */
    public int getNewDefaultPriorityCategories(boolean z, int i) {
        int i2 = this.mPolicy.priorityCategories;
        return z ? i2 | i : i2 & (~i);
    }

    /* access modifiers changed from: protected */
    public int getPriorityCallSenders() {
        if (isPriorityCategoryEnabled(8)) {
            return this.mPolicy.priorityCallSenders;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public int getPriorityMessageSenders() {
        if (isPriorityCategoryEnabled(4)) {
            return this.mPolicy.priorityMessageSenders;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public void saveVisualEffectsPolicy(int i, boolean z) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "zen_settings_updated", 1);
        int newSuppressedEffects = getNewSuppressedEffects(z, i);
        NotificationManager.Policy policy = this.mPolicy;
        savePolicy(policy.priorityCategories, policy.priorityCallSenders, policy.priorityMessageSenders, newSuppressedEffects);
    }

    /* access modifiers changed from: protected */
    public void saveSoundPolicy(int i, boolean z) {
        int newDefaultPriorityCategories = getNewDefaultPriorityCategories(z, i);
        NotificationManager.Policy policy = this.mPolicy;
        savePolicy(newDefaultPriorityCategories, policy.priorityCallSenders, policy.priorityMessageSenders, policy.suppressedVisualEffects);
    }

    /* access modifiers changed from: protected */
    public void savePolicy(int i, int i2, int i3, int i4) {
        this.mPolicy = new NotificationManager.Policy(i, i2, i3, i4);
        this.mNotificationManager.setNotificationPolicy(this.mPolicy);
    }

    private int getNewSuppressedEffects(boolean z, int i) {
        int i2 = this.mPolicy.suppressedVisualEffects;
        return clearDeprecatedEffects(z ? i2 | i : (~i) & i2);
    }

    /* access modifiers changed from: protected */
    public void saveSenders(int i, int i2) {
        String str;
        int priorityCallSenders = getPriorityCallSenders();
        int priorityMessageSenders = getPriorityMessageSenders();
        int prioritySenders = getPrioritySenders(i);
        boolean z = i2 != -1;
        if (i2 == -1) {
            i2 = prioritySenders;
        }
        if (i == 8) {
            str = "Calls";
            priorityCallSenders = i2;
        } else {
            str = "";
        }
        if (i == 4) {
            str = "Messages";
            priorityMessageSenders = i2;
        }
        savePolicy(getNewDefaultPriorityCategories(z, i), priorityCallSenders, priorityMessageSenders, this.mPolicy.suppressedVisualEffects);
        if (ZenModeSettingsBase.DEBUG) {
            String str2 = this.TAG;
            Log.d(str2, "onPrefChange allow" + str + "=" + z + " allow" + str + "From=" + ZenModeConfig.sourceToString(i2));
        }
    }

    private int getPrioritySenders(int i) {
        if (i == 8) {
            return getPriorityCallSenders();
        }
        if (i == 4) {
            return getPriorityMessageSenders();
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0038 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getContactsSummary(int r3) {
        /*
            r2 = this;
            r0 = 4
            if (r3 != r0) goto L_0x000e
            boolean r1 = r2.isPriorityCategoryEnabled(r3)
            if (r1 == 0) goto L_0x001d
            int r2 = r2.getPriorityMessageSenders()
            goto L_0x001e
        L_0x000e:
            r1 = 8
            if (r3 != r1) goto L_0x001d
            boolean r1 = r2.isPriorityCategoryEnabled(r3)
            if (r1 == 0) goto L_0x001d
            int r2 = r2.getPriorityCallSenders()
            goto L_0x001e
        L_0x001d:
            r2 = -1
        L_0x001e:
            if (r2 == 0) goto L_0x0038
            r1 = 1
            if (r2 == r1) goto L_0x0034
            r1 = 2
            if (r2 == r1) goto L_0x0030
            if (r3 != r0) goto L_0x002c
            r2 = 2131891968(0x7f121700, float:1.9418671E38)
            return r2
        L_0x002c:
            r2 = 2131891967(0x7f1216ff, float:1.941867E38)
            return r2
        L_0x0030:
            r2 = 2131891969(0x7f121701, float:1.9418673E38)
            return r2
        L_0x0034:
            r2 = 2131891965(0x7f1216fd, float:1.9418665E38)
            return r2
        L_0x0038:
            r2 = 2131891964(0x7f1216fc, float:1.9418663E38)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.notification.ZenModeBackend.getContactsSummary(int):int");
    }

    /* access modifiers changed from: protected */
    public int getContactsCallsSummary(ZenPolicy zenPolicy) {
        int priorityCallSenders = zenPolicy.getPriorityCallSenders();
        if (priorityCallSenders == 1) {
            return C1715R.string.zen_mode_from_anyone;
        }
        if (priorityCallSenders != 2) {
            return priorityCallSenders != 3 ? C1715R.string.zen_mode_from_none_calls : C1715R.string.zen_mode_from_starred;
        }
        return C1715R.string.zen_mode_from_contacts;
    }

    /* access modifiers changed from: protected */
    public int getContactsMessagesSummary(ZenPolicy zenPolicy) {
        int priorityMessageSenders = zenPolicy.getPriorityMessageSenders();
        if (priorityMessageSenders == 1) {
            return C1715R.string.zen_mode_from_anyone;
        }
        if (priorityMessageSenders != 2) {
            return priorityMessageSenders != 3 ? C1715R.string.zen_mode_from_none_messages : C1715R.string.zen_mode_from_starred;
        }
        return C1715R.string.zen_mode_from_contacts;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static int getZenPolicySettingFromPrefKey(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = 3
            r2 = 2
            r3 = 1
            switch(r0) {
                case -946901971: goto L_0x0029;
                case -423126328: goto L_0x001f;
                case 187510959: goto L_0x0015;
                case 462773226: goto L_0x000b;
                default: goto L_0x000a;
            }
        L_0x000a:
            goto L_0x0033
        L_0x000b:
            java.lang.String r0 = "zen_mode_from_starred"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0033
            r4 = r2
            goto L_0x0034
        L_0x0015:
            java.lang.String r0 = "zen_mode_from_anyone"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0033
            r4 = 0
            goto L_0x0034
        L_0x001f:
            java.lang.String r0 = "zen_mode_from_contacts"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0033
            r4 = r3
            goto L_0x0034
        L_0x0029:
            java.lang.String r0 = "zen_mode_from_none"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0033
            r4 = r1
            goto L_0x0034
        L_0x0033:
            r4 = -1
        L_0x0034:
            if (r4 == 0) goto L_0x003e
            if (r4 == r3) goto L_0x003d
            if (r4 == r2) goto L_0x003c
            r4 = 4
            return r4
        L_0x003c:
            return r1
        L_0x003d:
            return r2
        L_0x003e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.notification.ZenModeBackend.getZenPolicySettingFromPrefKey(java.lang.String):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static int getSettingFromPrefKey(java.lang.String r5) {
        /*
            int r0 = r5.hashCode()
            r1 = 0
            r2 = -1
            r3 = 2
            r4 = 1
            switch(r0) {
                case -946901971: goto L_0x002a;
                case -423126328: goto L_0x0020;
                case 187510959: goto L_0x0016;
                case 462773226: goto L_0x000c;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x0034
        L_0x000c:
            java.lang.String r0 = "zen_mode_from_starred"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0034
            r5 = r3
            goto L_0x0035
        L_0x0016:
            java.lang.String r0 = "zen_mode_from_anyone"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0034
            r5 = r1
            goto L_0x0035
        L_0x0020:
            java.lang.String r0 = "zen_mode_from_contacts"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0034
            r5 = r4
            goto L_0x0035
        L_0x002a:
            java.lang.String r0 = "zen_mode_from_none"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0034
            r5 = 3
            goto L_0x0035
        L_0x0034:
            r5 = r2
        L_0x0035:
            if (r5 == 0) goto L_0x003e
            if (r5 == r4) goto L_0x003d
            if (r5 == r3) goto L_0x003c
            return r2
        L_0x003c:
            return r3
        L_0x003d:
            return r4
        L_0x003e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.notification.ZenModeBackend.getSettingFromPrefKey(java.lang.String):int");
    }

    public boolean removeZenRule(String str) {
        return NotificationManager.from(this.mContext).removeAutomaticZenRule(str);
    }

    public NotificationManager.Policy getConsolidatedPolicy() {
        return NotificationManager.from(this.mContext).getConsolidatedNotificationPolicy();
    }

    /* access modifiers changed from: protected */
    public String addZenRule(AutomaticZenRule automaticZenRule) {
        try {
            return NotificationManager.from(this.mContext).addAutomaticZenRule(automaticZenRule);
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public ZenPolicy setDefaultZenPolicy(ZenPolicy zenPolicy) {
        int i = 4;
        int zenPolicySenders = this.mPolicy.allowCalls() ? ZenModeConfig.getZenPolicySenders(this.mPolicy.allowCallsFrom()) : 4;
        if (this.mPolicy.allowMessages()) {
            i = ZenModeConfig.getZenPolicySenders(this.mPolicy.allowMessagesFrom());
        }
        return new ZenPolicy.Builder(zenPolicy).allowAlarms(this.mPolicy.allowAlarms()).allowCalls(zenPolicySenders).allowEvents(this.mPolicy.allowEvents()).allowMedia(this.mPolicy.allowMedia()).allowMessages(i).allowReminders(this.mPolicy.allowReminders()).allowRepeatCallers(this.mPolicy.allowRepeatCallers()).allowSystem(this.mPolicy.allowSystem()).showFullScreenIntent(this.mPolicy.showFullScreenIntents()).showLights(this.mPolicy.showLights()).showInAmbientDisplay(this.mPolicy.showAmbient()).showInNotificationList(this.mPolicy.showInNotificationList()).showBadges(this.mPolicy.showBadges()).showPeeking(this.mPolicy.showPeeking()).showStatusBarIcons(this.mPolicy.showStatusBarIcons()).build();
    }

    /* access modifiers changed from: protected */
    public Map.Entry<String, AutomaticZenRule>[] getAutomaticZenRules() {
        Map<String, AutomaticZenRule> automaticZenRules = NotificationManager.from(this.mContext).getAutomaticZenRules();
        Map.Entry<String, AutomaticZenRule>[] entryArr = (Map.Entry[]) automaticZenRules.entrySet().toArray(new Map.Entry[automaticZenRules.size()]);
        Arrays.sort(entryArr, RULE_COMPARATOR);
        return entryArr;
    }

    /* access modifiers changed from: protected */
    public AutomaticZenRule getAutomaticZenRule(String str) {
        return NotificationManager.from(this.mContext).getAutomaticZenRule(str);
    }

    /* access modifiers changed from: private */
    public static List<String> getDefaultRuleIds() {
        if (mDefaultRuleIds == null) {
            mDefaultRuleIds = ZenModeConfig.DEFAULT_RULE_IDS;
        }
        return mDefaultRuleIds;
    }

    /* access modifiers changed from: package-private */
    public NotificationManager.Policy toNotificationPolicy(ZenPolicy zenPolicy) {
        return new ZenModeConfig().toNotificationPolicy(zenPolicy);
    }

    /* access modifiers changed from: package-private */
    public List<String> getStarredContacts(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor == null || !cursor.moveToFirst()) {
            return arrayList;
        }
        do {
            String string = cursor.getString(0);
            if (string != null) {
                arrayList.add(string);
            }
        } while (cursor.moveToNext());
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<java.lang.String> getStarredContacts() {
        /*
            r1 = this;
            android.database.Cursor r0 = r1.queryData()     // Catch:{ all -> 0x0010 }
            java.util.List r1 = r1.getStarredContacts(r0)     // Catch:{ all -> 0x000e }
            if (r0 == 0) goto L_0x000d
            r0.close()
        L_0x000d:
            return r1
        L_0x000e:
            r1 = move-exception
            goto L_0x0012
        L_0x0010:
            r1 = move-exception
            r0 = 0
        L_0x0012:
            if (r0 == 0) goto L_0x0017
            r0.close()
        L_0x0017:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.notification.ZenModeBackend.getStarredContacts():java.util.List");
    }

    public String getStarredContactsSummary() {
        List<String> starredContacts = getStarredContacts();
        int size = starredContacts.size();
        ArrayList arrayList = new ArrayList();
        if (size == 0) {
            arrayList.add(this.mContext.getString(C1715R.string.zen_mode_from_none));
        } else {
            int i = 0;
            while (i < 2 && i < size) {
                arrayList.add(starredContacts.get(i));
                i++;
            }
            if (size == 3) {
                arrayList.add(starredContacts.get(2));
            } else if (size > 2) {
                int i2 = size - 2;
                arrayList.add(this.mContext.getResources().getQuantityString(C1715R.plurals.zen_mode_starred_contacts_summary_additional_contacts, i2, new Object[]{Integer.valueOf(i2)}));
            }
        }
        return ListFormatter.getInstance().format(arrayList);
    }

    private Cursor queryData() {
        return this.mContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{"display_name"}, "starred=1", (String[]) null, "times_contacted");
    }
}
