package com.android.settings.applications;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.format.Formatter;
import androidx.preference.Preference;
import com.android.settings.SummaryPreference;
import com.android.settings.applications.ProcStatsData;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.dashboard.SummaryLoader;
import com.android.settingslib.Utils;
import com.havoc.config.center.C1715R;

public class ProcessStatsSummary extends ProcessStatsBase implements Preference.OnPreferenceClickListener {
    public static final SummaryLoader.SummaryProviderFactory SUMMARY_PROVIDER_FACTORY = new SummaryLoader.SummaryProviderFactory() {
        public SummaryLoader.SummaryProvider createSummaryProvider(Activity activity, SummaryLoader summaryLoader) {
            return new SummaryProvider(activity, summaryLoader);
        }
    };
    private Preference mAppListPreference;
    private Preference mAverageUsed;
    private Preference mFree;
    private Preference mPerformance;
    private SummaryPreference mSummaryPref;
    private Preference mTotalMemory;

    public int getHelpResource() {
        return C1715R.string.help_uri_process_stats_summary;
    }

    public int getMetricsCategory() {
        return 202;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.process_stats_summary);
        this.mSummaryPref = (SummaryPreference) findPreference("status_header");
        this.mPerformance = findPreference("performance");
        this.mTotalMemory = findPreference("total_memory");
        this.mAverageUsed = findPreference("average_used");
        this.mFree = findPreference("free");
        this.mAppListPreference = findPreference("apps_list");
        this.mAppListPreference.setOnPreferenceClickListener(this);
    }

    public void refreshUi() {
        CharSequence charSequence;
        Context context = getContext();
        ProcStatsData.MemInfo memInfo = this.mStatsManager.getMemInfo();
        double d = memInfo.realUsedRam;
        double d2 = memInfo.realTotalRam;
        double d3 = memInfo.realFreeRam;
        long j = (long) d;
        Formatter.BytesResult formatBytes = Formatter.formatBytes(context.getResources(), j, 1);
        long j2 = (long) d2;
        String formatShortFileSize = Formatter.formatShortFileSize(context, j2);
        String formatShortFileSize2 = Formatter.formatShortFileSize(context, (long) d3);
        CharSequence[] textArray = getResources().getTextArray(C1715R.array.ram_states);
        int memState = this.mStatsManager.getMemState();
        if (memState < 0 || memState >= textArray.length - 1) {
            charSequence = textArray[textArray.length - 1];
        } else {
            charSequence = textArray[memState];
        }
        this.mSummaryPref.setAmount(formatBytes.value);
        this.mSummaryPref.setUnits(formatBytes.units);
        float f = (float) (d / (d3 + d));
        this.mSummaryPref.setRatios(f, 0.0f, 1.0f - f);
        this.mPerformance.setSummary(charSequence);
        this.mTotalMemory.setSummary((CharSequence) formatShortFileSize);
        this.mAverageUsed.setSummary((CharSequence) Utils.formatPercentage(j, j2));
        this.mFree.setSummary((CharSequence) formatShortFileSize2);
        String string = getString(ProcessStatsBase.sDurationLabels[this.mDurationIndex]);
        int size = this.mStatsManager.getEntries().size();
        this.mAppListPreference.setSummary((CharSequence) getResources().getQuantityString(C1715R.plurals.memory_usage_apps_summary, size, new Object[]{Integer.valueOf(size), string}));
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference != this.mAppListPreference) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("transfer_stats", true);
        bundle.putInt("duration_index", this.mDurationIndex);
        this.mStatsManager.xferStats();
        new SubSettingLauncher(getContext()).setDestination(ProcessStatsUi.class.getName()).setTitleRes(C1715R.string.memory_usage_apps).setArguments(bundle).setSourceMetricsCategory(getMetricsCategory()).launch();
        return true;
    }

    private static class SummaryProvider implements SummaryLoader.SummaryProvider {
        private final Context mContext;
        private final SummaryLoader mSummaryLoader;

        public SummaryProvider(Context context, SummaryLoader summaryLoader) {
            this.mContext = context;
            this.mSummaryLoader = summaryLoader;
        }

        public void setListening(boolean z) {
            if (z) {
                ProcStatsData procStatsData = new ProcStatsData(this.mContext, false);
                procStatsData.setDuration(ProcessStatsBase.sDurations[0]);
                ProcStatsData.MemInfo memInfo = procStatsData.getMemInfo();
                String formatShortFileSize = Formatter.formatShortFileSize(this.mContext, (long) memInfo.realUsedRam);
                String formatShortFileSize2 = Formatter.formatShortFileSize(this.mContext, (long) memInfo.realTotalRam);
                this.mSummaryLoader.setSummary(this, this.mContext.getString(C1715R.string.memory_summary, new Object[]{formatShortFileSize, formatShortFileSize2}));
            }
        }
    }
}
