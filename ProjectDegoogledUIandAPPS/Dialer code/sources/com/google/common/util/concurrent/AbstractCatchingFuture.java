package com.google.common.util.concurrent;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.util.concurrent.AbstractFuture;
import java.lang.Throwable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

abstract class AbstractCatchingFuture<V, X extends Throwable, F, T> extends AbstractFuture.TrustedFuture<V> implements Runnable {
    Class<X> exceptionType;
    F fallback;
    ListenableFuture<? extends V> inputFuture;

    private static final class CatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, Function<? super X, ? extends V>, V> {
        CatchingFuture(ListenableFuture<? extends V> listenableFuture, Class<X> cls, Function<? super X, ? extends V> function) {
            super(listenableFuture, cls, function);
        }

        /* access modifiers changed from: package-private */
        public void setResult(V v) {
            set(v);
        }
    }

    AbstractCatchingFuture(ListenableFuture<? extends V> listenableFuture, Class<X> cls, F f) {
        if (listenableFuture != null) {
            this.inputFuture = listenableFuture;
            if (cls != null) {
                this.exceptionType = cls;
                if (f != null) {
                    this.fallback = f;
                    return;
                }
                throw new NullPointerException();
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    static <V, X extends Throwable> ListenableFuture<V> create(ListenableFuture<? extends V> listenableFuture, Class<X> cls, Function<? super X, ? extends V> function, Executor executor) {
        CatchingFuture catchingFuture = new CatchingFuture(listenableFuture, cls, function);
        listenableFuture.addListener(catchingFuture, MoreExecutors.rejectionPropagatingExecutor(executor, catchingFuture));
        return catchingFuture;
    }

    /* access modifiers changed from: protected */
    public final void afterDone() {
        maybePropagateCancellation(this.inputFuture);
        this.inputFuture = null;
        this.exceptionType = null;
        this.fallback = null;
    }

    /* access modifiers changed from: protected */
    public String pendingToString() {
        ListenableFuture<? extends V> listenableFuture = this.inputFuture;
        Class<X> cls = this.exceptionType;
        F f = this.fallback;
        if (listenableFuture == null || cls == null || f == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("input=[");
        sb.append(listenableFuture);
        sb.append("], exceptionType=[");
        sb.append(cls);
        sb.append("], fallback=[");
        return GeneratedOutlineSupport.outline11(sb, f, "]");
    }

    public final void run() {
        Throwable th;
        ListenableFuture<? extends V> listenableFuture = this.inputFuture;
        Class<X> cls = this.exceptionType;
        F f = this.fallback;
        boolean z = true;
        boolean z2 = (listenableFuture == null) | (cls == null);
        if (f != null) {
            z = false;
        }
        if (!(z | z2) && !isCancelled()) {
            Object obj = null;
            this.inputFuture = null;
            this.exceptionType = null;
            this.fallback = null;
            try {
                obj = Futures.getDone(listenableFuture);
                th = null;
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                MoreObjects.checkNotNull(cause);
                th = cause;
            } catch (Throwable th2) {
                th = th2;
            }
            if (th == null) {
                set(obj);
            } else if (!cls.isInstance(th)) {
                setException(th);
            } else {
                try {
                    CatchingFuture catchingFuture = (CatchingFuture) this;
                    setResult(((Function) f).apply(th));
                } catch (Throwable th3) {
                    setException(th3);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public abstract void setResult(T t);
}
