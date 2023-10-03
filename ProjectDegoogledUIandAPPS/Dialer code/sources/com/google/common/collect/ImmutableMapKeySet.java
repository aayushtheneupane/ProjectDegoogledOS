package com.google.common.collect;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

final class ImmutableMapKeySet<K, V> extends ImmutableSet.Indexed<K> {
    private final ImmutableMap<K, V> map;

    private static class KeySetSerializedForm<K> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<K, ?> map;

        KeySetSerializedForm(ImmutableMap<K, ?> immutableMap) {
            this.map = immutableMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.map.keySet();
        }
    }

    ImmutableMapKeySet(ImmutableMap<K, V> immutableMap) {
        this.map = immutableMap;
    }

    public boolean contains(Object obj) {
        return this.map.containsKey(obj);
    }

    public void forEach(Consumer<? super K> consumer) {
        if (consumer != null) {
            this.map.forEach(new BiConsumer(consumer) {
                private final /* synthetic */ Consumer f$0;

                {
                    this.f$0 = r1;
                }

                public final void accept(Object obj, Object obj2) {
                    this.f$0.accept(obj);
                }
            });
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public K get(int i) {
        return this.map.entrySet().asList().get(i).getKey();
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return true;
    }

    public int size() {
        return this.map.size();
    }

    public Spliterator<K> spliterator() {
        return this.map.keySpliterator();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new KeySetSerializedForm(this.map);
    }

    public UnmodifiableIterator<K> iterator() {
        return this.map.keyIterator();
    }
}
