package com.android.contacts.util.concurrent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

public abstract class ListenableFutureLoader<D> extends Loader<D> {
    private ListenableFuture<D> mFuture;
    /* access modifiers changed from: private */
    public D mLoadedData;
    private final LocalBroadcastManager mLocalBroadcastManager;
    private BroadcastReceiver mReceiver;
    private final IntentFilter mReloadFilter;
    private final Executor mUiExecutor = ContactsExecutors.newUiThreadExecutor();

    /* access modifiers changed from: protected */
    public boolean isSameData(D d, D d2) {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract ListenableFuture<D> loadData();

    public ListenableFutureLoader(Context context, IntentFilter intentFilter) {
        super(context);
        this.mReloadFilter = intentFilter;
        this.mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    /* access modifiers changed from: protected */
    public void onStartLoading() {
        if (this.mReloadFilter != null && this.mReceiver == null) {
            this.mReceiver = new ForceLoadReceiver();
            this.mLocalBroadcastManager.registerReceiver(this.mReceiver, this.mReloadFilter);
        }
        D d = this.mLoadedData;
        if (d != null) {
            deliverResult(d);
        }
        if (this.mFuture == null) {
            takeContentChanged();
            forceLoad();
        } else if (takeContentChanged()) {
            forceLoad();
        }
    }

    /* access modifiers changed from: protected */
    public void onForceLoad() {
        this.mFuture = loadData();
        Futures.addCallback(this.mFuture, new FutureCallback<D>() {
            /* JADX WARNING: Code restructure failed: missing block: B:3:0x0012, code lost:
                if (r0.isSameData(com.android.contacts.util.concurrent.ListenableFutureLoader.access$000(r0), r3) == false) goto L_0x0014;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onSuccess(D r3) {
                /*
                    r2 = this;
                    com.android.contacts.util.concurrent.ListenableFutureLoader r0 = com.android.contacts.util.concurrent.ListenableFutureLoader.this
                    java.lang.Object r0 = r0.mLoadedData
                    if (r0 == 0) goto L_0x0014
                    com.android.contacts.util.concurrent.ListenableFutureLoader r0 = com.android.contacts.util.concurrent.ListenableFutureLoader.this
                    java.lang.Object r1 = r0.mLoadedData
                    boolean r0 = r0.isSameData(r1, r3)
                    if (r0 != 0) goto L_0x0019
                L_0x0014:
                    com.android.contacts.util.concurrent.ListenableFutureLoader r0 = com.android.contacts.util.concurrent.ListenableFutureLoader.this
                    r0.deliverResult(r3)
                L_0x0019:
                    com.android.contacts.util.concurrent.ListenableFutureLoader r0 = com.android.contacts.util.concurrent.ListenableFutureLoader.this
                    java.lang.Object unused = r0.mLoadedData = r3
                    com.android.contacts.util.concurrent.ListenableFutureLoader r3 = com.android.contacts.util.concurrent.ListenableFutureLoader.this
                    r3.commitContentChanged()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.util.concurrent.ListenableFutureLoader.C04641.onSuccess(java.lang.Object):void");
            }

            public void onFailure(Throwable th) {
                if (th instanceof CancellationException) {
                    Log.i("FutureLoader", "Loading cancelled", th);
                    ListenableFutureLoader.this.rollbackContentChanged();
                    return;
                }
                Log.e("FutureLoader", "Loading failed", th);
            }
        }, this.mUiExecutor);
    }

    /* access modifiers changed from: protected */
    public void onStopLoading() {
        ListenableFuture<D> listenableFuture = this.mFuture;
        if (listenableFuture != null) {
            listenableFuture.cancel(false);
            this.mFuture = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        this.mFuture = null;
        this.mLoadedData = null;
        BroadcastReceiver broadcastReceiver = this.mReceiver;
        if (broadcastReceiver != null) {
            this.mLocalBroadcastManager.unregisterReceiver(broadcastReceiver);
        }
    }

    public class ForceLoadReceiver extends BroadcastReceiver {
        public ForceLoadReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            ListenableFutureLoader.this.onContentChanged();
        }
    }
}
