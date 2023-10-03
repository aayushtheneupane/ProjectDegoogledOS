package com.google.common.collect;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.SortedSet;

/* renamed from: com.google.common.collect.j */
class C1666j extends C1657g implements SortedSet {
    final /* synthetic */ AbstractMapBasedMultimap this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1666j(AbstractMapBasedMultimap abstractMapBasedMultimap, SortedMap sortedMap) {
        super(abstractMapBasedMultimap, sortedMap);
        this.this$0 = abstractMapBasedMultimap;
    }

    public Comparator comparator() {
        return ((SortedMap) this.map).comparator();
    }

    public Object first() {
        return ((SortedMap) this.map).firstKey();
    }

    public SortedSet headSet(Object obj) {
        return new C1666j(this.this$0, ((SortedMap) this.map).headMap(obj));
    }

    public Object last() {
        return ((SortedMap) this.map).lastKey();
    }

    public SortedSet subSet(Object obj, Object obj2) {
        return new C1666j(this.this$0, ((SortedMap) this.map).subMap(obj, obj2));
    }

    public SortedSet tailSet(Object obj) {
        return new C1666j(this.this$0, ((SortedMap) this.map).tailMap(obj));
    }
}
