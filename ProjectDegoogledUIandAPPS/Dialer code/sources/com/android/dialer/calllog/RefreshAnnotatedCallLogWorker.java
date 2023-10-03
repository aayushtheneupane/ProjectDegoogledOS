package com.android.dialer.calllog;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.dialer.calllog.database.MutationApplier;
import com.android.dialer.calllog.datasources.CallLogDataSource;
import com.android.dialer.calllog.datasources.CallLogMutations;
import com.android.dialer.calllog.datasources.DataSources;
import com.android.dialer.calllog.datasources.systemcalllog.SystemCallLogDataSource;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultFutureCallback;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.common.concurrent.DialerFutureSerializer;
import com.android.dialer.metrics.FutureTimer;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class RefreshAnnotatedCallLogWorker {
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutorService;
    private final CallLogCacheUpdater callLogCacheUpdater;
    private final CallLogState callLogState;
    private final DataSources dataSources;
    private final DialerFutureSerializer dialerFutureSerializer = new DialerFutureSerializer();
    private final FutureTimer futureTimer;
    private final ListeningExecutorService lightweightExecutorService;
    private final MutationApplier mutationApplier;
    private final SharedPreferences sharedPreferences;

    public enum RefreshResult {
        NOT_DIRTY,
        REBUILT_BUT_NO_CHANGES_NEEDED,
        REBUILT_AND_CHANGES_NEEDED
    }

    RefreshAnnotatedCallLogWorker(Context context, DataSources dataSources2, SharedPreferences sharedPreferences2, MutationApplier mutationApplier2, FutureTimer futureTimer2, CallLogState callLogState2, CallLogCacheUpdater callLogCacheUpdater2, ListeningExecutorService listeningExecutorService, ListeningExecutorService listeningExecutorService2) {
        this.appContext = context;
        this.dataSources = dataSources2;
        this.sharedPreferences = sharedPreferences2;
        this.mutationApplier = mutationApplier2;
        this.futureTimer = futureTimer2;
        this.callLogState = callLogState2;
        this.callLogCacheUpdater = callLogCacheUpdater2;
        this.backgroundExecutorService = listeningExecutorService;
        this.lightweightExecutorService = listeningExecutorService2;
    }

    private static String eventNameForFill(CallLogDataSource callLogDataSource, boolean z) {
        return String.format(!z ? "%s.Initial.Fill" : "%s.Fill", new Object[]{callLogDataSource.getLoggingName()});
    }

    /* access modifiers changed from: private */
    public ListenableFuture<RefreshResult> rebuild(boolean z) {
        CallLogMutations callLogMutations = new CallLogMutations();
        SystemCallLogDataSource systemCallLogDataSource = ((CallLogModule$1) this.dataSources).val$systemCallLogDataSource;
        ListenableFuture fill = systemCallLogDataSource.fill(callLogMutations);
        this.futureTimer.applyTiming(fill, eventNameForFill(systemCallLogDataSource, z));
        ImmutableList immutableList = ((CallLogModule$1) this.dataSources).val$allDataSources;
        UnmodifiableIterator it = immutableList.subList(1, immutableList.size()).iterator();
        while (it.hasNext()) {
            fill = Futures.transformAsync(fill, new AsyncFunction((CallLogDataSource) it.next(), callLogMutations, z) {
                private final /* synthetic */ CallLogDataSource f$1;
                private final /* synthetic */ CallLogMutations f$2;
                private final /* synthetic */ boolean f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final ListenableFuture apply(Object obj) {
                    return RefreshAnnotatedCallLogWorker.this.lambda$rebuild$4$RefreshAnnotatedCallLogWorker(this.f$1, this.f$2, this.f$3, (Void) obj);
                }
            }, this.lightweightExecutorService);
        }
        this.futureTimer.applyTiming(fill, !z ? "RefreshAnnotatedCallLog.Initial.Fill" : "RefreshAnnotatedCallLog.Fill");
        ListenableFuture transformAsync = Futures.transformAsync(fill, new AsyncFunction(callLogMutations, z) {
            private final /* synthetic */ CallLogMutations f$1;
            private final /* synthetic */ boolean f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final ListenableFuture apply(Object obj) {
                return RefreshAnnotatedCallLogWorker.this.lambda$rebuild$5$RefreshAnnotatedCallLogWorker(this.f$1, this.f$2, (Void) obj);
            }
        }, this.lightweightExecutorService);
        Futures.addCallback(Futures.transformAsync(transformAsync, new AsyncFunction(callLogMutations) {
            private final /* synthetic */ CallLogMutations f$1;

            {
                this.f$1 = r2;
            }

            public final ListenableFuture apply(Object obj) {
                return RefreshAnnotatedCallLogWorker.this.lambda$rebuild$6$RefreshAnnotatedCallLogWorker(this.f$1, (Void) obj);
            }
        }, MoreExecutors.directExecutor()), new DefaultFutureCallback(), MoreExecutors.directExecutor());
        return Futures.transform(Futures.transformAsync(transformAsync, new AsyncFunction(z) {
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final ListenableFuture apply(Object obj) {
                return RefreshAnnotatedCallLogWorker.this.lambda$rebuild$7$RefreshAnnotatedCallLogWorker(this.f$1, (Void) obj);
            }
        }, this.lightweightExecutorService), new Function(callLogMutations) {
            private final /* synthetic */ CallLogMutations f$1;

            {
                this.f$1 = r2;
            }

            public final Object apply(Object obj) {
                return RefreshAnnotatedCallLogWorker.this.lambda$rebuild$8$RefreshAnnotatedCallLogWorker(this.f$1, (List) obj);
            }
        }, this.backgroundExecutorService);
    }

    private ListenableFuture<RefreshResult> refresh(boolean z) {
        LogUtil.m9i("RefreshAnnotatedCallLogWorker.refresh", "submitting serialized refresh request", new Object[0]);
        return this.dialerFutureSerializer.submitAsync(new AsyncCallable(z) {
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final ListenableFuture call() {
                return RefreshAnnotatedCallLogWorker.this.lambda$refresh$0$RefreshAnnotatedCallLogWorker(this.f$1);
            }
        }, this.lightweightExecutorService);
    }

    /* renamed from: lambda$checkDirtyAndRebuildIfNecessary$1$RefreshAnnotatedCallLogWorker */
    public /* synthetic */ Boolean mo5589x922db97(boolean z) throws Exception {
        LogUtil.m9i("RefreshAnnotatedCallLogWorker.checkDirtyAndRebuildIfNecessary", "starting refresh flow", new Object[0]);
        if (!z) {
            return true;
        }
        boolean z2 = this.sharedPreferences.getBoolean("force_rebuild", true);
        if (z2) {
            LogUtil.m9i("RefreshAnnotatedCallLogWorker.checkDirtyAndRebuildIfNecessary", "annotated call log has been marked dirty or does not exist", new Object[0]);
        }
        return Boolean.valueOf(z2);
    }

    /* renamed from: lambda$checkDirtyAndRebuildIfNecessary$2$RefreshAnnotatedCallLogWorker */
    public /* synthetic */ ListenableFuture mo5590x1e0ba3d8(Boolean bool) throws Exception {
        if (bool == null) {
            throw new NullPointerException();
        } else if (bool.booleanValue()) {
            return Futures.immediateFuture(true);
        } else {
            ArrayList arrayList = new ArrayList();
            UnmodifiableIterator it = ((CallLogModule$1) this.dataSources).val$allDataSources.iterator();
            while (it.hasNext()) {
                CallLogDataSource callLogDataSource = (CallLogDataSource) it.next();
                ListenableFuture<Boolean> isDirty = callLogDataSource.isDirty();
                arrayList.add(isDirty);
                this.futureTimer.applyTiming(isDirty, String.format("%s.IsDirty", new Object[]{callLogDataSource.getLoggingName()}), 2);
            }
            ListenableFuture firstMatching = DialerExecutorModule.firstMatching(arrayList, $$Lambda$VbxFCRFe5CwNTJmvYo_2CX5U7Sg.INSTANCE, false);
            this.futureTimer.applyTiming(firstMatching, "RefreshAnnotatedCallLog.IsDirty", 2);
            return firstMatching;
        }
    }

    /* renamed from: lambda$checkDirtyAndRebuildIfNecessary$3$RefreshAnnotatedCallLogWorker */
    public /* synthetic */ ListenableFuture mo5591x32f46c19(Boolean bool) throws Exception {
        Object[] objArr = new Object[1];
        if (bool != null) {
            objArr[0] = bool;
            if (bool.booleanValue()) {
                return Futures.transformAsync(this.callLogState.isBuilt(), new AsyncFunction() {
                    public final ListenableFuture apply(Object obj) {
                        return RefreshAnnotatedCallLogWorker.this.rebuild(((Boolean) obj).booleanValue());
                    }
                }, MoreExecutors.directExecutor());
            }
            return Futures.immediateFuture(RefreshResult.NOT_DIRTY);
        }
        throw new NullPointerException();
    }

    public /* synthetic */ ListenableFuture lambda$rebuild$4$RefreshAnnotatedCallLogWorker(CallLogDataSource callLogDataSource, CallLogMutations callLogMutations, boolean z, Void voidR) throws Exception {
        ListenableFuture<Void> fill = callLogDataSource.fill(callLogMutations);
        this.futureTimer.applyTiming(fill, eventNameForFill(callLogDataSource, z));
        return fill;
    }

    public /* synthetic */ ListenableFuture lambda$rebuild$5$RefreshAnnotatedCallLogWorker(CallLogMutations callLogMutations, boolean z, Void voidR) throws Exception {
        ListenableFuture<Void> applyToDatabase = this.mutationApplier.applyToDatabase(callLogMutations, this.appContext);
        this.futureTimer.applyTiming(applyToDatabase, !z ? "RefreshAnnotatedCallLog.Initial.ApplyMutations" : "RefreshAnnotatedCallLog.ApplyMutations");
        return applyToDatabase;
    }

    public /* synthetic */ ListenableFuture lambda$rebuild$6$RefreshAnnotatedCallLogWorker(CallLogMutations callLogMutations, Void voidR) throws Exception {
        return this.callLogCacheUpdater.updateCache(callLogMutations);
    }

    public /* synthetic */ ListenableFuture lambda$rebuild$7$RefreshAnnotatedCallLogWorker(boolean z, Void voidR) throws Exception {
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator it = ((CallLogModule$1) this.dataSources).val$allDataSources.iterator();
        while (it.hasNext()) {
            CallLogDataSource callLogDataSource = (CallLogDataSource) it.next();
            ListenableFuture<Void> onSuccessfulFill = callLogDataSource.onSuccessfulFill();
            arrayList.add(onSuccessfulFill);
            this.futureTimer.applyTiming(onSuccessfulFill, String.format(!z ? "%s.Initial.OnSuccessfulFill" : "%s.OnSuccessfulFill", new Object[]{callLogDataSource.getLoggingName()}));
        }
        ListenableFuture allAsList = Futures.allAsList(arrayList);
        this.futureTimer.applyTiming(allAsList, !z ? "RefreshAnnotatedCallLog.Initial.OnSuccessfulFill" : "RefreshAnnotatedCallLog.OnSuccessfulFill");
        return allAsList;
    }

    public /* synthetic */ RefreshResult lambda$rebuild$8$RefreshAnnotatedCallLogWorker(CallLogMutations callLogMutations, List list) {
        this.sharedPreferences.edit().putBoolean("force_rebuild", false).apply();
        this.callLogState.markBuilt();
        if (callLogMutations.isEmpty()) {
            return RefreshResult.REBUILT_BUT_NO_CHANGES_NEEDED;
        }
        return RefreshResult.REBUILT_AND_CHANGES_NEEDED;
    }

    public /* synthetic */ ListenableFuture lambda$refresh$0$RefreshAnnotatedCallLogWorker(boolean z) throws Exception {
        return Futures.transformAsync(Futures.transformAsync(this.backgroundExecutorService.submit(new Callable(z) {
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return RefreshAnnotatedCallLogWorker.this.mo5589x922db97(this.f$1);
            }
        }), new AsyncFunction() {
            public final ListenableFuture apply(Object obj) {
                return RefreshAnnotatedCallLogWorker.this.mo5590x1e0ba3d8((Boolean) obj);
            }
        }, this.lightweightExecutorService), new AsyncFunction() {
            public final ListenableFuture apply(Object obj) {
                return RefreshAnnotatedCallLogWorker.this.mo5591x32f46c19((Boolean) obj);
            }
        }, this.lightweightExecutorService);
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<RefreshResult> refreshWithDirtyCheck() {
        return refresh(true);
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<RefreshResult> refreshWithoutDirtyCheck() {
        return refresh(false);
    }
}
