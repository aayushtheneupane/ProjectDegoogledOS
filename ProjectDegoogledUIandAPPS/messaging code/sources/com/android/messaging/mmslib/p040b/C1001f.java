package com.android.messaging.mmslib.p040b;

import com.android.messaging.mmslib.p039a.C0976f;

/* renamed from: com.android.messaging.mmslib.b.f */
public final class C1001f {

    /* renamed from: MD */
    private final int f1454MD;
    private final C0976f mPdu;
    private final long mThreadId;

    public C1001f(C0976f fVar, int i, long j) {
        this.mPdu = fVar;
        this.f1454MD = i;
        this.mThreadId = j;
    }

    /* renamed from: fi */
    public int mo6768fi() {
        return this.f1454MD;
    }

    public C0976f getPdu() {
        return this.mPdu;
    }

    public long getThreadId() {
        return this.mThreadId;
    }
}
