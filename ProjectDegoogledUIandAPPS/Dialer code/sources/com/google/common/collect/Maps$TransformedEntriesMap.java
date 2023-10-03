package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.BiConsumer;

class Maps$TransformedEntriesMap<K, V1, V2> extends Maps$IteratorBasedAbstractMap<K, V2> {
    final Map<K, V1> fromMap;
    final Maps$EntryTransformer<? super K, ? super V1, V2> transformer;

    Maps$TransformedEntriesMap(Map<K, V1> map, Maps$EntryTransformer<? super K, ? super V1, V2> maps$EntryTransformer) {
        if (map != null) {
            this.fromMap = map;
            if (maps$EntryTransformer != null) {
                this.transformer = maps$EntryTransformer;
                return;
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    public void clear() {
        this.fromMap.clear();
    }

    public boolean containsKey(Object obj) {
        return this.fromMap.containsKey(obj);
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V2>> entryIterator() {
        Iterator<Map.Entry<K, V1>> it = this.fromMap.entrySet().iterator();
        Maps$EntryTransformer<? super K, ? super V1, V2> maps$EntryTransformer = this.transformer;
        if (maps$EntryTransformer != null) {
            return new Iterators$6(it, new Maps$11(maps$EntryTransformer));
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public Spliterator<Map.Entry<K, V2>> entrySpliterator() {
        Spliterator<Map.Entry<K, V1>> spliterator = this.fromMap.entrySet().spliterator();
        Maps$EntryTransformer<? super K, ? super V1, V2> maps$EntryTransformer = this.transformer;
        if (maps$EntryTransformer != null) {
            return Collections2.map(spliterator, new Maps$11(maps$EntryTransformer));
        }
        throw new NullPointerException();
    }

    public void forEach(BiConsumer<? super K, ? super V2> biConsumer) {
        if (biConsumer != null) {
            this.fromMap.forEach(new BiConsumer(biConsumer) {
                private final /* synthetic */ BiConsumer f$1;

                {
                    this.f$1 = r2;
                }

                public final void accept(Object obj, Object obj2) {
                    Maps$TransformedEntriesMap.this.lambda$forEach$0$Maps$TransformedEntriesMap(this.f$1, obj, obj2);
                }
            });
            return;
        }
        throw new NullPointerException();
    }

    public V2 get(Object obj) {
        V1 v1 = this.fromMap.get(obj);
        if (v1 != null || this.fromMap.containsKey(obj)) {
            return this.transformer.transformEntry(obj, v1);
        }
        return null;
    }

    public V2 getOrDefault(Object obj, V2 v2) {
        V1 v1 = this.fromMap.get(obj);
        return (v1 != null || this.fromMap.containsKey(obj)) ? this.transformer.transformEntry(obj, v1) : v2;
    }

    public Set<K> keySet() {
        return this.fromMap.keySet();
    }

    public /* synthetic */ void lambda$forEach$0$Maps$TransformedEntriesMap(BiConsumer biConsumer, Object obj, Object obj2) {
        biConsumer.accept(obj, this.transformer.transformEntry(obj, obj2));
    }

    public V2 remove(Object obj) {
        if (this.fromMap.containsKey(obj)) {
            return this.transformer.transformEntry(obj, this.fromMap.remove(obj));
        }
        return null;
    }

    public int size() {
        return this.fromMap.size();
    }

    public Collection<V2> values() {
        return new Maps$Values(this);
    }
}
