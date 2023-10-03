package android.support.p000v4.util;

/* renamed from: android.support.v4.util.Pools$SynchronizedPool */
public class Pools$SynchronizedPool<T> extends Pools$SimplePool<T> {
    private final Object mLock = new Object();

    public Pools$SynchronizedPool(int i) {
        super(i);
    }

    public T acquire() {
        T acquire;
        synchronized (this.mLock) {
            acquire = super.acquire();
        }
        return acquire;
    }

    public boolean release(T t) {
        boolean release;
        synchronized (this.mLock) {
            release = super.release(t);
        }
        return release;
    }
}
