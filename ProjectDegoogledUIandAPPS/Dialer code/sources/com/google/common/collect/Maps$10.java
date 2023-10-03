package com.google.common.collect;

import java.util.Map;

final class Maps$10 extends AbstractMapEntry<K, V2> {
    final /* synthetic */ Map.Entry val$entry;
    final /* synthetic */ Maps$EntryTransformer val$transformer;

    Maps$10(Map.Entry entry, Maps$EntryTransformer maps$EntryTransformer) {
        this.val$entry = entry;
        this.val$transformer = maps$EntryTransformer;
    }

    public K getKey() {
        return this.val$entry.getKey();
    }

    public V2 getValue() {
        return this.val$transformer.transformEntry(this.val$entry.getKey(), this.val$entry.getValue());
    }
}
