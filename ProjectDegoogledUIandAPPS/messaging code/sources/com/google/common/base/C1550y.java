package com.google.common.base;

import java.util.AbstractList;

/* renamed from: com.google.common.base.y */
class C1550y extends AbstractList {

    /* renamed from: PP */
    final /* synthetic */ Object[] f2416PP;

    /* renamed from: QP */
    final /* synthetic */ Object f2417QP;

    /* renamed from: RP */
    final /* synthetic */ Object f2418RP;

    C1550y(Object[] objArr, Object obj, Object obj2) {
        this.f2416PP = objArr;
        this.f2417QP = obj;
        this.f2418RP = obj2;
    }

    public Object get(int i) {
        if (i == 0) {
            return this.f2417QP;
        }
        if (i != 1) {
            return this.f2416PP[i - 2];
        }
        return this.f2418RP;
    }

    public int size() {
        return this.f2416PP.length + 2;
    }
}
