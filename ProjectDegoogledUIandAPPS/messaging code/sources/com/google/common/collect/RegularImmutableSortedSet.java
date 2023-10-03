package com.google.common.collect;

import com.google.common.base.C1508E;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

final class RegularImmutableSortedSet extends ImmutableSortedSet {
    private final transient ImmutableList elements;

    RegularImmutableSortedSet(ImmutableList immutableList, Comparator comparator) {
        super(comparator);
        this.elements = immutableList;
        C1508E.checkArgument(!immutableList.isEmpty());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: N */
    public ImmutableSortedSet mo8964N(int i, int i2) {
        if (i == 0 && i2 == size()) {
            return this;
        }
        if (i < i2) {
            return new RegularImmutableSortedSet(this.elements.subList(i, i2), this.comparator);
        }
        return ImmutableSortedSet.m4246c(this.comparator);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Pl */
    public ImmutableList mo8702Pl() {
        return new ImmutableSortedAsList(this, this.elements);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Sl */
    public ImmutableSortedSet mo8624Sl() {
        return new RegularImmutableSortedSet(this.elements.reverse(), C1644bb.m4562b(this.comparator).reverse());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Tl */
    public Comparator mo8965Tl() {
        return this.comparator;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public ImmutableSortedSet mo8625a(Object obj, boolean z) {
        return mo8964N(0, mo8966c(obj, z));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public ImmutableSortedSet mo8627b(Object obj, boolean z) {
        return mo8964N(mo8968d(obj, z), size());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public int mo8966c(Object obj, boolean z) {
        ImmutableList immutableList = this.elements;
        if (obj != null) {
            return C1630W.m4529a(immutableList, obj, comparator(), z ? SortedLists$KeyPresentBehavior.FIRST_AFTER : SortedLists$KeyPresentBehavior.FIRST_PRESENT, SortedLists$KeyAbsentBehavior.NEXT_HIGHER);
        }
        throw new NullPointerException();
    }

    public Object ceiling(Object obj) {
        int d = mo8968d(obj, true);
        if (d == size()) {
            return null;
        }
        return this.elements.get(d);
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return Collections.binarySearch(this.elements, obj, mo8965Tl()) >= 0;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public boolean containsAll(Collection collection) {
        if (collection instanceof C1637Za) {
            collection = ((C1637Za) collection).mo9126V();
        }
        if (!C1630W.m4531a(comparator(), (Iterable) collection) || collection.size() <= 1) {
            return super.containsAll(collection);
        }
        C1647cb c = C1652ea.m4578c(iterator());
        Iterator it = collection.iterator();
        Object next = it.next();
        while (c.hasNext()) {
            try {
                C1649da daVar = (C1649da) c;
                int j = mo8782j(daVar.peek(), next);
                if (j < 0) {
                    daVar.next();
                } else if (j == 0) {
                    if (!it.hasNext()) {
                        return true;
                    }
                    next = it.next();
                } else if (j > 0) {
                    break;
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public int mo8652d(Object[] objArr, int i) {
        return this.elements.mo8652d(objArr, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002d A[Catch:{ ClassCastException | NoSuchElementException -> 0x003f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r6 instanceof java.util.Set
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            java.util.Set r6 = (java.util.Set) r6
            int r1 = r5.size()
            int r3 = r6.size()
            if (r1 == r3) goto L_0x0017
            return r2
        L_0x0017:
            java.util.Comparator r1 = r5.comparator
            boolean r1 = com.google.common.collect.C1630W.m4531a((java.util.Comparator) r1, (java.lang.Iterable) r6)
            if (r1 == 0) goto L_0x0040
            java.util.Iterator r6 = r6.iterator()
            com.google.common.collect.rb r1 = r5.iterator()     // Catch:{ ClassCastException | NoSuchElementException -> 0x003f }
        L_0x0027:
            boolean r3 = r1.hasNext()     // Catch:{ ClassCastException | NoSuchElementException -> 0x003f }
            if (r3 == 0) goto L_0x003e
            java.lang.Object r3 = r1.next()     // Catch:{ ClassCastException | NoSuchElementException -> 0x003f }
            java.lang.Object r4 = r6.next()     // Catch:{ ClassCastException | NoSuchElementException -> 0x003f }
            if (r4 == 0) goto L_0x003d
            int r3 = r5.mo8782j(r3, r4)     // Catch:{ ClassCastException | NoSuchElementException -> 0x003f }
            if (r3 == 0) goto L_0x0027
        L_0x003d:
            return r2
        L_0x003e:
            return r0
        L_0x003f:
            return r2
        L_0x0040:
            boolean r5 = r5.containsAll(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.RegularImmutableSortedSet.equals(java.lang.Object):boolean");
    }

    public Object first() {
        return this.elements.get(0);
    }

    public Object floor(Object obj) {
        int c = mo8966c(obj, true) - 1;
        if (c == -1) {
            return null;
        }
        return this.elements.get(c);
    }

    public Object higher(Object obj) {
        int d = mo8968d(obj, false);
        if (d == size()) {
            return null;
        }
        return this.elements.get(d);
    }

    /* access modifiers changed from: package-private */
    public int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        try {
            int a = C1630W.m4529a(this.elements, obj, mo8965Tl(), SortedLists$KeyPresentBehavior.ANY_PRESENT, SortedLists$KeyAbsentBehavior.INVERTED_INSERTION_INDEX);
            if (a >= 0) {
                return a;
            }
            return -1;
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    public boolean isEmpty() {
        return false;
    }

    public Object last() {
        return this.elements.get(size() - 1);
    }

    public Object lower(Object obj) {
        int c = mo8966c(obj, false) - 1;
        if (c == -1) {
            return null;
        }
        return this.elements.get(c);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8636pl() {
        return this.elements.mo8636pl();
    }

    public int size() {
        return this.elements.size();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public ImmutableSortedSet mo8626a(Object obj, boolean z, Object obj2, boolean z2) {
        return mo8627b(obj, z).mo8625a(obj2, z2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public int mo8968d(Object obj, boolean z) {
        ImmutableList immutableList = this.elements;
        if (obj != null) {
            return C1630W.m4529a(immutableList, obj, comparator(), z ? SortedLists$KeyPresentBehavior.FIRST_PRESENT : SortedLists$KeyPresentBehavior.FIRST_AFTER, SortedLists$KeyAbsentBehavior.NEXT_HIGHER);
        }
        throw new NullPointerException();
    }

    public C1692rb descendingIterator() {
        return this.elements.reverse().iterator();
    }

    public C1692rb iterator() {
        return this.elements.iterator();
    }
}
