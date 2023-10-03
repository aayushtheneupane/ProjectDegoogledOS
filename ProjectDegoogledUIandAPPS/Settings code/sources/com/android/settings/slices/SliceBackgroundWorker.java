package com.android.settings.slices;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.util.ArrayMap;
import android.util.Log;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SliceBackgroundWorker<E> implements Closeable {
    private static final Map<Uri, SliceBackgroundWorker> LIVE_WORKERS = new ArrayMap();
    private List<E> mCachedResults;
    private final Context mContext;
    private final Uri mUri;

    /* access modifiers changed from: protected */
    public abstract void onSlicePinned();

    /* access modifiers changed from: protected */
    public abstract void onSliceUnpinned();

    protected SliceBackgroundWorker(Context context, Uri uri) {
        this.mContext = context;
        this.mUri = uri;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.mContext;
    }

    public static <T extends SliceBackgroundWorker> T getInstance(Uri uri) {
        return (SliceBackgroundWorker) LIVE_WORKERS.get(uri);
    }

    static SliceBackgroundWorker getInstance(Context context, Sliceable sliceable, Uri uri) {
        SliceBackgroundWorker instance = getInstance(uri);
        if (instance != null) {
            return instance;
        }
        SliceBackgroundWorker createInstance = createInstance(context.getApplicationContext(), uri, sliceable.getBackgroundWorkerClass());
        LIVE_WORKERS.put(uri, createInstance);
        return createInstance;
    }

    private static SliceBackgroundWorker createInstance(Context context, Uri uri, Class<? extends SliceBackgroundWorker> cls) {
        Log.d("SliceBackgroundWorker", "create instance: " + cls);
        try {
            return (SliceBackgroundWorker) cls.getConstructor(new Class[]{Context.class, Uri.class}).newInstance(new Object[]{context, uri});
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalStateException("Invalid slice background worker: " + cls, e);
        }
    }

    /* access modifiers changed from: package-private */
    public static void shutdown() {
        for (SliceBackgroundWorker close : LIVE_WORKERS.values()) {
            try {
                close.close();
            } catch (IOException e) {
                Log.w("SliceBackgroundWorker", "Shutting down worker failed", e);
            }
        }
        LIVE_WORKERS.clear();
    }

    public final List<E> getResults() {
        List<E> list = this.mCachedResults;
        if (list == null) {
            return null;
        }
        return new ArrayList(list);
    }

    /* access modifiers changed from: protected */
    public final void updateResults(List<E> list) {
        boolean z = true;
        if (list != null) {
            z = true ^ areListsTheSame(list, this.mCachedResults);
        } else if (this.mCachedResults == null) {
            z = false;
        }
        if (z) {
            this.mCachedResults = list;
            notifySliceChange();
        }
    }

    /* access modifiers changed from: protected */
    public boolean areListsTheSame(List<E> list, List<E> list2) {
        return list.equals(list2);
    }

    /* access modifiers changed from: protected */
    public final void notifySliceChange() {
        this.mContext.getContentResolver().notifyChange(this.mUri, (ContentObserver) null);
    }
}
