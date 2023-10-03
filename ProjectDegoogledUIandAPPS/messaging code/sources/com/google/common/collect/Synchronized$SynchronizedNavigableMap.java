package com.google.common.collect;

import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;

class Synchronized$SynchronizedNavigableMap extends Synchronized$SynchronizedSortedMap implements NavigableMap {
    private static final long serialVersionUID = 0;

    /* renamed from: dO */
    transient NavigableSet f2500dO;

    /* renamed from: oN */
    transient NavigableSet f2501oN;

    /* renamed from: wN */
    transient NavigableMap f2502wN;

    Synchronized$SynchronizedNavigableMap(NavigableMap navigableMap, Object obj) {
        super(navigableMap, obj);
    }

    public Map.Entry ceilingEntry(Object obj) {
        Map.Entry a;
        synchronized (this.mutex) {
            a = C1683ob.m4615a(m4508ml().ceilingEntry(obj), this.mutex);
        }
        return a;
    }

    public Object ceilingKey(Object obj) {
        Object ceilingKey;
        synchronized (this.mutex) {
            ceilingKey = m4508ml().ceilingKey(obj);
        }
        return ceilingKey;
    }

    public NavigableSet descendingKeySet() {
        synchronized (this.mutex) {
            if (this.f2500dO == null) {
                Synchronized$SynchronizedNavigableSet synchronized$SynchronizedNavigableSet = new Synchronized$SynchronizedNavigableSet(m4508ml().descendingKeySet(), this.mutex);
                this.f2500dO = synchronized$SynchronizedNavigableSet;
                return synchronized$SynchronizedNavigableSet;
            }
            NavigableSet navigableSet = this.f2500dO;
            return navigableSet;
        }
    }

    public NavigableMap descendingMap() {
        synchronized (this.mutex) {
            if (this.f2502wN == null) {
                Synchronized$SynchronizedNavigableMap synchronized$SynchronizedNavigableMap = new Synchronized$SynchronizedNavigableMap(m4508ml().descendingMap(), this.mutex);
                this.f2502wN = synchronized$SynchronizedNavigableMap;
                return synchronized$SynchronizedNavigableMap;
            }
            NavigableMap navigableMap = this.f2502wN;
            return navigableMap;
        }
    }

    public Map.Entry firstEntry() {
        Map.Entry a;
        synchronized (this.mutex) {
            a = C1683ob.m4615a(m4508ml().firstEntry(), this.mutex);
        }
        return a;
    }

    public Map.Entry floorEntry(Object obj) {
        Map.Entry a;
        synchronized (this.mutex) {
            a = C1683ob.m4615a(m4508ml().floorEntry(obj), this.mutex);
        }
        return a;
    }

    public Object floorKey(Object obj) {
        Object floorKey;
        synchronized (this.mutex) {
            floorKey = m4508ml().floorKey(obj);
        }
        return floorKey;
    }

    public NavigableMap headMap(Object obj, boolean z) {
        Synchronized$SynchronizedNavigableMap synchronized$SynchronizedNavigableMap;
        synchronized (this.mutex) {
            synchronized$SynchronizedNavigableMap = new Synchronized$SynchronizedNavigableMap(m4508ml().headMap(obj, z), this.mutex);
        }
        return synchronized$SynchronizedNavigableMap;
    }

    public Map.Entry higherEntry(Object obj) {
        Map.Entry a;
        synchronized (this.mutex) {
            a = C1683ob.m4615a(m4508ml().higherEntry(obj), this.mutex);
        }
        return a;
    }

    public Object higherKey(Object obj) {
        Object higherKey;
        synchronized (this.mutex) {
            higherKey = m4508ml().higherKey(obj);
        }
        return higherKey;
    }

    public Set keySet() {
        return navigableKeySet();
    }

    public Map.Entry lastEntry() {
        Map.Entry a;
        synchronized (this.mutex) {
            a = C1683ob.m4615a(m4508ml().lastEntry(), this.mutex);
        }
        return a;
    }

    public Map.Entry lowerEntry(Object obj) {
        Map.Entry a;
        synchronized (this.mutex) {
            a = C1683ob.m4615a(m4508ml().lowerEntry(obj), this.mutex);
        }
        return a;
    }

    public Object lowerKey(Object obj) {
        Object lowerKey;
        synchronized (this.mutex) {
            lowerKey = m4508ml().lowerKey(obj);
        }
        return lowerKey;
    }

    public NavigableSet navigableKeySet() {
        synchronized (this.mutex) {
            if (this.f2501oN == null) {
                Synchronized$SynchronizedNavigableSet synchronized$SynchronizedNavigableSet = new Synchronized$SynchronizedNavigableSet(m4508ml().navigableKeySet(), this.mutex);
                this.f2501oN = synchronized$SynchronizedNavigableSet;
                return synchronized$SynchronizedNavigableSet;
            }
            NavigableSet navigableSet = this.f2501oN;
            return navigableSet;
        }
    }

    public Map.Entry pollFirstEntry() {
        Map.Entry a;
        synchronized (this.mutex) {
            a = C1683ob.m4615a(m4508ml().pollFirstEntry(), this.mutex);
        }
        return a;
    }

    public Map.Entry pollLastEntry() {
        Map.Entry a;
        synchronized (this.mutex) {
            a = C1683ob.m4615a(m4508ml().pollLastEntry(), this.mutex);
        }
        return a;
    }

    public NavigableMap subMap(Object obj, boolean z, Object obj2, boolean z2) {
        Synchronized$SynchronizedNavigableMap synchronized$SynchronizedNavigableMap;
        synchronized (this.mutex) {
            synchronized$SynchronizedNavigableMap = new Synchronized$SynchronizedNavigableMap(m4508ml().subMap(obj, z, obj2, z2), this.mutex);
        }
        return synchronized$SynchronizedNavigableMap;
    }

    public NavigableMap tailMap(Object obj, boolean z) {
        Synchronized$SynchronizedNavigableMap synchronized$SynchronizedNavigableMap;
        synchronized (this.mutex) {
            synchronized$SynchronizedNavigableMap = new Synchronized$SynchronizedNavigableMap(m4508ml().tailMap(obj, z), this.mutex);
        }
        return synchronized$SynchronizedNavigableMap;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ml */
    public NavigableMap m4507ml() {
        return (NavigableMap) super.mo8993ml();
    }

    public SortedMap headMap(Object obj) {
        return headMap(obj, false);
    }

    public SortedMap subMap(Object obj, Object obj2) {
        return subMap(obj, true, obj2, false);
    }

    public SortedMap tailMap(Object obj) {
        return tailMap(obj, true);
    }
}
