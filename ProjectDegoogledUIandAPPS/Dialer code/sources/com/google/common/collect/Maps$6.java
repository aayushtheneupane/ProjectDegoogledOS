package com.google.common.collect;

import java.util.Iterator;
import java.util.Map;

final class Maps$6 extends UnmodifiableIterator<Map.Entry<K, V>> {
    final /* synthetic */ Iterator val$entryIterator;

    Maps$6(Iterator it) {
        this.val$entryIterator = it;
    }

    public boolean hasNext() {
        return this.val$entryIterator.hasNext();
    }

    public Object next() {
        Map.Entry entry = (Map.Entry) this.val$entryIterator.next();
        if (entry != null) {
            return new Maps$5(entry);
        }
        throw new NullPointerException();
    }
}
