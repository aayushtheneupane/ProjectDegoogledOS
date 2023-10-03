package com.google.common.collect;

class ImmutableMapEntry<K, V> extends ImmutableEntry<K, V> {

    static final class NonTerminalImmutableBiMapEntry<K, V> extends NonTerminalImmutableMapEntry<K, V> {
        private final transient ImmutableMapEntry<K, V> nextInValueBucket;

        NonTerminalImmutableBiMapEntry(K k, V v, ImmutableMapEntry<K, V> immutableMapEntry, ImmutableMapEntry<K, V> immutableMapEntry2) {
            super(k, v, immutableMapEntry);
            this.nextInValueBucket = immutableMapEntry2;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMapEntry<K, V> getNextInValueBucket() {
            return this.nextInValueBucket;
        }
    }

    static class NonTerminalImmutableMapEntry<K, V> extends ImmutableMapEntry<K, V> {
        private final transient ImmutableMapEntry<K, V> nextInKeyBucket;

        NonTerminalImmutableMapEntry(K k, V v, ImmutableMapEntry<K, V> immutableMapEntry) {
            super(k, v);
            this.nextInKeyBucket = immutableMapEntry;
        }

        /* access modifiers changed from: package-private */
        public final ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return this.nextInKeyBucket;
        }

        /* access modifiers changed from: package-private */
        public final boolean isReusable() {
            return false;
        }
    }

    ImmutableMapEntry(K k, V v) {
        super(k, v);
        Collections2.checkEntryNotNull(k, v);
    }

    static <K, V> ImmutableMapEntry<K, V>[] createEntryArray(int i) {
        return new ImmutableMapEntry[i];
    }

    /* access modifiers changed from: package-private */
    public ImmutableMapEntry<K, V> getNextInKeyBucket() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public ImmutableMapEntry<K, V> getNextInValueBucket() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean isReusable() {
        return true;
    }
}
