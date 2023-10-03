package com.android.settings.display;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;

public class RefreshRatePreferenceController extends AbstractPreferenceController implements Preference.OnPreferenceChangeListener {
    private int mDefaultRefreshRate;
    private ListPreference mRefreshRate;

    public String getPreferenceKey() {
        return "refresh_rate_setting";
    }

    public RefreshRatePreferenceController(Context context) {
        super(context);
    }

    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(17891485);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        if (!isAvailable()) {
            setVisible(preferenceScreen, "refresh_rate_setting", false);
            return;
        }
        this.mDefaultRefreshRate = this.mContext.getResources().getInteger(17694786);
        this.mRefreshRate = (ListPreference) preferenceScreen.findPreference("refresh_rate_setting");
        int i = Settings.System.getInt(this.mContext.getContentResolver(), "refresh_rate_setting", this.mDefaultRefreshRate);
        this.mRefreshRate.setValue(String.valueOf(i));
        this.mRefreshRate.setOnPreferenceChangeListener(this);
        updateRefreshRate(i);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        int intValue = Integer.valueOf((String) obj).intValue();
        Settings.System.putInt(this.mContext.getContentResolver(), "refresh_rate_setting", intValue);
        updateRefreshRate(intValue);
        return true;
    }

    public void updateRefreshRate(int i) {
        if (i == 1) {
            Settings.System.putInt(this.mContext.getContentResolver(), "peak_refresh_rate", 60);
            Settings.System.putInt(this.mContext.getContentResolver(), "min_refresh_rate", 60);
        } else if (i != 2) {
            Settings.System.putInt(this.mContext.getContentResolver(), "peak_refresh_rate", 90);
            Settings.System.putInt(this.mContext.getContentResolver(), "min_refresh_rate", 60);
        } else {
            Settings.System.putInt(this.mContext.getContentResolver(), "peak_refresh_rate", 90);
            Settings.System.putInt(this.mContext.getContentResolver(), "min_refresh_rate", 90);
        }
        updateRefreshRateSummary(i);
    }

    public void updateRefreshRateSummary(int i) {
        if (i == 1) {
            this.mRefreshRate.setSummary((int) C1715R.string.refresh_rate_summary_60);
        } else if (i == 2) {
            this.mRefreshRate.setSummary((int) C1715R.string.refresh_rate_summary_90);
        } else {
            this.mRefreshRate.setSummary((int) C1715R.string.refresh_rate_summary_auto);
        }
    }
}
