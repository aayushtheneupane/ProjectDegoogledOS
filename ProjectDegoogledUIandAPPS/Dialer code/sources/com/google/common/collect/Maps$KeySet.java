package com.google.common.collect;

import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class Maps$KeySet<K, V> extends Sets$ImprovedAbstractSet<K> {
    final Map<K, V> map;

    Maps$KeySet(Map<K, V> map2) {
        if (map2 != null) {
            this.map = map2;
            return;
        }
        throw new NullPointerException();
    }

    public void clear() {
        map().clear();
    }

    public boolean contains(Object obj) {
        return map().containsKey(obj);
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

    public boolean isEmpty() {
        return map().isEmpty();
    }

    public Iterator<K> iterator() {
        return Collections2.transform(map().entrySet().iterator(), Collections2.keyFunction());
    }

    /* access modifiers changed from: package-private */
    public Map<K, V> map() {
        return this.map;
    }

    public boolean remove(Object obj) {
        if (!map().containsKey(obj)) {
            return false;
        }
        map().remove(obj);
        return true;
    }

    public int size() {
        return map().size();
    }
}
