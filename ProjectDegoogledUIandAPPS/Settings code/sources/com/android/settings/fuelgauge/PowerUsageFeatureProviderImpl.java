package com.android.settings.fuelgauge;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.SparseIntArray;
import com.android.internal.os.BatterySipper;
import com.android.internal.util.ArrayUtils;
import com.android.settingslib.fuelgauge.Estimate;

public class PowerUsageFeatureProviderImpl implements PowerUsageFeatureProvider {
    private static final String[] PACKAGES_SYSTEM = {PACKAGE_MEDIA_PROVIDER, PACKAGE_CALENDAR_PROVIDER, PACKAGE_SYSTEMUI};
    private static final String PACKAGE_CALENDAR_PROVIDER = "com.android.providers.calendar";
    private static final String PACKAGE_MEDIA_PROVIDER = "com.android.providers.media";
    private static final String PACKAGE_SYSTEMUI = "com.android.systemui";
    protected Context mContext;
    protected PackageManager mPackageManager;

    public Intent getAdditionalBatteryInfoIntent() {
        return null;
    }

    public String getAdvancedUsageScreenInfoString() {
        return null;
    }

    public boolean getEarlyWarningSignal(Context context, String str) {
        return false;
    }

    public Estimate getEnhancedBatteryPrediction(Context context) {
        return null;
    }

    public SparseIntArray getEnhancedBatteryPredictionCurve(Context context, long j) {
        return null;
    }

    public String getEnhancedEstimateDebugString(String str) {
        return null;
    }

    public String getOldEstimateDebugString(String str) {
        return null;
    }

    public boolean isAdditionalBatteryInfoEnabled() {
        return false;
    }

    public boolean isAdvancedUiEnabled() {
        return true;
    }

    public boolean isEnhancedBatteryPredictionEnabled(Context context) {
        return false;
    }

    public boolean isEstimateDebugEnabled() {
        return false;
    }

    public boolean isLocationSettingEnabled(String[] strArr) {
        return false;
    }

    public boolean isPowerAccountingToggleEnabled() {
        return true;
    }

    public boolean isTypeService(BatterySipper batterySipper) {
        return false;
    }

    public PowerUsageFeatureProviderImpl(Context context) {
        this.mPackageManager = context.getPackageManager();
        this.mContext = context.getApplicationContext();
    }

    public boolean isTypeSystem(BatterySipper batterySipper) {
        int uid = batterySipper.uidObj == null ? -1 : batterySipper.getUid();
        batterySipper.mPackages = this.mPackageManager.getPackagesForUid(uid);
        if (uid >= 0 && uid < 10000) {
            return true;
        }
        String[] strArr = batterySipper.mPackages;
        if (strArr != null) {
            for (String contains : strArr) {
                if (ArrayUtils.contains(PACKAGES_SYSTEM, contains)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSmartBatterySupported() {
        return this.mContext.getResources().getBoolean(17891552);
    }
}
