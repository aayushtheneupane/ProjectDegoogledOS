package com.android.settings.network.telephony;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkTemplate;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.settings.datausage.DataUsageUtils;
import com.android.settingslib.net.DataUsageController;
import com.havoc.config.center.C1715R;

public class DataUsagePreferenceController extends TelephonyBasePreferenceController {
    private DataUsageController.DataUsageInfo mDataUsageInfo;
    private Intent mIntent;
    private NetworkTemplate mTemplate;

    public int getAvailabilityStatus(int i) {
        return i != -1 ? 0 : 1;
    }

    public DataUsagePreferenceController(Context context, String str) {
        super(context, str);
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return false;
        }
        this.mContext.startActivity(this.mIntent);
        return true;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        if (this.mSubId == -1) {
            preference.setEnabled(false);
            return;
        }
        long j = this.mDataUsageInfo.usageLevel;
        if (j <= 0) {
            j = new DataUsageController(this.mContext).getHistoricalUsageLevel(this.mTemplate);
        }
        boolean z = j > 0;
        preference.setEnabled(z);
        if (z) {
            Context context = this.mContext;
            preference.setSummary((CharSequence) context.getString(C1715R.string.data_usage_template, new Object[]{DataUsageUtils.formatDataUsage(context, this.mDataUsageInfo.usageLevel), this.mDataUsageInfo.period}));
        }
    }

    public void init(int i) {
        DataUsageController.DataUsageInfo dataUsageInfo;
        this.mSubId = i;
        int i2 = this.mSubId;
        if (i2 != -1) {
            this.mTemplate = DataUsageUtils.getDefaultTemplate(this.mContext, i2);
            boolean z = true;
            if (Settings.System.getInt(this.mContext.getContentResolver(), "data_usage_period", 1) != 0) {
                z = false;
            }
            DataUsageController dataUsageController = new DataUsageController(this.mContext);
            dataUsageController.setSubscriptionId(this.mSubId);
            if (z) {
                dataUsageInfo = dataUsageController.getDailyDataUsageInfo(this.mTemplate);
            } else {
                dataUsageInfo = dataUsageController.getDataUsageInfo(this.mTemplate);
            }
            this.mDataUsageInfo = dataUsageInfo;
            this.mIntent = new Intent("android.settings.MOBILE_DATA_USAGE");
            this.mIntent.putExtra("network_template", this.mTemplate);
            this.mIntent.putExtra("android.provider.extra.SUB_ID", this.mSubId);
        }
    }
}
