package com.android.contacts.common.database;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class NoNullCursorAsyncQueryHandler extends AsyncQueryHandler {
    private static final AtomicInteger pendingQueryCount = new AtomicInteger();

    private static class CookieWithProjection {
        public final Object originalCookie;
        public final String[] projection;

        public CookieWithProjection(Object obj, String[] strArr) {
            this.originalCookie = obj;
            this.projection = strArr;
        }
    }

    public interface PendingQueryCountChangedListener {
    }

    public NoNullCursorAsyncQueryHandler(ContentResolver contentResolver) {
        super(contentResolver);
    }

    public static int getPendingQueryCount() {
        return pendingQueryCount.get();
    }

    public static void setPendingQueryCountChangedListener(PendingQueryCountChangedListener pendingQueryCountChangedListener) {
    }

    /* access modifiers changed from: protected */
    public abstract void onNotNullableQueryComplete(int i, Object obj, Cursor cursor);

    /* access modifiers changed from: protected */
    public final void onQueryComplete(int i, Object obj, Cursor cursor) {
        CookieWithProjection cookieWithProjection = (CookieWithProjection) obj;
        super.onQueryComplete(i, cookieWithProjection.originalCookie, cursor);
        if (cursor == null) {
            cursor = new EmptyCursor(cookieWithProjection.projection);
        }
        onNotNullableQueryComplete(i, cookieWithProjection.originalCookie, cursor);
        pendingQueryCount.getAndDecrement();
    }

    public void startQuery(int i, Object obj, Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        pendingQueryCount.getAndIncrement();
        Object obj2 = obj;
        int i2 = i;
        super.startQuery(i2, new CookieWithProjection(obj, strArr), uri, strArr, str, strArr2, str2);
    }
}
