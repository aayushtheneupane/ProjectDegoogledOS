package com.android.settings.fuelgauge.batterytip.tips;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.havoc.config.center.C1715R;

public class EarlyWarningTip extends BatteryTip {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public BatteryTip createFromParcel(Parcel parcel) {
            return new EarlyWarningTip(parcel);
        }

        public BatteryTip[] newArray(int i) {
            return new EarlyWarningTip[i];
        }
    };
    private boolean mPowerSaveModeOn;

    public EarlyWarningTip(int i, boolean z) {
        super(3, i, false);
        this.mPowerSaveModeOn = z;
    }

    public EarlyWarningTip(Parcel parcel) {
        super(parcel);
        this.mPowerSaveModeOn = parcel.readBoolean();
    }

    public CharSequence getTitle(Context context) {
        return context.getString(this.mState == 1 ? C1715R.string.battery_tip_early_heads_up_done_title : C1715R.string.battery_tip_early_heads_up_title);
    }

    public CharSequence getSummary(Context context) {
        return context.getString(this.mState == 1 ? C1715R.string.battery_tip_early_heads_up_done_summary : C1715R.string.battery_tip_early_heads_up_summary);
    }

    public int getIconId() {
        return this.mState == 1 ? C1715R.C1717drawable.ic_battery_status_maybe_24dp : C1715R.C1717drawable.ic_battery_status_bad_24dp;
    }

    public int getIconTintColorId() {
        return this.mState == 1 ? C1715R.C1716color.battery_maybe_color_light : C1715R.C1716color.battery_bad_color_light;
    }

    public void updateState(BatteryTip batteryTip) {
        EarlyWarningTip earlyWarningTip = (EarlyWarningTip) batteryTip;
        int i = earlyWarningTip.mState;
        if (i == 0) {
            this.mState = 0;
        } else {
            if (this.mState == 0) {
                int i2 = 2;
                if (i == 2) {
                    if (earlyWarningTip.mPowerSaveModeOn) {
                        i2 = 1;
                    }
                    this.mState = i2;
                }
            }
            this.mState = earlyWarningTip.getState();
        }
        this.mPowerSaveModeOn = earlyWarningTip.mPowerSaveModeOn;
    }

    public void log(Context context, MetricsFeatureProvider metricsFeatureProvider) {
        metricsFeatureProvider.action(context, 1351, this.mState);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeBoolean(this.mPowerSaveModeOn);
    }
}
