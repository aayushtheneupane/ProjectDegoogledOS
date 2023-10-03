package com.google.common.collect;

import com.google.common.collect.MapMakerInternalMap;
import java.lang.ref.ReferenceQueue;

/* renamed from: com.google.common.collect.Na */
final class C1603Na extends C1579Ka implements C1553Aa {

    /* renamed from: PN */
    C1553Aa f2470PN;

    /* renamed from: QN */
    C1553Aa f2471QN;

    /* renamed from: RN */
    C1553Aa f2472RN = MapMakerInternalMap.m4303_l();

    /* renamed from: SN */
    C1553Aa f2473SN;
    volatile long time = Long.MAX_VALUE;

    C1603Na(ReferenceQueue referenceQueue, Object obj, int i, C1553Aa aa) {
        super(referenceQueue, obj, i, aa);
        MapMakerInternalMap.NullEntry nullEntry = MapMakerInternalMap.NullEntry.INSTANCE;
        this.f2473SN = nullEntry;
        this.f2470PN = nullEntry;
        this.f2471QN = nullEntry;
    }

    /* renamed from: F */
    public C1553Aa mo8566F() {
        return this.f2471QN;
    }

    /* renamed from: P */
    public C1553Aa mo8567P() {
        return this.f2473SN;
    }

    /* renamed from: a */
    public void mo8568a(long j) {
        this.time = j;
    }

    /* renamed from: b */
    public void mo8571b(C1553Aa aa) {
        this.f2470PN = aa;
    }

    /* renamed from: c */
    public void mo8572c(C1553Aa aa) {
        this.f2472RN = aa;
    }

    /* renamed from: d */
    public void mo8573d(C1553Aa aa) {
        this.f2473SN = aa;
    }

    public long getExpirationTime() {
        return this.time;
    }

    /* renamed from: s */
    public C1553Aa mo8579s() {
        return this.f2470PN;
    }

    /* renamed from: t */
    public C1553Aa mo8580t() {
        return this.f2472RN;
    }

    /* renamed from: a */
    public void mo8569a(C1553Aa aa) {
        this.f2471QN = aa;
    }
}
