package com.google.common.collect;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Set;

class EmptyImmutableSortedSet extends ImmutableSortedSet {
    EmptyImmutableSortedSet(Comparator comparator) {
        super(comparator);
    }

    /* renamed from: Ol */
    public ImmutableList mo8648Ol() {
        return ImmutableList.m4204ql();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Sl */
    public ImmutableSortedSet mo8624Sl() {
        return new EmptyImmutableSortedSet(C1644bb.m4562b(this.comparator).reverse());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public ImmutableSortedSet mo8625a(Object obj, boolean z) {
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public ImmutableSortedSet mo8626a(Object obj, boolean z, Object obj2, boolean z2) {
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public ImmutableSortedSet mo8627b(Object obj, boolean z) {
        return this;
    }

    public boolean contains(Object obj) {
        return false;
    }

    public boolean containsAll(Collection collection) {
        return collection.isEmpty();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public int mo8652d(Object[] objArr, int i) {
        return i;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Set) {
            return ((Set) obj).isEmpty();
        }
        return false;
    }

    public Object first() {
        throw new NoSuchElementException();
    }

    public int hashCode() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int indexOf(Object obj) {
        return -1;
    }

    public boolean isEmpty() {
        return true;
    }

    public Object last() {
        throw new NoSuchElementException();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8636pl() {
        return false;
    }

    public int size() {
        return 0;
    }

    public String toString() {
        return "[]";
    }

    public C1692rb descendingIterator() {
        return C1652ea.emptyListIterator();
    }

    public C1692rb iterator() {
        return C1652ea.emptyListIterator();
    }
}
