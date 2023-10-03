package com.google.common.collect;

import com.google.common.collect.MapMakerInternalMap;

/* renamed from: com.google.common.collect.Ea */
final class C1563Ea extends C1559Ca implements C1553Aa {

    /* renamed from: RN */
    C1553Aa f2427RN = MapMakerInternalMap.m4303_l();

    /* renamed from: SN */
    C1553Aa f2428SN = MapMakerInternalMap.NullEntry.INSTANCE;
    volatile long time = Long.MAX_VALUE;

    C1563Ea(Object obj, int i, C1553Aa aa) {
        super(obj, i, aa);
    }

    /* renamed from: P */
    public C1553Aa mo8567P() {
        return this.f2428SN;
    }

    /* renamed from: a */
    public void mo8568a(long j) {
        this.time = j;
    }

    /* renamed from: c */
    public void mo8572c(C1553Aa aa) {
        this.f2427RN = aa;
    }

    /* renamed from: d */
    public void mo8573d(C1553Aa aa) {
        this.f2428SN = aa;
    }

    public long getExpirationTime() {
        return this.time;
    }

    /* renamed from: t */
    public C1553Aa mo8580t() {
        return this.f2427RN;
    }
}
