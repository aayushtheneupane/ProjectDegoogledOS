package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Futures {
    private static final AsyncFunction<ListenableFuture<Object>, Object> DEREFERENCER = new AsyncFunction<ListenableFuture<Object>, Object>() {
        public ListenableFuture<Object> apply(ListenableFuture<Object> listenableFuture) {
            return listenableFuture;
        }
    };
    private static final Ordering<Constructor<?>> WITH_STRING_PARAM_FIRST = Ordering.natural().onResultOf(new Function<Constructor<?>, Boolean>() {
        public Boolean apply(Constructor<?> constructor) {
            return Boolean.valueOf(Arrays.asList(constructor.getParameterTypes()).contains(String.class));
        }
    }).reverse();

    private interface FutureCombiner<V, C> {
        C combine(List<Optional<V>> list);
    }

    private Futures() {
    }

    public static <V, X extends Exception> CheckedFuture<V, X> makeChecked(ListenableFuture<V> listenableFuture, Function<? super Exception, X> function) {
        Preconditions.checkNotNull(listenableFuture);
        return new MappingCheckedFuture(listenableFuture, function);
    }

    private static abstract class ImmediateFuture<V> implements ListenableFuture<V> {
        private static final Logger log = Logger.getLogger(ImmediateFuture.class.getName());

        public boolean cancel(boolean z) {
            return false;
        }

        public abstract V get() throws ExecutionException;

        public boolean isCancelled() {
            return false;
        }

        public boolean isDone() {
            return true;
        }

        private ImmediateFuture() {
        }

        public void addListener(Runnable runnable, Executor executor) {
            Preconditions.checkNotNull(runnable, "Runnable was null.");
            Preconditions.checkNotNull(executor, "Executor was null.");
            try {
                executor.execute(runnable);
            } catch (RuntimeException e) {
                Logger logger = log;
                Level level = Level.SEVERE;
                logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e);
            }
        }

        public V get(long j, TimeUnit timeUnit) throws ExecutionException {
            Preconditions.checkNotNull(timeUnit);
            return get();
        }
    }

    private static class ImmediateSuccessfulFuture<V> extends ImmediateFuture<V> {
        private final V value;

        ImmediateSuccessfulFuture(V v) {
            super();
            this.value = v;
        }

        public V get() {
            return this.value;
        }
    }

    private static class ImmediateSuccessfulCheckedFuture<V, X extends Exception> extends ImmediateFuture<V> implements CheckedFuture<V, X> {
        private final V value;

        ImmediateSuccessfulCheckedFuture(V v) {
            super();
            this.value = v;
        }

        public V get() {
            return this.value;
        }

        public V checkedGet() {
            return this.value;
        }

        public V checkedGet(long j, TimeUnit timeUnit) {
            Preconditions.checkNotNull(timeUnit);
            return this.value;
        }
    }

    private static class ImmediateFailedFuture<V> extends ImmediateFuture<V> {
        private final Throwable thrown;

        ImmediateFailedFuture(Throwable th) {
            super();
            this.thrown = th;
        }

        public V get() throws ExecutionException {
            throw new ExecutionException(this.thrown);
        }
    }

    private static class ImmediateCancelledFuture<V> extends ImmediateFuture<V> {
        private final CancellationException thrown = new CancellationException("Immediate cancelled future.");

        public boolean isCancelled() {
            return true;
        }

        ImmediateCancelledFuture() {
            super();
        }

        public V get() {
            throw AbstractFuture.cancellationExceptionWithCause("Task was cancelled.", this.thrown);
        }
    }

    private static class ImmediateFailedCheckedFuture<V, X extends Exception> extends ImmediateFuture<V> implements CheckedFuture<V, X> {
        private final X thrown;

        ImmediateFailedCheckedFuture(X x) {
            super();
            this.thrown = x;
        }

        public V get() throws ExecutionException {
            throw new ExecutionException(this.thrown);
        }

        public V checkedGet() throws Exception {
            throw this.thrown;
        }

        public V checkedGet(long j, TimeUnit timeUnit) throws Exception {
            Preconditions.checkNotNull(timeUnit);
            throw this.thrown;
        }
    }

    public static <V> ListenableFuture<V> immediateFuture(V v) {
        return new ImmediateSuccessfulFuture(v);
    }

    public static <V, X extends Exception> CheckedFuture<V, X> immediateCheckedFuture(V v) {
        return new ImmediateSuccessfulCheckedFuture(v);
    }

    public static <V> ListenableFuture<V> immediateFailedFuture(Throwable th) {
        Preconditions.checkNotNull(th);
        return new ImmediateFailedFuture(th);
    }

    public static <V> ListenableFuture<V> immediateCancelledFuture() {
        return new ImmediateCancelledFuture();
    }

    public static <V, X extends Exception> CheckedFuture<V, X> immediateFailedCheckedFuture(X x) {
        Preconditions.checkNotNull(x);
        return new ImmediateFailedCheckedFuture(x);
    }

    public static <V> ListenableFuture<V> withFallback(ListenableFuture<? extends V> listenableFuture, FutureFallback<? extends V> futureFallback) {
        return withFallback(listenableFuture, futureFallback, MoreExecutors.directExecutor());
    }

    public static <V> ListenableFuture<V> withFallback(ListenableFuture<? extends V> listenableFuture, FutureFallback<? extends V> futureFallback, Executor executor) {
        Preconditions.checkNotNull(futureFallback);
        return new FallbackFuture(listenableFuture, futureFallback, executor);
    }

    private static class FallbackFuture<V> extends AbstractFuture<V> {
        /* access modifiers changed from: private */
        public volatile ListenableFuture<? extends V> running;

        FallbackFuture(ListenableFuture<? extends V> listenableFuture, final FutureFallback<? extends V> futureFallback, Executor executor) {
            this.running = listenableFuture;
            Futures.addCallback(this.running, new FutureCallback<V>() {
                public void onSuccess(V v) {
                    FallbackFuture.this.set(v);
                }

                public void onFailure(Throwable th) {
                    if (!FallbackFuture.this.isCancelled()) {
                        try {
                            ListenableFuture unused = FallbackFuture.this.running = futureFallback.create(th);
                            if (FallbackFuture.this.isCancelled()) {
                                FallbackFuture.this.running.cancel(FallbackFuture.this.wasInterrupted());
                            } else {
                                Futures.addCallback(FallbackFuture.this.running, new FutureCallback<V>() {
                                    public void onSuccess(V v) {
                                        FallbackFuture.this.set(v);
                                    }

                                    public void onFailure(Throwable th) {
                                        if (FallbackFuture.this.running.isCancelled()) {
                                            FallbackFuture.this.cancel(false);
                                        } else {
                                            FallbackFuture.this.setException(th);
                                        }
                                    }
                                }, MoreExecutors.directExecutor());
                            }
                        } catch (Throwable th2) {
                            FallbackFuture.this.setException(th2);
                        }
                    }
                }
            }, executor);
        }

        public boolean cancel(boolean z) {
            if (!super.cancel(z)) {
                return false;
            }
            this.running.cancel(z);
            return true;
        }
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction) {
        ChainingListenableFuture chainingListenableFuture = new ChainingListenableFuture(asyncFunction, listenableFuture);
        listenableFuture.addListener(chainingListenableFuture, MoreExecutors.directExecutor());
        return chainingListenableFuture;
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction, Executor executor) {
        Preconditions.checkNotNull(executor);
        ChainingListenableFuture chainingListenableFuture = new ChainingListenableFuture(asyncFunction, listenableFuture);
        listenableFuture.addListener(rejectionPropagatingRunnable(chainingListenableFuture, chainingListenableFuture, executor), MoreExecutors.directExecutor());
        return chainingListenableFuture;
    }

    private static Runnable rejectionPropagatingRunnable(final AbstractFuture<?> abstractFuture, final Runnable runnable, final Executor executor) {
        return new Runnable() {
            public void run() {
                final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
                try {
                    executor.execute(new Runnable() {
                        public void run() {
                            atomicBoolean.set(false);
                            runnable.run();
                        }
                    });
                } catch (RejectedExecutionException e) {
                    if (atomicBoolean.get()) {
                        abstractFuture.setException(e);
                    }
                }
            }
        };
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function) {
        Preconditions.checkNotNull(function);
        ChainingListenableFuture chainingListenableFuture = new ChainingListenableFuture(asAsyncFunction(function), listenableFuture);
        listenableFuture.addListener(chainingListenableFuture, MoreExecutors.directExecutor());
        return chainingListenableFuture;
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function, Executor executor) {
        Preconditions.checkNotNull(function);
        return transform(listenableFuture, asAsyncFunction(function), executor);
    }

    private static <I, O> AsyncFunction<I, O> asAsyncFunction(final Function<? super I, ? extends O> function) {
        return new AsyncFunction<I, O>() {
            public ListenableFuture<O> apply(I i) {
                return Futures.immediateFuture(Function.this.apply(i));
            }
        };
    }

    public static <I, O> Future<O> lazyTransform(final Future<I> future, final Function<? super I, ? extends O> function) {
        Preconditions.checkNotNull(future);
        Preconditions.checkNotNull(function);
        return new Future<O>() {
            public boolean cancel(boolean z) {
                return future.cancel(z);
            }

            public boolean isCancelled() {
                return future.isCancelled();
            }

            public boolean isDone() {
                return future.isDone();
            }

            public O get() throws InterruptedException, ExecutionException {
                return applyTransformation(future.get());
            }

            public O get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
                return applyTransformation(future.get(j, timeUnit));
            }

            private O applyTransformation(I i) throws ExecutionException {
                try {
                    return function.apply(i);
                } catch (Throwable th) {
                    throw new ExecutionException(th);
                }
            }
        };
    }

    private static class ChainingListenableFuture<I, O> extends AbstractFuture<O> implements Runnable {
        private AsyncFunction<? super I, ? extends O> function;
        private ListenableFuture<? extends I> inputFuture;
        /* access modifiers changed from: private */
        public volatile ListenableFuture<? extends O> outputFuture;

        private ChainingListenableFuture(AsyncFunction<? super I, ? extends O> asyncFunction, ListenableFuture<? extends I> listenableFuture) {
            Preconditions.checkNotNull(asyncFunction);
            this.function = asyncFunction;
            Preconditions.checkNotNull(listenableFuture);
            this.inputFuture = listenableFuture;
        }

        public boolean cancel(boolean z) {
            if (!super.cancel(z)) {
                return false;
            }
            cancel(this.inputFuture, z);
            cancel(this.outputFuture, z);
            return true;
        }

        private void cancel(Future<?> future, boolean z) {
            if (future != null) {
                future.cancel(z);
            }
        }

        public void run() {
            try {
                try {
                    ListenableFuture<? extends O> apply = this.function.apply(Uninterruptibles.getUninterruptibly(this.inputFuture));
                    Preconditions.checkNotNull(apply, "AsyncFunction may not return null.");
                    final ListenableFuture<? extends O> listenableFuture = apply;
                    this.outputFuture = listenableFuture;
                    if (isCancelled()) {
                        listenableFuture.cancel(wasInterrupted());
                        this.outputFuture = null;
                        this.function = null;
                        this.inputFuture = null;
                        return;
                    }
                    listenableFuture.addListener(new Runnable() {
                        public void run() {
                            try {
                                ChainingListenableFuture.this.set(Uninterruptibles.getUninterruptibly(listenableFuture));
                            } catch (CancellationException unused) {
                                ChainingListenableFuture.this.cancel(false);
                                ListenableFuture unused2 = ChainingListenableFuture.this.outputFuture = null;
                                return;
                            } catch (ExecutionException e) {
                                ChainingListenableFuture.this.setException(e.getCause());
                            } catch (Throwable th) {
                                ListenableFuture unused3 = ChainingListenableFuture.this.outputFuture = null;
                                throw th;
                            }
                            ListenableFuture unused4 = ChainingListenableFuture.this.outputFuture = null;
                        }
                    }, MoreExecutors.directExecutor());
                    this.function = null;
                    this.inputFuture = null;
                } catch (UndeclaredThrowableException e) {
                    setException(e.getCause());
                } catch (Throwable th) {
                    this.function = null;
                    this.inputFuture = null;
                    throw th;
                }
            } catch (CancellationException unused) {
                cancel(false);
            } catch (ExecutionException e2) {
                setException(e2.getCause());
            }
        }
    }

    public static <V> ListenableFuture<V> dereference(ListenableFuture<? extends ListenableFuture<? extends V>> listenableFuture) {
        return transform(listenableFuture, DEREFERENCER);
    }

    public static <V> ListenableFuture<List<V>> allAsList(ListenableFuture<? extends V>... listenableFutureArr) {
        return listFuture(ImmutableList.copyOf((E[]) listenableFutureArr), true, MoreExecutors.directExecutor());
    }

    public static <V> ListenableFuture<List<V>> allAsList(Iterable<? extends ListenableFuture<? extends V>> iterable) {
        return listFuture(ImmutableList.copyOf(iterable), true, MoreExecutors.directExecutor());
    }

    private static final class WrappedCombiner<T> implements Callable<T> {
        final Callable<T> delegate;
        CombinerFuture<T> outputFuture;

        WrappedCombiner(Callable<T> callable) {
            Preconditions.checkNotNull(callable);
            this.delegate = callable;
        }

        public T call() throws Exception {
            try {
                return this.delegate.call();
            } catch (ExecutionException e) {
                this.outputFuture.setException(e.getCause());
                return null;
            } catch (CancellationException unused) {
                this.outputFuture.cancel(false);
                return null;
            }
        }
    }

    private static final class CombinerFuture<V> extends ListenableFutureTask<V> {
        ImmutableList<ListenableFuture<?>> futures;

        CombinerFuture(Callable<V> callable, ImmutableList<ListenableFuture<?>> immutableList) {
            super(callable);
            this.futures = immutableList;
        }

        public boolean cancel(boolean z) {
            ImmutableList<ListenableFuture<?>> immutableList = this.futures;
            if (!super.cancel(z)) {
                return false;
            }
            UnmodifiableIterator<ListenableFuture<?>> it = immutableList.iterator();
            while (it.hasNext()) {
                it.next().cancel(z);
            }
            return true;
        }

        /* access modifiers changed from: protected */
        public void done() {
            super.done();
            this.futures = null;
        }

        /* access modifiers changed from: protected */
        public void setException(Throwable th) {
            super.setException(th);
        }
    }

    public static <V> ListenableFuture<V> nonCancellationPropagating(ListenableFuture<V> listenableFuture) {
        return new NonCancellationPropagatingFuture(listenableFuture);
    }

    private static class NonCancellationPropagatingFuture<V> extends AbstractFuture<V> {
        NonCancellationPropagatingFuture(final ListenableFuture<V> listenableFuture) {
            Preconditions.checkNotNull(listenableFuture);
            Futures.addCallback(listenableFuture, new FutureCallback<V>() {
                public void onSuccess(V v) {
                    NonCancellationPropagatingFuture.this.set(v);
                }

                public void onFailure(Throwable th) {
                    if (listenableFuture.isCancelled()) {
                        NonCancellationPropagatingFuture.this.cancel(false);
                    } else {
                        NonCancellationPropagatingFuture.this.setException(th);
                    }
                }
            }, MoreExecutors.directExecutor());
        }
    }

    public static <V> ListenableFuture<List<V>> successfulAsList(ListenableFuture<? extends V>... listenableFutureArr) {
        return listFuture(ImmutableList.copyOf((E[]) listenableFutureArr), false, MoreExecutors.directExecutor());
    }

    public static <V> ListenableFuture<List<V>> successfulAsList(Iterable<? extends ListenableFuture<? extends V>> iterable) {
        return listFuture(ImmutableList.copyOf(iterable), false, MoreExecutors.directExecutor());
    }

    public static <T> ImmutableList<ListenableFuture<T>> inCompletionOrder(Iterable<? extends ListenableFuture<? extends T>> iterable) {
        final ConcurrentLinkedQueue newConcurrentLinkedQueue = Queues.newConcurrentLinkedQueue();
        ImmutableList.Builder builder = ImmutableList.builder();
        SerializingExecutor serializingExecutor = new SerializingExecutor(MoreExecutors.directExecutor());
        for (final ListenableFuture listenableFuture : iterable) {
            AsyncSettableFuture create = AsyncSettableFuture.create();
            newConcurrentLinkedQueue.add(create);
            listenableFuture.addListener(new Runnable() {
                public void run() {
                    ((AsyncSettableFuture) newConcurrentLinkedQueue.remove()).setFuture(listenableFuture);
                }
            }, serializingExecutor);
            builder.add((Object) create);
        }
        return builder.build();
    }

    public static <V> void addCallback(ListenableFuture<V> listenableFuture, FutureCallback<? super V> futureCallback) {
        addCallback(listenableFuture, futureCallback, MoreExecutors.directExecutor());
    }

    public static <V> void addCallback(final ListenableFuture<V> listenableFuture, final FutureCallback<? super V> futureCallback, Executor executor) {
        Preconditions.checkNotNull(futureCallback);
        listenableFuture.addListener(new Runnable() {
            public void run() {
                try {
                    futureCallback.onSuccess(Uninterruptibles.getUninterruptibly(ListenableFuture.this));
                } catch (ExecutionException e) {
                    futureCallback.onFailure(e.getCause());
                } catch (RuntimeException e2) {
                    futureCallback.onFailure(e2);
                } catch (Error e3) {
                    futureCallback.onFailure(e3);
                }
            }
        }, executor);
    }

    public static <V, X extends Exception> V get(Future<V> future, Class<X> cls) throws Exception {
        Preconditions.checkNotNull(future);
        Preconditions.checkArgument(!RuntimeException.class.isAssignableFrom(cls), "Futures.get exception type (%s) must not be a RuntimeException", cls);
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw newWithCause(cls, e);
        } catch (ExecutionException e2) {
            wrapAndThrowExceptionOrError(e2.getCause(), cls);
            throw null;
        }
    }

    public static <V, X extends Exception> V get(Future<V> future, long j, TimeUnit timeUnit, Class<X> cls) throws Exception {
        Preconditions.checkNotNull(future);
        Preconditions.checkNotNull(timeUnit);
        Preconditions.checkArgument(!RuntimeException.class.isAssignableFrom(cls), "Futures.get exception type (%s) must not be a RuntimeException", cls);
        try {
            return future.get(j, timeUnit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw newWithCause(cls, e);
        } catch (TimeoutException e2) {
            throw newWithCause(cls, e2);
        } catch (ExecutionException e3) {
            wrapAndThrowExceptionOrError(e3.getCause(), cls);
            throw null;
        }
    }

    private static <X extends Exception> void wrapAndThrowExceptionOrError(Throwable th, Class<X> cls) throws Exception {
        if (th instanceof Error) {
            throw new ExecutionError((Error) th);
        } else if (th instanceof RuntimeException) {
            throw new UncheckedExecutionException(th);
        } else {
            throw newWithCause(cls, th);
        }
    }

    public static <V> V getUnchecked(Future<V> future) {
        Preconditions.checkNotNull(future);
        try {
            return Uninterruptibles.getUninterruptibly(future);
        } catch (ExecutionException e) {
            wrapAndThrowUnchecked(e.getCause());
            throw null;
        }
    }

    private static void wrapAndThrowUnchecked(Throwable th) {
        if (th instanceof Error) {
            throw new ExecutionError((Error) th);
        }
        throw new UncheckedExecutionException(th);
    }

    private static <X extends Exception> X newWithCause(Class<X> cls, Throwable th) {
        for (Constructor newFromConstructor : preferringStrings(Arrays.asList(cls.getConstructors()))) {
            X x = (Exception) newFromConstructor(newFromConstructor, th);
            if (x != null) {
                if (x.getCause() == null) {
                    x.initCause(th);
                }
                return x;
            }
        }
        throw new IllegalArgumentException("No appropriate constructor for exception of type " + cls + " in response to chained exception", th);
    }

    private static <X extends Exception> List<Constructor<X>> preferringStrings(List<Constructor<X>> list) {
        return WITH_STRING_PARAM_FIRST.sortedCopy(list);
    }

    private static <X> X newFromConstructor(Constructor<X> constructor, Throwable th) {
        Class[] parameterTypes = constructor.getParameterTypes();
        Object[] objArr = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            Class cls = parameterTypes[i];
            if (cls.equals(String.class)) {
                objArr[i] = th.toString();
            } else if (!cls.equals(Throwable.class)) {
                return null;
            } else {
                objArr[i] = th;
            }
        }
        try {
            return constructor.newInstance(objArr);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    private static class CombinedFuture<V, C> extends AbstractFuture<C> {
        private static final Logger logger = Logger.getLogger(CombinedFuture.class.getName());
        final boolean allMustSucceed;
        FutureCombiner<V, C> combiner;
        ImmutableCollection<? extends ListenableFuture<? extends V>> futures;
        final AtomicInteger remaining;
        Set<Throwable> seenExceptions;
        final Object seenExceptionsLock = new Object();
        List<Optional<V>> values;

        CombinedFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> immutableCollection, boolean z, Executor executor, FutureCombiner<V, C> futureCombiner) {
            this.futures = immutableCollection;
            this.allMustSucceed = z;
            this.remaining = new AtomicInteger(immutableCollection.size());
            this.combiner = futureCombiner;
            this.values = Lists.newArrayListWithCapacity(immutableCollection.size());
            init(executor);
        }

        /* access modifiers changed from: protected */
        public void init(Executor executor) {
            addListener(new Runnable() {
                public void run() {
                    if (CombinedFuture.this.isCancelled()) {
                        UnmodifiableIterator<? extends ListenableFuture<? extends V>> it = CombinedFuture.this.futures.iterator();
                        while (it.hasNext()) {
                            ((ListenableFuture) it.next()).cancel(CombinedFuture.this.wasInterrupted());
                        }
                    }
                    CombinedFuture combinedFuture = CombinedFuture.this;
                    combinedFuture.futures = null;
                    combinedFuture.values = null;
                    combinedFuture.combiner = null;
                }
            }, MoreExecutors.directExecutor());
            if (this.futures.isEmpty()) {
                set(this.combiner.combine(ImmutableList.m19of()));
                return;
            }
            final int i = 0;
            for (int i2 = 0; i2 < this.futures.size(); i2++) {
                this.values.add((Object) null);
            }
            UnmodifiableIterator<? extends ListenableFuture<? extends V>> it = this.futures.iterator();
            while (it.hasNext()) {
                final ListenableFuture listenableFuture = (ListenableFuture) it.next();
                listenableFuture.addListener(new Runnable() {
                    public void run() {
                        CombinedFuture.this.setOneValue(i, listenableFuture);
                    }
                }, executor);
                i++;
            }
        }

        private void setExceptionAndMaybeLog(Throwable th) {
            boolean z;
            boolean z2;
            if (this.allMustSucceed) {
                z2 = super.setException(th);
                synchronized (this.seenExceptionsLock) {
                    if (this.seenExceptions == null) {
                        this.seenExceptions = Sets.newHashSet();
                    }
                    z = this.seenExceptions.add(th);
                }
            } else {
                z2 = false;
                z = true;
            }
            if ((th instanceof Error) || (this.allMustSucceed && !z2 && z)) {
                logger.log(Level.SEVERE, "input future failed.", th);
            }
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x004a, code lost:
            if (r1 != null) goto L_0x004c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x004c, code lost:
            set(r7.combine(r1));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0073, code lost:
            if (r1 != null) goto L_0x004c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x0091, code lost:
            if (r1 != null) goto L_0x004c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x00ae, code lost:
            if (r1 != null) goto L_0x004c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
            return;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:46:0x0094 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setOneValue(int r7, java.util.concurrent.Future<? extends V> r8) {
            /*
                r6 = this;
                java.lang.String r0 = "Less than 0 remaining futures"
                java.util.List<com.google.common.base.Optional<V>> r1 = r6.values
                boolean r2 = r6.isDone()
                r3 = 1
                r4 = 0
                if (r2 != 0) goto L_0x000e
                if (r1 != 0) goto L_0x0021
            L_0x000e:
                boolean r2 = r6.allMustSucceed
                if (r2 != 0) goto L_0x001b
                boolean r2 = r6.isCancelled()
                if (r2 == 0) goto L_0x0019
                goto L_0x001b
            L_0x0019:
                r2 = 0
                goto L_0x001c
            L_0x001b:
                r2 = 1
            L_0x001c:
                java.lang.String r5 = "Future was done before all dependencies completed"
                com.google.common.base.Preconditions.checkState(r2, r5)
            L_0x0021:
                boolean r2 = r8.isDone()     // Catch:{ CancellationException -> 0x0094, ExecutionException -> 0x0076, all -> 0x005c }
                java.lang.String r5 = "Tried to set value from future which is not done"
                com.google.common.base.Preconditions.checkState(r2, r5)     // Catch:{ CancellationException -> 0x0094, ExecutionException -> 0x0076, all -> 0x005c }
                java.lang.Object r8 = com.google.common.util.concurrent.Uninterruptibles.getUninterruptibly(r8)     // Catch:{ CancellationException -> 0x0094, ExecutionException -> 0x0076, all -> 0x005c }
                if (r1 == 0) goto L_0x0037
                com.google.common.base.Optional r8 = com.google.common.base.Optional.fromNullable(r8)     // Catch:{ CancellationException -> 0x0094, ExecutionException -> 0x0076, all -> 0x005c }
                r1.set(r7, r8)     // Catch:{ CancellationException -> 0x0094, ExecutionException -> 0x0076, all -> 0x005c }
            L_0x0037:
                java.util.concurrent.atomic.AtomicInteger r7 = r6.remaining
                int r7 = r7.decrementAndGet()
                if (r7 < 0) goto L_0x0040
                goto L_0x0041
            L_0x0040:
                r3 = 0
            L_0x0041:
                com.google.common.base.Preconditions.checkState(r3, r0)
                if (r7 != 0) goto L_0x00b1
                com.google.common.util.concurrent.Futures$FutureCombiner<V, C> r7 = r6.combiner
                if (r7 == 0) goto L_0x0054
                if (r1 == 0) goto L_0x0054
            L_0x004c:
                java.lang.Object r7 = r7.combine(r1)
                r6.set(r7)
                goto L_0x00b1
            L_0x0054:
                boolean r7 = r6.isDone()
                com.google.common.base.Preconditions.checkState(r7)
                goto L_0x00b1
            L_0x005c:
                r7 = move-exception
                r6.setExceptionAndMaybeLog(r7)     // Catch:{ all -> 0x00b2 }
                java.util.concurrent.atomic.AtomicInteger r7 = r6.remaining
                int r7 = r7.decrementAndGet()
                if (r7 < 0) goto L_0x0069
                goto L_0x006a
            L_0x0069:
                r3 = 0
            L_0x006a:
                com.google.common.base.Preconditions.checkState(r3, r0)
                if (r7 != 0) goto L_0x00b1
                com.google.common.util.concurrent.Futures$FutureCombiner<V, C> r7 = r6.combiner
                if (r7 == 0) goto L_0x0054
                if (r1 == 0) goto L_0x0054
                goto L_0x004c
            L_0x0076:
                r7 = move-exception
                java.lang.Throwable r7 = r7.getCause()     // Catch:{ all -> 0x00b2 }
                r6.setExceptionAndMaybeLog(r7)     // Catch:{ all -> 0x00b2 }
                java.util.concurrent.atomic.AtomicInteger r7 = r6.remaining
                int r7 = r7.decrementAndGet()
                if (r7 < 0) goto L_0x0087
                goto L_0x0088
            L_0x0087:
                r3 = 0
            L_0x0088:
                com.google.common.base.Preconditions.checkState(r3, r0)
                if (r7 != 0) goto L_0x00b1
                com.google.common.util.concurrent.Futures$FutureCombiner<V, C> r7 = r6.combiner
                if (r7 == 0) goto L_0x0054
                if (r1 == 0) goto L_0x0054
                goto L_0x004c
            L_0x0094:
                boolean r7 = r6.allMustSucceed     // Catch:{ all -> 0x00b2 }
                if (r7 == 0) goto L_0x009b
                r6.cancel(r4)     // Catch:{ all -> 0x00b2 }
            L_0x009b:
                java.util.concurrent.atomic.AtomicInteger r7 = r6.remaining
                int r7 = r7.decrementAndGet()
                if (r7 < 0) goto L_0x00a4
                goto L_0x00a5
            L_0x00a4:
                r3 = 0
            L_0x00a5:
                com.google.common.base.Preconditions.checkState(r3, r0)
                if (r7 != 0) goto L_0x00b1
                com.google.common.util.concurrent.Futures$FutureCombiner<V, C> r7 = r6.combiner
                if (r7 == 0) goto L_0x0054
                if (r1 == 0) goto L_0x0054
                goto L_0x004c
            L_0x00b1:
                return
            L_0x00b2:
                r7 = move-exception
                java.util.concurrent.atomic.AtomicInteger r8 = r6.remaining
                int r8 = r8.decrementAndGet()
                if (r8 < 0) goto L_0x00bc
                goto L_0x00bd
            L_0x00bc:
                r3 = 0
            L_0x00bd:
                com.google.common.base.Preconditions.checkState(r3, r0)
                if (r8 != 0) goto L_0x00d7
                com.google.common.util.concurrent.Futures$FutureCombiner<V, C> r8 = r6.combiner
                if (r8 == 0) goto L_0x00d0
                if (r1 == 0) goto L_0x00d0
                java.lang.Object r8 = r8.combine(r1)
                r6.set(r8)
                goto L_0x00d7
            L_0x00d0:
                boolean r8 = r6.isDone()
                com.google.common.base.Preconditions.checkState(r8)
            L_0x00d7:
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Futures.CombinedFuture.setOneValue(int, java.util.concurrent.Future):void");
        }
    }

    private static <V> ListenableFuture<List<V>> listFuture(ImmutableList<ListenableFuture<? extends V>> immutableList, boolean z, Executor executor) {
        return new CombinedFuture(immutableList, z, executor, new FutureCombiner<V, List<V>>() {
            public List<V> combine(List<Optional<V>> list) {
                ArrayList newArrayList = Lists.newArrayList();
                Iterator<Optional<V>> it = list.iterator();
                while (it.hasNext()) {
                    Optional next = it.next();
                    newArrayList.add(next != null ? next.orNull() : null);
                }
                return Collections.unmodifiableList(newArrayList);
            }
        });
    }

    private static class MappingCheckedFuture<V, X extends Exception> extends AbstractCheckedFuture<V, X> {
        final Function<? super Exception, X> mapper;

        MappingCheckedFuture(ListenableFuture<V> listenableFuture, Function<? super Exception, X> function) {
            super(listenableFuture);
            Preconditions.checkNotNull(function);
            this.mapper = function;
        }

        /* access modifiers changed from: protected */
        public X mapException(Exception exc) {
            return (Exception) this.mapper.apply(exc);
        }
    }
}
