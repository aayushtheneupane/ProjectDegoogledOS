package com.google.common.collect;

abstract class ImmutableMapEntry<K, V> extends ImmutableEntry<K, V> {
    /* access modifiers changed from: package-private */
    public abstract ImmutableMapEntry<K, V> getNextInKeyBucket();

    /* access modifiers changed from: package-private */
    public abstract ImmutableMapEntry<K, V> getNextInValueBucket();

    ImmutableMapEntry(K k, V v) {
        super(k, v);
        CollectPreconditions.checkEntryNotNull(k, v);
    }

    ImmutableMapEntry(ImmutableMapEntry<K, V> immutableMapEntry) {
        super(immutableMapEntry.getKey(), immutableMapEntry.getValue());
    }

    static final class TerminalEntry<K, V> extends ImmutableMapEntry<K, V> {
        /* access modifiers changed from: package-private */
        public ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return null;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMapEntry<K, V> getNextInValueBucket() {
            return null;
        }

        TerminalEntry(K k, V v) {
            super(k, v);
        }
    }
}
