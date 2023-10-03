package com.google.common.collect;

import java.io.Serializable;
import java.util.Map;

abstract class ImmutableMapEntrySet extends ImmutableSet {

    class EntrySetSerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap map;

        EntrySetSerializedForm(ImmutableMap immutableMap) {
            this.map = immutableMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.map.entrySet();
        }
    }

    ImmutableMapEntrySet() {
    }

    public boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = map().get(entry.getKey());
        if (obj2 == null || !obj2.equals(entry.getValue())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public abstract ImmutableMap map();

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8636pl() {
        return map().mo8645pl();
    }

    public int size() {
        return map().size();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new EntrySetSerializedForm(map());
    }
}
