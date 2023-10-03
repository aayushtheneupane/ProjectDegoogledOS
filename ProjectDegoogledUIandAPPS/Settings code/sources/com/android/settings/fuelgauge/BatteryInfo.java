package com.android.settings.fuelgauge;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.BatteryStats;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.Formatter;
import android.util.SparseIntArray;
import com.android.internal.os.BatteryStatsHelper;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.widget.UsageView;
import com.android.settingslib.Utils;
import com.android.settingslib.fuelgauge.Estimate;
import com.android.settingslib.utils.PowerUtil;
import com.android.settingslib.utils.StringUtil;
import com.havoc.config.center.C1715R;

public class BatteryInfo {
    public long averageTimeToDischarge = -1;
    public int batteryLevel;
    public String batteryPercentString;
    public CharSequence chargeLabel;
    private String chargeStatusLabel;
    public boolean discharging = true;
    /* access modifiers changed from: private */
    public boolean mCharging;
    private BatteryStats mStats;
    public CharSequence remainingLabel;
    public long remainingTimeUs = 0;
    public String statusLabel;
    public String suggestionLabel;
    /* access modifiers changed from: private */
    public long timePeriod;

    public interface BatteryDataParser {
        void onDataGap();

        void onDataPoint(long j, BatteryStats.HistoryItem historyItem);

        void onParsingDone();

        void onParsingStarted(long j, long j2);
    }

    public interface Callback {
        void onBatteryInfoLoaded(BatteryInfo batteryInfo);
    }

    public void bindHistory(final UsageView usageView, BatteryDataParser... batteryDataParserArr) {
        String str;
        final Context context = usageView.getContext();
        C08291 r1 = new BatteryDataParser() {
            byte lastLevel;
            int lastTime = -1;
            SparseIntArray points = new SparseIntArray();
            long startTime;

            public void onParsingStarted(long j, long j2) {
                this.startTime = j;
                long unused = BatteryInfo.this.timePeriod = j2 - j;
                usageView.clearPaths();
                usageView.configureGraph((int) BatteryInfo.this.timePeriod, 100);
            }

            public void onDataPoint(long j, BatteryStats.HistoryItem historyItem) {
                this.lastTime = (int) j;
                this.lastLevel = historyItem.batteryLevel;
                this.points.put(this.lastTime, this.lastLevel);
            }

            public void onDataGap() {
                if (this.points.size() > 1) {
                    usageView.addPath(this.points);
                }
                this.points.clear();
            }

            public void onParsingDone() {
                onDataGap();
                if (BatteryInfo.this.remainingTimeUs != 0) {
                    PowerUsageFeatureProvider powerUsageFeatureProvider = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context);
                    if (BatteryInfo.this.mCharging || !powerUsageFeatureProvider.isEnhancedBatteryPredictionEnabled(context)) {
                        int i = this.lastTime;
                        if (i >= 0) {
                            this.points.put(i, this.lastLevel);
                            this.points.put((int) (BatteryInfo.this.timePeriod + PowerUtil.convertUsToMs(BatteryInfo.this.remainingTimeUs)), BatteryInfo.this.mCharging ? 100 : 0);
                        }
                    } else {
                        this.points = powerUsageFeatureProvider.getEnhancedBatteryPredictionCurve(context, this.startTime);
                    }
                }
                SparseIntArray sparseIntArray = this.points;
                if (sparseIntArray != null && sparseIntArray.size() > 0) {
                    SparseIntArray sparseIntArray2 = this.points;
                    usageView.configureGraph(sparseIntArray2.keyAt(sparseIntArray2.size() - 1), 100);
                    usageView.addProjectedPath(this.points);
                }
            }
        };
        BatteryDataParser[] batteryDataParserArr2 = new BatteryDataParser[(batteryDataParserArr.length + 1)];
        for (int i = 0; i < batteryDataParserArr.length; i++) {
            batteryDataParserArr2[i] = batteryDataParserArr[i];
        }
        batteryDataParserArr2[batteryDataParserArr.length] = r1;
        parse(this.mStats, batteryDataParserArr2);
        String string = context.getString(C1715R.string.charge_length_format, new Object[]{Formatter.formatShortElapsedTime(context, this.timePeriod)});
        long j = this.remainingTimeUs;
        if (j != 0) {
            str = context.getString(C1715R.string.remaining_length_format, new Object[]{Formatter.formatShortElapsedTime(context, j / 1000)});
        } else {
            str = "";
        }
        usageView.setBottomLabels(new CharSequence[]{string, str});
    }

    public static void getBatteryInfo(Context context, Callback callback, boolean z) {
        getBatteryInfo(context, callback, (BatteryStatsHelper) null, z);
    }

    public static void getBatteryInfo(final Context context, final Callback callback, final BatteryStatsHelper batteryStatsHelper, final boolean z) {
        new AsyncTask<Void, Void, BatteryInfo>() {
            /* access modifiers changed from: protected */
            public BatteryInfo doInBackground(Void... voidArr) {
                return BatteryInfo.getBatteryInfo(context, batteryStatsHelper, z);
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(BatteryInfo batteryInfo) {
                long currentTimeMillis = System.currentTimeMillis();
                callback.onBatteryInfoLoaded(batteryInfo);
                BatteryUtils.logRuntime("BatteryInfo", "time for callback", currentTimeMillis);
            }
        }.execute(new Void[0]);
    }

    public static BatteryInfo getBatteryInfo(Context context, BatteryStatsHelper batteryStatsHelper, boolean z) {
        BatteryStats batteryStats;
        Estimate enhancedBatteryPrediction;
        Context context2 = context;
        long currentTimeMillis = System.currentTimeMillis();
        boolean z2 = true;
        if (batteryStatsHelper == null) {
            BatteryStatsHelper batteryStatsHelper2 = new BatteryStatsHelper(context2, true);
            batteryStatsHelper2.create((Bundle) null);
            batteryStats = batteryStatsHelper2.getStats();
        } else {
            batteryStats = batteryStatsHelper.getStats();
        }
        BatteryUtils.logRuntime("BatteryInfo", "time for getStats", currentTimeMillis);
        long currentTimeMillis2 = System.currentTimeMillis();
        PowerUsageFeatureProvider powerUsageFeatureProvider = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context2);
        long convertMsToUs = PowerUtil.convertMsToUs(SystemClock.elapsedRealtime());
        Intent registerReceiver = context2.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver.getIntExtra("plugged", -1) != 0) {
            z2 = false;
        }
        if (!z2 || powerUsageFeatureProvider == null || !powerUsageFeatureProvider.isEnhancedBatteryPredictionEnabled(context2) || (enhancedBatteryPrediction = powerUsageFeatureProvider.getEnhancedBatteryPrediction(context2)) == null) {
            Estimate estimate = new Estimate(PowerUtil.convertUsToMs(z2 ? batteryStats.computeBatteryTimeRemaining(convertMsToUs) : 0), false, -1);
            BatteryUtils.logRuntime("BatteryInfo", "time for regular BatteryInfo", currentTimeMillis2);
            return getBatteryInfo(context, registerReceiver, batteryStats, estimate, convertMsToUs, z);
        }
        Estimate.storeCachedEstimate(context2, enhancedBatteryPrediction);
        BatteryUtils.logRuntime("BatteryInfo", "time for enhanced BatteryInfo", currentTimeMillis2);
        return getBatteryInfo(context, registerReceiver, batteryStats, enhancedBatteryPrediction, convertMsToUs, z);
    }

    public static BatteryInfo getBatteryInfoOld(Context context, Intent intent, BatteryStats batteryStats, long j, boolean z) {
        return getBatteryInfo(context, intent, batteryStats, new Estimate(PowerUtil.convertUsToMs(batteryStats.computeBatteryTimeRemaining(j)), false, -1), j, z);
    }

    public static BatteryInfo getBatteryInfo(Context context, Intent intent, BatteryStats batteryStats, Estimate estimate, long j, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        BatteryInfo batteryInfo = new BatteryInfo();
        batteryInfo.mStats = batteryStats;
        batteryInfo.batteryLevel = Utils.getBatteryLevel(intent);
        batteryInfo.batteryPercentString = Utils.formatPercentage(batteryInfo.batteryLevel);
        boolean z2 = false;
        if (intent.getIntExtra("plugged", 0) != 0) {
            z2 = true;
        }
        batteryInfo.mCharging = z2;
        batteryInfo.averageTimeToDischarge = estimate.getAverageDischargeTime();
        batteryInfo.statusLabel = Utils.getBatteryStatus(context.getResources(), intent);
        if (!batteryInfo.mCharging) {
            updateBatteryInfoDischarging(context, z, estimate, batteryInfo);
        } else {
            updateBatteryInfoCharging(context, intent, batteryStats, j, batteryInfo);
        }
        BatteryUtils.logRuntime("BatteryInfo", "time for getBatteryInfo", currentTimeMillis);
        return batteryInfo;
    }

    private static void updateBatteryInfoCharging(Context context, Intent intent, BatteryStats batteryStats, long j, BatteryInfo batteryInfo) {
        String str;
        Resources resources = context.getResources();
        long computeChargeTimeRemaining = batteryStats.computeChargeTimeRemaining(j);
        int intExtra = intent.getIntExtra("status", 1);
        boolean booleanExtra = intent.getBooleanExtra("dash_charger", false);
        boolean booleanExtra2 = intent.getBooleanExtra("warp_charger", false);
        batteryInfo.discharging = false;
        batteryInfo.suggestionLabel = null;
        batteryInfo.chargeStatusLabel = null;
        if (computeChargeTimeRemaining <= 0 || intExtra == 5) {
            if (booleanExtra) {
                batteryInfo.chargeStatusLabel = resources.getString(C1715R.string.battery_info_status_dash_charging_lower);
            } else if (booleanExtra2) {
                batteryInfo.chargeStatusLabel = resources.getString(C1715R.string.battery_info_status_warp_charging_lower);
            } else {
                batteryInfo.chargeStatusLabel = resources.getString(C1715R.string.battery_info_status_charging_lower);
            }
            batteryInfo.remainingLabel = null;
            if (batteryInfo.batteryLevel == 100) {
                str = batteryInfo.batteryPercentString;
            } else {
                str = resources.getString(C1715R.string.power_charging, new Object[]{batteryInfo.batteryPercentString, batteryInfo.chargeStatusLabel});
            }
            batteryInfo.chargeLabel = str;
            return;
        }
        batteryInfo.remainingTimeUs = computeChargeTimeRemaining;
        CharSequence formatElapsedTime = StringUtil.formatElapsedTime(context, (double) PowerUtil.convertUsToMs(batteryInfo.remainingTimeUs), false);
        if (booleanExtra) {
            batteryInfo.remainingLabel = context.getString(C1715R.string.power_remaining_dash_charging_duration_only, new Object[]{formatElapsedTime});
        } else if (booleanExtra2) {
            batteryInfo.remainingLabel = context.getString(C1715R.string.power_remaining_warp_charging_duration_only, new Object[]{formatElapsedTime});
        } else {
            batteryInfo.remainingLabel = context.getString(C1715R.string.power_remaining_charging_duration_only, new Object[]{formatElapsedTime});
        }
        batteryInfo.chargeLabel = context.getString(C1715R.string.power_charging_duration, new Object[]{batteryInfo.batteryPercentString, formatElapsedTime});
    }

    private static void updateBatteryInfoDischarging(Context context, boolean z, Estimate estimate, BatteryInfo batteryInfo) {
        long convertMsToUs = PowerUtil.convertMsToUs(estimate.getEstimateMillis());
        if (convertMsToUs > 0) {
            batteryInfo.remainingTimeUs = convertMsToUs;
            boolean z2 = true;
            batteryInfo.remainingLabel = PowerUtil.getBatteryRemainingStringFormatted(context, PowerUtil.convertUsToMs(convertMsToUs), (String) null, estimate.isBasedOnUsage() && !z);
            long convertUsToMs = PowerUtil.convertUsToMs(convertMsToUs);
            String str = batteryInfo.batteryPercentString;
            if (!estimate.isBasedOnUsage() || z) {
                z2 = false;
            }
            batteryInfo.chargeLabel = PowerUtil.getBatteryRemainingStringFormatted(context, convertUsToMs, str, z2);
            batteryInfo.suggestionLabel = PowerUtil.getBatteryTipStringFormatted(context, PowerUtil.convertUsToMs(convertMsToUs));
            return;
        }
        batteryInfo.remainingLabel = null;
        batteryInfo.suggestionLabel = null;
        batteryInfo.chargeLabel = batteryInfo.batteryPercentString;
    }

    /* JADX WARNING: Removed duplicated region for block: B:63:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x010f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void parse(android.os.BatteryStats r25, com.android.settings.fuelgauge.BatteryInfo.BatteryDataParser... r26) {
        /*
            r0 = r25
            r1 = r26
            boolean r2 = r25.startIteratingHistoryLocked()
            r3 = 7
            r4 = 5
            r6 = 0
            if (r2 == 0) goto L_0x0077
            android.os.BatteryStats$HistoryItem r2 = new android.os.BatteryStats$HistoryItem
            r2.<init>()
            r8 = 1
            r9 = r6
            r11 = r9
            r14 = r11
            r16 = r14
            r20 = r16
            r19 = r8
            r13 = 0
            r18 = 0
        L_0x0020:
            boolean r22 = r0.getNextHistoryLocked(r2)
            if (r22 == 0) goto L_0x0074
            int r18 = r18 + 1
            r23 = r9
            if (r19 == 0) goto L_0x0032
            long r8 = r2.time
            r20 = r8
            r19 = 0
        L_0x0032:
            byte r8 = r2.cmd
            if (r8 == r4) goto L_0x003c
            if (r8 != r3) goto L_0x0039
            goto L_0x003c
        L_0x0039:
            r3 = r23
            goto L_0x0063
        L_0x003c:
            long r8 = r2.currentTime
            r23 = 15552000000(0x39ef8b000, double:7.683708924E-314)
            long r16 = r16 + r23
            int r8 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1))
            if (r8 > 0) goto L_0x0054
            long r8 = r2.time
            r16 = 300000(0x493e0, double:1.482197E-318)
            long r16 = r20 + r16
            int r8 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1))
            if (r8 >= 0) goto L_0x0055
        L_0x0054:
            r11 = r6
        L_0x0055:
            long r8 = r2.currentTime
            long r3 = r2.time
            int r16 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r16 != 0) goto L_0x0061
            long r11 = r3 - r20
            long r11 = r8 - r11
        L_0x0061:
            r16 = r8
        L_0x0063:
            boolean r8 = r2.isDeltaData()
            if (r8 == 0) goto L_0x006f
            long r14 = r2.time
            r9 = r3
            r13 = r18
            goto L_0x0070
        L_0x006f:
            r9 = r3
        L_0x0070:
            r3 = 7
            r4 = 5
            r8 = 1
            goto L_0x0020
        L_0x0074:
            r23 = r9
            goto L_0x0080
        L_0x0077:
            r11 = r6
            r14 = r11
            r16 = r14
            r20 = r16
            r23 = r20
            r13 = 0
        L_0x0080:
            r25.finishIteratingHistoryLocked()
            long r16 = r16 + r14
            long r2 = r16 - r23
            r4 = 0
        L_0x0088:
            int r8 = r1.length
            if (r4 >= r8) goto L_0x0093
            r8 = r1[r4]
            r8.onParsingStarted(r11, r2)
            int r4 = r4 + 1
            goto L_0x0088
        L_0x0093:
            int r2 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r2 <= 0) goto L_0x0118
            boolean r2 = r25.startIteratingHistoryLocked()
            if (r2 == 0) goto L_0x0118
            android.os.BatteryStats$HistoryItem r2 = new android.os.BatteryStats$HistoryItem
            r2.<init>()
            r8 = r6
            r3 = 0
        L_0x00a4:
            boolean r4 = r0.getNextHistoryLocked(r2)
            if (r4 == 0) goto L_0x0118
            if (r3 >= r13) goto L_0x0118
            boolean r4 = r2.isDeltaData()
            if (r4 == 0) goto L_0x00d3
            long r14 = r2.time
            long r16 = r14 - r23
            long r8 = r8 + r16
            long r16 = r8 - r11
            int r4 = (r16 > r6 ? 1 : (r16 == r6 ? 0 : -1))
            if (r4 >= 0) goto L_0x00c0
            r5 = r6
            goto L_0x00c2
        L_0x00c0:
            r5 = r16
        L_0x00c2:
            r7 = 0
        L_0x00c3:
            int r4 = r1.length
            if (r7 >= r4) goto L_0x00ce
            r4 = r1[r7]
            r4.onDataPoint(r5, r2)
            int r7 = r7 + 1
            goto L_0x00c3
        L_0x00ce:
            r23 = r14
            r5 = 7
            r10 = 5
            goto L_0x0113
        L_0x00d3:
            byte r4 = r2.cmd
            r5 = 5
            if (r4 == r5) goto L_0x00e0
            r5 = 7
            if (r4 != r5) goto L_0x00dc
            goto L_0x00e1
        L_0x00dc:
            r6 = r8
            r14 = r23
            goto L_0x00ef
        L_0x00e0:
            r5 = 7
        L_0x00e1:
            long r6 = r2.currentTime
            int r4 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r4 < 0) goto L_0x00e8
            goto L_0x00ed
        L_0x00e8:
            long r6 = r2.time
            long r6 = r6 - r20
            long r6 = r6 + r11
        L_0x00ed:
            long r14 = r2.time
        L_0x00ef:
            byte r4 = r2.cmd
            r10 = 6
            if (r4 == r10) goto L_0x010f
            r10 = 5
            if (r4 != r10) goto L_0x0103
            long r8 = r8 - r6
            long r8 = java.lang.Math.abs(r8)
            r22 = 3600000(0x36ee80, double:1.7786363E-317)
            int r4 = (r8 > r22 ? 1 : (r8 == r22 ? 0 : -1))
            if (r4 <= 0) goto L_0x0110
        L_0x0103:
            r4 = 0
        L_0x0104:
            int r8 = r1.length
            if (r4 >= r8) goto L_0x0110
            r8 = r1[r4]
            r8.onDataGap()
            int r4 = r4 + 1
            goto L_0x0104
        L_0x010f:
            r10 = 5
        L_0x0110:
            r8 = r6
            r23 = r14
        L_0x0113:
            int r3 = r3 + 1
            r6 = 0
            goto L_0x00a4
        L_0x0118:
            r25.finishIteratingHistoryLocked()
            r0 = 0
        L_0x011c:
            int r2 = r1.length
            if (r0 >= r2) goto L_0x0127
            r2 = r1[r0]
            r2.onParsingDone()
            int r0 = r0 + 1
            goto L_0x011c
        L_0x0127:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.fuelgauge.BatteryInfo.parse(android.os.BatteryStats, com.android.settings.fuelgauge.BatteryInfo$BatteryDataParser[]):void");
    }
}
