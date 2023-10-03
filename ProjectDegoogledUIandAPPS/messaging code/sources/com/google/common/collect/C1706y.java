package com.google.common.collect;

import java.util.NoSuchElementException;

/* renamed from: com.google.common.collect.y */
public abstract class C1706y extends C1692rb {

    /* renamed from: hO */
    private Object f2558hO;

    protected C1706y(Object obj) {
        this.f2558hO = obj;
    }

    /* access modifiers changed from: protected */
    /* renamed from: C */
    public abstract Object mo9259C(Object obj);

    public final boolean hasNext() {
        return this.f2558hO != null;
    }

    public final Object next() {
        if (this.f2558hO != null) {
            try {
                return this.f2558hO;
            } finally {
                this.f2558hO = mo9259C(this.f2558hO);
            }
        } else {
            throw new NoSuchElementException();
        }
    }
}
