package androidx.core.util;

public final class Pools {

    public interface Pool {
        Object acquire();

        boolean release(Object obj);
    }

    public class SimplePool implements Pool {
        private final Object[] mPool;
        private int mPoolSize;

        public SimplePool(int i) {
            if (i > 0) {
                this.mPool = new Object[i];
                return;
            }
            throw new IllegalArgumentException("The max pool size must be > 0");
        }

        private boolean isInPool(Object obj) {
            for (int i = 0; i < this.mPoolSize; i++) {
                if (this.mPool[i] == obj) {
                    return true;
                }
            }
            return false;
        }

        public Object acquire() {
            int i = this.mPoolSize;
            if (i <= 0) {
                return null;
            }
            int i2 = i - 1;
            Object[] objArr = this.mPool;
            Object obj = objArr[i2];
            objArr[i2] = null;
            this.mPoolSize = i - 1;
            return obj;
        }

        public boolean release(Object obj) {
            if (!isInPool(obj)) {
                int i = this.mPoolSize;
                Object[] objArr = this.mPool;
                if (i >= objArr.length) {
                    return false;
                }
                objArr[i] = obj;
                this.mPoolSize = i + 1;
                return true;
            }
            throw new IllegalStateException("Already in the pool!");
        }
    }

    public class SynchronizedPool extends SimplePool {
        private final Object mLock = new Object();

        public SynchronizedPool(int i) {
            super(i);
        }

        public Object acquire() {
            Object acquire;
            synchronized (this.mLock) {
                acquire = super.acquire();
            }
            return acquire;
        }

        public boolean release(Object obj) {
            boolean release;
            synchronized (this.mLock) {
                release = super.release(obj);
            }
            return release;
        }
    }

    private Pools() {
    }
}
