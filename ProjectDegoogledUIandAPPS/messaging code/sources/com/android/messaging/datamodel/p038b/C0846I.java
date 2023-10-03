package com.android.messaging.datamodel.p038b;

import android.os.SystemClock;
import com.android.messaging.util.C1424b;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.android.messaging.datamodel.b.I */
public abstract class C0846I {

    /* renamed from: TC */
    private int f1099TC = 0;

    /* renamed from: UC */
    private long f1100UC;
    private final String mKey;
    private final ReentrantLock mLock;

    public C0846I(String str) {
        new ArrayList();
        this.mLock = new ReentrantLock();
        this.mKey = str;
    }

    /* renamed from: Oh */
    public void mo6100Oh() {
        this.mLock.lock();
        try {
            this.f1099TC++;
            this.f1100UC = SystemClock.elapsedRealtime();
        } finally {
            this.mLock.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ph */
    public void mo6101Ph() {
        C1424b.m3592ia(this.mLock.isHeldByCurrentThread());
    }

    /* renamed from: Qh */
    public void mo6102Qh() {
        this.mLock.lock();
        try {
            C1424b.equals(1, this.f1099TC);
        } finally {
            this.mLock.unlock();
        }
    }

    /* renamed from: Rh */
    public long mo6103Rh() {
        this.mLock.lock();
        try {
            return this.f1100UC;
        } finally {
            this.mLock.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Sh */
    public boolean mo6104Sh() {
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Th */
    public boolean mo6105Th() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void acquireLock() {
        this.mLock.lock();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public C0883w mo6107c(C0883w wVar) {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void close();

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public C0883w mo6109d(C0883w wVar) {
        return null;
    }

    public String getKey() {
        return this.mKey;
    }

    public abstract int getMediaSize();

    public int getRefCount() {
        this.mLock.lock();
        try {
            return this.f1099TC;
        } finally {
            this.mLock.unlock();
        }
    }

    public void release() {
        this.mLock.lock();
        try {
            this.f1099TC--;
            if (this.f1099TC == 0) {
                close();
            } else if (this.f1099TC < 0) {
                C1424b.fail("RefCountedMediaResource has unbalanced ref. Refcount=" + this.f1099TC);
            }
        } finally {
            this.mLock.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void releaseLock() {
        this.mLock.unlock();
    }
}
