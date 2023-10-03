package com.android.settings.homepage.contextualcards;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import com.android.settingslib.utils.ThreadUtils;

public class CardContentProvider extends ContentProvider {
    public static final Uri DELETE_CARD_URI = new Uri.Builder().scheme("content").authority("com.android.settings.homepage.CardContentProvider").appendPath("card_dismissed").build();
    public static final Uri REFRESH_CARD_URI = new Uri.Builder().scheme("content").authority("com.android.settings.homepage.CardContentProvider").appendPath("cards").build();
    private static final UriMatcher URI_MATCHER = new UriMatcher(-1);
    private CardDatabaseHelper mDBHelper;

    static {
        URI_MATCHER.addURI("com.android.settings.homepage.CardContentProvider", "cards", 100);
    }

    public boolean onCreate() {
        this.mDBHelper = CardDatabaseHelper.getInstance(getContext());
        return true;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        bulkInsert(uri, new ContentValues[]{contentValues});
        return uri;
    }

    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        SQLiteDatabase writableDatabase = this.mDBHelper.getWritableDatabase();
        try {
            maybeEnableStrictMode();
            String tableFromMatch = getTableFromMatch(uri);
            writableDatabase.beginTransaction();
            writableDatabase.delete(tableFromMatch, (String) null, (String[]) null);
            int i = 0;
            for (ContentValues contentValues : contentValuesArr) {
                if (writableDatabase.insert(tableFromMatch, (String) null, contentValues) != -1) {
                    i++;
                } else {
                    Log.e("CardContentProvider", "The row " + contentValues.getAsString("name") + " insertion failed! Please check your data.");
                }
            }
            writableDatabase.setTransactionSuccessful();
            getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
            return i;
        } finally {
            writableDatabase.endTransaction();
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("delete operation not supported currently.");
    }

    public String getType(Uri uri) {
        throw new UnsupportedOperationException("getType operation not supported currently.");
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            maybeEnableStrictMode();
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables(getTableFromMatch(uri));
            Cursor query = sQLiteQueryBuilder.query(this.mDBHelper.getReadableDatabase(), strArr, str, strArr2, (String) null, (String) null, str2);
            query.setNotificationUri(getContext().getContentResolver(), uri);
            return query;
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("update operation not supported currently.");
    }

    /* access modifiers changed from: package-private */
    public void maybeEnableStrictMode() {
        if (Build.IS_ENG && ThreadUtils.isMainThread()) {
            enableStrictMode();
        }
    }

    /* access modifiers changed from: package-private */
    public void enableStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().build());
    }

    /* access modifiers changed from: package-private */
    public String getTableFromMatch(Uri uri) {
        if (URI_MATCHER.match(uri) == 100) {
            return "cards";
        }
        throw new IllegalArgumentException("Unknown Uri format: " + uri);
    }
}
