package com.android.contacts.list;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import com.android.contacts.compat.ProviderStatusCompat;
import com.android.contactsbind.FeedbackHelper;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;

public class ProviderStatusWatcher extends ContentObserver {
    /* access modifiers changed from: private */
    public static final String[] PROJECTION = {"status"};
    private static ProviderStatusWatcher sInstance;
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Handler mHandler = new Handler();
    private final ArrayList<ProviderStatusListener> mListeners = Lists.newArrayList();
    /* access modifiers changed from: private */
    public LoaderTask mLoaderTask;
    /* access modifiers changed from: private */
    public Integer mProviderStatus;
    /* access modifiers changed from: private */
    public final Object mSignal = new Object();
    private final Runnable mStartLoadingRunnable = new Runnable() {
        public void run() {
            ProviderStatusWatcher.this.startLoading();
        }
    };
    private int mStartRequestedCount;

    public interface ProviderStatusListener {
        void onProviderStatusChange();
    }

    public static synchronized ProviderStatusWatcher getInstance(Context context) {
        ProviderStatusWatcher providerStatusWatcher;
        synchronized (ProviderStatusWatcher.class) {
            if (sInstance == null) {
                sInstance = new ProviderStatusWatcher(context);
            }
            providerStatusWatcher = sInstance;
        }
        return providerStatusWatcher;
    }

    private ProviderStatusWatcher(Context context) {
        super((Handler) null);
        this.mContext = context;
    }

    public void addListener(ProviderStatusListener providerStatusListener) {
        this.mListeners.add(providerStatusListener);
    }

    public void removeListener(ProviderStatusListener providerStatusListener) {
        this.mListeners.remove(providerStatusListener);
    }

    /* access modifiers changed from: private */
    public void notifyListeners() {
        if (isStarted()) {
            Iterator<ProviderStatusListener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onProviderStatusChange();
            }
        }
    }

    private boolean isStarted() {
        return this.mStartRequestedCount > 0;
    }

    public void start() {
        int i = this.mStartRequestedCount + 1;
        this.mStartRequestedCount = i;
        if (i == 1) {
            this.mContext.getContentResolver().registerContentObserver(ContactsContract.ProviderStatus.CONTENT_URI, false, this);
            startLoading();
        }
    }

    public void stop() {
        if (!isStarted()) {
            Log.e("ProviderStatusWatcher", "Already stopped");
            return;
        }
        int i = this.mStartRequestedCount - 1;
        this.mStartRequestedCount = i;
        if (i == 0) {
            this.mHandler.removeCallbacks(this.mStartLoadingRunnable);
            this.mContext.getContentResolver().unregisterContentObserver(this);
        }
    }

    public int getProviderStatus() {
        waitForLoaded();
        Integer num = this.mProviderStatus;
        if (num == null) {
            return ProviderStatusCompat.STATUS_BUSY;
        }
        return num.intValue();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0018 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void waitForLoaded() {
        /*
            r4 = this;
            java.lang.Integer r0 = r4.mProviderStatus
            if (r0 != 0) goto L_0x001c
            com.android.contacts.list.ProviderStatusWatcher$LoaderTask r0 = r4.mLoaderTask
            if (r0 != 0) goto L_0x000b
            r4.startLoading()
        L_0x000b:
            java.lang.Object r0 = r4.mSignal
            monitor-enter(r0)
            java.lang.Object r1 = r4.mSignal     // Catch:{ InterruptedException -> 0x0018 }
            r2 = 1000(0x3e8, double:4.94E-321)
            r1.wait(r2)     // Catch:{ InterruptedException -> 0x0018 }
            goto L_0x0018
        L_0x0016:
            r1 = move-exception
            goto L_0x001a
        L_0x0018:
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            goto L_0x001c
        L_0x001a:
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            throw r1
        L_0x001c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.list.ProviderStatusWatcher.waitForLoaded():void");
    }

    /* access modifiers changed from: private */
    public void startLoading() {
        if (this.mLoaderTask == null) {
            this.mLoaderTask = new LoaderTask();
            this.mLoaderTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private class LoaderTask extends AsyncTask<Void, Void, Boolean> {
        private LoaderTask() {
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(Void... voidArr) {
            Cursor query;
            try {
                query = ProviderStatusWatcher.this.mContext.getContentResolver().query(ContactsContract.ProviderStatus.CONTENT_URI, ProviderStatusWatcher.PROJECTION, (String) null, (String[]) null, (String) null);
                if (query != null) {
                    if (query.moveToFirst()) {
                        Integer unused = ProviderStatusWatcher.this.mProviderStatus = Integer.valueOf(query.getInt(0));
                        query.close();
                        synchronized (ProviderStatusWatcher.this.mSignal) {
                            ProviderStatusWatcher.this.mSignal.notifyAll();
                        }
                        return true;
                    }
                    query.close();
                }
                synchronized (ProviderStatusWatcher.this.mSignal) {
                    ProviderStatusWatcher.this.mSignal.notifyAll();
                }
                return false;
            } catch (SecurityException e) {
                try {
                    FeedbackHelper.sendFeedback(ProviderStatusWatcher.this.mContext, "ProviderStatusWatcher", "Security exception when querying provider status", e);
                    synchronized (ProviderStatusWatcher.this.mSignal) {
                        ProviderStatusWatcher.this.mSignal.notifyAll();
                        return false;
                    }
                } catch (Throwable th) {
                    synchronized (ProviderStatusWatcher.this.mSignal) {
                        ProviderStatusWatcher.this.mSignal.notifyAll();
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                query.close();
                throw th2;
            }
        }

        /* access modifiers changed from: protected */
        public void onCancelled(Boolean bool) {
            cleanUp();
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            cleanUp();
            if (bool != null && bool.booleanValue()) {
                ProviderStatusWatcher.this.notifyListeners();
            }
        }

        private void cleanUp() {
            LoaderTask unused = ProviderStatusWatcher.this.mLoaderTask = null;
        }
    }

    public void onChange(boolean z, Uri uri) {
        if (ContactsContract.ProviderStatus.CONTENT_URI.equals(uri)) {
            Log.i("ProviderStatusWatcher", "Provider status changed.");
            this.mHandler.removeCallbacks(this.mStartLoadingRunnable);
            this.mHandler.post(this.mStartLoadingRunnable);
        }
    }
}
