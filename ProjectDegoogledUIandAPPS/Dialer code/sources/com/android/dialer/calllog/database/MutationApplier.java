package com.android.dialer.calllog.database;

import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.android.dialer.calllog.database.contract.AnnotatedCallLogContract;
import com.android.dialer.calllog.datasources.CallLogMutations;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.google.common.collect.Collections2;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class MutationApplier {
    private final ListeningExecutorService backgroundExecutorService;

    public MutationApplier(ListeningExecutorService listeningExecutorService) {
        this.backgroundExecutorService = listeningExecutorService;
    }

    public ListenableFuture<Void> applyToDatabase(CallLogMutations callLogMutations, Context context) {
        if (callLogMutations.isEmpty()) {
            return Futures.immediateFuture(null);
        }
        return this.backgroundExecutorService.submit(new Callable(callLogMutations, context) {
            private final /* synthetic */ CallLogMutations f$1;
            private final /* synthetic */ Context f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object call() {
                return MutationApplier.this.lambda$applyToDatabase$0$MutationApplier(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ Void lambda$applyToDatabase$0$MutationApplier(CallLogMutations callLogMutations, Context context) throws Exception {
        Assert.isWorkerThread();
        ArrayList arrayList = new ArrayList();
        if (!callLogMutations.getInserts().isEmpty()) {
            LogUtil.m9i("MutationApplier.applyToDatabase", "inserting %d rows", Integer.valueOf(callLogMutations.getInserts().size()));
            for (Map.Entry next : callLogMutations.getInserts().entrySet()) {
                arrayList.add(ContentProviderOperation.newInsert(ContentUris.withAppendedId(AnnotatedCallLogContract.AnnotatedCallLog.CONTENT_URI, ((Long) next.getKey()).longValue())).withValues((ContentValues) next.getValue()).build());
            }
        }
        if (!callLogMutations.getUpdates().isEmpty()) {
            LogUtil.m9i("MutationApplier.applyToDatabase", "updating %d rows", Integer.valueOf(callLogMutations.getUpdates().size()));
            for (Map.Entry next2 : callLogMutations.getUpdates().entrySet()) {
                arrayList.add(ContentProviderOperation.newUpdate(ContentUris.withAppendedId(AnnotatedCallLogContract.AnnotatedCallLog.CONTENT_URI, ((Long) next2.getKey()).longValue())).withValues((ContentValues) next2.getValue()).build());
            }
        }
        if (!callLogMutations.getDeletes().isEmpty()) {
            LogUtil.m9i("MutationApplier.applyToDatabase", "deleting %d rows", Integer.valueOf(callLogMutations.getDeletes().size()));
            for (List<Long> next3 : Collections2.partition(callLogMutations.getDeletes(), 999)) {
                String[] strArr = new String[next3.size()];
                Arrays.fill(strArr, "?");
                String str = "_id in (" + TextUtils.join(",", strArr) + ")";
                String[] strArr2 = new String[next3.size()];
                int i = 0;
                for (Long longValue : next3) {
                    strArr2[i] = String.valueOf(longValue.longValue());
                    i++;
                }
                arrayList.add(ContentProviderOperation.newDelete(AnnotatedCallLogContract.AnnotatedCallLog.CONTENT_URI).withSelection(str, strArr2).build());
            }
        }
        context.getContentResolver().applyBatch(AnnotatedCallLogContract.AUTHORITY, arrayList);
        return null;
    }
}
