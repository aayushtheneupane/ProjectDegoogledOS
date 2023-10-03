package com.google.common.collect;

import android.support.p002v7.appcompat.R$style;
import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

final class Maps$FilteredMapValues<K, V> extends Maps$Values<K, V> {
    final Predicate<? super Map.Entry<K, V>> predicate;
    final Map<K, V> unfiltered;

    Maps$FilteredMapValues(Map<K, V> map, Map<K, V> map2, Predicate<? super Map.Entry<K, V>> predicate2) {
        super(map);
        this.unfiltered = map2;
        this.predicate = predicate2;
    }

    public boolean remove(Object obj) {
        Iterator<Map.Entry<K, V>> it = this.unfiltered.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (this.predicate.apply(next) && R$style.equal(next.getValue(), obj)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public boolean removeAll(Collection<?> collection) {
        Iterator<Map.Entry<K, V>> it = this.unfiltered.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (this.predicate.apply(next) && collection.contains(next.getValue())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public boolean retainAll(Collection<?> collection) {
        Iterator<Map.Entry<K, V>> it = this.unfiltered.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (this.predicate.apply(next) && !collection.contains(next.getValue())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public Object[] toArray() {
        return Lists.newArrayList(Collections2.transform(this.map.entrySet().iterator(), Maps$EntryFunction.VALUE)).toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return Lists.newArrayList(Collections2.transform(this.map.entrySet().iterator(), Maps$EntryFunction.VALUE)).toArray(tArr);
    }
}
