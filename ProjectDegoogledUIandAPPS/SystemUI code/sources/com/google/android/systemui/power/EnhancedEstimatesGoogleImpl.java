package com.google.android.systemui.power;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Settings;
import android.util.KeyValueListParser;
import android.util.Log;
import com.android.settingslib.fuelgauge.Estimate;
import com.android.settingslib.utils.PowerUtil;
import com.android.systemui.power.EnhancedEstimates;
import java.time.Duration;

public class EnhancedEstimatesGoogleImpl implements EnhancedEstimates {
    private Context mContext;
    private final KeyValueListParser mParser = new KeyValueListParser(',');

    public EnhancedEstimatesGoogleImpl(Context context) {
        this.mContext = context;
    }

    public boolean isHybridNotificationEnabled() {
        try {
            if (!this.mContext.getPackageManager().getPackageInfo("com.google.android.apps.turbo", 512).applicationInfo.enabled) {
                return false;
            }
            updateFlags();
            return this.mParser.getBoolean("hybrid_enabled", true);
        } catch (Exception unused) {
            return false;
        }
    }

    public Estimate getEstimate() {
        try {
            Cursor query = this.mContext.getContentResolver().query(new Uri.Builder().scheme("content").authority("com.google.android.apps.turbo.estimated_time_remaining").appendPath("time_remaining").appendPath("id").build(), (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null && query.moveToFirst()) {
                boolean z = true;
                if (query.getColumnIndex("is_based_on_usage") != -1 && query.getInt(query.getColumnIndex("is_based_on_usage")) == 0) {
                    z = false;
                }
                boolean z2 = z;
                int columnIndex = query.getColumnIndex("average_battery_life");
                long j = -1;
                if (columnIndex != -1) {
                    long j2 = query.getLong(columnIndex);
                    if (j2 != -1) {
                        long millis = Duration.ofMinutes(15).toMillis();
                        if (Duration.ofMillis(j2).compareTo(Duration.ofDays(1)) >= 0) {
                            millis = Duration.ofHours(1).toMillis();
                        }
                        j = PowerUtil.roundTimeToNearestThreshold(j2, millis);
                    }
                }
                Estimate estimate = new Estimate(query.getLong(query.getColumnIndex("battery_estimate")), z2, j);
                if (query != null) {
                    query.close();
                }
                return estimate;
            } else if (query == null) {
                return null;
            } else {
                query.close();
                return null;
            }
        } catch (Exception e) {
            Log.d("EnhancedEstimates", "Something went wrong when getting an estimate from Turbo", e);
            return null;
        }
    }

    public long getLowWarningThreshold() {
        updateFlags();
        return this.mParser.getLong("low_threshold", Duration.ofHours(3).toMillis());
    }

    public long getSevereWarningThreshold() {
        updateFlags();
        return this.mParser.getLong("severe_threshold", Duration.ofHours(1).toMillis());
    }

    public boolean getLowWarningEnabled() {
        updateFlags();
        return this.mParser.getBoolean("low_warning_enabled", false);
    }

    /* access modifiers changed from: protected */
    public void updateFlags() {
        try {
            this.mParser.setString(Settings.Global.getString(this.mContext.getContentResolver(), "hybrid_sysui_battery_warning_flags"));
        } catch (IllegalArgumentException unused) {
            Log.e("EnhancedEstimates", "Bad hybrid sysui warning flags");
        }
    }
}
