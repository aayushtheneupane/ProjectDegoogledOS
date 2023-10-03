package com.google.common.collect;

import com.google.common.base.C1508E;
import java.util.NoSuchElementException;

/* renamed from: com.google.common.collect.a */
abstract class C1639a extends C1695sb {
    private int position;
    private final int size;

    protected C1639a(int i, int i2) {
        C1508E.m3959K(i2, i);
        this.size = i;
        this.position = i2;
    }

    /* access modifiers changed from: protected */
    public abstract Object get(int i);

    public final boolean hasNext() {
        return this.position < this.size;
    }

    public final boolean hasPrevious() {
        return this.position > 0;
    }

    public final Object next() {
        if (this.position < this.size) {
            int i = this.position;
            this.position = i + 1;
            return get(i);
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.position;
    }

    public final Object previous() {
        if (this.position > 0) {
            int i = this.position - 1;
            this.position = i;
            return get(i);
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return this.position - 1;
    }
}
