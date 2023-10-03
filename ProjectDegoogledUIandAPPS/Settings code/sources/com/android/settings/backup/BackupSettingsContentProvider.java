package com.android.settings.backup;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

public class BackupSettingsContentProvider extends ContentProvider {
    private static final UriMatcher URI_MATCHER = new UriMatcher(-1);

    public boolean onCreate() {
        return true;
    }

    static {
        URI_MATCHER.addURI("com.android.settings.backup.BackupSettingsContentProvider", "summary", 1);
    }

    public Bundle call(String str, String str2, Bundle bundle) {
        if (!"summary".equals(str)) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("com.android.settings.summary", new BackupSettingsHelper(getContext()).getSummary());
        return bundle2;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new UnsupportedOperationException();
    }

    public String getType(Uri uri) {
        throw new UnsupportedOperationException();
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException();
    }

    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }
}
