package com.google.common.collect;

import java.util.AbstractQueue;
import java.util.Iterator;

/* renamed from: com.google.common.collect.na */
class C1679na extends AbstractQueue {
    C1679na() {
    }

    public Iterator iterator() {
        return C1652ea.emptyListIterator();
    }

    public boolean offer(Object obj) {
        return true;
    }

    public Object peek() {
        return null;
    }

    public Object poll() {
        return null;
    }

    public int size() {
        return 0;
    }
}
