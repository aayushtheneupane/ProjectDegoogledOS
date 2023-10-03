package com.google.common.collect;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Set;

class EmptyImmutableSortedSet<E> extends ImmutableSortedSet<E> {
    public boolean contains(Object obj) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public int copyIntoArray(Object[] objArr, int i) {
        return i;
    }

    public int hashCode() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<E> headSetImpl(E e, boolean z) {
        return this;
    }

    /* access modifiers changed from: package-private */
    public int indexOf(Object obj) {
        return -1;
    }

    public boolean isEmpty() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }

    public int size() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<E> subSetImpl(E e, boolean z, E e2, boolean z2) {
        return this;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<E> tailSetImpl(E e, boolean z) {
        return this;
    }

    public String toString() {
        return "[]";
    }

    EmptyImmutableSortedSet(Comparator<? super E> comparator) {
        super(comparator);
    }

    public boolean containsAll(Collection<?> collection) {
        return collection.isEmpty();
    }

    public UnmodifiableIterator<E> iterator() {
        return Iterators.emptyIterator();
    }

    public UnmodifiableIterator<E> descendingIterator() {
        return Iterators.emptyIterator();
    }

    public ImmutableList<E> asList() {
        return ImmutableList.m65of();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Set) {
            return ((Set) obj).isEmpty();
        }
        return false;
    }

    public E first() {
        throw new NoSuchElementException();
    }

    public E last() {
        throw new NoSuchElementException();
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<E> createDescendingSet() {
        return new EmptyImmutableSortedSet(Ordering.from(this.comparator).reverse());
    }
}
