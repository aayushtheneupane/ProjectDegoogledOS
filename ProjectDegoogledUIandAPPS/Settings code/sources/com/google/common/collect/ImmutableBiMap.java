package com.google.common.collect;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

public abstract class ImmutableBiMap<K, V> extends ImmutableMap<K, V> implements BiMap<K, V> {
    private static final Map.Entry<?, ?>[] EMPTY_ENTRY_ARRAY = new Map.Entry[0];

    public abstract ImmutableBiMap<V, K> inverse();

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m63of() {
        return EmptyImmutableBiMap.INSTANCE;
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m64of(K k, V v) {
        return new SingletonImmutableBiMap(k, v);
    }

    public static final class Builder<K, V> extends ImmutableMap.Builder<K, V> {
        public Builder<K, V> put(K k, V v) {
            super.put(k, v);
            return this;
        }

        public ImmutableBiMap<K, V> build() {
            int i = this.size;
            if (i == 0) {
                return ImmutableBiMap.m63of();
            }
            if (i != 1) {
                return new RegularImmutableBiMap(i, this.entries);
            }
            return ImmutableBiMap.m64of(this.entries[0].getKey(), this.entries[0].getValue());
        }
    }

    ImmutableBiMap() {
    }

    public ImmutableSet<V> values() {
        return inverse().keySet();
    }

    private static class SerializedForm extends ImmutableMap.SerializedForm {
        private static final long serialVersionUID = 0;

        SerializedForm(ImmutableBiMap<?, ?> immutableBiMap) {
            super(immutableBiMap);
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return createMap(new Builder());
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
