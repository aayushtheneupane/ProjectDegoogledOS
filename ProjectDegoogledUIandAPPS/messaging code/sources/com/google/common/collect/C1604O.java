package com.google.common.collect;

import com.google.common.collect.ImmutableEnumMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.common.collect.O */
class C1604O extends C1692rb {

    /* renamed from: AN */
    private final Iterator f2474AN = ImmutableEnumMap.this.delegate.entrySet().iterator();
    final /* synthetic */ ImmutableEnumMap.C15732 this$1;

    C1604O(ImmutableEnumMap.C15732 r1) {
        this.this$1 = r1;
    }

    public boolean hasNext() {
        return this.f2474AN.hasNext();
    }

    public Object next() {
        Map.Entry entry = (Map.Entry) this.f2474AN.next();
        return C1633Xa.m4547i(entry.getKey(), entry.getValue());
    }
}
