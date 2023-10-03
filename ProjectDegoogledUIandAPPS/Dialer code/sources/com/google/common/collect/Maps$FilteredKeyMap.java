package com.google.common.collect;

import com.google.common.base.Predicate;
import java.util.Map;
import java.util.Set;

class Maps$FilteredKeyMap<K, V> extends Maps$AbstractFilteredMap<K, V> {
    final Predicate<? super K> keyPredicate;

    Maps$FilteredKeyMap(Map<K, V> map, Predicate<? super K> predicate, Predicate<? super Map.Entry<K, V>> predicate2) {
        super(map, predicate2);
        this.keyPredicate = predicate;
    }

    public boolean containsKey(Object obj) {
        return this.unfiltered.containsKey(obj) && this.keyPredicate.apply(obj);
    }

    /* access modifiers changed from: protected */
    public Set<Map.Entry<K, V>> createEntrySet() {
        return Collections2.filter(this.unfiltered.entrySet(), this.predicate);
    }

    /* access modifiers changed from: package-private */
    public Set<K> createKeySet() {
        return Collections2.filter(this.unfiltered.keySet(), this.keyPredicate);
    }
}
