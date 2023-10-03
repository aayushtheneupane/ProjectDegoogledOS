package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.common.collect.d */
class C1648d implements Iterator {
    Collection collection;

    /* renamed from: fN */
    final Iterator f2516fN = this.this$1.f2520_P.entrySet().iterator();
    final /* synthetic */ C1651e this$1;

    C1648d(C1651e eVar) {
        this.this$1 = eVar;
    }

    public boolean hasNext() {
        return this.f2516fN.hasNext();
    }

    public Object next() {
        Map.Entry entry = (Map.Entry) this.f2516fN.next();
        this.collection = (Collection) entry.getValue();
        return this.this$1.mo9152b(entry);
    }

    public void remove() {
        this.f2516fN.remove();
        AbstractMapBasedMultimap.m4060b(this.this$1.this$0, this.collection.size());
        this.collection.clear();
    }
}
