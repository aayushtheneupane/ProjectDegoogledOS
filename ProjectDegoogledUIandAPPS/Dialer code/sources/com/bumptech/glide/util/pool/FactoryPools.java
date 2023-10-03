package com.bumptech.glide.util.pool;

import android.support.p000v4.util.Pools$Pool;
import android.support.p000v4.util.Pools$SimplePool;
import android.support.p000v4.util.Pools$SynchronizedPool;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class FactoryPools {
    private static final Resetter<Object> EMPTY_RESETTER = new Resetter<Object>() {
        public void reset(Object obj) {
        }
    };

    public interface Factory<T> {
        T create();
    }

    private static final class FactoryPool<T> implements Pools$Pool<T> {
        private final Factory<T> factory;
        private final Pools$Pool<T> pool;
        private final Resetter<T> resetter;

        FactoryPool(Pools$Pool<T> pools$Pool, Factory<T> factory2, Resetter<T> resetter2) {
            this.pool = pools$Pool;
            this.factory = factory2;
            this.resetter = resetter2;
        }

        public T acquire() {
            T acquire = this.pool.acquire();
            if (acquire == null) {
                acquire = this.factory.create();
                if (Log.isLoggable("FactoryPools", 2)) {
                    String valueOf = String.valueOf(acquire.getClass());
                    StringBuilder sb = new StringBuilder(valueOf.length() + 12);
                    sb.append("Created new ");
                    sb.append(valueOf);
                    Log.v("FactoryPools", sb.toString());
                }
            }
            if (acquire instanceof Poolable) {
                ((Poolable) acquire).getVerifier().setRecycled(false);
            }
            return acquire;
        }

        public boolean release(T t) {
            if (t instanceof Poolable) {
                ((Poolable) t).getVerifier().setRecycled(true);
            }
            this.resetter.reset(t);
            return this.pool.release(t);
        }
    }

    public interface Poolable {
        StateVerifier getVerifier();
    }

    public interface Resetter<T> {
        void reset(T t);
    }

    private static <T> Pools$Pool<T> build(Pools$Pool<T> pools$Pool, Factory<T> factory, Resetter<T> resetter) {
        return new FactoryPool(pools$Pool, factory, resetter);
    }

    public static <T extends Poolable> Pools$Pool<T> simple(int i, Factory<T> factory) {
        return build(new Pools$SimplePool(i), factory);
    }

    public static <T extends Poolable> Pools$Pool<T> threadSafe(int i, Factory<T> factory) {
        return build(new Pools$SynchronizedPool(i), factory);
    }

    public static <T> Pools$Pool<List<T>> threadSafeList() {
        return build(new Pools$SynchronizedPool(20), new Factory<List<T>>() {
            public Object create() {
                return new ArrayList();
            }
        }, new Resetter<List<T>>() {
            public void reset(Object obj) {
                ((List) obj).clear();
            }
        });
    }

    private static <T extends Poolable> Pools$Pool<T> build(Pools$Pool<T> pools$Pool, Factory<T> factory) {
        return new FactoryPool(pools$Pool, factory, EMPTY_RESETTER);
    }
}
