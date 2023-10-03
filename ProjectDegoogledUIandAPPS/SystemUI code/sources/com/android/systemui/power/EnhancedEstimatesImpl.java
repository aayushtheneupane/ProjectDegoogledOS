package com.android.systemui.power;

import android.content.Context;
import android.os.BatteryStats;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.UserManager;
import com.android.internal.os.BatteryStatsHelper;
import com.android.settingslib.fuelgauge.Estimate;
import com.android.settingslib.utils.PowerUtil;

public class EnhancedEstimatesImpl implements EnhancedEstimates {
    BatteryStatsHelper mBatteryStatsHelper;
    UserManager mUserManager;

    public boolean getLowWarningEnabled() {
        return true;
    }

    public long getLowWarningThreshold() {
        return 0;
    }

    public long getSevereWarningThreshold() {
        return 0;
    }

    public boolean isHybridNotificationEnabled() {
        return true;
    }

    public EnhancedEstimatesImpl(Context context) {
        this.mBatteryStatsHelper = new BatteryStatsHelper(context, true);
        this.mUserManager = (UserManager) context.getSystemService("user");
    }

    public Estimate getEstimate() {
        try {
            this.mBatteryStatsHelper.create((Bundle) null);
            this.mBatteryStatsHelper.clearStats();
            this.mBatteryStatsHelper.refreshStats(0, this.mUserManager.getUserProfiles());
            BatteryStats stats = this.mBatteryStatsHelper.getStats();
            if (stats != null) {
                long computeBatteryTimeRemaining = stats.computeBatteryTimeRemaining(PowerUtil.convertMsToUs(SystemClock.elapsedRealtime()));
                if (computeBatteryTimeRemaining != -1) {
                    return new Estimate(PowerUtil.convertUsToMs(computeBatteryTimeRemaining), false, -1);
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }
}
