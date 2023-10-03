package com.google.common.collect;

import java.util.AbstractSet;
import java.util.Iterator;

/* renamed from: com.google.common.collect.za */
final class C1709za extends AbstractSet {
    final /* synthetic */ MapMakerInternalMap this$0;

    C1709za(MapMakerInternalMap mapMakerInternalMap) {
        this.this$0 = mapMakerInternalMap;
    }

    public void clear() {
        this.this$0.clear();
    }

    public boolean contains(Object obj) {
        return this.this$0.containsKey(obj);
    }

    public boolean isEmpty() {
        return this.this$0.isEmpty();
    }

    public Iterator iterator() {
        return new C1707ya(this.this$0);
    }

    public boolean remove(Object obj) {
        return this.this$0.remove(obj) != null;
    }

    public int size() {
        return this.this$0.size();
    }
}
