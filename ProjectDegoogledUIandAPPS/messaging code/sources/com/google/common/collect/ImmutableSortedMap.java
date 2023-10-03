package com.google.common.collect;

import com.google.common.base.C1508E;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;

public abstract class ImmutableSortedMap extends ImmutableSortedMapFauxverideShim implements NavigableMap {
    private static final long serialVersionUID = 0;

    /* renamed from: xN */
    private static final Comparator f2439xN = C1644bb.m4563zl();

    /* renamed from: yN */
    private static final ImmutableSortedMap f2440yN = new EmptyImmutableSortedMap(f2439xN);

    /* renamed from: wN */
    private transient ImmutableSortedMap f2441wN;

    class SerializedForm extends ImmutableMap.SerializedForm {
        private static final long serialVersionUID = 0;
        private final Comparator comparator;

        SerializedForm(ImmutableSortedMap immutableSortedMap) {
            super(immutableSortedMap);
            this.comparator = immutableSortedMap.comparator();
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return mo8745a(new C1626U(this.comparator));
        }
    }

    ImmutableSortedMap() {
    }

    /* renamed from: a */
    static ImmutableSortedMap m4240a(Comparator comparator) {
        if (C1644bb.m4563zl().equals(comparator)) {
            return m4242ql();
        }
        return new EmptyImmutableSortedMap(comparator);
    }

    /* renamed from: ql */
    public static ImmutableSortedMap m4242ql() {
        return f2440yN;
    }

    public Map.Entry ceilingEntry(Object obj) {
        return tailMap(obj, true).firstEntry();
    }

    public Object ceilingKey(Object obj) {
        return C1633Xa.m4539a(ceilingEntry(obj));
    }

    public Comparator comparator() {
        return keySet().comparator();
    }

    public boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    public Map.Entry firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Map.Entry) entrySet().mo8648Ol().get(0);
    }

    public Object firstKey() {
        return keySet().first();
    }

    public Map.Entry floorEntry(Object obj) {
        return headMap(obj, true).lastEntry();
    }

    public Object floorKey(Object obj) {
        return C1633Xa.m4539a(floorEntry(obj));
    }

    public abstract ImmutableSortedMap headMap(Object obj, boolean z);

    public Map.Entry higherEntry(Object obj) {
        return tailMap(obj, false).firstEntry();
    }

    public Object higherKey(Object obj) {
        return C1633Xa.m4539a(higherEntry(obj));
    }

    public abstract ImmutableSortedSet keySet();

    public Map.Entry lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Map.Entry) entrySet().mo8648Ol().get(size() - 1);
    }

    public Object lastKey() {
        return keySet().last();
    }

    public Map.Entry lowerEntry(Object obj) {
        return headMap(obj, false).lastEntry();
    }

    public Object lowerKey(Object obj) {
        return C1633Xa.m4539a(lowerEntry(obj));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8645pl() {
        return keySet().mo8636pl() || values().mo8636pl();
    }

    @Deprecated
    public final Map.Entry pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Map.Entry pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: rl */
    public abstract ImmutableSortedMap mo8660rl();

    public int size() {
        return values().size();
    }

    public abstract ImmutableSortedMap tailMap(Object obj, boolean z);

    public /* bridge */ /* synthetic */ Collection values() {
        return values();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    ImmutableSortedMap(ImmutableSortedMap immutableSortedMap) {
        this.f2441wN = immutableSortedMap;
    }

    public ImmutableSortedSet descendingKeySet() {
        return keySet().descendingSet();
    }

    public ImmutableSortedMap descendingMap() {
        ImmutableSortedMap immutableSortedMap = this.f2441wN;
        if (immutableSortedMap != null) {
            return immutableSortedMap;
        }
        ImmutableSortedMap rl = mo8660rl();
        this.f2441wN = rl;
        return rl;
    }

    public ImmutableSet entrySet() {
        return super.entrySet();
    }

    public ImmutableSortedSet navigableKeySet() {
        return keySet();
    }

    public ImmutableSortedMap headMap(Object obj) {
        return headMap(obj, false);
    }

    public ImmutableSortedMap subMap(Object obj, Object obj2) {
        return subMap(obj, true, obj2, false);
    }

    public ImmutableSortedMap tailMap(Object obj) {
        return tailMap(obj, true);
    }

    /* renamed from: a */
    static ImmutableSortedMap m4241a(Comparator comparator, boolean z, int i, Map.Entry... entryArr) {
        for (int i2 = 0; i2 < i; i2++) {
            Map.Entry entry = entryArr[i2];
            entryArr[i2] = ImmutableMap.m4213f(entry.getKey(), entry.getValue());
        }
        if (!z) {
            Arrays.sort(entryArr, 0, i, C1644bb.m4562b(comparator).mo9139Al());
            for (int i3 = 1; i3 < i; i3++) {
                int i4 = i3 - 1;
                ImmutableMap.m4211a(comparator.compare(entryArr[i4].getKey(), entryArr[i3].getKey()) != 0, "key", entryArr[i4], entryArr[i3]);
            }
        }
        if (i == 0) {
            return m4240a(comparator);
        }
        C1608Q builder = ImmutableList.builder();
        C1608Q builder2 = ImmutableList.builder();
        for (int i5 = 0; i5 < i; i5++) {
            Map.Entry entry2 = entryArr[i5];
            builder.add(entry2.getKey());
            builder2.add(entry2.getValue());
        }
        return new RegularImmutableSortedMap(new RegularImmutableSortedSet(builder.build(), comparator), builder2.build());
    }

    public ImmutableSortedMap subMap(Object obj, boolean z, Object obj2, boolean z2) {
        if (obj == null) {
            throw new NullPointerException();
        } else if (obj2 != null) {
            C1508E.checkArgument(comparator().compare(obj, obj2) <= 0, "expected fromKey <= toKey but %s > %s", obj, obj2);
            return headMap(obj2, z2).tailMap(obj, z);
        } else {
            throw new NullPointerException();
        }
    }
}
