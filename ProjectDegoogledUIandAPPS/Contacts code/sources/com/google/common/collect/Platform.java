package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

final class Platform {
    static <T> T[] newArray(T[] tArr, int i) {
        return (Object[]) Array.newInstance(tArr.getClass().getComponentType(), i);
    }

    static <E> Set<E> newSetFromMap(Map<E, Boolean> map) {
        return Collections.newSetFromMap(map);
    }

    static MapMaker tryWeakKeys(MapMaker mapMaker) {
        return mapMaker.weakKeys();
    }

    static <K, V1, V2> SortedMap<K, V2> mapsTransformEntriesSortedMap(SortedMap<K, V1> sortedMap, Maps.EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        if (sortedMap instanceof NavigableMap) {
            return Maps.transformEntries((NavigableMap) sortedMap, entryTransformer);
        }
        return Maps.transformEntriesIgnoreNavigable(sortedMap, entryTransformer);
    }

    static <K, V> SortedMap<K, V> mapsAsMapSortedSet(SortedSet<K> sortedSet, Function<? super K, V> function) {
        if (sortedSet instanceof NavigableSet) {
            return Maps.asMap((NavigableSet) sortedSet, function);
        }
        return Maps.asMapSortedIgnoreNavigable(sortedSet, function);
    }

    static <E> SortedSet<E> setsFilterSortedSet(SortedSet<E> sortedSet, Predicate<? super E> predicate) {
        if (sortedSet instanceof NavigableSet) {
            return Sets.filter((NavigableSet) sortedSet, predicate);
        }
        return Sets.filterSortedIgnoreNavigable(sortedSet, predicate);
    }

    static <K, V> SortedMap<K, V> mapsFilterSortedMap(SortedMap<K, V> sortedMap, Predicate<? super Map.Entry<K, V>> predicate) {
        if (sortedMap instanceof NavigableMap) {
            return Maps.filterEntries((NavigableMap) sortedMap, predicate);
        }
        return Maps.filterSortedIgnoreNavigable(sortedMap, predicate);
    }

    private Platform() {
    }
}
