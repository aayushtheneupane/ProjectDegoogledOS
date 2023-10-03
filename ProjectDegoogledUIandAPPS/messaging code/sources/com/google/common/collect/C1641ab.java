package com.google.common.collect;

import com.google.common.base.C1547v;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.google.common.collect.ab */
class C1641ab implements C1547v {
    final AtomicInteger counter = new AtomicInteger(0);

    C1641ab(Ordering$ArbitraryOrdering ordering$ArbitraryOrdering) {
    }

    public Object apply(Object obj) {
        return Integer.valueOf(this.counter.getAndIncrement());
    }
}
