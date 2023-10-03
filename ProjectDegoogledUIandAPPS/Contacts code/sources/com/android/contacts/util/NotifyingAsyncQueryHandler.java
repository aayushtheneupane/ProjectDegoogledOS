package com.android.contacts.util;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.database.Cursor;
import java.lang.ref.WeakReference;

public class NotifyingAsyncQueryHandler extends AsyncQueryHandler {
    private WeakReference<AsyncQueryListener> mListener;

    public interface AsyncQueryListener {
        void onQueryComplete(int i, Object obj, Cursor cursor);
    }

    public NotifyingAsyncQueryHandler(Context context, AsyncQueryListener asyncQueryListener) {
        super(context.getContentResolver());
        setQueryListener(asyncQueryListener);
    }

    public void setQueryListener(AsyncQueryListener asyncQueryListener) {
        this.mListener = new WeakReference<>(asyncQueryListener);
    }

    /* access modifiers changed from: protected */
    public void onQueryComplete(int i, Object obj, Cursor cursor) {
        AsyncQueryListener asyncQueryListener = (AsyncQueryListener) this.mListener.get();
        if (asyncQueryListener != null) {
            asyncQueryListener.onQueryComplete(i, obj, cursor);
        } else if (cursor != null) {
            cursor.close();
        }
    }
}
