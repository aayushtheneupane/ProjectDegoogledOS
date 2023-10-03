package com.android.dialer.calllog;

import android.content.ContentValues;
import android.content.Context;
import android.provider.CallLog;
import android.support.design.R$dimen;
import android.support.p002v7.appcompat.R$style;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.database.Selection;
import com.android.dialer.util.PermissionsUtil;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;

public final class ClearMissedCalls {
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutor;
    private final ListeningExecutorService uiThreadExecutor;

    ClearMissedCalls(Context context, ListeningExecutorService listeningExecutorService, ListeningExecutorService listeningExecutorService2) {
        this.appContext = context;
        this.backgroundExecutor = listeningExecutorService;
        this.uiThreadExecutor = listeningExecutorService2;
    }

    static /* synthetic */ Void lambda$clearAll$1(ListenableFuture listenableFuture, ListenableFuture listenableFuture2) throws Exception {
        listenableFuture.get();
        listenableFuture2.get();
        return null;
    }

    static /* synthetic */ Void lambda$clearBySystemCallLogId$3(ListenableFuture listenableFuture, ListenableFuture listenableFuture2) throws Exception {
        listenableFuture.get();
        listenableFuture2.get();
        return null;
    }

    public ListenableFuture<Void> clearAll() {
        ListenableFuture submit = this.backgroundExecutor.submit(new Callable(ImmutableSet.m86of()) {
            private final /* synthetic */ Collection f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return ClearMissedCalls.this.lambda$markRead$4$ClearMissedCalls(this.f$1);
            }
        });
        ListenableFuture submit2 = this.uiThreadExecutor.submit(new Callable() {
            public final Object call() {
                return ClearMissedCalls.this.lambda$clearAll$0$ClearMissedCalls();
            }
        });
        return Futures.whenAllComplete(submit, submit2).call(new Callable(submit2) {
            private final /* synthetic */ ListenableFuture f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                ClearMissedCalls.lambda$clearAll$1(ListenableFuture.this, this.f$1);
                return null;
            }
        }, MoreExecutors.directExecutor());
    }

    public ListenableFuture<Void> clearBySystemCallLogId(Collection<Long> collection) {
        ListenableFuture submit = this.backgroundExecutor.submit(new Callable(collection) {
            private final /* synthetic */ Collection f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return ClearMissedCalls.this.lambda$markRead$4$ClearMissedCalls(this.f$1);
            }
        });
        ListenableFuture submit2 = this.uiThreadExecutor.submit(new Callable(collection) {
            private final /* synthetic */ Collection f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return ClearMissedCalls.this.lambda$clearBySystemCallLogId$2$ClearMissedCalls(this.f$1);
            }
        });
        return Futures.whenAllComplete(submit, submit2).call(new Callable(submit2) {
            private final /* synthetic */ ListenableFuture f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                ClearMissedCalls.lambda$clearBySystemCallLogId$3(ListenableFuture.this, this.f$1);
                return null;
            }
        }, MoreExecutors.directExecutor());
    }

    public /* synthetic */ Void lambda$clearAll$0$ClearMissedCalls() throws Exception {
        R$style.cancelAllInGroup(this.appContext, "MissedCallGroup");
        return null;
    }

    public /* synthetic */ Void lambda$clearBySystemCallLogId$2$ClearMissedCalls(Collection collection) throws Exception {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            R$style.cancelSingle(this.appContext, CallLog.Calls.CONTENT_URI.buildUpon().appendPath(Long.toString(((Long) it.next()).longValue())).build());
        }
        return null;
    }

    public /* synthetic */ Void lambda$markRead$4$ClearMissedCalls(Collection collection) throws Exception {
        int i = 0;
        if (!R$dimen.isUserUnlocked(this.appContext)) {
            LogUtil.m8e("ClearMissedCalls.markRead", "locked", new Object[0]);
            return null;
        } else if (!PermissionsUtil.hasCallLogWritePermissions(this.appContext)) {
            LogUtil.m8e("ClearMissedCalls.markRead", "no permission", new Object[0]);
            return null;
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_read", 1);
            Selection.Builder builder = Selection.builder();
            Selection.Builder buildUpon = Selection.column("is_read").mo5867is("=", 0).buildUpon();
            buildUpon.mo5864or(Selection.column("is_read").mo5866is("IS NULL"));
            builder.and(buildUpon.build());
            builder.and(Selection.column("type").mo5867is("=", 3));
            if (!collection.isEmpty()) {
                Selection.Column column = Selection.column("_id");
                String[] strArr = new String[collection.size()];
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    strArr[i] = Long.toString(((Long) it.next()).longValue());
                    i++;
                }
                builder.and(column.mo5865in(Arrays.asList(strArr)));
            }
            Selection build = builder.build();
            this.appContext.getContentResolver().update(CallLog.Calls.CONTENT_URI, contentValues, build.getSelection(), build.getSelectionArgs());
            return null;
        }
    }
}
