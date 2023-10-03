package com.google.common.collect;

class DescendingImmutableSortedSet extends ImmutableSortedSet {
    private final ImmutableSortedSet forward;

    DescendingImmutableSortedSet(ImmutableSortedSet immutableSortedSet) {
        super(C1644bb.m4562b(immutableSortedSet.comparator()).reverse());
        this.forward = immutableSortedSet;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Sl */
    public ImmutableSortedSet mo8624Sl() {
        throw new AssertionError("should never be called");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public ImmutableSortedSet mo8625a(Object obj, boolean z) {
        return this.forward.tailSet(obj, z).descendingSet();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public ImmutableSortedSet mo8627b(Object obj, boolean z) {
        return this.forward.headSet(obj, z).descendingSet();
    }

    public Object ceiling(Object obj) {
        return this.forward.floor(obj);
    }

    public Object floor(Object obj) {
        return this.forward.ceiling(obj);
    }

    public Object higher(Object obj) {
        return this.forward.lower(obj);
    }

    /* access modifiers changed from: package-private */
    public int indexOf(Object obj) {
        int indexOf = this.forward.indexOf(obj);
        if (indexOf == -1) {
            return indexOf;
        }
        return (size() - 1) - indexOf;
    }

    public Object lower(Object obj) {
        return this.forward.higher(obj);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8636pl() {
        return this.forward.mo8636pl();
    }

    public int size() {
        return this.forward.size();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public ImmutableSortedSet mo8626a(Object obj, boolean z, Object obj2, boolean z2) {
        return this.forward.subSet(obj2, z2, obj, z).descendingSet();
    }

    public C1692rb descendingIterator() {
        return this.forward.iterator();
    }

    public ImmutableSortedSet descendingSet() {
        return this.forward;
    }

    public C1692rb iterator() {
        return this.forward.descendingIterator();
    }
}
