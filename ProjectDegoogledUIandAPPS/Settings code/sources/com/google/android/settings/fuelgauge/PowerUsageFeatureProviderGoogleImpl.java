package com.google.android.settings.fuelgauge;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseIntArray;
import com.android.internal.os.BatterySipper;
import com.android.internal.util.ArrayUtils;
import com.android.settings.fuelgauge.PowerUsageFeatureProviderImpl;
import com.havoc.config.center.C1715R;

public class PowerUsageFeatureProviderGoogleImpl extends PowerUsageFeatureProviderImpl {
    static final String AVERAGE_BATTERY_LIFE_COL = "average_battery_life";
    static final String BATTERY_ESTIMATE_BASED_ON_USAGE_COL = "is_based_on_usage";
    static final String BATTERY_ESTIMATE_COL = "battery_estimate";
    static final String BATTERY_LEVEL_COL = "battery_level";
    static final int CUSTOMIZED_TO_USER = 1;
    static final String GFLAG_ADDITIONAL_BATTERY_INFO_ENABLED = "settingsgoogle:additional_battery_info_enabled";
    static final String GFLAG_BATTERY_ADVANCED_UI_ENABLED = "settingsgoogle:battery_advanced_ui_enabled";
    static final String GFLAG_POWER_ACCOUNTING_TOGGLE_ENABLED = "settingsgoogle:power_accounting_toggle_enabled";
    static final String IS_EARLY_WARNING_COL = "is_early_warning";
    static final int NEED_EARLY_WARNING = 1;
    private static final String[] PACKAGES_SERVICE = {"com.google.android.gms", "com.google.android.apps.gcs"};
    static final String TIMESTAMP_COL = "timestamp_millis";

    public PowerUsageFeatureProviderGoogleImpl(Context context) {
        super(context);
    }

    public boolean isTypeService(BatterySipper batterySipper) {
        String[] packagesForUid = this.mPackageManager.getPackagesForUid(batterySipper.getUid());
        if (packagesForUid == null) {
            return false;
        }
        for (String contains : packagesForUid) {
            if (ArrayUtils.contains(PACKAGES_SERVICE, contains)) {
                return true;
            }
        }
        return false;
    }

    public void setPackageManager(PackageManager packageManager) {
        this.mPackageManager = packageManager;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0077  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.settingslib.fuelgauge.Estimate getEnhancedBatteryPrediction(android.content.Context r9) {
        /*
            r8 = this;
            android.content.ContentResolver r0 = r9.getContentResolver()
            android.net.Uri r1 = r8.getEnhancedBatteryPredictionUri()
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r8 = r0.query(r1, r2, r3, r4, r5)
            if (r8 == 0) goto L_0x007b
            boolean r9 = r8.moveToFirst()
            if (r9 == 0) goto L_0x007b
            java.lang.String r9 = "is_based_on_usage"
            int r9 = r8.getColumnIndex(r9)
            r0 = -1
            r1 = 1
            if (r9 == r0) goto L_0x0029
            int r9 = r8.getInt(r9)
            if (r1 == r9) goto L_0x0029
            r1 = 0
        L_0x0029:
            r5 = r1
            java.lang.String r9 = "average_battery_life"
            int r9 = r8.getColumnIndex(r9)
            r1 = -1
            if (r9 == r0) goto L_0x0064
            long r3 = r8.getLong(r9)
            int r9 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r9 == 0) goto L_0x0064
            r0 = 15
            java.time.Duration r9 = java.time.Duration.ofMinutes(r0)
            long r0 = r9.toMillis()
            java.time.Duration r9 = java.time.Duration.ofMillis(r3)
            r6 = 1
            java.time.Duration r2 = java.time.Duration.ofDays(r6)
            int r9 = r9.compareTo(r2)
            if (r9 < 0) goto L_0x005e
            java.time.Duration r9 = java.time.Duration.ofHours(r6)
            long r0 = r9.toMillis()
        L_0x005e:
            long r0 = com.android.settingslib.utils.PowerUtil.roundTimeToNearestThreshold(r3, r0)
            r6 = r0
            goto L_0x0065
        L_0x0064:
            r6 = r1
        L_0x0065:
            com.android.settingslib.fuelgauge.Estimate r9 = new com.android.settingslib.fuelgauge.Estimate
            java.lang.String r0 = "battery_estimate"
            int r0 = r8.getColumnIndex(r0)
            long r3 = r8.getLong(r0)
            r2 = r9
            r2.<init>(r3, r5, r6)
            if (r8 == 0) goto L_0x007a
            r8.close()
        L_0x007a:
            return r9
        L_0x007b:
            if (r8 == 0) goto L_0x0080
            r8.close()
        L_0x0080:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.settings.fuelgauge.PowerUsageFeatureProviderGoogleImpl.getEnhancedBatteryPrediction(android.content.Context):com.android.settingslib.fuelgauge.Estimate");
    }

    public SparseIntArray getEnhancedBatteryPredictionCurve(Context context, long j) {
        Cursor query = context.getContentResolver().query(getEnhancedBatteryPredictionCurveUri(), (String[]) null, (String) null, (String[]) null, (String) null);
        if (query == null) {
            if (query != null) {
                try {
                    query.close();
                } catch (NullPointerException unused) {
                    return null;
                }
            }
            return null;
        }
        int columnIndex = query.getColumnIndex(TIMESTAMP_COL);
        int columnIndex2 = query.getColumnIndex(BATTERY_LEVEL_COL);
        SparseIntArray sparseIntArray = new SparseIntArray(query.getCount());
        while (query.moveToNext()) {
            sparseIntArray.append((int) (query.getLong(columnIndex) - j), query.getInt(columnIndex2));
        }
        if (query != null) {
            query.close();
        }
        return sparseIntArray;
    }

    public boolean isEnhancedBatteryPredictionEnabled(Context context) {
        try {
            return this.mPackageManager.getPackageInfo("com.google.android.apps.turbo", 512).applicationInfo.enabled;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private Uri getEnhancedBatteryPredictionUri() {
        return new Uri.Builder().scheme("content").authority("com.google.android.apps.turbo.estimated_time_remaining").appendPath("time_remaining").build();
    }

    private Uri getEnhancedBatteryPredictionCurveUri() {
        return new Uri.Builder().scheme("content").authority("com.google.android.apps.turbo.estimated_time_remaining").appendPath("discharge_curve").build();
    }

    public String getEnhancedEstimateDebugString(String str) {
        return this.mContext.getString(C1715R.string.power_usage_enhanced_debug, new Object[]{str});
    }

    public String getOldEstimateDebugString(String str) {
        return this.mContext.getString(C1715R.string.power_usage_old_debug, new Object[]{str});
    }

    public String getAdvancedUsageScreenInfoString() {
        return this.mContext.getString(C1715R.string.advanced_battery_graph_subtext);
    }

    public boolean getEarlyWarningSignal(Context context, String str) {
        Uri.Builder appendPath = new Uri.Builder().scheme("content").authority("com.google.android.apps.turbo.estimated_time_remaining").appendPath("early_warning").appendPath("id");
        if (TextUtils.isEmpty(str)) {
            appendPath.appendPath(context.getPackageName());
        } else {
            appendPath.appendPath(str);
        }
        Cursor query = context.getContentResolver().query(appendPath.build(), (String[]) null, (String) null, (String[]) null, (String) null);
        boolean z = false;
        if (query == null || !query.moveToFirst()) {
            if (query != null) {
                query.close();
            }
            return false;
        }
        if (1 == query.getInt(query.getColumnIndex(IS_EARLY_WARNING_COL))) {
            z = true;
        }
        if (query != null) {
            query.close();
        }
        return z;
    }
}
