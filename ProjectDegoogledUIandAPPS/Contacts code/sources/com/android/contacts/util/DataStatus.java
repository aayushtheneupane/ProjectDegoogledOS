package com.android.contacts.util;

import android.database.Cursor;

public class DataStatus {
    private int mIconRes = -1;
    private int mLabelRes = -1;
    private int mPresence = -1;
    private String mResPackage = null;
    private String mStatus = null;
    private long mTimestamp = -1;

    public DataStatus(Cursor cursor) {
        fromCursor(cursor);
    }

    private void fromCursor(Cursor cursor) {
        this.mPresence = getInt(cursor, "mode", -1);
        this.mStatus = getString(cursor, "status");
        this.mTimestamp = getLong(cursor, "status_ts", -1);
        this.mResPackage = getString(cursor, "status_res_package");
        this.mIconRes = getInt(cursor, "status_icon", -1);
        this.mLabelRes = getInt(cursor, "status_label", -1);
    }

    private static String getString(Cursor cursor, String str) {
        return cursor.getString(cursor.getColumnIndex(str));
    }

    private static int getInt(Cursor cursor, String str, int i) {
        int columnIndex = cursor.getColumnIndex(str);
        return cursor.isNull(columnIndex) ? i : cursor.getInt(columnIndex);
    }

    private static long getLong(Cursor cursor, String str, long j) {
        int columnIndex = cursor.getColumnIndex(str);
        return cursor.isNull(columnIndex) ? j : cursor.getLong(columnIndex);
    }
}
