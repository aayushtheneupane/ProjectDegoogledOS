package com.google.common.collect;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import java.io.Serializable;
import java.lang.Enum;
import java.util.EnumMap;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.BiConsumer;

final class ImmutableEnumMap<K extends Enum<K>, V> extends ImmutableMap.IteratorBasedImmutableMap<K, V> {
    private final transient EnumMap<K, V> delegate;

    private static class EnumSerializedForm<K extends Enum<K>, V> implements Serializable {
        private static final long serialVersionUID = 0;
        final EnumMap<K, V> delegate;

        EnumSerializedForm(EnumMap<K, V> enumMap) {
            this.delegate = enumMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return new ImmutableEnumMap(this.delegate, (C08601) null);
        }
    }

    private ImmutableEnumMap(EnumMap<K, V> enumMap) {
        this.delegate = enumMap;
        MoreObjects.checkArgument(!enumMap.isEmpty());
    }

    static <K extends Enum<K>, V> ImmutableMap<K, V> asImmutable(EnumMap<K, V> enumMap) {
        int size = enumMap.size();
        if (size == 0) {
            return RegularImmutableMap.EMPTY;
        }
        if (size != 1) {
            return new ImmutableEnumMap(enumMap);
        }
        Map.Entry entry = (Map.Entry) Collections2.getOnlyElement(enumMap.entrySet());
        return ImmutableBiMap.m73of(entry.getKey(), entry.getValue());
    }

    public boolean containsKey(Object obj) {
        return this.delegate.containsKey(obj);
    }

    /* access modifiers changed from: package-private */
    public UnmodifiableIterator<Map.Entry<K, V>> entryIterator() {
        return new Maps$6(this.delegate.entrySet().iterator());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ImmutableEnumMap) {
            obj = ((ImmutableEnumMap) obj).delegate;
        }
        return this.delegate.equals(obj);
    }

    public void forEach(BiConsumer<? super K, ? super V> biConsumer) {
        this.delegate.forEach(biConsumer);
    }

    public V get(Object obj) {
        return this.delegate.get(obj);
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public UnmodifiableIterator<K> keyIterator() {
        return Collections2.unmodifiableIterator(this.delegate.keySet().iterator());
    }

    /* access modifiers changed from: package-private */
    public Spliterator<K> keySpliterator() {
        return this.delegate.keySet().spliterator();
    }

    public int size() {
        return this.delegate.size();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new EnumSerializedForm(this.delegate);
    }

    /* synthetic */ ImmutableEnumMap(EnumMap enumMap, C08601 r2) {
        this.delegate = enumMap;
        MoreObjects.checkArgument(!enumMap.isEmpty());
    }
}
