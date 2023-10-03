package com.google.common.collect;

import java.util.AbstractCollection;
import java.util.Iterator;

/* renamed from: com.google.common.collect.Ja */
final class C1577Ja extends AbstractCollection {
    final /* synthetic */ MapMakerInternalMap this$0;

    C1577Ja(MapMakerInternalMap mapMakerInternalMap) {
        this.this$0 = mapMakerInternalMap;
    }

    public void clear() {
        this.this$0.clear();
    }

    public boolean contains(Object obj) {
        return this.this$0.containsValue(obj);
    }

    public boolean isEmpty() {
        return this.this$0.isEmpty();
    }

    public Iterator iterator() {
        return new C1569Ha(this.this$0);
    }

    public int size() {
        return this.this$0.size();
    }
}
