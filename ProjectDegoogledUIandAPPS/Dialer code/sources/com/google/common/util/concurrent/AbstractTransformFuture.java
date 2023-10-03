package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.util.concurrent.AbstractFuture;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

abstract class AbstractTransformFuture<I, O, F, T> extends AbstractFuture.TrustedFuture<O> implements Runnable {
    F function;
    ListenableFuture<? extends I> inputFuture;

    private static final class AsyncTransformFuture<I, O> extends AbstractTransformFuture<I, O, AsyncFunction<? super I, ? extends O>, ListenableFuture<? extends O>> {
        AsyncTransformFuture(ListenableFuture<? extends I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction) {
            super(listenableFuture, asyncFunction);
        }

        /* access modifiers changed from: package-private */
        public Object doTransform(Object obj, Object obj2) throws Exception {
            ListenableFuture apply = ((AsyncFunction) obj).apply(obj2);
            MoreObjects.checkNotNull(apply, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)?");
            return apply;
        }

        /* access modifiers changed from: package-private */
        public void setResult(Object obj) {
            setFuture((ListenableFuture) obj);
        }
    }

    private static final class TransformFuture<I, O> extends AbstractTransformFuture<I, O, Function<? super I, ? extends O>, O> {
        TransformFuture(ListenableFuture<? extends I> listenableFuture, Function<? super I, ? extends O> function) {
            super(listenableFuture, function);
        }

        /* access modifiers changed from: package-private */
        public Object doTransform(Object obj, Object obj2) throws Exception {
            return ((Function) obj).apply(obj2);
        }

        /* access modifiers changed from: package-private */
        public void setResult(O o) {
            set(o);
        }
    }

    AbstractTransformFuture(ListenableFuture<? extends I> listenableFuture, F f) {
        if (listenableFuture != null) {
            this.inputFuture = listenableFuture;
            if (f != null) {
                this.function = f;
                return;
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    static <I, O> ListenableFuture<O> create(ListenableFuture<I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction, Executor executor) {
        if (executor != null) {
            AsyncTransformFuture asyncTransformFuture = new AsyncTransformFuture(listenableFuture, asyncFunction);
            listenableFuture.addListener(asyncTransformFuture, MoreExecutors.rejectionPropagatingExecutor(executor, asyncTransformFuture));
            return asyncTransformFuture;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: protected */
    public final void afterDone() {
        maybePropagateCancellation(this.inputFuture);
        this.inputFuture = null;
        this.function = null;
    }

    /* access modifiers changed from: package-private */
    public abstract T doTransform(F f, I i) throws Exception;

    /* access modifiers changed from: protected */
    public String pendingToString() {
        ListenableFuture<? extends I> listenableFuture = this.inputFuture;
        F f = this.function;
        if (listenableFuture == null || f == null) {
            return null;
        }
        return "inputFuture=[" + listenableFuture + "], function=[" + f + "]";
    }

    public final void run() {
        ListenableFuture<? extends I> listenableFuture = this.inputFuture;
        F f = this.function;
        boolean z = true;
        boolean isCancelled = isCancelled() | (listenableFuture == null);
        if (f != null) {
            z = false;
        }
        if (!isCancelled && !z) {
            this.inputFuture = null;
            this.function = null;
            try {
                try {
                    setResult(doTransform(f, Futures.getDone(listenableFuture)));
                } catch (UndeclaredThrowableException e) {
                    setException(e.getCause());
                } catch (Throwable th) {
                    setException(th);
                }
            } catch (CancellationException unused) {
                cancel(false);
            } catch (ExecutionException e2) {
                setException(e2.getCause());
            } catch (RuntimeException e3) {
                setException(e3);
            } catch (Error e4) {
                setException(e4);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public abstract void setResult(T t);

    static <I, O> ListenableFuture<O> create(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function2, Executor executor) {
        if (function2 != null) {
            TransformFuture transformFuture = new TransformFuture(listenableFuture, function2);
            listenableFuture.addListener(transformFuture, MoreExecutors.rejectionPropagatingExecutor(executor, transformFuture));
            return transformFuture;
        }
        throw new NullPointerException();
    }
}
