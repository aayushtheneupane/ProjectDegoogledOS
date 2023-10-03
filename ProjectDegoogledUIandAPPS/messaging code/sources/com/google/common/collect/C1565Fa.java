package com.google.common.collect;

import com.google.common.collect.MapMakerInternalMap;

/* renamed from: com.google.common.collect.Fa */
final class C1565Fa extends C1559Ca implements C1553Aa {

    /* renamed from: PN */
    C1553Aa f2429PN;

    /* renamed from: QN */
    C1553Aa f2430QN;

    /* renamed from: RN */
    C1553Aa f2431RN = MapMakerInternalMap.m4303_l();

    /* renamed from: SN */
    C1553Aa f2432SN;
    volatile long time = Long.MAX_VALUE;

    C1565Fa(Object obj, int i, C1553Aa aa) {
        super(obj, i, aa);
        MapMakerInternalMap.NullEntry nullEntry = MapMakerInternalMap.NullEntry.INSTANCE;
        this.f2432SN = nullEntry;
        this.f2429PN = nullEntry;
        this.f2430QN = nullEntry;
    }

    /* renamed from: F */
    public C1553Aa mo8566F() {
        return this.f2430QN;
    }

    /* renamed from: P */
    public C1553Aa mo8567P() {
        return this.f2432SN;
    }

    /* renamed from: a */
    public void mo8568a(long j) {
        this.time = j;
    }

    /* renamed from: b */
    public void mo8571b(C1553Aa aa) {
        this.f2429PN = aa;
    }

    /* renamed from: c */
    public void mo8572c(C1553Aa aa) {
        this.f2431RN = aa;
    }

    /* renamed from: d */
    public void mo8573d(C1553Aa aa) {
        this.f2432SN = aa;
    }

    public long getExpirationTime() {
        return this.time;
    }

    /* renamed from: s */
    public C1553Aa mo8579s() {
        return this.f2429PN;
    }

    /* renamed from: t */
    public C1553Aa mo8580t() {
        return this.f2431RN;
    }

    /* renamed from: a */
    public void mo8569a(C1553Aa aa) {
        this.f2430QN = aa;
    }
}
