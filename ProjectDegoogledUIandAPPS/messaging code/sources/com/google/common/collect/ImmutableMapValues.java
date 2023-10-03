package com.google.common.collect;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

final class ImmutableMapValues extends ImmutableCollection {
    private final ImmutableMap map;

    class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap map;

        SerializedForm(ImmutableMap immutableMap) {
            this.map = immutableMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.map.values();
        }
    }

    ImmutableMapValues(ImmutableMap immutableMap) {
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
                return ImmutableMapValues.this;
            }

            public Object get(int i) {
                return ((Map.Entry) Ol.get(i)).getValue();
            }
        };
    }

    public boolean contains(Object obj) {
        return obj != null && C1652ea.m4573a((Iterator) iterator(), obj);
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
        return new SerializedForm(this.map);
    }

    public C1692rb iterator() {
        return C1633Xa.m4538a(this.map.entrySet().iterator());
    }
}
