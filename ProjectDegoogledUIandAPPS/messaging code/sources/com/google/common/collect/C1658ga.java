package com.google.common.collect;

import com.google.common.base.C1508E;
import com.google.common.collect.LinkedHashMultimap;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: com.google.common.collect.ga */
class C1658ga implements Iterator {

    /* renamed from: HN */
    C1661ha f2526HN = this.this$1.f2449VP;

    /* renamed from: JN */
    LinkedHashMultimap.ValueEntry f2527JN;

    /* renamed from: KN */
    int f2528KN = this.this$1.modCount;
    final /* synthetic */ LinkedHashMultimap.ValueSet this$1;

    C1658ga(LinkedHashMultimap.ValueSet valueSet) {
        this.this$1 = valueSet;
    }

    /* renamed from: Uo */
    private void m4583Uo() {
        if (this.this$1.modCount != this.f2528KN) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean hasNext() {
        if (this.this$1.modCount == this.f2528KN) {
            return this.f2526HN != this.this$1;
        }
        throw new ConcurrentModificationException();
    }

    public Object next() {
        m4583Uo();
        if (this.f2526HN != this.this$1) {
            LinkedHashMultimap.ValueEntry valueEntry = (LinkedHashMultimap.ValueEntry) this.f2526HN;
            Object value = valueEntry.getValue();
            this.f2527JN = valueEntry;
            this.f2526HN = valueEntry.mo8806O();
            return value;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (this.this$1.modCount == this.f2528KN) {
            C1508E.m3961a(this.f2527JN != null, "no calls to next() since the last call to remove()");
            this.this$1.remove(this.f2527JN.getValue());
            this.f2528KN = this.this$1.modCount;
            this.f2527JN = null;
            return;
        }
        throw new ConcurrentModificationException();
    }
}
