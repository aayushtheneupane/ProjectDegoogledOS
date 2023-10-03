package com.google.common.collect;

import com.google.common.collect.MapMakerInternalMap;

/* renamed from: com.google.common.collect.Da */
final class C1561Da extends C1559Ca implements C1553Aa {

    /* renamed from: PN */
    C1553Aa f2424PN = MapMakerInternalMap.m4303_l();

    /* renamed from: QN */
    C1553Aa f2425QN = MapMakerInternalMap.NullEntry.INSTANCE;

    C1561Da(Object obj, int i, C1553Aa aa) {
        super(obj, i, aa);
    }

    /* renamed from: F */
    public C1553Aa mo8566F() {
        return this.f2425QN;
    }

    /* renamed from: a */
    public void mo8569a(C1553Aa aa) {
        this.f2425QN = aa;
    }

    /* renamed from: b */
    public void mo8571b(C1553Aa aa) {
        this.f2424PN = aa;
    }

    /* renamed from: s */
    public C1553Aa mo8579s() {
        return this.f2424PN;
    }
}
