package com.google.common.collect;

import java.util.NoSuchElementException;

/* renamed from: com.google.common.collect.Z */
class C1636Z extends C1695sb {
    C1636Z() {
    }

    public boolean hasNext() {
        return false;
    }

    public boolean hasPrevious() {
        return false;
    }

    public Object next() {
        throw new NoSuchElementException();
    }

    public int nextIndex() {
        return 0;
    }

    public Object previous() {
        throw new NoSuchElementException();
    }

    public int previousIndex() {
        return -1;
    }
}
