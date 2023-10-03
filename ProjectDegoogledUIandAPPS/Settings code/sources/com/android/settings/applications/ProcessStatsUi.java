package com.android.settings.applications;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.settings.SettingsActivity;
import com.android.settings.applications.ProcStatsData;
import com.havoc.config.center.C1715R;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProcessStatsUi extends ProcessStatsBase {
    public static final int[] BACKGROUND_AND_SYSTEM_PROC_STATES = {0, 2, 3, 4, 8, 5, 6, 7, 9};
    public static final int[] CACHED_PROC_STATES = {11, 12, 13};
    public static final int[] FOREGROUND_PROC_STATES = {1};
    static final Comparator<ProcStatsPackageEntry> sMaxPackageEntryCompare = new Comparator<ProcStatsPackageEntry>() {
        public int compare(ProcStatsPackageEntry procStatsPackageEntry, ProcStatsPackageEntry procStatsPackageEntry2) {
            double max = (double) Math.max(procStatsPackageEntry2.mMaxBgMem, procStatsPackageEntry2.mMaxRunMem);
            double max2 = (double) Math.max(procStatsPackageEntry.mMaxBgMem, procStatsPackageEntry.mMaxRunMem);
            if (max2 == max) {
                return 0;
            }
            return max2 < max ? 1 : -1;
        }
    };
    static final Comparator<ProcStatsPackageEntry> sPackageEntryCompare = new Comparator<ProcStatsPackageEntry>() {
        public int compare(ProcStatsPackageEntry procStatsPackageEntry, ProcStatsPackageEntry procStatsPackageEntry2) {
            double max = Math.max(procStatsPackageEntry2.mRunWeight, procStatsPackageEntry2.mBgWeight);
            double max2 = Math.max(procStatsPackageEntry.mRunWeight, procStatsPackageEntry.mBgWeight);
            if (max2 == max) {
                return 0;
            }
            return max2 < max ? 1 : -1;
        }
    };
    private PreferenceGroup mAppListGroup;
    private MenuItem mMenuAvg;
    private MenuItem mMenuMax;
    private PackageManager mPm;
    private boolean mShowMax;

    public int getHelpResource() {
        return C1715R.string.help_uri_process_stats_apps;
    }

    public int getMetricsCategory() {
        return 23;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPm = getActivity().getPackageManager();
        addPreferencesFromResource(C1715R.xml.process_stats_ui);
        this.mAppListGroup = (PreferenceGroup) findPreference("app_list");
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        this.mMenuAvg = menu.add(0, 1, 0, C1715R.string.sort_avg_use);
        this.mMenuMax = menu.add(0, 2, 0, C1715R.string.sort_max_use);
        updateMenu();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId != 1 && itemId != 2) {
            return super.onOptionsItemSelected(menuItem);
        }
        this.mShowMax = !this.mShowMax;
        refreshUi();
        updateMenu();
        return true;
    }

    private void updateMenu() {
        this.mMenuMax.setVisible(!this.mShowMax);
        this.mMenuAvg.setVisible(this.mShowMax);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        if (!(preference instanceof ProcessStatsPreference)) {
            return false;
        }
        ProcessStatsBase.launchMemoryDetail((SettingsActivity) getActivity(), this.mStatsManager.getMemInfo(), ((ProcessStatsPreference) preference).getEntry(), true);
        return super.onPreferenceTreeClick(preference);
    }

    public void refreshUi() {
        double d;
        this.mAppListGroup.removeAll();
        int i = 0;
        this.mAppListGroup.setOrderingAsAdded(false);
        this.mAppListGroup.setTitle(this.mShowMax ? C1715R.string.maximum_memory_use : C1715R.string.average_memory_use);
        FragmentActivity activity = getActivity();
        ProcStatsData.MemInfo memInfo = this.mStatsManager.getMemInfo();
        List<ProcStatsPackageEntry> entries = this.mStatsManager.getEntries();
        int size = entries.size();
        for (int i2 = 0; i2 < size; i2++) {
            entries.get(i2).updateMetrics();
        }
        Collections.sort(entries, this.mShowMax ? sMaxPackageEntryCompare : sPackageEntryCompare);
        if (this.mShowMax) {
            d = memInfo.realTotalRam;
        } else {
            d = memInfo.usedWeight * memInfo.weightToRam;
        }
        while (i < entries.size()) {
            ProcStatsPackageEntry procStatsPackageEntry = entries.get(i);
            ProcessStatsPreference processStatsPreference = new ProcessStatsPreference(getPrefContext());
            procStatsPackageEntry.retrieveUiData(activity, this.mPm);
            FragmentActivity fragmentActivity = activity;
            ProcessStatsPreference processStatsPreference2 = processStatsPreference;
            processStatsPreference.init(procStatsPackageEntry, this.mPm, d, memInfo.weightToRam, memInfo.totalScale, !this.mShowMax);
            processStatsPreference2.setOrder(i);
            this.mAppListGroup.addPreference(processStatsPreference2);
            i++;
            activity = fragmentActivity;
        }
    }
}
