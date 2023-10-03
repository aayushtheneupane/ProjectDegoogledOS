package com.google.common.collect;

import com.google.common.base.MoreObjects;
import java.util.Iterator;
import java.util.NoSuchElementException;

enum Iterators$EmptyModifiableIterator implements Iterator<Object> {
    INSTANCE;

    public boolean hasNext() {
        return false;
    }

    public Object next() {
        throw new NoSuchElementException();
    }

    public void remove() {
        MoreObjects.checkState(false, "no calls to next() since the last call to remove()");
    }
}
