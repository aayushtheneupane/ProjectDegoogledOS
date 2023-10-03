package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class Maps$AsMapView<K, V> extends Maps$ViewCachingAbstractMap<K, V> {
    final Function<? super K, V> function;
    private final Set<K> set;

    Maps$AsMapView(Set<K> set2, Function<? super K, V> function2) {
        if (set2 != null) {
            this.set = set2;
            if (function2 != null) {
                this.function = function2;
                return;
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public Set<K> backingSet() {
        return this.set;
    }

    public void clear() {
        this.set.clear();
    }

    public boolean containsKey(Object obj) {
        return this.set.contains(obj);
    }

    /* access modifiers changed from: protected */
    public Set<Map.Entry<K, V>> createEntrySet() {
        return new Maps$EntrySet<K, V>() {
            public Iterator<Map.Entry<K, V>> iterator() {
                Set backingSet = Maps$AsMapView.this.backingSet();
                return new Maps$1(backingSet.iterator(), Maps$AsMapView.this.function);
            }

            /* access modifiers changed from: package-private */
            public Map<K, V> map() {
                return Maps$AsMapView.this;
            }
        };
    }

    public Set<K> createKeySet() {
        return new Maps$2(this.set);
    }

    /* access modifiers changed from: package-private */
    public Collection<V> createValues() {
        return new Collections2.TransformedCollection(this.set, this.function);
    }

    public void forEach(BiConsumer<? super K, ? super V> biConsumer) {
        if (biConsumer != null) {
            this.set.forEach(new Consumer(biConsumer) {
                private final /* synthetic */ BiConsumer f$1;

                {
                    this.f$1 = r2;
                }

                public final void accept(Object obj) {
                    Maps$AsMapView.this.lambda$forEach$0$Maps$AsMapView(this.f$1, obj);
                }
            });
            return;
        }
        throw new NullPointerException();
    }

    public V get(Object obj) {
        if (Collections2.safeContains(this.set, obj)) {
            return this.function.apply(obj);
        }
        return null;
    }

    public V getOrDefault(Object obj, V v) {
        return Collections2.safeContains(this.set, obj) ? this.function.apply(obj) : v;
    }

    public /* synthetic */ void lambda$forEach$0$Maps$AsMapView(BiConsumer biConsumer, Object obj) {
        biConsumer.accept(obj, this.function.apply(obj));
    }

    public V remove(Object obj) {
        if (this.set.remove(obj)) {
            return this.function.apply(obj);
        }
        return null;
    }

    public int size() {
        return this.set.size();
    }
}
