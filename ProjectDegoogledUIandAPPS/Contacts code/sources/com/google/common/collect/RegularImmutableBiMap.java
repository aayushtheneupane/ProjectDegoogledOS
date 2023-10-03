package com.google.common.collect;

import com.google.common.collect.ImmutableMapEntry;
import java.io.Serializable;
import java.util.Map;

class RegularImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    static final double MAX_LOAD_FACTOR = 1.2d;
    /* access modifiers changed from: private */
    public final transient ImmutableMapEntry<K, V>[] entries;
    /* access modifiers changed from: private */
    public final transient int hashCode;
    private transient ImmutableBiMap<V, K> inverse;
    private final transient ImmutableMapEntry<K, V>[] keyTable;
    /* access modifiers changed from: private */
    public final transient int mask;
    /* access modifiers changed from: private */
    public final transient ImmutableMapEntry<K, V>[] valueTable;

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }

    RegularImmutableBiMap(ImmutableMapEntry.TerminalEntry<?, ?>... terminalEntryArr) {
        this(terminalEntryArr.length, terminalEntryArr);
    }

    RegularImmutableBiMap(int i, ImmutableMapEntry.TerminalEntry<?, ?>[] terminalEntryArr) {
        int i2 = i;
        int closedTableSize = Hashing.closedTableSize(i2, MAX_LOAD_FACTOR);
        this.mask = closedTableSize - 1;
        ImmutableMapEntry<K, V>[] createEntryArray = createEntryArray(closedTableSize);
        ImmutableMapEntry<K, V>[] createEntryArray2 = createEntryArray(closedTableSize);
        ImmutableMapEntry<K, V>[] createEntryArray3 = createEntryArray(i);
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            ImmutableMapEntry immutableMapEntry = terminalEntryArr[i3];
            Object key = immutableMapEntry.getKey();
            Object value = immutableMapEntry.getValue();
            int hashCode2 = key.hashCode();
            int hashCode3 = value.hashCode();
            int smear = Hashing.smear(hashCode2) & this.mask;
            int smear2 = Hashing.smear(hashCode3) & this.mask;
            ImmutableMapEntry<K, V> immutableMapEntry2 = createEntryArray[smear];
            ImmutableMapEntry<K, V> immutableMapEntry3 = immutableMapEntry2;
            while (immutableMapEntry3 != null) {
                ImmutableMap.checkNoConflict(!key.equals(immutableMapEntry3.getKey()), "key", immutableMapEntry, immutableMapEntry3);
                immutableMapEntry3 = immutableMapEntry3.getNextInKeyBucket();
                int i5 = i;
                key = key;
            }
            ImmutableMapEntry<K, V> immutableMapEntry4 = createEntryArray2[smear2];
            ImmutableMapEntry<K, V> immutableMapEntry5 = immutableMapEntry4;
            while (immutableMapEntry5 != null) {
                ImmutableMap.checkNoConflict(!value.equals(immutableMapEntry5.getValue()), "value", immutableMapEntry, immutableMapEntry5);
                immutableMapEntry5 = immutableMapEntry5.getNextInValueBucket();
                value = value;
            }
            if (immutableMapEntry2 != null || immutableMapEntry4 != null) {
                immutableMapEntry = new NonTerminalBiMapEntry(immutableMapEntry, immutableMapEntry2, immutableMapEntry4);
            }
            createEntryArray[smear] = immutableMapEntry;
            createEntryArray2[smear2] = immutableMapEntry;
            createEntryArray3[i3] = immutableMapEntry;
            i4 += hashCode2 ^ hashCode3;
            i3++;
            i2 = i;
        }
        this.keyTable = createEntryArray;
        this.valueTable = createEntryArray2;
        this.entries = createEntryArray3;
        this.hashCode = i4;
    }

    RegularImmutableBiMap(Map.Entry<?, ?>[] entryArr) {
        ImmutableMapEntry<K, V> immutableMapEntry;
        RegularImmutableBiMap regularImmutableBiMap = this;
        Map.Entry<?, ?>[] entryArr2 = entryArr;
        int length = entryArr2.length;
        int closedTableSize = Hashing.closedTableSize(length, MAX_LOAD_FACTOR);
        regularImmutableBiMap.mask = closedTableSize - 1;
        ImmutableMapEntry<K, V>[] createEntryArray = createEntryArray(closedTableSize);
        ImmutableMapEntry<K, V>[] createEntryArray2 = createEntryArray(closedTableSize);
        ImmutableMapEntry<K, V>[] createEntryArray3 = createEntryArray(length);
        int i = 0;
        int i2 = 0;
        while (i < length) {
            Map.Entry<?, ?> entry = entryArr2[i];
            Object key = entry.getKey();
            Object value = entry.getValue();
            CollectPreconditions.checkEntryNotNull(key, value);
            int hashCode2 = key.hashCode();
            int hashCode3 = value.hashCode();
            int smear = Hashing.smear(hashCode2) & regularImmutableBiMap.mask;
            int smear2 = Hashing.smear(hashCode3) & regularImmutableBiMap.mask;
            ImmutableMapEntry<K, V> immutableMapEntry2 = createEntryArray[smear];
            ImmutableMapEntry<K, V> immutableMapEntry3 = immutableMapEntry2;
            while (immutableMapEntry3 != null) {
                ImmutableMap.checkNoConflict(!key.equals(immutableMapEntry3.getKey()), "key", entry, immutableMapEntry3);
                immutableMapEntry3 = immutableMapEntry3.getNextInKeyBucket();
                length = length;
            }
            int i3 = length;
            ImmutableMapEntry<K, V> immutableMapEntry4 = createEntryArray2[smear2];
            ImmutableMapEntry<K, V> immutableMapEntry5 = immutableMapEntry4;
            while (immutableMapEntry5 != null) {
                ImmutableMap.checkNoConflict(!value.equals(immutableMapEntry5.getValue()), "value", entry, immutableMapEntry5);
                immutableMapEntry5 = immutableMapEntry5.getNextInValueBucket();
                i2 = i2;
            }
            int i4 = i2;
            if (immutableMapEntry2 == null && immutableMapEntry4 == null) {
                immutableMapEntry = new ImmutableMapEntry.TerminalEntry<>(key, value);
            } else {
                immutableMapEntry = new NonTerminalBiMapEntry<>(key, value, immutableMapEntry2, immutableMapEntry4);
            }
            createEntryArray[smear] = immutableMapEntry;
            createEntryArray2[smear2] = immutableMapEntry;
            createEntryArray3[i] = immutableMapEntry;
            i2 = i4 + (hashCode2 ^ hashCode3);
            i++;
            regularImmutableBiMap = this;
            entryArr2 = entryArr;
            length = i3;
        }
        regularImmutableBiMap.keyTable = createEntryArray;
        regularImmutableBiMap.valueTable = createEntryArray2;
        regularImmutableBiMap.entries = createEntryArray3;
        regularImmutableBiMap.hashCode = i2;
    }

    private static final class NonTerminalBiMapEntry<K, V> extends ImmutableMapEntry<K, V> {
        private final ImmutableMapEntry<K, V> nextInKeyBucket;
        private final ImmutableMapEntry<K, V> nextInValueBucket;

        NonTerminalBiMapEntry(K k, V v, ImmutableMapEntry<K, V> immutableMapEntry, ImmutableMapEntry<K, V> immutableMapEntry2) {
            super(k, v);
            this.nextInKeyBucket = immutableMapEntry;
            this.nextInValueBucket = immutableMapEntry2;
        }

        NonTerminalBiMapEntry(ImmutableMapEntry<K, V> immutableMapEntry, ImmutableMapEntry<K, V> immutableMapEntry2, ImmutableMapEntry<K, V> immutableMapEntry3) {
            super(immutableMapEntry);
            this.nextInKeyBucket = immutableMapEntry2;
            this.nextInValueBucket = immutableMapEntry3;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return this.nextInKeyBucket;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMapEntry<K, V> getNextInValueBucket() {
            return this.nextInValueBucket;
        }
    }

    private static <K, V> ImmutableMapEntry<K, V>[] createEntryArray(int i) {
        return new ImmutableMapEntry[i];
    }

    public V get(Object obj) {
        if (obj == null) {
            return null;
        }
        for (ImmutableMapEntry<K, V> immutableMapEntry = this.keyTable[Hashing.smear(obj.hashCode()) & this.mask]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.getNextInKeyBucket()) {
            if (obj.equals(immutableMapEntry.getKey())) {
                return immutableMapEntry.getValue();
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new ImmutableMapEntrySet<K, V>() {
            /* access modifiers changed from: package-private */
            public boolean isHashCodeFast() {
                return true;
            }

            /* access modifiers changed from: package-private */
            public ImmutableMap<K, V> map() {
                return RegularImmutableBiMap.this;
            }

            public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
                return asList().iterator();
            }

            /* access modifiers changed from: package-private */
            public ImmutableList<Map.Entry<K, V>> createAsList() {
                return new RegularImmutableAsList(this, (Object[]) RegularImmutableBiMap.this.entries);
            }

            public int hashCode() {
                return RegularImmutableBiMap.this.hashCode;
            }
        };
    }

    public int size() {
        return this.entries.length;
    }

    public ImmutableBiMap<V, K> inverse() {
        ImmutableBiMap<V, K> immutableBiMap = this.inverse;
        if (immutableBiMap != null) {
            return immutableBiMap;
        }
        Inverse inverse2 = new Inverse();
        this.inverse = inverse2;
        return inverse2;
    }

    private final class Inverse extends ImmutableBiMap<V, K> {
        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return false;
        }

        private Inverse() {
        }

        public int size() {
            return inverse().size();
        }

        public ImmutableBiMap<K, V> inverse() {
            return RegularImmutableBiMap.this;
        }

        public K get(Object obj) {
            if (obj == null) {
                return null;
            }
            for (ImmutableMapEntry immutableMapEntry = RegularImmutableBiMap.this.valueTable[Hashing.smear(obj.hashCode()) & RegularImmutableBiMap.this.mask]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.getNextInValueBucket()) {
                if (obj.equals(immutableMapEntry.getValue())) {
                    return immutableMapEntry.getKey();
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public ImmutableSet<Map.Entry<V, K>> createEntrySet() {
            return new InverseEntrySet();
        }

        final class InverseEntrySet extends ImmutableMapEntrySet<V, K> {
            /* access modifiers changed from: package-private */
            public boolean isHashCodeFast() {
                return true;
            }

            InverseEntrySet() {
            }

            /* access modifiers changed from: package-private */
            public ImmutableMap<V, K> map() {
                return Inverse.this;
            }

            public int hashCode() {
                return RegularImmutableBiMap.this.hashCode;
            }

            public UnmodifiableIterator<Map.Entry<V, K>> iterator() {
                return asList().iterator();
            }

            /* access modifiers changed from: package-private */
            public ImmutableList<Map.Entry<V, K>> createAsList() {
                return new ImmutableAsList<Map.Entry<V, K>>() {
                    public Map.Entry<V, K> get(int i) {
                        ImmutableMapEntry immutableMapEntry = RegularImmutableBiMap.this.entries[i];
                        return Maps.immutableEntry(immutableMapEntry.getValue(), immutableMapEntry.getKey());
                    }

                    /* access modifiers changed from: package-private */
                    public ImmutableCollection<Map.Entry<V, K>> delegateCollection() {
                        return InverseEntrySet.this;
                    }
                };
            }
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return new InverseSerializedForm(RegularImmutableBiMap.this);
        }
    }

    private static class InverseSerializedForm<K, V> implements Serializable {
        private static final long serialVersionUID = 1;
        private final ImmutableBiMap<K, V> forward;

        InverseSerializedForm(ImmutableBiMap<K, V> immutableBiMap) {
            this.forward = immutableBiMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.forward.inverse();
        }
    }
}
