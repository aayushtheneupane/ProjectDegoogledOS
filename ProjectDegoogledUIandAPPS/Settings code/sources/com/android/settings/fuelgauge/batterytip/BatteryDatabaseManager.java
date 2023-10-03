package com.android.settings.fuelgauge.batterytip;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.util.Collections;
import java.util.List;

public class BatteryDatabaseManager {
    private static BatteryDatabaseManager sSingleton;
    private AnomalyDatabaseHelper mDatabaseHelper;

    private BatteryDatabaseManager(Context context) {
        this.mDatabaseHelper = AnomalyDatabaseHelper.getInstance(context);
    }

    public static synchronized BatteryDatabaseManager getInstance(Context context) {
        BatteryDatabaseManager batteryDatabaseManager;
        synchronized (BatteryDatabaseManager.class) {
            if (sSingleton == null) {
                sSingleton = new BatteryDatabaseManager(context);
            }
            batteryDatabaseManager = sSingleton;
        }
        return batteryDatabaseManager;
    }

    public static void setUpForTest(BatteryDatabaseManager batteryDatabaseManager) {
        sSingleton = batteryDatabaseManager;
    }

    public synchronized boolean insertAnomaly(int i, String str, int i2, int i3, long j) {
        SQLiteDatabase writableDatabase;
        ContentValues contentValues;
        writableDatabase = this.mDatabaseHelper.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("uid", Integer.valueOf(i));
        contentValues.put("package_name", str);
        contentValues.put("anomaly_type", Integer.valueOf(i2));
        contentValues.put("anomaly_state", Integer.valueOf(i3));
        contentValues.put("time_stamp_ms", Long.valueOf(j));
        return writableDatabase.insertWithOnConflict("anomaly", (String) null, contentValues, 4) != -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00b2, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b3, code lost:
        if (r11 != null) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        $closeResource(r12, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00b8, code lost:
        throw r13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.List<com.android.settings.fuelgauge.batterytip.AppInfo> queryAllAnomalies(long r11, int r13) {
        /*
            r10 = this;
            monitor-enter(r10)
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x00b9 }
            r0.<init>()     // Catch:{ all -> 0x00b9 }
            com.android.settings.fuelgauge.batterytip.AnomalyDatabaseHelper r1 = r10.mDatabaseHelper     // Catch:{ all -> 0x00b9 }
            android.database.sqlite.SQLiteDatabase r2 = r1.getReadableDatabase()     // Catch:{ all -> 0x00b9 }
            java.lang.String r1 = "package_name"
            java.lang.String r3 = "anomaly_type"
            java.lang.String r4 = "uid"
            java.lang.String[] r4 = new java.lang.String[]{r1, r3, r4}     // Catch:{ all -> 0x00b9 }
            android.util.ArrayMap r1 = new android.util.ArrayMap     // Catch:{ all -> 0x00b9 }
            r1.<init>()     // Catch:{ all -> 0x00b9 }
            r3 = 2
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ all -> 0x00b9 }
            r3 = 0
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x00b9 }
            r6[r3] = r11     // Catch:{ all -> 0x00b9 }
            r11 = 1
            java.lang.String r12 = java.lang.String.valueOf(r13)     // Catch:{ all -> 0x00b9 }
            r6[r11] = r12     // Catch:{ all -> 0x00b9 }
            java.lang.String r3 = "anomaly"
            java.lang.String r5 = "time_stamp_ms > ? AND anomaly_state = ? "
            r7 = 0
            r8 = 0
            java.lang.String r9 = "time_stamp_ms DESC"
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00b9 }
            r12 = 0
        L_0x0039:
            boolean r13 = r11.moveToNext()     // Catch:{ all -> 0x00b0 }
            if (r13 == 0) goto L_0x0087
            java.lang.String r13 = "uid"
            int r13 = r11.getColumnIndex(r13)     // Catch:{ all -> 0x00b0 }
            int r13 = r11.getInt(r13)     // Catch:{ all -> 0x00b0 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x00b0 }
            boolean r2 = r1.containsKey(r2)     // Catch:{ all -> 0x00b0 }
            if (r2 != 0) goto L_0x006f
            com.android.settings.fuelgauge.batterytip.AppInfo$Builder r2 = new com.android.settings.fuelgauge.batterytip.AppInfo$Builder     // Catch:{ all -> 0x00b0 }
            r2.<init>()     // Catch:{ all -> 0x00b0 }
            r2.setUid(r13)     // Catch:{ all -> 0x00b0 }
            java.lang.String r3 = "package_name"
            int r3 = r11.getColumnIndex(r3)     // Catch:{ all -> 0x00b0 }
            java.lang.String r3 = r11.getString(r3)     // Catch:{ all -> 0x00b0 }
            r2.setPackageName(r3)     // Catch:{ all -> 0x00b0 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x00b0 }
            r1.put(r3, r2)     // Catch:{ all -> 0x00b0 }
        L_0x006f:
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x00b0 }
            java.lang.Object r13 = r1.get(r13)     // Catch:{ all -> 0x00b0 }
            com.android.settings.fuelgauge.batterytip.AppInfo$Builder r13 = (com.android.settings.fuelgauge.batterytip.AppInfo.Builder) r13     // Catch:{ all -> 0x00b0 }
            java.lang.String r2 = "anomaly_type"
            int r2 = r11.getColumnIndex(r2)     // Catch:{ all -> 0x00b0 }
            int r2 = r11.getInt(r2)     // Catch:{ all -> 0x00b0 }
            r13.addAnomalyType(r2)     // Catch:{ all -> 0x00b0 }
            goto L_0x0039
        L_0x0087:
            if (r11 == 0) goto L_0x008c
            $closeResource(r12, r11)     // Catch:{ all -> 0x00b9 }
        L_0x008c:
            java.util.Set r11 = r1.keySet()     // Catch:{ all -> 0x00b9 }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ all -> 0x00b9 }
        L_0x0094:
            boolean r12 = r11.hasNext()     // Catch:{ all -> 0x00b9 }
            if (r12 == 0) goto L_0x00ae
            java.lang.Object r12 = r11.next()     // Catch:{ all -> 0x00b9 }
            java.lang.Integer r12 = (java.lang.Integer) r12     // Catch:{ all -> 0x00b9 }
            java.lang.Object r12 = r1.get(r12)     // Catch:{ all -> 0x00b9 }
            com.android.settings.fuelgauge.batterytip.AppInfo$Builder r12 = (com.android.settings.fuelgauge.batterytip.AppInfo.Builder) r12     // Catch:{ all -> 0x00b9 }
            com.android.settings.fuelgauge.batterytip.AppInfo r12 = r12.build()     // Catch:{ all -> 0x00b9 }
            r0.add(r12)     // Catch:{ all -> 0x00b9 }
            goto L_0x0094
        L_0x00ae:
            monitor-exit(r10)
            return r0
        L_0x00b0:
            r12 = move-exception
            throw r12     // Catch:{ all -> 0x00b2 }
        L_0x00b2:
            r13 = move-exception
            if (r11 == 0) goto L_0x00b8
            $closeResource(r12, r11)     // Catch:{ all -> 0x00b9 }
        L_0x00b8:
            throw r13     // Catch:{ all -> 0x00b9 }
        L_0x00b9:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.fuelgauge.batterytip.BatteryDatabaseManager.queryAllAnomalies(long, int):java.util.List");
    }

    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            autoCloseable.close();
        }
    }

    public synchronized void deleteAllAnomaliesBeforeTimeStamp(long j) {
        this.mDatabaseHelper.getWritableDatabase().delete("anomaly", "time_stamp_ms < ?", new String[]{String.valueOf(j)});
    }

    public synchronized void updateAnomalies(List<AppInfo> list, int i) {
        if (!list.isEmpty()) {
            int size = list.size();
            String[] strArr = new String[size];
            for (int i2 = 0; i2 < size; i2++) {
                strArr[i2] = list.get(i2).packageName;
            }
            SQLiteDatabase writableDatabase = this.mDatabaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("anomaly_state", Integer.valueOf(i));
            writableDatabase.update("anomaly", contentValues, "package_name IN (" + TextUtils.join(",", Collections.nCopies(list.size(), "?")) + ")", strArr);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0052, code lost:
        if (r11 != null) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        $closeResource(r0, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0057, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.util.SparseLongArray queryActionTime(int r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            android.util.SparseLongArray r0 = new android.util.SparseLongArray     // Catch:{ all -> 0x0058 }
            r0.<init>()     // Catch:{ all -> 0x0058 }
            com.android.settings.fuelgauge.batterytip.AnomalyDatabaseHelper r1 = r10.mDatabaseHelper     // Catch:{ all -> 0x0058 }
            android.database.sqlite.SQLiteDatabase r2 = r1.getReadableDatabase()     // Catch:{ all -> 0x0058 }
            java.lang.String r1 = "uid"
            java.lang.String r3 = "time_stamp_ms"
            java.lang.String[] r4 = new java.lang.String[]{r1, r3}     // Catch:{ all -> 0x0058 }
            r1 = 1
            java.lang.String[] r6 = new java.lang.String[r1]     // Catch:{ all -> 0x0058 }
            r1 = 0
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x0058 }
            r6[r1] = r11     // Catch:{ all -> 0x0058 }
            java.lang.String r3 = "action"
            java.lang.String r5 = "action_type = ? "
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0058 }
            r1 = 0
            java.lang.String r2 = "uid"
            int r2 = r11.getColumnIndex(r2)     // Catch:{ all -> 0x004f }
            java.lang.String r3 = "time_stamp_ms"
            int r3 = r11.getColumnIndex(r3)     // Catch:{ all -> 0x004f }
        L_0x0036:
            boolean r4 = r11.moveToNext()     // Catch:{ all -> 0x004f }
            if (r4 == 0) goto L_0x0048
            int r4 = r11.getInt(r2)     // Catch:{ all -> 0x004f }
            long r5 = r11.getLong(r3)     // Catch:{ all -> 0x004f }
            r0.append(r4, r5)     // Catch:{ all -> 0x004f }
            goto L_0x0036
        L_0x0048:
            if (r11 == 0) goto L_0x004d
            $closeResource(r1, r11)     // Catch:{ all -> 0x0058 }
        L_0x004d:
            monitor-exit(r10)
            return r0
        L_0x004f:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r1 = move-exception
            if (r11 == 0) goto L_0x0057
            $closeResource(r0, r11)     // Catch:{ all -> 0x0058 }
        L_0x0057:
            throw r1     // Catch:{ all -> 0x0058 }
        L_0x0058:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.fuelgauge.batterytip.BatteryDatabaseManager.queryActionTime(int):android.util.SparseLongArray");
    }

    public synchronized boolean insertAction(int i, int i2, String str, long j) {
        SQLiteDatabase writableDatabase;
        ContentValues contentValues;
        writableDatabase = this.mDatabaseHelper.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("uid", Integer.valueOf(i2));
        contentValues.put("package_name", str);
        contentValues.put("action_type", Integer.valueOf(i));
        contentValues.put("time_stamp_ms", Long.valueOf(j));
        return writableDatabase.insertWithOnConflict("action", (String) null, contentValues, 5) != -1;
    }

    public synchronized boolean deleteAction(int i, int i2, String str) {
        boolean z;
        SQLiteDatabase writableDatabase = this.mDatabaseHelper.getWritableDatabase();
        String valueOf = String.valueOf(i2);
        z = true;
        if (writableDatabase.delete("action", "action_type = ? AND uid = ? AND package_name = ? ", new String[]{String.valueOf(i), valueOf, String.valueOf(str)}) == 0) {
            z = false;
        }
        return z;
    }
}
