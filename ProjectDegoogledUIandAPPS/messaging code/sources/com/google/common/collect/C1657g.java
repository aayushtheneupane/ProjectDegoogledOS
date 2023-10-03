package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.common.collect.g */
class C1657g extends C1625Ta {
    final /* synthetic */ AbstractMapBasedMultimap this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1657g(AbstractMapBasedMultimap abstractMapBasedMultimap, Map map) {
        super(map);
        this.this$0 = abstractMapBasedMultimap;
    }

    public void clear() {
        C1652ea.m4571a(new C1654f(this, this.map.entrySet().iterator()));
    }

    public boolean containsAll(Collection collection) {
        return map().keySet().containsAll(collection);
    }

    public boolean equals(Object obj) {
        return this == obj || map().keySet().equals(obj);
    }

    public int hashCode() {
        return map().keySet().hashCode();
    }

    public Iterator iterator() {
        return new C1654f(this, this.map.entrySet().iterator());
    }

    public boolean remove(Object obj) {
        int i;
        Collection collection = (Collection) this.map.remove(obj);
        if (collection != null) {
            i = collection.size();
            collection.clear();
            AbstractMapBasedMultimap.m4060b(this.this$0, i);
        } else {
            i = 0;
        }
        if (i > 0) {
            return true;
        }
        return false;
    }
}
