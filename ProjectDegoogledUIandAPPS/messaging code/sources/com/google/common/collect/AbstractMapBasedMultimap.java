package com.google.common.collect;

import com.google.common.base.C1508E;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

abstract class AbstractMapBasedMultimap extends C1698u implements Serializable {
    private static final long serialVersionUID = 2447537837011683357L;

    /* renamed from: jN */
    private transient int f2422jN;
    /* access modifiers changed from: private */
    public transient Map map;

    /* renamed from: b */
    static /* synthetic */ int m4059b(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        int i = abstractMapBasedMultimap.f2422jN;
        abstractMapBasedMultimap.f2422jN = i + 1;
        return i;
    }

    /* renamed from: c */
    static /* synthetic */ int m4061c(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        int i = abstractMapBasedMultimap.f2422jN;
        abstractMapBasedMultimap.f2422jN = i - 1;
        return i;
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public Iterator m4062e(Collection collection) {
        if (collection instanceof List) {
            return ((List) collection).listIterator();
        }
        return collection.iterator();
    }

    public void clear() {
        for (Collection clear : this.map.values()) {
            clear.clear();
        }
        this.map.clear();
        this.f2422jN = 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: dl */
    public Map mo8584dl() {
        Map map2 = this.map;
        return map2 instanceof SortedMap ? new C1663i(this, (SortedMap) map2) : new C1651e(this, map2);
    }

    public Collection entries() {
        return super.entries();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: fl */
    public Set mo8586fl() {
        Map map2 = this.map;
        return map2 instanceof SortedMap ? new C1666j(this, (SortedMap) map2) : new C1657g(this, map2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: hl */
    public abstract Collection mo8587hl();

    public int size() {
        return this.f2422jN;
    }

    /* renamed from: b */
    static /* synthetic */ int m4060b(AbstractMapBasedMultimap abstractMapBasedMultimap, int i) {
        int i2 = abstractMapBasedMultimap.f2422jN - i;
        abstractMapBasedMultimap.f2422jN = i2;
        return i2;
    }

    /* renamed from: a */
    static /* synthetic */ int m4053a(AbstractMapBasedMultimap abstractMapBasedMultimap, int i) {
        int i2 = abstractMapBasedMultimap.f2422jN + i;
        abstractMapBasedMultimap.f2422jN = i2;
        return i2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo8582b(Map map2) {
        this.map = map2;
        this.f2422jN = 0;
        for (Collection collection : map2.values()) {
            C1508E.checkArgument(!collection.isEmpty());
            this.f2422jN = collection.size() + this.f2422jN;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public Collection mo8581a(Object obj, Collection collection) {
        if (collection instanceof SortedSet) {
            return new C1684p(this, obj, (SortedSet) collection, (C1672l) null);
        }
        if (collection instanceof Set) {
            return new C1681o(this, obj, (Set) collection);
        }
        if (collection instanceof List) {
            return m4057a(obj, (List) collection, (C1672l) null);
        }
        return new C1672l(this, obj, collection, (C1672l) null);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public List m4057a(Object obj, List list, C1672l lVar) {
        if (list instanceof RandomAccess) {
            return new C1660h(this, obj, list, lVar);
        }
        return new C1678n(this, obj, list, lVar);
    }

    /* renamed from: a */
    static /* synthetic */ int m4054a(AbstractMapBasedMultimap abstractMapBasedMultimap, Object obj) {
        Collection collection = (Collection) C1633Xa.m4543d(abstractMapBasedMultimap.map, obj);
        if (collection == null) {
            return 0;
        }
        int size = collection.size();
        collection.clear();
        abstractMapBasedMultimap.f2422jN -= size;
        return size;
    }
}
