package com.android.settings.applications;

import android.app.Application;
import android.app.usage.UsageStats;
import android.content.Context;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.IconDrawableFactory;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.applications.RecentAppStatsMixin;
import com.android.settings.applications.appinfo.AppInfoDashboardFragment;
import com.android.settings.applications.manageapplications.ManageApplications;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.core.SubSettingLauncher;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.utils.StringUtil;
import com.android.settingslib.widget.AppEntitiesHeaderController;
import com.android.settingslib.widget.AppEntityInfo;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;
import java.util.List;

public class RecentAppsPreferenceController extends BasePreferenceController implements RecentAppStatsMixin.RecentAppStatsListener {
    static final String KEY_DIVIDER = "recent_apps_divider";
    AppEntitiesHeaderController mAppEntitiesController;
    private final ApplicationsState mApplicationsState = ApplicationsState.getInstance((Application) this.mContext.getApplicationContext());
    Preference mDivider;
    private Fragment mHost;
    private final IconDrawableFactory mIconDrawableFactory = IconDrawableFactory.newInstance(this.mContext);
    private List<UsageStats> mRecentApps;
    LayoutPreference mRecentAppsPreference;
    private final int mUserId = UserHandle.myUserId();

    public int getAvailabilityStatus() {
        return 1;
    }

    public RecentAppsPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void setFragment(Fragment fragment) {
        this.mHost = fragment;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mDivider = preferenceScreen.findPreference(KEY_DIVIDER);
        this.mRecentAppsPreference = (LayoutPreference) preferenceScreen.findPreference(getPreferenceKey());
        AppEntitiesHeaderController newInstance = AppEntitiesHeaderController.newInstance(this.mContext, this.mRecentAppsPreference.findViewById(C1715R.C1718id.app_entities_header));
        newInstance.setHeaderTitleRes(C1715R.string.recent_app_category_title);
        newInstance.setHeaderDetailsClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                RecentAppsPreferenceController.this.lambda$displayPreference$0$RecentAppsPreferenceController(view);
            }
        });
        this.mAppEntitiesController = newInstance;
    }

    public /* synthetic */ void lambda$displayPreference$0$RecentAppsPreferenceController(View view) {
        new SubSettingLauncher(this.mContext).setDestination(ManageApplications.class.getName()).setArguments((Bundle) null).setTitleRes(C1715R.string.application_info_label).setSourceMetricsCategory(748).launch();
    }

    public void onReloadDataCompleted(List<UsageStats> list) {
        this.mRecentApps = list;
        refreshUi();
        Context context = this.mContext;
        new InstalledAppCounter(context, -1, context.getPackageManager()) {
            /* access modifiers changed from: protected */
            public void onCountComplete(int i) {
                RecentAppsPreferenceController recentAppsPreferenceController = RecentAppsPreferenceController.this;
                recentAppsPreferenceController.mAppEntitiesController.setHeaderDetails(recentAppsPreferenceController.mContext.getString(C1715R.string.see_all_apps_title, new Object[]{Integer.valueOf(i)}));
                RecentAppsPreferenceController.this.mAppEntitiesController.apply();
            }
        }.execute(new Void[0]);
    }

    private void refreshUi() {
        if (!this.mRecentApps.isEmpty()) {
            displayRecentApps();
            this.mRecentAppsPreference.setVisible(true);
            this.mDivider.setVisible(true);
            return;
        }
        this.mDivider.setVisible(false);
        this.mRecentAppsPreference.setVisible(false);
    }

    private void displayRecentApps() {
        int i = 0;
        for (UsageStats createAppEntity : this.mRecentApps) {
            AppEntityInfo createAppEntity2 = createAppEntity(createAppEntity);
            if (createAppEntity2 != null) {
                this.mAppEntitiesController.setAppEntity(i, createAppEntity2);
                i++;
            }
            if (i == 3) {
                return;
            }
        }
    }

    private AppEntityInfo createAppEntity(UsageStats usageStats) {
        String packageName = usageStats.getPackageName();
        ApplicationsState.AppEntry entry = this.mApplicationsState.getEntry(packageName, this.mUserId);
        if (entry == null) {
            return null;
        }
        AppEntityInfo.Builder builder = new AppEntityInfo.Builder();
        builder.setIcon(this.mIconDrawableFactory.getBadgedIcon(entry.info));
        builder.setTitle(entry.label);
        builder.setSummary(StringUtil.formatRelativeTime(this.mContext, (double) (System.currentTimeMillis() - usageStats.getLastTimeUsed()), false, RelativeDateTimeFormatter.Style.SHORT));
        builder.setOnClickListener(new View.OnClickListener(packageName, entry) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ ApplicationsState.AppEntry f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                RecentAppsPreferenceController.this.lambda$createAppEntity$1$RecentAppsPreferenceController(this.f$1, this.f$2, view);
            }
        });
        return builder.build();
    }

    public /* synthetic */ void lambda$createAppEntity$1$RecentAppsPreferenceController(String str, ApplicationsState.AppEntry appEntry, View view) {
        AppInfoBase.startAppInfoFragment(AppInfoDashboardFragment.class, C1715R.string.application_info_label, str, appEntry.info.uid, this.mHost, 1001, 748);
    }
}
