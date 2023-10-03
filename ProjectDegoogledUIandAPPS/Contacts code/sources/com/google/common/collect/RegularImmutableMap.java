package com.google.common.collect;

import com.google.common.collect.ImmutableMapEntry;
import java.util.Map;

final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    private static final double MAX_LOAD_FACTOR = 1.2d;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public final transient ImmutableMapEntry<K, V>[] entries;
    private final transient int mask;
    private final transient ImmutableMapEntry<K, V>[] table;

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }

    RegularImmutableMap(ImmutableMapEntry.TerminalEntry<?, ?>... terminalEntryArr) {
        this(terminalEntryArr.length, terminalEntryArr);
    }

    RegularImmutableMap(int i, ImmutableMapEntry.TerminalEntry<?, ?>[] terminalEntryArr) {
        this.entries = createEntryArray(i);
        int closedTableSize = Hashing.closedTableSize(i, MAX_LOAD_FACTOR);
        this.table = createEntryArray(closedTableSize);
        this.mask = closedTableSize - 1;
        for (int i2 = 0; i2 < i; i2++) {
            ImmutableMapEntry immutableMapEntry = terminalEntryArr[i2];
            Object key = immutableMapEntry.getKey();
            int smear = Hashing.smear(key.hashCode()) & this.mask;
            ImmutableMapEntry<K, V> immutableMapEntry2 = this.table[smear];
            if (immutableMapEntry2 != null) {
                immutableMapEntry = new NonTerminalMapEntry(immutableMapEntry, immutableMapEntry2);
            }
            this.table[smear] = immutableMapEntry;
            this.entries[i2] = immutableMapEntry;
            checkNoConflictInBucket(key, immutableMapEntry, immutableMapEntry2);
        }
    }

    RegularImmutableMap(Map.Entry<?, ?>[] entryArr) {
        ImmutableMapEntry<K, V> immutableMapEntry;
        int length = entryArr.length;
        this.entries = createEntryArray(length);
        int closedTableSize = Hashing.closedTableSize(length, MAX_LOAD_FACTOR);
        this.table = createEntryArray(closedTableSize);
        this.mask = closedTableSize - 1;
        for (int i = 0; i < length; i++) {
            Map.Entry<?, ?> entry = entryArr[i];
            Object key = entry.getKey();
            Object value = entry.getValue();
            CollectPreconditions.checkEntryNotNull(key, value);
            int smear = Hashing.smear(key.hashCode()) & this.mask;
            ImmutableMapEntry<K, V> immutableMapEntry2 = this.table[smear];
            if (immutableMapEntry2 == null) {
                immutableMapEntry = new ImmutableMapEntry.TerminalEntry<>(key, value);
            } else {
                immutableMapEntry = new NonTerminalMapEntry<>(key, value, immutableMapEntry2);
            }
            this.table[smear] = immutableMapEntry;
            this.entries[i] = immutableMapEntry;
            checkNoConflictInBucket(key, immutableMapEntry, immutableMapEntry2);
        }
    }

    private void checkNoConflictInBucket(K k, ImmutableMapEntry<K, V> immutableMapEntry, ImmutableMapEntry<K, V> immutableMapEntry2) {
        while (immutableMapEntry2 != null) {
            ImmutableMap.checkNoConflict(!k.equals(immutableMapEntry2.getKey()), "key", immutableMapEntry, immutableMapEntry2);
            immutableMapEntry2 = immutableMapEntry2.getNextInKeyBucket();
        }
    }

    private static final class NonTerminalMapEntry<K, V> extends ImmutableMapEntry<K, V> {
        private final ImmutableMapEntry<K, V> nextInKeyBucket;

        /* access modifiers changed from: package-private */
        public ImmutableMapEntry<K, V> getNextInValueBucket() {
            return null;
        }

        NonTerminalMapEntry(K k, V v, ImmutableMapEntry<K, V> immutableMapEntry) {
            super(k, v);
            this.nextInKeyBucket = immutableMapEntry;
        }

        NonTerminalMapEntry(ImmutableMapEntry<K, V> immutableMapEntry, ImmutableMapEntry<K, V> immutableMapEntry2) {
            super(immutableMapEntry);
            this.nextInKeyBucket = immutableMapEntry2;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return this.nextInKeyBucket;
        }
    }

    private ImmutableMapEntry<K, V>[] createEntryArray(int i) {
        return new ImmutableMapEntry[i];
    }

    public V get(Object obj) {
        if (obj == null) {
            return null;
        }
        for (ImmutableMapEntry<K, V> immutableMapEntry = this.table[Hashing.smear(obj.hashCode()) & this.mask]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.getNextInKeyBucket()) {
            if (obj.equals(immutableMapEntry.getKey())) {
                return immutableMapEntry.getValue();
            }
        }
        return null;
    }

    public int size() {
        return this.entries.length;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new EntrySet();
    }

    private class EntrySet extends ImmutableMapEntrySet<K, V> {
        private EntrySet() {
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap<K, V> map() {
            return RegularImmutableMap.this;
        }

        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return asList().iterator();
        }

        /* access modifiers changed from: package-private */
        public ImmutableList<Map.Entry<K, V>> createAsList() {
            return new RegularImmutableAsList(this, (Object[]) RegularImmutableMap.this.entries);
        }
    }
}
