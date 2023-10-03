package com.android.settings.applications;

import android.app.usage.UsageStats;
import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.applications.RecentAppStatsMixin;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;
import java.util.List;

public class AllAppsInfoPreferenceController extends BasePreferenceController implements RecentAppStatsMixin.RecentAppStatsListener {
    Preference mPreference;

    public int getAvailabilityStatus() {
        return 0;
    }

    public AllAppsInfoPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference.setVisible(false);
    }

    public void onReloadDataCompleted(List<UsageStats> list) {
        if (!list.isEmpty()) {
            this.mPreference.setVisible(false);
            return;
        }
        this.mPreference.setVisible(true);
        Context context = this.mContext;
        new InstalledAppCounter(context, -1, context.getPackageManager()) {
            /* access modifiers changed from: protected */
            public void onCountComplete(int i) {
                AllAppsInfoPreferenceController allAppsInfoPreferenceController = AllAppsInfoPreferenceController.this;
                allAppsInfoPreferenceController.mPreference.setSummary((CharSequence) allAppsInfoPreferenceController.mContext.getString(C1715R.string.apps_summary, new Object[]{Integer.valueOf(i)}));
            }
        }.execute(new Void[0]);
    }
}
