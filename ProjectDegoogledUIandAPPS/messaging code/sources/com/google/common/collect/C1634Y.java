package com.google.common.collect;

import java.util.NoSuchElementException;

/* renamed from: com.google.common.collect.Y */
class C1634Y extends C1692rb {

    /* renamed from: iO */
    boolean f2509iO;

    /* renamed from: jO */
    final /* synthetic */ Object f2510jO;

    C1634Y(Object obj) {
        this.f2510jO = obj;
    }

    public boolean hasNext() {
        return !this.f2509iO;
    }

    public Object next() {
        if (!this.f2509iO) {
            this.f2509iO = true;
            return this.f2510jO;
        }
        throw new NoSuchElementException();
    }
}
