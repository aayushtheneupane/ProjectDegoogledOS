package com.google.common.collect;

import com.google.common.base.C1508E;
import java.util.Iterator;

/* renamed from: com.google.common.collect.da */
class C1649da implements C1647cb {

    /* renamed from: DN */
    private final Iterator f2517DN;

    /* renamed from: EN */
    private boolean f2518EN;

    /* renamed from: FN */
    private Object f2519FN;

    public C1649da(Iterator it) {
        if (it != null) {
            this.f2517DN = it;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasNext() {
        return this.f2518EN || this.f2517DN.hasNext();
    }

    public Object next() {
        if (!this.f2518EN) {
            return this.f2517DN.next();
        }
        Object obj = this.f2519FN;
        this.f2518EN = false;
        this.f2519FN = null;
        return obj;
    }

    public Object peek() {
        if (!this.f2518EN) {
            this.f2519FN = this.f2517DN.next();
            this.f2518EN = true;
        }
        return this.f2519FN;
    }

    public void remove() {
        C1508E.m3961a(!this.f2518EN, "Can't remove after you've peeked at next");
        this.f2517DN.remove();
    }
}
