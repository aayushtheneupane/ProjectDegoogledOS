package com.google.common.collect;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMapEntry;
import com.google.common.collect.ImmutableMapEntrySet;
import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;

final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    static final ImmutableMap<Object, Object> EMPTY = new RegularImmutableMap(ImmutableMap.EMPTY_ENTRY_ARRAY, (ImmutableMapEntry<K, V>[]) null, 0);
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public final transient Map.Entry<K, V>[] entries;
    private final transient int mask;
    private final transient ImmutableMapEntry<K, V>[] table;

    private static final class KeySet<K, V> extends ImmutableSet.Indexed<K> {
        private final RegularImmutableMap<K, V> map;

        private static class SerializedForm<K> implements Serializable {
            private static final long serialVersionUID = 0;
            final ImmutableMap<K, ?> map;

            SerializedForm(ImmutableMap<K, ?> immutableMap) {
                this.map = immutableMap;
            }

            /* access modifiers changed from: package-private */
            public Object readResolve() {
                return this.map.keySet();
            }
        }

        KeySet(RegularImmutableMap<K, V> regularImmutableMap) {
            this.map = regularImmutableMap;
        }

        public boolean contains(Object obj) {
            return this.map.containsKey(obj);
        }

        /* access modifiers changed from: package-private */
        public K get(int i) {
            return this.map.entries[i].getKey();
        }

        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return true;
        }

        public int size() {
            return this.map.size();
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return new SerializedForm(this.map);
        }
    }

    private static final class Values<K, V> extends ImmutableList<V> {
        final RegularImmutableMap<K, V> map;

        private static class SerializedForm<V> implements Serializable {
            private static final long serialVersionUID = 0;
            final ImmutableMap<?, V> map;

            SerializedForm(ImmutableMap<?, V> immutableMap) {
                this.map = immutableMap;
            }

            /* access modifiers changed from: package-private */
            public Object readResolve() {
                return this.map.values();
            }
        }

        Values(RegularImmutableMap<K, V> regularImmutableMap) {
            this.map = regularImmutableMap;
        }

        public V get(int i) {
            return this.map.entries[i].getValue();
        }

        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return true;
        }

        public int size() {
            return this.map.size();
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return new SerializedForm(this.map);
        }
    }

    private RegularImmutableMap(Map.Entry<K, V>[] entryArr, ImmutableMapEntry<K, V>[] immutableMapEntryArr, int i) {
        this.entries = entryArr;
        this.table = immutableMapEntryArr;
        this.mask = i;
    }

    static void checkNoConflictInKeyBucket(Object obj, Map.Entry<?, ?> entry, ImmutableMapEntry<?, ?> immutableMapEntry) {
        while (immutableMapEntry != null) {
            ImmutableMap.checkNoConflict(!obj.equals(immutableMapEntry.getKey()), "key", entry, immutableMapEntry);
            immutableMapEntry = immutableMapEntry.getNextInKeyBucket();
        }
    }

    static <K, V> RegularImmutableMap<K, V> fromEntries(Map.Entry<K, V>... entryArr) {
        return fromEntryArray(entryArr.length, entryArr);
    }

    static <K, V> RegularImmutableMap<K, V> fromEntryArray(int i, Map.Entry<K, V>[] entryArr) {
        Map.Entry<K, V>[] entryArr2;
        ImmutableMapEntry immutableMapEntry;
        MoreObjects.checkPositionIndex(i, entryArr.length);
        if (i == 0) {
            return (RegularImmutableMap) EMPTY;
        }
        if (i == entryArr.length) {
            entryArr2 = entryArr;
        } else {
            entryArr2 = ImmutableMapEntry.createEntryArray(i);
        }
        int closedTableSize = Collections2.closedTableSize(i, 1.2d);
        ImmutableMapEntry[] createEntryArray = ImmutableMapEntry.createEntryArray(closedTableSize);
        int i2 = closedTableSize - 1;
        for (int i3 = 0; i3 < i; i3++) {
            ImmutableMapEntry immutableMapEntry2 = entryArr[i3];
            K key = immutableMapEntry2.getKey();
            V value = immutableMapEntry2.getValue();
            Collections2.checkEntryNotNull(key, value);
            int smear = Collections2.smear(key.hashCode()) & i2;
            ImmutableMapEntry immutableMapEntry3 = createEntryArray[smear];
            if (immutableMapEntry3 == null) {
                immutableMapEntry = (immutableMapEntry2 instanceof ImmutableMapEntry) && immutableMapEntry2.isReusable() ? immutableMapEntry2 : new ImmutableMapEntry(key, value);
            } else {
                immutableMapEntry = new ImmutableMapEntry.NonTerminalImmutableMapEntry(key, value, immutableMapEntry3);
            }
            createEntryArray[smear] = immutableMapEntry;
            entryArr2[i3] = immutableMapEntry;
            checkNoConflictInKeyBucket(key, immutableMapEntry, immutableMapEntry3);
        }
        return new RegularImmutableMap<>(entryArr2, createEntryArray, i2);
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new ImmutableMapEntrySet.RegularEntrySet(this, this.entries);
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<K> createKeySet() {
        return new KeySet(this);
    }

    /* access modifiers changed from: package-private */
    public ImmutableCollection<V> createValues() {
        return new Values(this);
    }

    public void forEach(BiConsumer<? super K, ? super V> biConsumer) {
        if (biConsumer != null) {
            for (Map.Entry<K, V> entry : this.entries) {
                biConsumer.accept(entry.getKey(), entry.getValue());
            }
            return;
        }
        throw new NullPointerException();
    }

    public V get(Object obj) {
        return get(obj, this.table, this.mask);
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }

    public int size() {
        return this.entries.length;
    }

    static <V> V get(Object obj, ImmutableMapEntry<?, V>[] immutableMapEntryArr, int i) {
        if (!(obj == null || immutableMapEntryArr == null)) {
            for (ImmutableMapEntry<?, V> immutableMapEntry = immutableMapEntryArr[i & Collections2.smear(obj.hashCode())]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.getNextInKeyBucket()) {
                if (obj.equals(immutableMapEntry.getKey())) {
                    return immutableMapEntry.getValue();
                }
            }
        }
        return null;
    }
}
