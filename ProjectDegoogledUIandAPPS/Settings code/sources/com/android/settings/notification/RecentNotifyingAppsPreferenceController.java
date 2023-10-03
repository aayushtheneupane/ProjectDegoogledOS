package com.android.settings.notification;

import android.app.Application;
import android.app.usage.IUsageStatsManager;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.service.notification.NotifyingApp;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.IconDrawableFactory;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.android.settings.Utils;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.core.SubSettingLauncher;
import com.android.settingslib.applications.AppUtils;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.utils.StringUtil;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RecentNotifyingAppsPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    static final String KEY_DIVIDER = "all_notifications_divider";
    static final String KEY_SEE_ALL = "all_notifications";
    private static final Set<String> SKIP_SYSTEM_PACKAGES = new ArraySet();
    private final ApplicationsState mApplicationsState;
    List<NotifyingApp> mApps;
    private Calendar mCal;
    private PreferenceCategory mCategory;
    private Preference mDivider;
    private final Fragment mHost;
    private final IconDrawableFactory mIconDrawableFactory;
    private final NotificationBackend mNotificationBackend;
    private final PackageManager mPm;
    private Preference mSeeAllPref;
    private IUsageStatsManager mUsageStatsManager;
    protected List<Integer> mUserIds;

    public String getPreferenceKey() {
        return "recent_notifications_category";
    }

    public boolean isAvailable() {
        return true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public RecentNotifyingAppsPreferenceController(Context context, NotificationBackend notificationBackend, IUsageStatsManager iUsageStatsManager, UserManager userManager, Application application, Fragment fragment) {
        this(context, notificationBackend, iUsageStatsManager, userManager, application == null ? null : ApplicationsState.getInstance(application), fragment);
    }

    RecentNotifyingAppsPreferenceController(Context context, NotificationBackend notificationBackend, IUsageStatsManager iUsageStatsManager, UserManager userManager, ApplicationsState applicationsState, Fragment fragment) {
        super(context);
        this.mIconDrawableFactory = IconDrawableFactory.newInstance(context);
        this.mPm = context.getPackageManager();
        this.mHost = fragment;
        this.mApplicationsState = applicationsState;
        this.mNotificationBackend = notificationBackend;
        this.mUsageStatsManager = iUsageStatsManager;
        this.mUserIds = new ArrayList();
        this.mUserIds.add(Integer.valueOf(this.mContext.getUserId()));
        int managedProfileId = Utils.getManagedProfileId(userManager, this.mContext.getUserId());
        if (managedProfileId != -10000) {
            this.mUserIds.add(Integer.valueOf(managedProfileId));
        }
    }

    public void updateNonIndexableKeys(List<String> list) {
        super.updateNonIndexableKeys(list);
        list.add("recent_notifications_category");
        list.add(KEY_DIVIDER);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mCategory = (PreferenceCategory) preferenceScreen.findPreference(getPreferenceKey());
        this.mSeeAllPref = preferenceScreen.findPreference(KEY_SEE_ALL);
        this.mDivider = preferenceScreen.findPreference(KEY_DIVIDER);
        super.displayPreference(preferenceScreen);
        refreshUi(this.mCategory.getContext());
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        refreshUi(this.mCategory.getContext());
        this.mSeeAllPref.setTitle((CharSequence) this.mContext.getString(C1715R.string.recent_notifications_see_all_title));
    }

    /* access modifiers changed from: package-private */
    public void refreshUi(Context context) {
        reloadData();
        List<NotifyingApp> displayableRecentAppList = getDisplayableRecentAppList();
        if (displayableRecentAppList == null || displayableRecentAppList.isEmpty()) {
            displayOnlyAllAppsLink();
        } else {
            displayRecentApps(context, displayableRecentAppList);
        }
    }

    /* access modifiers changed from: package-private */
    public void reloadData() {
        this.mApps = new ArrayList();
        this.mCal = Calendar.getInstance();
        this.mCal.add(6, -3);
        for (Integer intValue : this.mUserIds) {
            int intValue2 = intValue.intValue();
            UsageEvents usageEvents = null;
            try {
                usageEvents = this.mUsageStatsManager.queryEventsForUser(this.mCal.getTimeInMillis(), System.currentTimeMillis(), intValue2, this.mContext.getPackageName());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (usageEvents != null) {
                ArrayMap arrayMap = new ArrayMap();
                UsageEvents.Event event = new UsageEvents.Event();
                while (usageEvents.hasNextEvent()) {
                    usageEvents.getNextEvent(event);
                    if (event.getEventType() == 12) {
                        NotifyingApp notifyingApp = (NotifyingApp) arrayMap.get(getKey(intValue2, event.getPackageName()));
                        if (notifyingApp == null) {
                            notifyingApp = new NotifyingApp();
                            arrayMap.put(getKey(intValue2, event.getPackageName()), notifyingApp);
                            notifyingApp.setPackage(event.getPackageName());
                            notifyingApp.setUserId(intValue2);
                        }
                        if (event.getTimeStamp() > notifyingApp.getLastNotified()) {
                            notifyingApp.setLastNotified(event.getTimeStamp());
                        }
                    }
                }
                this.mApps.addAll(arrayMap.values());
            }
        }
    }

    static String getKey(int i, String str) {
        return i + "|" + str;
    }

    private void displayOnlyAllAppsLink() {
        this.mCategory.setTitle((CharSequence) null);
        this.mDivider.setVisible(false);
        this.mSeeAllPref.setTitle((int) C1715R.string.notifications_title);
        this.mSeeAllPref.setIcon((Drawable) null);
        for (int preferenceCount = this.mCategory.getPreferenceCount() - 1; preferenceCount >= 0; preferenceCount--) {
            Preference preference = this.mCategory.getPreference(preferenceCount);
            if (!TextUtils.equals(preference.getKey(), KEY_SEE_ALL)) {
                this.mCategory.removePreference(preference);
            }
        }
    }

    private void displayRecentApps(Context context, List<NotifyingApp> list) {
        boolean z;
        this.mCategory.setTitle((int) C1715R.string.recent_notifications);
        this.mDivider.setVisible(true);
        this.mSeeAllPref.setSummary((CharSequence) null);
        this.mSeeAllPref.setIcon((int) C1715R.C1717drawable.ic_chevron_right_24dp);
        ArrayMap arrayMap = new ArrayMap();
        int preferenceCount = this.mCategory.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = this.mCategory.getPreference(i);
            String key = preference.getKey();
            if (!TextUtils.equals(key, KEY_SEE_ALL)) {
                arrayMap.put(key, (NotificationAppPreference) preference);
            }
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            NotifyingApp notifyingApp = list.get(i2);
            String str = notifyingApp.getPackage();
            ApplicationsState.AppEntry entry = this.mApplicationsState.getEntry(notifyingApp.getPackage(), notifyingApp.getUserId());
            if (entry == null) {
                Context context2 = context;
            } else {
                NotificationAppPreference notificationAppPreference = (NotificationAppPreference) arrayMap.remove(getKey(notifyingApp.getUserId(), str));
                if (notificationAppPreference == null) {
                    notificationAppPreference = new NotificationAppPreference(context);
                    z = false;
                } else {
                    Context context3 = context;
                    z = true;
                }
                notificationAppPreference.setKey(getKey(notifyingApp.getUserId(), str));
                notificationAppPreference.setTitle((CharSequence) entry.label);
                notificationAppPreference.setIcon(this.mIconDrawableFactory.getBadgedIcon(entry.info));
                notificationAppPreference.setIconSize(2);
                notificationAppPreference.setSummary(StringUtil.formatRelativeTime(this.mContext, (double) (System.currentTimeMillis() - notifyingApp.getLastNotified()), true));
                notificationAppPreference.setOrder(i2);
                Bundle bundle = new Bundle();
                bundle.putString("package", str);
                bundle.putInt("uid", entry.info.uid);
                notificationAppPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(bundle, entry) {
                    private final /* synthetic */ Bundle f$1;
                    private final /* synthetic */ ApplicationsState.AppEntry f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final boolean onPreferenceClick(Preference preference) {
                        return RecentNotifyingAppsPreferenceController.this.mo11058x78e409e1(this.f$1, this.f$2, preference);
                    }
                });
                notificationAppPreference.setSwitchEnabled(this.mNotificationBackend.isBlockable(this.mContext, entry.info));
                notificationAppPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener(str, entry) {
                    private final /* synthetic */ String f$1;
                    private final /* synthetic */ ApplicationsState.AppEntry f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final boolean onPreferenceChange(Preference preference, Object obj) {
                        return RecentNotifyingAppsPreferenceController.this.mo11059x58434e2(this.f$1, this.f$2, preference, obj);
                    }
                });
                notificationAppPreference.setChecked(!this.mNotificationBackend.getNotificationsBanned(str, entry.info.uid));
                if (!z) {
                    this.mCategory.addPreference(notificationAppPreference);
                }
            }
        }
        for (Preference removePreference : arrayMap.values()) {
            this.mCategory.removePreference(removePreference);
        }
    }

    /* renamed from: lambda$displayRecentApps$0$RecentNotifyingAppsPreferenceController */
    public /* synthetic */ boolean mo11058x78e409e1(Bundle bundle, ApplicationsState.AppEntry appEntry, Preference preference) {
        new SubSettingLauncher(this.mHost.getActivity()).setDestination(AppNotificationSettings.class.getName()).setTitleRes(C1715R.string.notifications_title).setArguments(bundle).setUserHandle(new UserHandle(UserHandle.getUserId(appEntry.info.uid))).setSourceMetricsCategory(133).launch();
        return true;
    }

    /* renamed from: lambda$displayRecentApps$1$RecentNotifyingAppsPreferenceController */
    public /* synthetic */ boolean mo11059x58434e2(String str, ApplicationsState.AppEntry appEntry, Preference preference, Object obj) {
        this.mNotificationBackend.setNotificationsEnabledForPackage(str, appEntry.info.uid, !(((Boolean) obj).booleanValue() ^ true));
        return true;
    }

    private List<NotifyingApp> getDisplayableRecentAppList() {
        Collections.sort(this.mApps);
        ArrayList arrayList = new ArrayList(3);
        int i = 0;
        for (NotifyingApp next : this.mApps) {
            if (this.mApplicationsState.getEntry(next.getPackage(), next.getUserId()) != null && shouldIncludePkgInRecents(next.getPackage(), next.getUserId())) {
                arrayList.add(next);
                i++;
                if (i >= 3) {
                    break;
                }
            }
        }
        return arrayList;
    }

    private boolean shouldIncludePkgInRecents(String str, int i) {
        ApplicationInfo applicationInfo;
        if (this.mPm.resolveActivity(new Intent().addCategory("android.intent.category.LAUNCHER").setPackage(str), 0) != null) {
            return true;
        }
        ApplicationsState.AppEntry entry = this.mApplicationsState.getEntry(str, i);
        if (entry != null && (applicationInfo = entry.info) != null && AppUtils.isInstant(applicationInfo)) {
            return true;
        }
        Log.d("RecentNotisCtrl", "Not a user visible or instant app, skipping " + str);
        return false;
    }
}
