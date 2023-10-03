package com.android.dialer.calllog;

import android.content.SharedPreferences;
import com.android.dialer.calllog.RefreshAnnotatedCallLogWorker;
import com.android.dialer.common.LogUtil;
import com.google.common.base.Function;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;

public final class AnnotatedCallLogMigrator {
    private final ListeningExecutorService backgroundExecutor;
    private final RefreshAnnotatedCallLogWorker refreshAnnotatedCallLogWorker;
    private final SharedPreferences sharedPreferences;

    AnnotatedCallLogMigrator(SharedPreferences sharedPreferences2, ListeningExecutorService listeningExecutorService, RefreshAnnotatedCallLogWorker refreshAnnotatedCallLogWorker2) {
        this.sharedPreferences = sharedPreferences2;
        this.backgroundExecutor = listeningExecutorService;
        this.refreshAnnotatedCallLogWorker = refreshAnnotatedCallLogWorker2;
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> clearData() {
        return this.backgroundExecutor.submit(new Callable() {
            public final Object call() {
                return AnnotatedCallLogMigrator.this.lambda$clearData$3$AnnotatedCallLogMigrator();
            }
        });
    }

    public /* synthetic */ Void lambda$clearData$3$AnnotatedCallLogMigrator() throws Exception {
        this.sharedPreferences.edit().remove("annotatedCallLogMigratorMigrated").apply();
        return null;
    }

    public /* synthetic */ Void lambda$migrate$0$AnnotatedCallLogMigrator(RefreshAnnotatedCallLogWorker.RefreshResult refreshResult) {
        this.sharedPreferences.edit().putBoolean("annotatedCallLogMigratorMigrated", true).apply();
        return null;
    }

    public /* synthetic */ ListenableFuture lambda$migrate$1$AnnotatedCallLogMigrator(Boolean bool) throws Exception {
        if (!bool.booleanValue()) {
            return Futures.immediateFuture(null);
        }
        LogUtil.m9i("AnnotatedCallLogMigrator.migrate", "migrating annotated call log", new Object[0]);
        return Futures.transform(this.refreshAnnotatedCallLogWorker.refreshWithoutDirtyCheck(), new Function() {
            public final Object apply(Object obj) {
                return AnnotatedCallLogMigrator.this.lambda$migrate$0$AnnotatedCallLogMigrator((RefreshAnnotatedCallLogWorker.RefreshResult) obj);
            }
        }, MoreExecutors.directExecutor());
    }

    public /* synthetic */ Boolean lambda$shouldMigrate$2$AnnotatedCallLogMigrator() throws Exception {
        return Boolean.valueOf(!this.sharedPreferences.getBoolean("annotatedCallLogMigratorMigrated", false));
    }

    public ListenableFuture<Void> migrate() {
        return Futures.transformAsync(this.backgroundExecutor.submit(new Callable() {
            public final Object call() {
                return AnnotatedCallLogMigrator.this.lambda$shouldMigrate$2$AnnotatedCallLogMigrator();
            }
        }), new AsyncFunction() {
            public final ListenableFuture apply(Object obj) {
                return AnnotatedCallLogMigrator.this.lambda$migrate$1$AnnotatedCallLogMigrator((Boolean) obj);
            }
        }, MoreExecutors.directExecutor());
    }
}
