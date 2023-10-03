package com.google.common.collect;

import java.util.NoSuchElementException;

final class Iterators$9 extends UnmodifiableIterator<T> {
    boolean done;
    final /* synthetic */ Object val$value;

    Iterators$9(Object obj) {
        this.val$value = obj;
    }

    public boolean hasNext() {
        return !this.done;
    }

    public T next() {
        if (!this.done) {
            this.done = true;
            return this.val$value;
        }
        throw new NoSuchElementException();
    }
}
