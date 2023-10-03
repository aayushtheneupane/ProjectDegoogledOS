package com.android.settings.fuelgauge.batterytip.tips;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.havoc.config.center.C1715R;

public class LowBatteryTip extends EarlyWarningTip {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public BatteryTip createFromParcel(Parcel parcel) {
            return new LowBatteryTip(parcel);
        }

        public BatteryTip[] newArray(int i) {
            return new LowBatteryTip[i];
        }
    };
    private CharSequence mSummary;

    public LowBatteryTip(int i, boolean z, CharSequence charSequence) {
        super(i, z);
        this.mType = 5;
        this.mSummary = charSequence;
    }

    public LowBatteryTip(Parcel parcel) {
        super(parcel);
        this.mSummary = parcel.readCharSequence();
    }

    public CharSequence getSummary(Context context) {
        if (this.mState == 1) {
            return context.getString(C1715R.string.battery_tip_early_heads_up_done_summary);
        }
        return this.mSummary;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeCharSequence(this.mSummary);
    }

    public void log(Context context, MetricsFeatureProvider metricsFeatureProvider) {
        metricsFeatureProvider.action(context, 1352, this.mState);
    }
}
