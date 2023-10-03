package com.google.common.base;

import java.util.Iterator;

/* renamed from: com.google.common.base.x */
class C1549x extends C1504A {
    final /* synthetic */ C1504A this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1549x(C1504A a, C1504A a2) {
        super(a2, (C1548w) null);
        this.this$0 = a;
    }

    /* renamed from: Va */
    public C1504A mo8509Va(String str) {
        throw new UnsupportedOperationException("already specified skipNulls");
    }

    /* renamed from: Wa */
    public C1551z mo8511Wa(String str) {
        throw new UnsupportedOperationException("can't use .skipNulls() with maps");
    }

    /* renamed from: a */
    public Appendable mo8512a(Appendable appendable, Iterator it) {
        C1508E.checkNotNull(appendable, "appendable");
        C1508E.checkNotNull(it, "parts");
        while (true) {
            if (it.hasNext()) {
                Object next = it.next();
                if (next != null) {
                    appendable.append(this.this$0.toString(next));
                    break;
                }
            } else {
                break;
            }
        }
        while (it.hasNext()) {
            Object next2 = it.next();
            if (next2 != null) {
                appendable.append(this.this$0.separator);
                appendable.append(this.this$0.toString(next2));
            }
        }
        return appendable;
    }
}
