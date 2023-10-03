package com.google.common.collect;

import java.util.Map;

/* renamed from: com.google.common.collect.Qa */
class C1609Qa extends C1692rb {

    /* renamed from: gN */
    final /* synthetic */ C1692rb f2476gN;

    C1609Qa(C1692rb rbVar) {
        this.f2476gN = rbVar;
    }

    public boolean hasNext() {
        return this.f2476gN.hasNext();
    }

    public Object next() {
        return ((Map.Entry) this.f2476gN.next()).getValue();
    }
}
