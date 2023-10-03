package com.google.common.collect;

import java.util.AbstractCollection;
import java.util.Map;

abstract class Multimaps$Entries<K, V> extends AbstractCollection<Map.Entry<K, V>> {
    Multimaps$Entries() {
    }

    public void clear() {
        AbstractMultimap.this.clear();
    }

    public boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return AbstractMultimap.this.containsEntry(entry.getKey(), entry.getValue());
    }

    public boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return AbstractMultimap.this.remove(entry.getKey(), entry.getValue());
    }

    public int size() {
        return AbstractMultimap.this.size();
    }
}
