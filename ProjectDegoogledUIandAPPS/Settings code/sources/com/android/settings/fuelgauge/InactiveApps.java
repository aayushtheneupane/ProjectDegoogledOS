package com.android.settings.fuelgauge;

import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;

public class InactiveApps extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private static final CharSequence[] SETTABLE_BUCKETS_NAMES = {"ACTIVE", "WORKING_SET", "FREQUENT", "RARE"};
    private static final CharSequence[] SETTABLE_BUCKETS_VALUES = {Integer.toString(10), Integer.toString(20), Integer.toString(30), Integer.toString(40)};
    private UsageStatsManager mUsageStats;

    static String bucketToName(int i) {
        return i != 5 ? i != 10 ? i != 20 ? i != 30 ? i != 40 ? i != 50 ? "" : "NEVER" : "RARE" : "FREQUENT" : "WORKING_SET" : "ACTIVE" : "EXEMPTED";
    }

    public int getMetricsCategory() {
        return 238;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUsageStats = (UsageStatsManager) getActivity().getSystemService(UsageStatsManager.class);
        addPreferencesFromResource(C1715R.xml.inactive_apps);
    }

    public void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.removeAll();
        preferenceScreen.setOrderingAsAdded(false);
        FragmentActivity activity = getActivity();
        PackageManager packageManager = activity.getPackageManager();
        UsageStatsManager usageStatsManager = (UsageStatsManager) activity.getSystemService(UsageStatsManager.class);
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        for (ResolveInfo next : packageManager.queryIntentActivities(intent, 0)) {
            String str = next.activityInfo.applicationInfo.packageName;
            ListPreference listPreference = new ListPreference(getPrefContext());
            listPreference.setTitle(next.loadLabel(packageManager));
            listPreference.setIcon(next.loadIcon(packageManager));
            listPreference.setKey(str);
            listPreference.setEntries(SETTABLE_BUCKETS_NAMES);
            listPreference.setEntryValues(SETTABLE_BUCKETS_VALUES);
            updateSummary(listPreference);
            listPreference.setOnPreferenceChangeListener(this);
            preferenceScreen.addPreference(listPreference);
        }
    }

    private void updateSummary(ListPreference listPreference) {
        Resources resources = getActivity().getResources();
        int appStandbyBucket = this.mUsageStats.getAppStandbyBucket(listPreference.getKey());
        boolean z = true;
        listPreference.setSummary(resources.getString(C1715R.string.standby_bucket_summary, new Object[]{bucketToName(appStandbyBucket)}));
        if (appStandbyBucket < 10 || appStandbyBucket > 40) {
            z = false;
        }
        if (z) {
            listPreference.setValue(Integer.toString(appStandbyBucket));
        }
        listPreference.setEnabled(z);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mUsageStats.setAppStandbyBucket(preference.getKey(), Integer.parseInt((String) obj));
        updateSummary((ListPreference) preference);
        return false;
    }
}
