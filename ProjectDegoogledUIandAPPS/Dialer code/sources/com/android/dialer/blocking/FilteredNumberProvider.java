package com.android.dialer.blocking;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.p002v7.appcompat.R$style;
import android.text.TextUtils;
import com.android.dialer.database.Database;
import com.android.dialer.database.DialerDatabaseHelper;
import com.android.dialer.database.FilteredNumberContract;
import com.android.tools.p006r8.GeneratedOutlineSupport;

@Deprecated
public class FilteredNumberProvider extends ContentProvider {
    private static final UriMatcher uriMatcher = new UriMatcher(-1);
    private DialerDatabaseHelper dialerDatabaseHelper;

    private String getSelectionWithId(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return "_id=" + j;
        }
        return str + "AND " + "_id" + "=" + j;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.dialerDatabaseHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        if (match != 1) {
            if (match == 2) {
                str = getSelectionWithId(str, ContentUris.parseId(uri));
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Unknown uri: ", uri));
            }
        }
        int delete = writableDatabase.delete("filtered_numbers_table", str, strArr);
        if (delete > 0) {
            getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
        }
        return delete;
    }

    /* access modifiers changed from: protected */
    public long getCurrentTimeMs() {
        return System.currentTimeMillis();
    }

    public String getType(Uri uri) {
        return "vnd.android.cursor.item/filtered_numbers_table";
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase writableDatabase = this.dialerDatabaseHelper.getWritableDatabase();
        if (contentValues.getAsString("country_iso") == null) {
            contentValues.put("country_iso", R$style.getCurrentCountryIso(getContext()));
        }
        if (contentValues.getAsInteger("times_filtered") == null) {
            contentValues.put("times_filtered", 0);
        }
        if (contentValues.getAsLong("creation_time") == null) {
            contentValues.put("creation_time", Long.valueOf(getCurrentTimeMs()));
        }
        long insert = writableDatabase.insert("filtered_numbers_table", (String) null, contentValues);
        if (insert < 0) {
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
        return ContentUris.withAppendedId(uri, insert);
    }

    public boolean onCreate() {
        this.dialerDatabaseHelper = Database.get(getContext()).getDatabaseHelper(getContext());
        if (this.dialerDatabaseHelper == null) {
            return false;
        }
        uriMatcher.addURI(FilteredNumberContract.AUTHORITY, "filtered_numbers_table", 1);
        uriMatcher.addURI(FilteredNumberContract.AUTHORITY, "filtered_numbers_table/#", 2);
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteDatabase readableDatabase = this.dialerDatabaseHelper.getReadableDatabase();
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("filtered_numbers_table");
        int match = uriMatcher.match(uri);
        if (match != 1) {
            if (match == 2) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("_id=");
                outline13.append(ContentUris.parseId(uri));
                sQLiteQueryBuilder.appendWhere(outline13.toString());
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Unknown uri: ", uri));
            }
        }
        Cursor query = sQLiteQueryBuilder.query(readableDatabase, strArr, str, strArr2, (String) null, (String) null, (String) null);
        if (query != null) {
            query.setNotificationUri(getContext().getContentResolver(), FilteredNumberContract.FilteredNumber.CONTENT_URI);
        }
        return query;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.dialerDatabaseHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        if (match != 1) {
            if (match == 2) {
                str = getSelectionWithId(str, ContentUris.parseId(uri));
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Unknown uri: ", uri));
            }
        }
        int update = writableDatabase.update("filtered_numbers_table", contentValues, str, strArr);
        if (update > 0) {
            getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
        }
        return update;
    }
}
