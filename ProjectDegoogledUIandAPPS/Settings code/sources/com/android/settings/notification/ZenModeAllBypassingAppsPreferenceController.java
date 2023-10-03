package com.android.settings.notification;

import android.app.Application;
import android.app.NotificationChannel;
import android.content.Context;
import android.os.Bundle;
import androidx.core.text.BidiFormatter;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.core.SubSettingLauncher;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.widget.apppreference.AppPreference;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class ZenModeAllBypassingAppsPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private final String KEY;
    private ApplicationsState.Session mAppSession;
    private final ApplicationsState.Callbacks mAppSessionCallbacks;
    ApplicationsState mApplicationsState;
    /* access modifiers changed from: private */
    public Fragment mHostFragment;
    private NotificationBackend mNotificationBackend;
    Context mPrefContext;
    PreferenceScreen mPreferenceScreen;

    public String getPreferenceKey() {
        return "zen_mode_bypassing_apps_category";
    }

    public boolean isAvailable() {
        return true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ZenModeAllBypassingAppsPreferenceController(Context context, Application application, Fragment fragment) {
        this(context, application == null ? null : ApplicationsState.getInstance(application), fragment);
    }

    private ZenModeAllBypassingAppsPreferenceController(Context context, ApplicationsState applicationsState, Fragment fragment) {
        super(context);
        this.KEY = "zen_mode_bypassing_apps_category";
        this.mNotificationBackend = new NotificationBackend();
        this.mAppSessionCallbacks = new ApplicationsState.Callbacks() {
            public void onAllSizesComputed() {
            }

            public void onRunningStateChanged(boolean z) {
                ZenModeAllBypassingAppsPreferenceController.this.updateNotificationChannelList();
            }

            public void onPackageListChanged() {
                ZenModeAllBypassingAppsPreferenceController.this.updateNotificationChannelList();
            }

            public void onRebuildComplete(ArrayList<ApplicationsState.AppEntry> arrayList) {
                ZenModeAllBypassingAppsPreferenceController.this.updateNotificationChannelList(arrayList);
            }

            public void onPackageIconChanged() {
                ZenModeAllBypassingAppsPreferenceController.this.updateNotificationChannelList();
            }

            public void onPackageSizeChanged(String str) {
                ZenModeAllBypassingAppsPreferenceController.this.updateNotificationChannelList();
            }

            public void onLauncherInfoChanged() {
                ZenModeAllBypassingAppsPreferenceController.this.updateNotificationChannelList();
            }

            public void onLoadEntriesCompleted() {
                ZenModeAllBypassingAppsPreferenceController.this.updateNotificationChannelList();
            }
        };
        this.mApplicationsState = applicationsState;
        this.mHostFragment = fragment;
        ApplicationsState applicationsState2 = this.mApplicationsState;
        if (applicationsState2 != null && fragment != null) {
            this.mAppSession = applicationsState2.newSession(this.mAppSessionCallbacks, fragment.getLifecycle());
        }
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mPreferenceScreen = preferenceScreen;
        this.mPrefContext = this.mPreferenceScreen.getContext();
        updateNotificationChannelList();
        super.displayPreference(preferenceScreen);
    }

    public void updateNotificationChannelList() {
        ArrayList<ApplicationsState.AppEntry> rebuild;
        ApplicationsState.Session session = this.mAppSession;
        if (session != null && (rebuild = session.rebuild(ApplicationsState.FILTER_ALL_ENABLED, ApplicationsState.ALPHA_COMPARATOR)) != null) {
            updateNotificationChannelList(rebuild);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateNotificationChannelList(List<ApplicationsState.AppEntry> list) {
        if (this.mPreferenceScreen != null && list != null) {
            ArrayList<Preference> arrayList = new ArrayList<>();
            for (final ApplicationsState.AppEntry next : list) {
                String str = next.info.packageName;
                this.mApplicationsState.ensureIcon(next);
                for (final NotificationChannel notificationChannel : this.mNotificationBackend.getNotificationChannelsBypassingDnd(str, next.info.uid).getList()) {
                    AppPreference appPreference = new AppPreference(this.mPrefContext);
                    appPreference.setKey(str + "|" + notificationChannel.getId());
                    appPreference.setTitle((CharSequence) BidiFormatter.getInstance().unicodeWrap(next.label));
                    appPreference.setIcon(next.icon);
                    appPreference.setSummary(BidiFormatter.getInstance().unicodeWrap(notificationChannel.getName()));
                    appPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                        public boolean onPreferenceClick(Preference preference) {
                            Bundle bundle = new Bundle();
                            bundle.putString("package", next.info.packageName);
                            bundle.putInt("uid", next.info.uid);
                            bundle.putString("android.provider.extra.CHANNEL_ID", notificationChannel.getId());
                            new SubSettingLauncher(ZenModeAllBypassingAppsPreferenceController.this.mContext).setDestination(ChannelNotificationSettings.class.getName()).setArguments(bundle).setTitleRes(C1715R.string.notification_channel_title).setResultListener(ZenModeAllBypassingAppsPreferenceController.this.mHostFragment, 0).setSourceMetricsCategory(1589).launch();
                            return true;
                        }
                    });
                    arrayList.add(appPreference);
                }
                this.mPreferenceScreen.removeAll();
                if (arrayList.size() > 0) {
                    for (Preference addPreference : arrayList) {
                        this.mPreferenceScreen.addPreference(addPreference);
                    }
                }
            }
        }
    }
}
