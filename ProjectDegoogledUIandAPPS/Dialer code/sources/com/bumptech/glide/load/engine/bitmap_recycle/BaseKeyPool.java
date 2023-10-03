package com.bumptech.glide.load.engine.bitmap_recycle;

import com.bumptech.glide.load.engine.bitmap_recycle.Poolable;
import com.bumptech.glide.util.Util;
import java.util.Queue;

abstract class BaseKeyPool<T extends Poolable> {
    private final Queue<T> keyPool = Util.createQueue(20);

    BaseKeyPool() {
    }

    /* access modifiers changed from: package-private */
    public abstract T create();

    /* access modifiers changed from: package-private */
    public T get() {
        T t = (Poolable) this.keyPool.poll();
        return t == null ? create() : t;
    }

    public void offer(T t) {
        if (this.keyPool.size() < 20) {
            this.keyPool.offer(t);
        }
    }
}
