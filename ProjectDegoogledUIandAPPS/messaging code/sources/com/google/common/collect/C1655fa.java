package com.google.common.collect;

import com.google.common.base.C1508E;
import com.google.common.collect.LinkedHashMultimap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: com.google.common.collect.fa */
class C1655fa implements Iterator {

    /* renamed from: HN */
    LinkedHashMultimap.ValueEntry f2524HN = this.this$0.f2448kN.successorInMultimap;

    /* renamed from: JN */
    LinkedHashMultimap.ValueEntry f2525JN;
    final /* synthetic */ LinkedHashMultimap this$0;

    C1655fa(LinkedHashMultimap linkedHashMultimap) {
        this.this$0 = linkedHashMultimap;
    }

    public boolean hasNext() {
        return this.f2524HN != this.this$0.f2448kN;
    }

    public Object next() {
        if (this.f2524HN != this.this$0.f2448kN) {
            LinkedHashMultimap.ValueEntry valueEntry = this.f2524HN;
            this.f2525JN = valueEntry;
            this.f2524HN = valueEntry.successorInMultimap;
            return valueEntry;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        C1508E.m3961a(this.f2525JN != null, "no calls to next() since the last call to remove()");
        this.this$0.remove(this.f2525JN.getKey(), this.f2525JN.getValue());
        this.f2525JN = null;
    }
}
