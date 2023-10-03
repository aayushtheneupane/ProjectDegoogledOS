package com.google.common.collect;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/* renamed from: com.google.common.collect.i */
class C1663i extends C1651e implements SortedMap {

    /* renamed from: aQ */
    SortedSet f2529aQ;
    final /* synthetic */ AbstractMapBasedMultimap this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1663i(AbstractMapBasedMultimap abstractMapBasedMultimap, SortedMap sortedMap) {
        super(abstractMapBasedMultimap, sortedMap);
        this.this$0 = abstractMapBasedMultimap;
    }

    public Comparator comparator() {
        return ((SortedMap) this.f2520_P).comparator();
    }

    public Object firstKey() {
        return ((SortedMap) this.f2520_P).firstKey();
    }

    public SortedMap headMap(Object obj) {
        return new C1663i(this.this$0, ((SortedMap) this.f2520_P).headMap(obj));
    }

    public Set keySet() {
        SortedSet sortedSet = this.f2529aQ;
        if (sortedSet != null) {
            return sortedSet;
        }
        C1666j jVar = new C1666j(this.this$0, (SortedMap) this.f2520_P);
        this.f2529aQ = jVar;
        return jVar;
    }

    public Object lastKey() {
        return ((SortedMap) this.f2520_P).lastKey();
    }

    public SortedMap subMap(Object obj, Object obj2) {
        return new C1663i(this.this$0, ((SortedMap) this.f2520_P).subMap(obj, obj2));
    }

    public SortedMap tailMap(Object obj) {
        return new C1663i(this.this$0, ((SortedMap) this.f2520_P).tailMap(obj));
    }
}
