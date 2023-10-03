package com.google.common.collect;

import java.io.Serializable;
import java.util.Map;

final class ImmutableMapKeySet extends ImmutableSet {
    private final ImmutableMap map;

    class KeySetSerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap map;

        KeySetSerializedForm(ImmutableMap immutableMap) {
            this.map = immutableMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.map.keySet();
        }
    }

    ImmutableMapKeySet(ImmutableMap immutableMap) {
        this.map = immutableMap;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Pl */
    public ImmutableList mo8702Pl() {
        final ImmutableList Ol = this.map.entrySet().mo8648Ol();
        return new ImmutableAsList() {
            /* access modifiers changed from: package-private */
            /* renamed from: Ql */
            public ImmutableCollection mo8695Ql() {
                return ImmutableMapKeySet.this;
            }

            public Object get(int i) {
                return ((Map.Entry) Ol.get(i)).getKey();
            }
        };
    }

    public boolean contains(Object obj) {
        return this.map.containsKey(obj);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8636pl() {
        return true;
    }

    public int size() {
        return this.map.size();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new KeySetSerializedForm(this.map);
    }

    public C1692rb iterator() {
        return mo8648Ol().iterator();
    }
}
