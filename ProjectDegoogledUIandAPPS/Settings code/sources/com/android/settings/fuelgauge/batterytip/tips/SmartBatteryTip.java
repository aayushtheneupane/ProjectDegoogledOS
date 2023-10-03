package com.android.settings.fuelgauge.batterytip.tips;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.havoc.config.center.C1715R;

public class SmartBatteryTip extends BatteryTip {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public BatteryTip createFromParcel(Parcel parcel) {
            return new SmartBatteryTip(parcel);
        }

        public BatteryTip[] newArray(int i) {
            return new SmartBatteryTip[i];
        }
    };

    public int getIconId() {
        return C1715R.C1717drawable.ic_perm_device_information_red_24dp;
    }

    public SmartBatteryTip(int i) {
        super(0, i, false);
    }

    private SmartBatteryTip(Parcel parcel) {
        super(parcel);
    }

    public CharSequence getTitle(Context context) {
        return context.getString(C1715R.string.battery_tip_smart_battery_title);
    }

    public CharSequence getSummary(Context context) {
        return context.getString(C1715R.string.battery_tip_smart_battery_summary_custom);
    }

    public void updateState(BatteryTip batteryTip) {
        this.mState = batteryTip.mState;
    }

    public void log(Context context, MetricsFeatureProvider metricsFeatureProvider) {
        metricsFeatureProvider.action(context, 1350, this.mState);
    }
}
