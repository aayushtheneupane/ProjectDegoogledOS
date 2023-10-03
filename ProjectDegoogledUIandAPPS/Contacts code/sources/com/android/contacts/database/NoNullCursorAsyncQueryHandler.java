package com.android.contacts.database;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public abstract class NoNullCursorAsyncQueryHandler extends AsyncQueryHandler {
    /* access modifiers changed from: protected */
    public abstract void onNotNullableQueryComplete(int i, Object obj, Cursor cursor);

    public NoNullCursorAsyncQueryHandler(ContentResolver contentResolver) {
        super(contentResolver);
    }

    public void startQuery(int i, Object obj, Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        super.startQuery(i, new CookieWithProjection(obj, strArr), uri, strArr, str, strArr2, str2);
    }

    /* access modifiers changed from: protected */
    public final void onQueryComplete(int i, Object obj, Cursor cursor) {
        CookieWithProjection cookieWithProjection = (CookieWithProjection) obj;
        super.onQueryComplete(i, cookieWithProjection.originalCookie, cursor);
        if (cursor == null) {
            cursor = new EmptyCursor(cookieWithProjection.projection);
        }
        onNotNullableQueryComplete(i, cookieWithProjection.originalCookie, cursor);
    }

    private static class CookieWithProjection {
        public final Object originalCookie;
        public final String[] projection;

        public CookieWithProjection(Object obj, String[] strArr) {
            this.originalCookie = obj;
            this.projection = strArr;
        }
    }
}
