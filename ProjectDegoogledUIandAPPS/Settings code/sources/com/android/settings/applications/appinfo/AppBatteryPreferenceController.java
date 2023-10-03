package com.android.settings.applications.appinfo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.internal.os.BatterySipper;
import com.android.internal.os.BatteryStatsHelper;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.fuelgauge.AdvancedPowerUsageDetail;
import com.android.settings.fuelgauge.BatteryEntry;
import com.android.settings.fuelgauge.BatteryStatsHelperLoader;
import com.android.settings.fuelgauge.BatteryUtils;
import com.android.settingslib.Utils;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class AppBatteryPreferenceController extends BasePreferenceController implements LoaderManager.LoaderCallbacks<BatteryStatsHelper>, LifecycleObserver, OnResume, OnPause {
    private static final String KEY_BATTERY = "battery";
    BatteryStatsHelper mBatteryHelper;
    private String mBatteryPercent;
    BatteryUtils mBatteryUtils = BatteryUtils.getInstance(this.mContext);
    private final String mPackageName;
    private final AppInfoDashboardFragment mParent;
    private Preference mPreference;
    BatterySipper mSipper;

    public void onLoaderReset(Loader<BatteryStatsHelper> loader) {
    }

    public AppBatteryPreferenceController(Context context, AppInfoDashboardFragment appInfoDashboardFragment, String str, Lifecycle lifecycle) {
        super(context, KEY_BATTERY);
        this.mParent = appInfoDashboardFragment;
        this.mPackageName = str;
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_app_info_settings_battery) ? 0 : 2;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference.setEnabled(false);
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!KEY_BATTERY.equals(preference.getKey())) {
            return false;
        }
        if (isBatteryStatsAvailable()) {
            BatteryEntry batteryEntry = new BatteryEntry(this.mContext, (Handler) null, (UserManager) this.mContext.getSystemService("user"), this.mSipper);
            batteryEntry.defaultPackageName = this.mPackageName;
            AdvancedPowerUsageDetail.startBatteryDetailPage(this.mParent.getActivity(), this.mParent, this.mBatteryHelper, 0, batteryEntry, this.mBatteryPercent);
            return true;
        }
        AdvancedPowerUsageDetail.startBatteryDetailPage(this.mParent.getActivity(), this.mParent, this.mPackageName);
        return true;
    }

    public void onResume() {
        this.mParent.getLoaderManager().restartLoader(4, Bundle.EMPTY, this);
    }

    public void onPause() {
        this.mParent.getLoaderManager().destroyLoader(4);
    }

    public Loader<BatteryStatsHelper> onCreateLoader(int i, Bundle bundle) {
        return new BatteryStatsHelperLoader(this.mContext);
    }

    public void onLoadFinished(Loader<BatteryStatsHelper> loader, BatteryStatsHelper batteryStatsHelper) {
        this.mBatteryHelper = batteryStatsHelper;
        PackageInfo packageInfo = this.mParent.getPackageInfo();
        if (packageInfo != null) {
            this.mSipper = findTargetSipper(batteryStatsHelper, packageInfo.applicationInfo.uid);
            if (this.mParent.getActivity() != null) {
                updateBattery();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void updateBattery() {
        this.mPreference.setEnabled(true);
        if (isBatteryStatsAvailable()) {
            int dischargeAmount = this.mBatteryHelper.getStats().getDischargeAmount(0);
            this.mBatteryPercent = Utils.formatPercentage((int) this.mBatteryUtils.calculateBatteryPercent(this.mSipper.totalPowerMah, this.mBatteryHelper.getTotalPower(), this.mBatteryUtils.removeHiddenBatterySippers(new ArrayList(this.mBatteryHelper.getUsageList())), dischargeAmount));
            this.mPreference.setSummary((CharSequence) this.mContext.getString(C1715R.string.battery_summary, new Object[]{this.mBatteryPercent}));
            return;
        }
        this.mPreference.setSummary((CharSequence) this.mContext.getString(C1715R.string.no_battery_summary));
    }

    /* access modifiers changed from: package-private */
    public boolean isBatteryStatsAvailable() {
        return (this.mBatteryHelper == null || this.mSipper == null) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public BatterySipper findTargetSipper(BatteryStatsHelper batteryStatsHelper, int i) {
        List usageList = batteryStatsHelper.getUsageList();
        int size = usageList.size();
        for (int i2 = 0; i2 < size; i2++) {
            BatterySipper batterySipper = (BatterySipper) usageList.get(i2);
            if (batterySipper.getUid() == i) {
                return batterySipper;
            }
        }
        return null;
    }
}
