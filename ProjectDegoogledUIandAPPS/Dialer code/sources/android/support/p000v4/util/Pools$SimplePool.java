package android.support.p000v4.util;

/* renamed from: android.support.v4.util.Pools$SimplePool */
public class Pools$SimplePool<T> implements Pools$Pool<T> {
    private final Object[] mPool;
    private int mPoolSize;

    public Pools$SimplePool(int i) {
        if (i > 0) {
            this.mPool = new Object[i];
            return;
        }
        throw new IllegalArgumentException("The max pool size must be > 0");
    }

    public T acquire() {
        int i = this.mPoolSize;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        T[] tArr = this.mPool;
        T t = tArr[i2];
        tArr[i2] = null;
        this.mPoolSize = i - 1;
        return t;
    }

    public boolean release(T t) {
        boolean z;
        int i = 0;
        while (true) {
            if (i >= this.mPoolSize) {
                z = false;
                break;
            } else if (this.mPool[i] == t) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            int i2 = this.mPoolSize;
            Object[] objArr = this.mPool;
            if (i2 >= objArr.length) {
                return false;
            }
            objArr[i2] = t;
            this.mPoolSize = i2 + 1;
            return true;
        }
        throw new IllegalStateException("Already in the pool!");
    }
}
