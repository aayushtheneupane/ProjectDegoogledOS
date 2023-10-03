package com.google.common.collect;

import java.util.Iterator;

abstract class TransformedIterator<F, T> implements Iterator<T> {
    final Iterator<? extends F> backingIterator;

    TransformedIterator(Iterator<? extends F> it) {
        if (it != null) {
            this.backingIterator = it;
            return;
        }
        throw new NullPointerException();
    }

    public final boolean hasNext() {
        return this.backingIterator.hasNext();
    }

    public final T next() {
        return transform(this.backingIterator.next());
    }

    public final void remove() {
        this.backingIterator.remove();
    }

    /* access modifiers changed from: package-private */
    public abstract T transform(F f);
}
