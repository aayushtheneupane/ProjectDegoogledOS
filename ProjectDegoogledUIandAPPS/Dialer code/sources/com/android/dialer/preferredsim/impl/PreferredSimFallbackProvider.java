package com.android.dialer.preferredsim.impl;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import com.android.dialer.preferredsim.PreferredSimFallbackContract;
import com.google.common.collect.ImmutableMap;

public class PreferredSimFallbackProvider extends ContentProvider {
    private static final ImmutableMap<String, String> PROJECTION_MAP = ImmutableMap.m84of("data_id", "data_id", "preferred_phone_account_component_name", "preferred_phone_account_component_name", "preferred_phone_account_id", "preferred_phone_account_id");
    private PreferredSimDatabaseHelper databaseHelper;

    private void checkWriteContactsPermission() {
        if (getContext().checkCallingOrSelfPermission("android.permission.WRITE_CONTACTS") == -1) {
            throw new SecurityException("WRITE_CONTACTS required");
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        checkWriteContactsPermission();
        if (PreferredSimFallbackContract.CONTENT_URI.equals(uri) && str == null && strArr == null) {
            return this.databaseHelper.getWritableDatabase().delete("preferred_sim", (String) null, (String[]) null);
        }
        if (TextUtils.equals(getContext().getPackageName(), getCallingPackage())) {
            return this.databaseHelper.getWritableDatabase().delete("preferred_sim", str, strArr);
        }
        throw new IllegalArgumentException("Unsupported operation");
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new IllegalArgumentException("Unsupported operation");
    }

    public boolean onCreate() {
        this.databaseHelper = new PreferredSimDatabaseHelper(getContext());
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (getContext().checkCallingOrSelfPermission("android.permission.READ_CONTACTS") != -1) {
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setStrict(true);
            sQLiteQueryBuilder.setProjectionMap(PROJECTION_MAP);
            sQLiteQueryBuilder.setTables("preferred_sim");
            return sQLiteQueryBuilder.query(this.databaseHelper.getReadableDatabase(), strArr, str, strArr2, (String) null, (String) null, str2);
        }
        throw new SecurityException("READ_CONTACTS required");
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        checkWriteContactsPermission();
        if (contentValues == null) {
            return 0;
        }
        if (!"data_id = ?".equals(str) || strArr == null || strArr.length != 1) {
            throw new IllegalArgumentException("Unsupported operation");
        }
        contentValues.put("data_id", strArr[0]);
        if (this.databaseHelper.getWritableDatabase().replace("preferred_sim", (String) null, contentValues) != -1) {
            getContext().getContentResolver().notifyChange(PreferredSimFallbackContract.CONTENT_URI, (ContentObserver) null);
            return 1;
        }
        throw new IllegalStateException("update failed");
    }
}
