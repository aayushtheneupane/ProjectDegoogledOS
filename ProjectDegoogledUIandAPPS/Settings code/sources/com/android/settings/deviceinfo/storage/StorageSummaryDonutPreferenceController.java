package com.android.settings.deviceinfo.storage;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.Formatter;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;

public class StorageSummaryDonutPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private StorageSummaryDonutPreference mSummary;
    private long mTotalBytes;
    private long mUsedBytes;

    public String getPreferenceKey() {
        return "pref_summary";
    }

    public boolean isAvailable() {
        return true;
    }

    public StorageSummaryDonutPreferenceController(Context context) {
        super(context);
    }

    public static CharSequence convertUsedBytesToFormattedText(Context context, long j) {
        Formatter.BytesResult formatBytes = Formatter.formatBytes(context.getResources(), j, 0);
        return TextUtils.expandTemplate(context.getText(C1715R.string.storage_size_large_alternate), new CharSequence[]{formatBytes.value, formatBytes.units});
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mSummary = (StorageSummaryDonutPreference) preferenceScreen.findPreference("pref_summary");
        this.mSummary.setEnabled(true);
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        StorageSummaryDonutPreference storageSummaryDonutPreference = (StorageSummaryDonutPreference) preference;
        storageSummaryDonutPreference.setTitle(convertUsedBytesToFormattedText(this.mContext, this.mUsedBytes));
        Context context = this.mContext;
        storageSummaryDonutPreference.setSummary((CharSequence) context.getString(C1715R.string.storage_volume_total, new Object[]{Formatter.formatShortFileSize(context, this.mTotalBytes)}));
        storageSummaryDonutPreference.setPercent(this.mUsedBytes, this.mTotalBytes);
        storageSummaryDonutPreference.setEnabled(true);
    }

    public void invalidateData() {
        StorageSummaryDonutPreference storageSummaryDonutPreference = this.mSummary;
        if (storageSummaryDonutPreference != null) {
            updateState(storageSummaryDonutPreference);
        }
    }

    public void updateBytes(long j, long j2) {
        this.mUsedBytes = j;
        this.mTotalBytes = j2;
        invalidateData();
    }
}
