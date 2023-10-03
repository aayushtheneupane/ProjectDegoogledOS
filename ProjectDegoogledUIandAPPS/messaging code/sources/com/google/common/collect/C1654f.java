package com.google.common.collect;

import com.google.common.base.C1508E;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.common.collect.f */
class C1654f implements Iterator {
    Map.Entry entry;

    /* renamed from: gN */
    final /* synthetic */ Iterator f2523gN;
    final /* synthetic */ C1657g this$1;

    C1654f(C1657g gVar, Iterator it) {
        this.this$1 = gVar;
        this.f2523gN = it;
    }

    public boolean hasNext() {
        return this.f2523gN.hasNext();
    }

    public Object next() {
        this.entry = (Map.Entry) this.f2523gN.next();
        return this.entry.getKey();
    }

    public void remove() {
        C1508E.m3961a(this.entry != null, "no calls to next() since the last call to remove()");
        Collection collection = (Collection) this.entry.getValue();
        this.f2523gN.remove();
        AbstractMapBasedMultimap.m4060b(this.this$1.this$0, collection.size());
        collection.clear();
    }
}
