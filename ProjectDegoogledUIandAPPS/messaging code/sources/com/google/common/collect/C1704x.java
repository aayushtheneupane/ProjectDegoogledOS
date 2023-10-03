package com.google.common.collect;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;

/* renamed from: com.google.common.collect.x */
abstract class C1704x extends AbstractMap implements NavigableMap {
    C1704x() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Vl */
    public abstract Iterator mo9079Vl();

    public Map.Entry ceilingEntry(Object obj) {
        return tailMap(obj, true).firstEntry();
    }

    public Object ceilingKey(Object obj) {
        return C1633Xa.m4539a(ceilingEntry(obj));
    }

    public NavigableSet descendingKeySet() {
        return descendingMap().navigableKeySet();
    }

    public NavigableMap descendingMap() {
        return new C1702w(this, (C1700v) null);
    }

    public Set entrySet() {
        return new C1700v(this);
    }

    public Map.Entry firstEntry() {
        return (Map.Entry) C1652ea.m4577b(mo9086gl(), (Object) null);
    }

    public Object firstKey() {
        Map.Entry firstEntry = firstEntry();
        if (firstEntry != null) {
            return firstEntry.getKey();
        }
        throw new NoSuchElementException();
    }

    public Map.Entry floorEntry(Object obj) {
        return headMap(obj, true).lastEntry();
    }

    public Object floorKey(Object obj) {
        return C1633Xa.m4539a(floorEntry(obj));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: gl */
    public abstract Iterator mo9086gl();

    public SortedMap headMap(Object obj) {
        return headMap(obj, false);
    }

    public Map.Entry higherEntry(Object obj) {
        return tailMap(obj, false).firstEntry();
    }

    public Object higherKey(Object obj) {
        return C1633Xa.m4539a(higherEntry(obj));
    }

    public Set keySet() {
        return navigableKeySet();
    }

    public Map.Entry lastEntry() {
        return (Map.Entry) C1652ea.m4577b(mo9079Vl(), (Object) null);
    }

    public Object lastKey() {
        Map.Entry lastEntry = lastEntry();
        if (lastEntry != null) {
            return lastEntry.getKey();
        }
        throw new NoSuchElementException();
    }

    public Map.Entry lowerEntry(Object obj) {
        return headMap(obj, false).lastEntry();
    }

    public Object lowerKey(Object obj) {
        return C1633Xa.m4539a(lowerEntry(obj));
    }

    public NavigableSet navigableKeySet() {
        return new C1627Ua(this);
    }

    public Map.Entry pollFirstEntry() {
        return (Map.Entry) C1652ea.m4579d(mo9086gl());
    }

    public Map.Entry pollLastEntry() {
        return (Map.Entry) C1652ea.m4579d(mo9079Vl());
    }

    public SortedMap subMap(Object obj, Object obj2) {
        return subMap(obj, true, obj2, false);
    }

    public SortedMap tailMap(Object obj) {
        return tailMap(obj, true);
    }
}
