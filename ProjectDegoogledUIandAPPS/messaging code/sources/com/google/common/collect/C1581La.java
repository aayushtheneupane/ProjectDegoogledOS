package com.google.common.collect;

import com.google.common.collect.MapMakerInternalMap;
import java.lang.ref.ReferenceQueue;

/* renamed from: com.google.common.collect.La */
final class C1581La extends C1579Ka implements C1553Aa {

    /* renamed from: PN */
    C1553Aa f2446PN = MapMakerInternalMap.m4303_l();

    /* renamed from: QN */
    C1553Aa f2447QN = MapMakerInternalMap.NullEntry.INSTANCE;

    C1581La(ReferenceQueue referenceQueue, Object obj, int i, C1553Aa aa) {
        super(referenceQueue, obj, i, aa);
    }

    /* renamed from: F */
    public C1553Aa mo8566F() {
        return this.f2447QN;
    }

    /* renamed from: a */
    public void mo8569a(C1553Aa aa) {
        this.f2447QN = aa;
    }

    /* renamed from: b */
    public void mo8571b(C1553Aa aa) {
        this.f2446PN = aa;
    }

    /* renamed from: s */
    public C1553Aa mo8579s() {
        return this.f2446PN;
    }
}
