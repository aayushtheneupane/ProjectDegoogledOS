package com.android.settings.fuelgauge.batterytip;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.UserManager;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.fuelgauge.PowerUsageFeatureProvider;
import com.android.settings.overlay.FeatureFactory;
import com.havoc.config.center.C1715R;

public class BatteryManagerPreferenceController extends BasePreferenceController {
    private static final String KEY_BATTERY_MANAGER = "smart_battery_manager";

    /* renamed from: ON */
    private static final int f31ON = 1;
    private AppOpsManager mAppOpsManager;
    private PowerUsageFeatureProvider mPowerUsageFeatureProvider;
    private UserManager mUserManager;

    public int getAvailabilityStatus() {
        return 1;
    }

    public BatteryManagerPreferenceController(Context context) {
        super(context, KEY_BATTERY_MANAGER);
        this.mPowerUsageFeatureProvider = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context);
        this.mAppOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        this.mUserManager = (UserManager) context.getSystemService(UserManager.class);
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        int size = BatteryTipUtils.getRestrictedAppsList(this.mAppOpsManager, this.mUserManager).size();
        boolean z = true;
        if (Settings.Global.getInt(this.mContext.getContentResolver(), this.mPowerUsageFeatureProvider.isSmartBatterySupported() ? "adaptive_battery_management_enabled" : "app_auto_restriction_enabled", 1) != 1) {
            z = false;
        }
        updateSummary(preference, z, size);
    }

    /* access modifiers changed from: package-private */
    public void updateSummary(Preference preference, boolean z, int i) {
        if (i > 0) {
            preference.setSummary((CharSequence) this.mContext.getResources().getQuantityString(C1715R.plurals.battery_manager_app_restricted, i, new Object[]{Integer.valueOf(i)}));
        } else if (z) {
            preference.setSummary((int) C1715R.string.battery_manager_on);
        } else {
            preference.setSummary((int) C1715R.string.battery_manager_off);
        }
    }
}
