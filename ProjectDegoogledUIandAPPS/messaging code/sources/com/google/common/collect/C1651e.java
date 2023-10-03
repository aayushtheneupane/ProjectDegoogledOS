package com.google.common.collect;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.common.collect.e */
class C1651e extends AbstractMap {

    /* renamed from: _P */
    final transient Map f2520_P;

    /* renamed from: nN */
    private transient Set f2521nN;
    final /* synthetic */ AbstractMapBasedMultimap this$0;
    private transient Collection values;

    C1651e(AbstractMapBasedMultimap abstractMapBasedMultimap, Map map) {
        this.this$0 = abstractMapBasedMultimap;
        this.f2520_P = map;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public Map.Entry mo9152b(Map.Entry entry) {
        Object key = entry.getKey();
        return C1633Xa.m4547i(key, this.this$0.mo8581a(key, (Collection) entry.getValue()));
    }

    public void clear() {
        if (this.f2520_P == this.this$0.map) {
            this.this$0.clear();
        } else {
            C1652ea.m4571a(new C1648d(this));
        }
    }

    public boolean containsKey(Object obj) {
        return C1633Xa.m4541b(this.f2520_P, obj);
    }

    public Set entrySet() {
        Set set = this.f2521nN;
        if (set != null) {
            return set;
        }
        C1645c cVar = new C1645c(this);
        this.f2521nN = cVar;
        return cVar;
    }

    public boolean equals(Object obj) {
        return this == obj || this.f2520_P.equals(obj);
    }

    public Object get(Object obj) {
        Collection collection = (Collection) C1633Xa.m4542c(this.f2520_P, obj);
        if (collection == null) {
            return null;
        }
        return this.this$0.mo8581a(obj, collection);
    }

    public int hashCode() {
        return this.f2520_P.hashCode();
    }

    public Set keySet() {
        return this.this$0.keySet();
    }

    public Object remove(Object obj) {
        Collection collection = (Collection) this.f2520_P.remove(obj);
        if (collection == null) {
            return null;
        }
        Collection hl = this.this$0.mo8587hl();
        hl.addAll(collection);
        AbstractMapBasedMultimap.m4060b(this.this$0, collection.size());
        collection.clear();
        return hl;
    }

    public int size() {
        return this.f2520_P.size();
    }

    public String toString() {
        return this.f2520_P.toString();
    }

    public Collection values() {
        Collection collection = this.values;
        if (collection != null) {
            return collection;
        }
        C1631Wa wa = new C1631Wa(this);
        this.values = wa;
        return wa;
    }
}
