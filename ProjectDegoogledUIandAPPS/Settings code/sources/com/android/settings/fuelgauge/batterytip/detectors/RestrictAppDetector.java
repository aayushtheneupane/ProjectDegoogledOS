package com.android.settings.fuelgauge.batterytip.detectors;

import android.content.Context;
import com.android.settings.fuelgauge.batterytip.AppInfo;
import com.android.settings.fuelgauge.batterytip.BatteryDatabaseManager;
import com.android.settings.fuelgauge.batterytip.BatteryTipPolicy;
import com.android.settings.fuelgauge.batterytip.BatteryTipUtils;
import com.android.settings.fuelgauge.batterytip.tips.AppLabelPredicate;
import com.android.settings.fuelgauge.batterytip.tips.AppRestrictionPredicate;
import com.android.settings.fuelgauge.batterytip.tips.BatteryTip;
import com.android.settings.fuelgauge.batterytip.tips.RestrictAppTip;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RestrictAppDetector implements BatteryTipDetector {
    static final boolean USE_FAKE_DATA = false;
    private AppLabelPredicate mAppLabelPredicate;
    private AppRestrictionPredicate mAppRestrictionPredicate;
    BatteryDatabaseManager mBatteryDatabaseManager;
    private Context mContext;
    private BatteryTipPolicy mPolicy;

    public RestrictAppDetector(Context context, BatteryTipPolicy batteryTipPolicy) {
        this.mContext = context;
        this.mPolicy = batteryTipPolicy;
        this.mBatteryDatabaseManager = BatteryDatabaseManager.getInstance(context);
        this.mAppRestrictionPredicate = AppRestrictionPredicate.getInstance(context);
        this.mAppLabelPredicate = AppLabelPredicate.getInstance(context);
    }

    public BatteryTip detect() {
        int i = 2;
        if (!this.mPolicy.appRestrictionEnabled) {
            return new RestrictAppTip(2, (List<AppInfo>) new ArrayList());
        }
        long currentTimeMillis = System.currentTimeMillis() - TimeUnit.HOURS.toMillis((long) this.mPolicy.appRestrictionActiveHour);
        List<AppInfo> detectAnomalies = BatteryTipUtils.detectAnomalies(this.mContext, currentTimeMillis);
        if (!detectAnomalies.isEmpty()) {
            return new RestrictAppTip(0, detectAnomalies);
        }
        List<AppInfo> queryAllAnomalies = this.mBatteryDatabaseManager.queryAllAnomalies(currentTimeMillis, 2);
        queryAllAnomalies.removeIf(this.mAppLabelPredicate.or(this.mAppRestrictionPredicate.negate()));
        if (!queryAllAnomalies.isEmpty()) {
            i = 1;
        }
        return new RestrictAppTip(i, queryAllAnomalies);
    }
}
