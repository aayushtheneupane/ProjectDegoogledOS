package com.google.common.collect;

import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.Map;

public abstract class ImmutableBiMap<K, V> extends ImmutableBiMapFauxverideShim<K, V> implements BiMap<K, V> {

    public static final class Builder<K, V> extends ImmutableMap.Builder<K, V> {
        public Builder() {
            super(4);
        }

        public ImmutableMap build() {
            int i = this.size;
            if (i == 0) {
                return ImmutableBiMap.m72of();
            }
            boolean z = true;
            if (i == 1) {
                return ImmutableBiMap.m73of(this.entries[0].getKey(), this.entries[0].getValue());
            }
            if (this.valueComparator != null) {
                if (this.entriesUsed) {
                    this.entries = (Map.Entry[]) Arrays.copyOf(this.entries, i);
                }
                Arrays.sort(this.entries, 0, this.size, Ordering.from(this.valueComparator).onResultOf(Maps$EntryFunction.VALUE));
            }
            if (this.size != this.entries.length) {
                z = false;
            }
            this.entriesUsed = z;
            return RegularImmutableBiMap.fromEntryArray(this.size, this.entries);
        }

        public ImmutableMap.Builder put(Object obj, Object obj2) {
            super.put(obj, obj2);
            return this;
        }

        public ImmutableMap.Builder putAll(Map map) {
            super.putAll(map);
            return this;
        }

        public ImmutableMap.Builder put(Map.Entry entry) {
            super.put(entry);
            return this;
        }

        public ImmutableMap.Builder putAll(Iterable iterable) {
            super.putAll(iterable);
            return this;
        }
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

    ImmutableBiMap() {
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m72of() {
        return RegularImmutableBiMap.EMPTY;
    }

    public abstract ImmutableBiMap<V, K> inverse();

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m73of(K k, V v) {
        return new SingletonImmutableBiMap(k, v);
    }

    /* access modifiers changed from: package-private */
    public final ImmutableSet<V> createValues() {
        throw new AssertionError("should never be called");
    }

    public ImmutableSet<V> values() {
        return inverse().keySet();
    }
}
