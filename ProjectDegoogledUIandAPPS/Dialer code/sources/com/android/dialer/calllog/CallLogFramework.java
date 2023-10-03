package com.android.dialer.calllog;

import android.content.Context;
import android.content.Intent;
import android.support.p000v4.content.LocalBroadcastManager;
import com.android.dialer.calllog.datasources.CallLogDataSource;
import com.android.dialer.calllog.datasources.DataSources;
import com.android.dialer.common.LogUtil;
import com.google.common.base.Function;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.List;

public final class CallLogFramework {
    private final AnnotatedCallLogMigrator annotatedCallLogMigrator;
    private final Context appContext;
    private final CallLogState callLogState;
    private final DataSources dataSources;
    private final ListeningExecutorService uiExecutor;

    CallLogFramework(Context context, DataSources dataSources2, AnnotatedCallLogMigrator annotatedCallLogMigrator2, ListeningExecutorService listeningExecutorService, CallLogState callLogState2) {
        this.appContext = context;
        this.dataSources = dataSources2;
        this.annotatedCallLogMigrator = annotatedCallLogMigrator2;
        this.uiExecutor = listeningExecutorService;
        this.callLogState = callLogState2;
    }

    public ListenableFuture<Void> disable() {
        ListenableFuture[] listenableFutureArr = new ListenableFuture[2];
        LogUtil.enterBlock("CallLogFramework.disableDataSources");
        UnmodifiableIterator it = ((CallLogModule$1) this.dataSources).val$allDataSources.iterator();
        while (it.hasNext()) {
            ((CallLogDataSource) it.next()).unregisterContentObservers();
        }
        this.callLogState.clearData();
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator it2 = ((CallLogModule$1) this.dataSources).val$allDataSources.iterator();
        while (it2.hasNext()) {
            arrayList.add(((CallLogDataSource) it2.next()).clearData());
        }
        listenableFutureArr[0] = Futures.transform(Futures.allAsList(arrayList), new Function() {
            public final Object apply(Object obj) {
                return CallLogFramework.this.lambda$disableDataSources$1$CallLogFramework((List) obj);
            }
        }, this.uiExecutor);
        listenableFutureArr[1] = this.annotatedCallLogMigrator.clearData();
        return Futures.transform(Futures.allAsList((ListenableFuture<? extends V>[]) listenableFutureArr), $$Lambda$CallLogFramework$0jl7tmVuFL8epTvm5v0UbvBcJfU.INSTANCE, MoreExecutors.directExecutor());
    }

    public ListenableFuture<Void> enable() {
        registerContentObservers();
        return this.annotatedCallLogMigrator.migrate();
    }

    public /* synthetic */ Void lambda$disableDataSources$1$CallLogFramework(List list) {
        LocalBroadcastManager.getInstance(this.appContext).sendBroadcastSync(new Intent("disableCallLogFramework"));
        return null;
    }

    public void registerContentObservers() {
        LogUtil.enterBlock("CallLogFramework.registerContentObservers");
        UnmodifiableIterator it = ((CallLogModule$1) this.dataSources).val$allDataSources.iterator();
        while (it.hasNext()) {
            ((CallLogDataSource) it.next()).registerContentObservers();
        }
    }
}
