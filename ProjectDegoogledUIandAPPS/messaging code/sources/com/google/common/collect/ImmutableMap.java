package com.google.common.collect;

import com.google.common.collect.ImmutableMapEntry;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

public abstract class ImmutableMap implements Map, Serializable {

    /* renamed from: pN */
    private static final Map.Entry[] f2437pN = new Map.Entry[0];
    private transient ImmutableSet keySet;

    /* renamed from: nN */
    private transient ImmutableSet f2438nN;
    private transient ImmutableCollection values;

    class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final Object[] keys;
        private final Object[] values;

        SerializedForm(ImmutableMap immutableMap) {
            this.keys = new Object[immutableMap.size()];
            this.values = new Object[immutableMap.size()];
            C1692rb it = immutableMap.entrySet().iterator();
            int i = 0;
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                this.keys[i] = entry.getKey();
                this.values[i] = entry.getValue();
                i++;
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public Object mo8745a(C1614S s) {
            int i = 0;
            while (true) {
                Object[] objArr = this.keys;
                if (i >= objArr.length) {
                    return s.build();
                }
                s.put(objArr[i], this.values[i]);
                i++;
            }
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return mo8745a(new C1614S());
        }
    }

    ImmutableMap() {
    }

    /* renamed from: a */
    static void m4211a(boolean z, String str, Map.Entry entry, Map.Entry entry2) {
        if (!z) {
            throw new IllegalArgumentException("Multiple entries with same " + str + ": " + entry + " and " + entry2);
        }
    }

    /* renamed from: c */
    public static ImmutableMap m4212c(Map map) {
        if ((map instanceof ImmutableMap) && !(map instanceof ImmutableSortedMap)) {
            ImmutableMap immutableMap = (ImmutableMap) map;
            if (!immutableMap.mo8645pl()) {
                return immutableMap;
            }
        } else if (map instanceof EnumMap) {
            EnumMap enumMap = new EnumMap((EnumMap) map);
            for (Map.Entry entry : enumMap.entrySet()) {
                C1630W.m4537h(entry.getKey(), entry.getValue());
            }
            return ImmutableEnumMap.m4195a(enumMap);
        }
        Map.Entry[] entryArr = (Map.Entry[]) map.entrySet().toArray(f2437pN);
        int length = entryArr.length;
        if (length == 0) {
            return ImmutableBiMap.m4190ql();
        }
        if (length != 1) {
            return new RegularImmutableMap(entryArr);
        }
        Map.Entry entry2 = entryArr[0];
        return ImmutableBiMap.m4189g(entry2.getKey(), entry2.getValue());
    }

    /* renamed from: f */
    static ImmutableMapEntry.TerminalEntry m4213f(Object obj, Object obj2) {
        C1630W.m4537h(obj, obj2);
        return new ImmutableMapEntry.TerminalEntry(obj, obj2);
    }

    /* renamed from: g */
    public static ImmutableMap m4214g(Object obj, Object obj2) {
        return ImmutableBiMap.m4189g(obj, obj2);
    }

    /* renamed from: ql */
    public static ImmutableMap m4215ql() {
        return ImmutableBiMap.m4190ql();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    public boolean equals(Object obj) {
        return C1633Xa.m4540a(this, obj);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: fl */
    public ImmutableSet mo8715fl() {
        return new ImmutableMapKeySet(this);
    }

    public abstract Object get(Object obj);

    public int hashCode() {
        return entrySet().hashCode();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ol */
    public abstract ImmutableSet mo8644ol();

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public abstract boolean mo8645pl();

    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return C1633Xa.m4544d(this);
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public ImmutableSet entrySet() {
        ImmutableSet immutableSet = this.f2438nN;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet ol = mo8644ol();
        this.f2438nN = ol;
        return ol;
    }

    public ImmutableSet keySet() {
        ImmutableSet immutableSet = this.keySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet fl = mo8715fl();
        this.keySet = fl;
        return fl;
    }

    public ImmutableCollection values() {
        ImmutableCollection immutableCollection = this.values;
        if (immutableCollection != null) {
            return immutableCollection;
        }
        ImmutableMapValues immutableMapValues = new ImmutableMapValues(this);
        this.values = immutableMapValues;
        return immutableMapValues;
    }
}
