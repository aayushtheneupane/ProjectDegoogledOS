package com.google.common.collect;

import com.google.common.base.C1547v;
import java.util.Iterator;

/* renamed from: com.google.common.collect.ca */
class C1646ca implements Iterator {

    /* renamed from: AN */
    final Iterator f2514AN;

    /* renamed from: BN */
    final /* synthetic */ C1547v f2515BN;

    C1646ca(Iterator it, C1547v vVar) {
        this.f2515BN = vVar;
        if (it != null) {
            this.f2514AN = it;
            return;
        }
        throw new NullPointerException();
    }

    public final boolean hasNext() {
        return this.f2514AN.hasNext();
    }

    public final Object next() {
        return this.f2515BN.apply(this.f2514AN.next());
    }

    public final void remove() {
        this.f2514AN.remove();
    }
}
