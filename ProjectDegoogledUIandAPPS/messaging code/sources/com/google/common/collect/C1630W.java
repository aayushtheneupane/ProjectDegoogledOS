package com.google.common.collect;

import com.google.common.base.C1508E;
import com.google.common.base.C1511H;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedSet;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.google.common.collect.W */
public final class C1630W {
    /* renamed from: a */
    public static boolean m4531a(Comparator comparator, Iterable iterable) {
        Object obj;
        if (comparator == null) {
            throw new NullPointerException();
        } else if (iterable != null) {
            if (iterable instanceof SortedSet) {
                obj = ((SortedSet) iterable).comparator();
                if (obj == null) {
                    obj = C1644bb.m4563zl();
                }
            } else if (!(iterable instanceof C1674lb)) {
                return false;
            } else {
                obj = ((C1674lb) iterable).comparator();
            }
            return comparator.equals(obj);
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: c */
    static int m4535c(Set set) {
        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i = ~(~(i + (next != null ? next.hashCode() : 0)));
        }
        return i;
    }

    /* renamed from: e */
    static int m4536e(int i, String str) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " cannot be negative but was: " + i);
    }

    /* renamed from: h */
    static void m4537h(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException(C0632a.m1014a("null key in entry: null=", obj2));
        } else if (obj2 == null) {
            throw new NullPointerException(C0632a.m1015a("null value in entry: ", obj, "=null"));
        }
    }

    /* renamed from: a */
    public static int m4529a(List list, Object obj, Comparator comparator, SortedLists$KeyPresentBehavior sortedLists$KeyPresentBehavior, SortedLists$KeyAbsentBehavior sortedLists$KeyAbsentBehavior) {
        if (comparator == null) {
            throw new NullPointerException();
        } else if (list == null) {
            throw new NullPointerException();
        } else if (sortedLists$KeyPresentBehavior == null) {
            throw new NullPointerException();
        } else if (sortedLists$KeyAbsentBehavior != null) {
            if (!(list instanceof RandomAccess)) {
                list = new ArrayList(C1552A.m4040b(list));
            }
            int i = 0;
            int size = list.size() - 1;
            while (i <= size) {
                int i2 = (i + size) >>> 1;
                int compare = comparator.compare(obj, list.get(i2));
                if (compare < 0) {
                    size = i2 - 1;
                } else if (compare <= 0) {
                    return i + sortedLists$KeyPresentBehavior.mo8992a(comparator, obj, list.subList(i, size + 1), i2 - i);
                } else {
                    i = i2 + 1;
                }
            }
            return sortedLists$KeyAbsentBehavior.mo8991eb(i);
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: a */
    public static C1671kb m4530a(Set set, Set set2) {
        C1508E.checkNotNull(set, "set1");
        C1508E.checkNotNull(set2, "set2");
        return new C1665ib(set, C1511H.m3970b(C1511H.m3971d(set2)), set2);
    }

    /* renamed from: a */
    static boolean m4532a(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() != set2.size() || !set.containsAll(set2)) {
                    return false;
                }
                return true;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    /* renamed from: a */
    static boolean m4534a(Set set, Iterator it) {
        boolean z = false;
        while (it.hasNext()) {
            z |= set.remove(it.next());
        }
        return z;
    }

    /* renamed from: a */
    static boolean m4533a(Set set, Collection collection) {
        if (collection != null) {
            if (collection instanceof C1637Za) {
                collection = ((C1637Za) collection).mo9126V();
            }
            if (!(collection instanceof Set) || collection.size() <= set.size()) {
                return m4534a(set, collection.iterator());
            }
            return C1652ea.m4574a(set.iterator(), collection);
        }
        throw new NullPointerException();
    }
}
