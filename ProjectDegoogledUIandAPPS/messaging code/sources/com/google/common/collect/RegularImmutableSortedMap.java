package com.google.common.collect;

import java.util.Map;

final class RegularImmutableSortedMap extends ImmutableSortedMap {
    private final transient RegularImmutableSortedSet keySet;
    /* access modifiers changed from: private */

    /* renamed from: zN */
    public final transient ImmutableList f2483zN;

    class EntrySet extends ImmutableMapEntrySet {
        /* synthetic */ EntrySet(C1656fb fbVar) {
        }

        /* access modifiers changed from: package-private */
        /* renamed from: Pl */
        public ImmutableList mo8702Pl() {
            return new ImmutableAsList() {
                private final ImmutableList keyList = RegularImmutableSortedMap.this.keySet().mo8648Ol();

                /* access modifiers changed from: package-private */
                /* renamed from: Ql */
                public ImmutableCollection mo8695Ql() {
                    return EntrySet.this;
                }

                public Map.Entry get(int i) {
                    return C1633Xa.m4547i(this.keyList.get(i), RegularImmutableSortedMap.this.f2483zN.get(i));
                }
            };
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap map() {
            return RegularImmutableSortedMap.this;
        }

        public C1692rb iterator() {
            return mo8648Ol().iterator();
        }
    }

    RegularImmutableSortedMap(RegularImmutableSortedSet regularImmutableSortedSet, ImmutableList immutableList) {
        this.keySet = regularImmutableSortedSet;
        this.f2483zN = immutableList;
    }

    /* renamed from: aa */
    private ImmutableSortedMap m4467aa(int i, int i2) {
        if (i == 0 && i2 == size()) {
            return this;
        }
        if (i == i2) {
            return ImmutableSortedMap.m4240a(comparator());
        }
        ImmutableSortedSet N = this.keySet.mo8964N(i, i2);
        ImmutableList subList = this.f2483zN.subList(i, i2);
        if (N.isEmpty()) {
            return ImmutableSortedMap.m4240a(N.comparator());
        }
        return new RegularImmutableSortedMap((RegularImmutableSortedSet) N, subList);
    }

    public Object get(Object obj) {
        int indexOf = this.keySet.indexOf(obj);
        if (indexOf == -1) {
            return null;
        }
        return this.f2483zN.get(indexOf);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ol */
    public ImmutableSet mo8644ol() {
        return new EntrySet((C1656fb) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: rl */
    public ImmutableSortedMap mo8660rl() {
        return new RegularImmutableSortedMap((RegularImmutableSortedSet) this.keySet.descendingSet(), this.f2483zN.reverse(), this);
    }

    public ImmutableSortedMap headMap(Object obj, boolean z) {
        RegularImmutableSortedSet regularImmutableSortedSet = this.keySet;
        if (obj != null) {
            return m4467aa(0, regularImmutableSortedSet.mo8966c(obj, z));
        }
        throw new NullPointerException();
    }

    public ImmutableSortedMap tailMap(Object obj, boolean z) {
        RegularImmutableSortedSet regularImmutableSortedSet = this.keySet;
        if (obj != null) {
            return m4467aa(regularImmutableSortedSet.mo8968d(obj, z), size());
        }
        throw new NullPointerException();
    }

    public ImmutableCollection values() {
        return this.f2483zN;
    }

    public ImmutableSortedSet keySet() {
        return this.keySet;
    }

    RegularImmutableSortedMap(RegularImmutableSortedSet regularImmutableSortedSet, ImmutableList immutableList, ImmutableSortedMap immutableSortedMap) {
        super(immutableSortedMap);
        this.keySet = regularImmutableSortedSet;
        this.f2483zN = immutableList;
    }
}
