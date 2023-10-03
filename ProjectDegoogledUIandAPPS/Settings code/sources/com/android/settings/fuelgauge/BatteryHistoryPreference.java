package com.android.settings.fuelgauge;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.internal.os.BatteryStatsHelper;
import com.android.settings.fuelgauge.BatteryInfo;
import com.android.settings.widget.UsageView;
import com.havoc.config.center.C1715R;

public class BatteryHistoryPreference extends Preference {
    boolean hideSummary;
    BatteryInfo mBatteryInfo;
    private CharSequence mSummary;
    private TextView mSummaryView;

    public BatteryHistoryPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(C1715R.layout.battery_usage_graph);
        setSelectable(false);
    }

    public void setStats(BatteryStatsHelper batteryStatsHelper) {
        BatteryInfo.getBatteryInfo(getContext(), new BatteryInfo.Callback() {
            public final void onBatteryInfoLoaded(BatteryInfo batteryInfo) {
                BatteryHistoryPreference.this.lambda$setStats$0$BatteryHistoryPreference(batteryInfo);
            }
        }, batteryStatsHelper, false);
    }

    public /* synthetic */ void lambda$setStats$0$BatteryHistoryPreference(BatteryInfo batteryInfo) {
        this.mBatteryInfo = batteryInfo;
        notifyChanged();
    }

    public void setBottomSummary(CharSequence charSequence) {
        this.mSummary = charSequence;
        TextView textView = this.mSummaryView;
        if (textView != null) {
            textView.setVisibility(0);
            this.mSummaryView.setText(this.mSummary);
        }
        this.hideSummary = false;
    }

    public void hideBottomSummary() {
        TextView textView = this.mSummaryView;
        if (textView != null) {
            textView.setVisibility(8);
        }
        this.hideSummary = true;
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mBatteryInfo != null) {
            ((TextView) preferenceViewHolder.findViewById(C1715R.C1718id.charge)).setText(this.mBatteryInfo.batteryPercentString);
            this.mSummaryView = (TextView) preferenceViewHolder.findViewById(C1715R.C1718id.bottom_summary);
            CharSequence charSequence = this.mSummary;
            if (charSequence != null) {
                this.mSummaryView.setText(charSequence);
            }
            if (this.hideSummary) {
                this.mSummaryView.setVisibility(8);
            }
            UsageView usageView = (UsageView) preferenceViewHolder.findViewById(C1715R.C1718id.battery_usage);
            usageView.findViewById(C1715R.C1718id.label_group).setAlpha(0.7f);
            this.mBatteryInfo.bindHistory(usageView, new BatteryInfo.BatteryDataParser[0]);
            BatteryUtils.logRuntime("BatteryHistoryPreference", "onBindViewHolder", currentTimeMillis);
        }
    }
}
