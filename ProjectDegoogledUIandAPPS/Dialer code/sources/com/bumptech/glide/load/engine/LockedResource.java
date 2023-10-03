package com.bumptech.glide.load.engine;

import android.support.p000v4.util.Pools$Pool;
import android.support.p002v7.appcompat.R$style;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;

final class LockedResource<Z> implements Resource<Z>, FactoryPools.Poolable {
    private static final Pools$Pool<LockedResource<?>> POOL = FactoryPools.threadSafe(20, new FactoryPools.Factory<LockedResource<?>>() {
        public Object create() {
            return new LockedResource();
        }
    });
    private boolean isLocked;
    private boolean isRecycled;
    private final StateVerifier stateVerifier = StateVerifier.newInstance();
    private Resource<Z> toWrap;

    LockedResource() {
    }

    static <Z> LockedResource<Z> obtain(Resource<Z> resource) {
        LockedResource<Z> acquire = POOL.acquire();
        R$style.checkNotNull(acquire, "Argument must not be null");
        acquire.isRecycled = false;
        acquire.isLocked = true;
        acquire.toWrap = resource;
        return acquire;
    }

    public Z get() {
        return this.toWrap.get();
    }

    public Class<Z> getResourceClass() {
        return this.toWrap.getResourceClass();
    }

    public int getSize() {
        return this.toWrap.getSize();
    }

    public StateVerifier getVerifier() {
        return this.stateVerifier;
    }

    public synchronized void recycle() {
        this.stateVerifier.throwIfRecycled();
        this.isRecycled = true;
        if (!this.isLocked) {
            this.toWrap.recycle();
            this.toWrap = null;
            POOL.release(this);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void unlock() {
        this.stateVerifier.throwIfRecycled();
        if (this.isLocked) {
            this.isLocked = false;
            if (this.isRecycled) {
                recycle();
            }
        } else {
            throw new IllegalStateException("Already unlocked");
        }
    }
}
