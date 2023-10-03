package com.android.settings.fuelgauge.batterytip.tips;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.havoc.config.center.C1715R;

public class SummaryTip extends BatteryTip {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public BatteryTip createFromParcel(Parcel parcel) {
            return new SummaryTip(parcel);
        }

        public BatteryTip[] newArray(int i) {
            return new SummaryTip[i];
        }
    };
    private long mAverageTimeMs;

    public int getIconId() {
        return C1715R.C1717drawable.ic_battery_status_good_24dp;
    }

    public int getIconTintColorId() {
        return C1715R.C1716color.battery_good_color_light;
    }

    public SummaryTip(int i, long j) {
        super(6, i, true);
        this.mAverageTimeMs = j;
    }

    SummaryTip(Parcel parcel) {
        super(parcel);
        this.mAverageTimeMs = parcel.readLong();
    }

    public CharSequence getTitle(Context context) {
        return context.getString(C1715R.string.battery_tip_summary_title);
    }

    public CharSequence getSummary(Context context) {
        return context.getString(C1715R.string.battery_tip_summary_summary);
    }

    public void updateState(BatteryTip batteryTip) {
        this.mState = batteryTip.mState;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.mAverageTimeMs);
    }

    public void log(Context context, MetricsFeatureProvider metricsFeatureProvider) {
        metricsFeatureProvider.action(context, 1349, this.mState);
    }
}
