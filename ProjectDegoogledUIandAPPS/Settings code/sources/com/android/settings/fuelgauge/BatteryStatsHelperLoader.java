package com.android.settings.fuelgauge;

import android.content.Context;
import android.os.Bundle;
import android.os.UserManager;
import com.android.internal.os.BatteryStatsHelper;
import com.android.settingslib.utils.AsyncLoaderCompat;

public class BatteryStatsHelperLoader extends AsyncLoaderCompat<BatteryStatsHelper> {
    BatteryUtils mBatteryUtils;
    UserManager mUserManager;

    /* access modifiers changed from: protected */
    public void onDiscardResult(BatteryStatsHelper batteryStatsHelper) {
    }

    public BatteryStatsHelperLoader(Context context) {
        super(context);
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mBatteryUtils = BatteryUtils.getInstance(context);
    }

    public BatteryStatsHelper loadInBackground() {
        BatteryStatsHelper batteryStatsHelper = new BatteryStatsHelper(getContext(), true);
        this.mBatteryUtils.initBatteryStatsHelper(batteryStatsHelper, (Bundle) null, this.mUserManager);
        return batteryStatsHelper;
    }
}
