package com.google.common.collect;

import com.google.common.collect.MapMakerInternalMap;
import java.lang.ref.ReferenceQueue;

/* renamed from: com.google.common.collect.Ma */
final class C1583Ma extends C1579Ka implements C1553Aa {

    /* renamed from: RN */
    C1553Aa f2451RN = MapMakerInternalMap.m4303_l();

    /* renamed from: SN */
    C1553Aa f2452SN = MapMakerInternalMap.NullEntry.INSTANCE;
    volatile long time = Long.MAX_VALUE;

    C1583Ma(ReferenceQueue referenceQueue, Object obj, int i, C1553Aa aa) {
        super(referenceQueue, obj, i, aa);
    }

    /* renamed from: P */
    public C1553Aa mo8567P() {
        return this.f2452SN;
    }

    /* renamed from: a */
    public void mo8568a(long j) {
        this.time = j;
    }

    /* renamed from: c */
    public void mo8572c(C1553Aa aa) {
        this.f2451RN = aa;
    }

    /* renamed from: d */
    public void mo8573d(C1553Aa aa) {
        this.f2452SN = aa;
    }

    public long getExpirationTime() {
        return this.time;
    }

    /* renamed from: t */
    public C1553Aa mo8580t() {
        return this.f2451RN;
    }
}
