package com.google.common.collect;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMapEntry;
import com.google.common.collect.ImmutableMapEntrySet;
import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class RegularImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    static final RegularImmutableBiMap<Object, Object> EMPTY = new RegularImmutableBiMap((ImmutableMapEntry<K, V>[]) null, (ImmutableMapEntry<K, V>[]) null, ImmutableMap.EMPTY_ENTRY_ARRAY, 0, 0);
    /* access modifiers changed from: private */
    public final transient Map.Entry<K, V>[] entries;
    /* access modifiers changed from: private */
    public final transient int hashCode;
    private transient ImmutableBiMap<V, K> inverse;
    private final transient ImmutableMapEntry<K, V>[] keyTable;
    /* access modifiers changed from: private */
    public final transient int mask;
    /* access modifiers changed from: private */
    public final transient ImmutableMapEntry<K, V>[] valueTable;

    private final class Inverse extends ImmutableBiMap<V, K> {

        final class InverseEntrySet extends ImmutableMapEntrySet<V, K> {
            InverseEntrySet() {
            }

            /* access modifiers changed from: package-private */
            public ImmutableList<Map.Entry<V, K>> createAsList() {
                return new ImmutableAsList<Map.Entry<V, K>>() {
                    /* access modifiers changed from: package-private */
                    public ImmutableCollection<Map.Entry<V, K>> delegateCollection() {
                        return InverseEntrySet.this;
                    }

                    public Map.Entry<V, K> get(int i) {
                        Map.Entry entry = RegularImmutableBiMap.this.entries[i];
                        return new ImmutableEntry(entry.getValue(), entry.getKey());
                    }
                };
            }

            public void forEach(Consumer<? super Map.Entry<V, K>> consumer) {
                asList().forEach(consumer);
            }

            public int hashCode() {
                return RegularImmutableBiMap.this.hashCode;
            }

            /* access modifiers changed from: package-private */
            public boolean isHashCodeFast() {
                return true;
            }

            /* access modifiers changed from: package-private */
            public ImmutableMap<V, K> map() {
                return Inverse.this;
            }

            public UnmodifiableIterator<Map.Entry<V, K>> iterator() {
                return asList().iterator();
            }
        }

        /* synthetic */ Inverse(C08821 r2) {
        }

        /* access modifiers changed from: package-private */
        public ImmutableSet<Map.Entry<V, K>> createEntrySet() {
            return new InverseEntrySet();
        }

        /* access modifiers changed from: package-private */
        public ImmutableSet<V> createKeySet() {
            return new ImmutableMapKeySet(this);
        }

        public void forEach(BiConsumer<? super V, ? super K> biConsumer) {
            if (biConsumer != null) {
                RegularImmutableBiMap.this.forEach(new BiConsumer(biConsumer) {
                    private final /* synthetic */ BiConsumer f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final void accept(Object obj, Object obj2) {
                        this.f$0.accept(obj2, obj);
                    }
                });
                return;
            }
            throw new NullPointerException();
        }

        public K get(Object obj) {
            if (!(obj == null || RegularImmutableBiMap.this.valueTable == null)) {
                for (ImmutableMapEntry immutableMapEntry = RegularImmutableBiMap.this.valueTable[Collections2.smear(obj.hashCode()) & RegularImmutableBiMap.this.mask]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.getNextInValueBucket()) {
                    if (obj.equals(immutableMapEntry.getValue())) {
                        return immutableMapEntry.getKey();
                    }
                }
            }
            return null;
        }

        public ImmutableBiMap<K, V> inverse() {
            return RegularImmutableBiMap.this;
        }

        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return false;
        }

        public int size() {
            return inverse().size();
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

    private RegularImmutableBiMap(ImmutableMapEntry<K, V>[] immutableMapEntryArr, ImmutableMapEntry<K, V>[] immutableMapEntryArr2, Map.Entry<K, V>[] entryArr, int i, int i2) {
        this.keyTable = immutableMapEntryArr;
        this.valueTable = immutableMapEntryArr2;
        this.entries = entryArr;
        this.mask = i;
        this.hashCode = i2;
    }

    static <K, V> RegularImmutableBiMap<K, V> fromEntryArray(int i, Map.Entry<K, V>[] entryArr) {
        ImmutableMapEntry[] immutableMapEntryArr;
        boolean z;
        ImmutableMapEntry immutableMapEntry;
        int i2 = i;
        Map.Entry<K, V>[] entryArr2 = entryArr;
        MoreObjects.checkPositionIndex(i2, entryArr2.length);
        int closedTableSize = Collections2.closedTableSize(i2, 1.2d);
        int i3 = closedTableSize - 1;
        ImmutableMapEntry[] createEntryArray = ImmutableMapEntry.createEntryArray(closedTableSize);
        ImmutableMapEntry[] immutableMapEntryArr2 = new ImmutableMapEntry[closedTableSize];
        if (i2 == entryArr2.length) {
            immutableMapEntryArr = entryArr2;
        } else {
            immutableMapEntryArr = new ImmutableMapEntry[i2];
        }
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            Map.Entry<K, V> entry = entryArr2[i4];
            K key = entry.getKey();
            V value = entry.getValue();
            Collections2.checkEntryNotNull(key, value);
            int hashCode2 = key.hashCode();
            int hashCode3 = value.hashCode();
            int smear = Collections2.smear(hashCode2) & i3;
            int smear2 = Collections2.smear(hashCode3) & i3;
            ImmutableMapEntry immutableMapEntry2 = createEntryArray[smear];
            RegularImmutableMap.checkNoConflictInKeyBucket(key, entry, immutableMapEntry2);
            ImmutableMapEntry immutableMapEntry3 = immutableMapEntryArr2[smear2];
            ImmutableMapEntry immutableMapEntry4 = immutableMapEntry3;
            while (true) {
                z = true;
                if (immutableMapEntry4 == null) {
                    break;
                }
                ImmutableMap.checkNoConflict(!value.equals(immutableMapEntry4.getValue()), "value", entry, immutableMapEntry4);
                immutableMapEntry4 = immutableMapEntry4.getNextInValueBucket();
                i3 = i3;
                i5 = i5;
            }
            int i6 = i3;
            int i7 = i5;
            if (immutableMapEntry3 == null && immutableMapEntry2 == null) {
                if (!(entry instanceof ImmutableMapEntry) || !((ImmutableMapEntry) entry).isReusable()) {
                    z = false;
                }
                immutableMapEntry = z ? (ImmutableMapEntry) entry : new ImmutableMapEntry(key, value);
            } else {
                immutableMapEntry = new ImmutableMapEntry.NonTerminalImmutableBiMapEntry(key, value, immutableMapEntry2, immutableMapEntry3);
            }
            createEntryArray[smear] = immutableMapEntry;
            immutableMapEntryArr2[smear2] = immutableMapEntry;
            immutableMapEntryArr[i4] = immutableMapEntry;
            i5 = i7 + (hashCode2 ^ hashCode3);
            i4++;
            i2 = i;
            entryArr2 = entryArr;
            i3 = i6;
        }
        int i8 = i3;
        int i9 = i5;
        return new RegularImmutableBiMap(createEntryArray, immutableMapEntryArr2, immutableMapEntryArr, i3, i5);
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return isEmpty() ? ImmutableSet.m86of() : new ImmutableMapEntrySet.RegularEntrySet(this, this.entries);
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<K> createKeySet() {
        return new ImmutableMapKeySet(this);
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
        ImmutableMapEntry<K, V>[] immutableMapEntryArr = this.keyTable;
        if (immutableMapEntryArr == null) {
            return null;
        }
        return RegularImmutableMap.get(obj, immutableMapEntryArr, this.mask);
    }

    public int hashCode() {
        return this.hashCode;
    }

    public ImmutableBiMap<V, K> inverse() {
        if (isEmpty()) {
            return ImmutableBiMap.m72of();
        }
        ImmutableBiMap<V, K> immutableBiMap = this.inverse;
        if (immutableBiMap != null) {
            return immutableBiMap;
        }
        Inverse inverse2 = new Inverse((C08821) null);
        this.inverse = inverse2;
        return inverse2;
    }

    /* access modifiers changed from: package-private */
    public boolean isHashCodeFast() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }

    public int size() {
        return this.entries.length;
    }
}
