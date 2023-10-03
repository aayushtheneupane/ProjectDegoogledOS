package com.google.common.base;

import java.util.Iterator;

/* renamed from: com.google.common.base.K */
class C1514K implements Iterable {

    /* renamed from: XM */
    final /* synthetic */ CharSequence f2388XM;
    final /* synthetic */ C1516M this$0;

    C1514K(C1516M m, CharSequence charSequence) {
        this.this$0 = m;
        this.f2388XM = charSequence;
    }

    public Iterator iterator() {
        return this.this$0.f2394aN.mo8525a(this.this$0, this.f2388XM);
    }

    public String toString() {
        C1504A Ua = C1504A.m3943Ua(", ");
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Ua.mo8515a(sb, iterator());
        sb.append(']');
        return sb.toString();
    }
}
