package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMapEntry;
import java.io.Serializable;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;

public abstract class ImmutableMap<K, V> implements Map<K, V>, Serializable {
    private static final Map.Entry<?, ?>[] EMPTY_ENTRY_ARRAY = new Map.Entry[0];
    private transient ImmutableSet<Map.Entry<K, V>> entrySet;
    private transient ImmutableSet<K> keySet;
    private transient ImmutableSetMultimap<K, V> multimapView;
    private transient ImmutableCollection<V> values;

    /* access modifiers changed from: package-private */
    public abstract ImmutableSet<Map.Entry<K, V>> createEntrySet();

    public abstract V get(Object obj);

    /* access modifiers changed from: package-private */
    public abstract boolean isPartialView();

    /* renamed from: of */
    public static <K, V> ImmutableMap<K, V> m38of() {
        return ImmutableBiMap.m13of();
    }

    /* renamed from: of */
    public static <K, V> ImmutableMap<K, V> m39of(K k, V v) {
        return ImmutableBiMap.m14of(k, v);
    }

    /* renamed from: of */
    public static <K, V> ImmutableMap<K, V> m40of(K k, V v, K k2, V v2) {
        return new RegularImmutableMap((ImmutableMapEntry.TerminalEntry<?, ?>[]) new ImmutableMapEntry.TerminalEntry[]{entryOf(k, v), entryOf(k2, v2)});
    }

    /* renamed from: of */
    public static <K, V> ImmutableMap<K, V> m41of(K k, V v, K k2, V v2, K k3, V v3) {
        return new RegularImmutableMap((ImmutableMapEntry.TerminalEntry<?, ?>[]) new ImmutableMapEntry.TerminalEntry[]{entryOf(k, v), entryOf(k2, v2), entryOf(k3, v3)});
    }

    /* renamed from: of */
    public static <K, V> ImmutableMap<K, V> m42of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        return new RegularImmutableMap((ImmutableMapEntry.TerminalEntry<?, ?>[]) new ImmutableMapEntry.TerminalEntry[]{entryOf(k, v), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4)});
    }

    /* renamed from: of */
    public static <K, V> ImmutableMap<K, V> m43of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return new RegularImmutableMap((ImmutableMapEntry.TerminalEntry<?, ?>[]) new ImmutableMapEntry.TerminalEntry[]{entryOf(k, v), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5)});
    }

    static <K, V> ImmutableMapEntry.TerminalEntry<K, V> entryOf(K k, V v) {
        CollectPreconditions.checkEntryNotNull(k, v);
        return new ImmutableMapEntry.TerminalEntry<>(k, v);
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    static void checkNoConflict(boolean z, String str, Map.Entry<?, ?> entry, Map.Entry<?, ?> entry2) {
        if (!z) {
            throw new IllegalArgumentException("Multiple entries with same " + str + ": " + entry + " and " + entry2);
        }
    }

    public static class Builder<K, V> {
        ImmutableMapEntry.TerminalEntry<K, V>[] entries;
        int size;

        public Builder() {
            this(4);
        }

        Builder(int i) {
            this.entries = new ImmutableMapEntry.TerminalEntry[i];
            this.size = 0;
        }

        private void ensureCapacity(int i) {
            ImmutableMapEntry.TerminalEntry<K, V>[] terminalEntryArr = this.entries;
            if (i > terminalEntryArr.length) {
                this.entries = (ImmutableMapEntry.TerminalEntry[]) ObjectArrays.arraysCopyOf(terminalEntryArr, ImmutableCollection.Builder.expandedCapacity(terminalEntryArr.length, i));
            }
        }

        public Builder<K, V> put(K k, V v) {
            ensureCapacity(this.size + 1);
            ImmutableMapEntry.TerminalEntry<K, V> entryOf = ImmutableMap.entryOf(k, v);
            ImmutableMapEntry.TerminalEntry<K, V>[] terminalEntryArr = this.entries;
            int i = this.size;
            this.size = i + 1;
            terminalEntryArr[i] = entryOf;
            return this;
        }

        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            return put(entry.getKey(), entry.getValue());
        }

        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            ensureCapacity(this.size + map.size());
            for (Map.Entry<? extends K, ? extends V> put : map.entrySet()) {
                put(put);
            }
            return this;
        }

        public ImmutableMap<K, V> build() {
            int i = this.size;
            if (i == 0) {
                return ImmutableMap.m38of();
            }
            if (i != 1) {
                return new RegularImmutableMap(i, this.entries);
            }
            return ImmutableMap.m39of(this.entries[0].getKey(), this.entries[0].getValue());
        }
    }

    public static <K, V> ImmutableMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if ((map instanceof ImmutableMap) && !(map instanceof ImmutableSortedMap)) {
            ImmutableMap<K, V> immutableMap = (ImmutableMap) map;
            if (!immutableMap.isPartialView()) {
                return immutableMap;
            }
        } else if (map instanceof EnumMap) {
            return copyOfEnumMapUnsafe(map);
        }
        Map.Entry[] entryArr = (Map.Entry[]) map.entrySet().toArray(EMPTY_ENTRY_ARRAY);
        int length = entryArr.length;
        if (length == 0) {
            return m38of();
        }
        if (length != 1) {
            return new RegularImmutableMap((Map.Entry<?, ?>[]) entryArr);
        }
        Map.Entry entry = entryArr[0];
        return m39of(entry.getKey(), entry.getValue());
    }

    private static <K, V> ImmutableMap<K, V> copyOfEnumMapUnsafe(Map<? extends K, ? extends V> map) {
        return copyOfEnumMap((EnumMap) map);
    }

    private static <K extends Enum<K>, V> ImmutableMap<K, V> copyOfEnumMap(Map<K, ? extends V> map) {
        EnumMap enumMap = new EnumMap(map);
        for (Map.Entry entry : enumMap.entrySet()) {
            CollectPreconditions.checkEntryNotNull(entry.getKey(), entry.getValue());
        }
        return ImmutableEnumMap.asImmutable(enumMap);
    }

    ImmutableMap() {
    }

    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        ImmutableSet<Map.Entry<K, V>> immutableSet = this.entrySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<Map.Entry<K, V>> createEntrySet = createEntrySet();
        this.entrySet = createEntrySet;
        return createEntrySet;
    }

    public ImmutableSet<K> keySet() {
        ImmutableSet<K> immutableSet = this.keySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<K> createKeySet = createKeySet();
        this.keySet = createKeySet;
        return createKeySet;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<K> createKeySet() {
        return new ImmutableMapKeySet(this);
    }

    public ImmutableCollection<V> values() {
        ImmutableCollection<V> immutableCollection = this.values;
        if (immutableCollection != null) {
            return immutableCollection;
        }
        ImmutableMapValues immutableMapValues = new ImmutableMapValues(this);
        this.values = immutableMapValues;
        return immutableMapValues;
    }

    public ImmutableSetMultimap<K, V> asMultimap() {
        ImmutableSetMultimap<K, V> immutableSetMultimap = this.multimapView;
        if (immutableSetMultimap != null) {
            return immutableSetMultimap;
        }
        ImmutableSetMultimap<K, V> createMultimapView = createMultimapView();
        this.multimapView = createMultimapView;
        return createMultimapView;
    }

    private ImmutableSetMultimap<K, V> createMultimapView() {
        ImmutableMap viewMapValuesAsSingletonSets = viewMapValuesAsSingletonSets();
        return new ImmutableSetMultimap<>(viewMapValuesAsSingletonSets, viewMapValuesAsSingletonSets.size(), (Comparator) null);
    }

    private ImmutableMap<K, ImmutableSet<V>> viewMapValuesAsSingletonSets() {
        return new MapViewOfValuesAsSingletonSets(this);
    }

    private static final class MapViewOfValuesAsSingletonSets<K, V> extends ImmutableMap<K, ImmutableSet<V>> {
        /* access modifiers changed from: private */
        public final ImmutableMap<K, V> delegate;

        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return false;
        }

        MapViewOfValuesAsSingletonSets(ImmutableMap<K, V> immutableMap) {
            Preconditions.checkNotNull(immutableMap);
            this.delegate = immutableMap;
        }

        public int size() {
            return this.delegate.size();
        }

        public boolean containsKey(Object obj) {
            return this.delegate.containsKey(obj);
        }

        public ImmutableSet<V> get(Object obj) {
            V v = this.delegate.get(obj);
            if (v == null) {
                return null;
            }
            return ImmutableSet.m62of(v);
        }

        /* access modifiers changed from: package-private */
        public ImmutableSet<Map.Entry<K, ImmutableSet<V>>> createEntrySet() {
            return new ImmutableMapEntrySet<K, ImmutableSet<V>>() {
                /* access modifiers changed from: package-private */
                public ImmutableMap<K, ImmutableSet<V>> map() {
                    return MapViewOfValuesAsSingletonSets.this;
                }

                public UnmodifiableIterator<Map.Entry<K, ImmutableSet<V>>> iterator() {
                    final UnmodifiableIterator it = MapViewOfValuesAsSingletonSets.this.delegate.entrySet().iterator();
                    return new UnmodifiableIterator<Map.Entry<K, ImmutableSet<V>>>() {
                        public boolean hasNext() {
                            return it.hasNext();
                        }

                        public Map.Entry<K, ImmutableSet<V>> next() {
                            final Map.Entry entry = (Map.Entry) it.next();
                            return new AbstractMapEntry<K, ImmutableSet<V>>() {
                                public K getKey() {
                                    return entry.getKey();
                                }

                                public ImmutableSet<V> getValue() {
                                    return ImmutableSet.m62of(entry.getValue());
                                }
                            };
                        }
                    };
                }
            };
        }
    }

    public boolean equals(Object obj) {
        return Maps.equalsImpl(this, obj);
    }

    public int hashCode() {
        return entrySet().hashCode();
    }

    public String toString() {
        return Maps.toStringImpl(this);
    }

    static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final Object[] keys;
        private final Object[] values;

        SerializedForm(ImmutableMap<?, ?> immutableMap) {
            this.keys = new Object[immutableMap.size()];
            this.values = new Object[immutableMap.size()];
            UnmodifiableIterator<Map.Entry<?, ?>> it = immutableMap.entrySet().iterator();
            int i = 0;
            while (it.hasNext()) {
                Map.Entry next = it.next();
                this.keys[i] = next.getKey();
                this.values[i] = next.getValue();
                i++;
            }
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return createMap(new Builder());
        }

        /* access modifiers changed from: package-private */
        public Object createMap(Builder<Object, Object> builder) {
            int i = 0;
            while (true) {
                Object[] objArr = this.keys;
                if (i >= objArr.length) {
                    return builder.build();
                }
                builder.put(objArr[i], this.values[i]);
                i++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
