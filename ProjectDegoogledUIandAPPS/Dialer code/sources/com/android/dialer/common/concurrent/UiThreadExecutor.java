package com.android.dialer.common.concurrent;

import com.google.common.util.concurrent.AbstractListeningExecutorService;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class UiThreadExecutor extends AbstractListeningExecutorService {
    static /* synthetic */ void lambda$submit$0(SettableFuture settableFuture, Callable callable) {
        try {
            settableFuture.set(callable.call());
        } catch (Exception e) {
            settableFuture.setException(e);
            throw new RuntimeException(e);
        }
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    public void execute(Runnable runnable) {
        DialerExecutorModule.postOnUiThread(runnable);
    }

    public boolean isShutdown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    public <V> ListenableFuture<V> submit(Callable<V> callable) {
        SettableFuture create = SettableFuture.create();
        DialerExecutorModule.postOnUiThread(new Runnable(callable) {
            private final /* synthetic */ Callable f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                UiThreadExecutor.lambda$submit$0(SettableFuture.this, this.f$1);
            }
        });
        return create;
    }

    /* renamed from: submit  reason: collision with other method in class */
    public Future m109submit(Callable callable) {
        SettableFuture create = SettableFuture.create();
        DialerExecutorModule.postOnUiThread(new Runnable(callable) {
            private final /* synthetic */ Callable f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                UiThreadExecutor.lambda$submit$0(SettableFuture.this, this.f$1);
            }
        });
        return create;
    }
}
