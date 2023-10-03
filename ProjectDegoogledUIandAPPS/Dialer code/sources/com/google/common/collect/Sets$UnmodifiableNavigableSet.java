package com.google.common.collect;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

final class Sets$UnmodifiableNavigableSet<E> extends ForwardingSortedSet<E> implements NavigableSet<E>, Serializable {
    private static final long serialVersionUID = 0;
    private final NavigableSet<E> delegate;
    private transient Sets$UnmodifiableNavigableSet<E> descendingSet;
    private final SortedSet<E> unmodifiableDelegate;

    Sets$UnmodifiableNavigableSet(NavigableSet<E> navigableSet) {
        if (navigableSet != null) {
            this.delegate = navigableSet;
            this.unmodifiableDelegate = Collections.unmodifiableSortedSet(navigableSet);
            return;
        }
        throw new NullPointerException();
    }

    public E ceiling(E e) {
        return this.delegate.ceiling(e);
    }

    public Iterator<E> descendingIterator() {
        return Collections2.unmodifiableIterator(this.delegate.descendingIterator());
    }

    public NavigableSet<E> descendingSet() {
        Sets$UnmodifiableNavigableSet<E> sets$UnmodifiableNavigableSet = this.descendingSet;
        if (sets$UnmodifiableNavigableSet != null) {
            return sets$UnmodifiableNavigableSet;
        }
        Sets$UnmodifiableNavigableSet<E> sets$UnmodifiableNavigableSet2 = new Sets$UnmodifiableNavigableSet<>(this.delegate.descendingSet());
        this.descendingSet = sets$UnmodifiableNavigableSet2;
        sets$UnmodifiableNavigableSet2.descendingSet = this;
        return sets$UnmodifiableNavigableSet2;
    }

    public E floor(E e) {
        return this.delegate.floor(e);
    }

    public void forEach(Consumer<? super E> consumer) {
        this.delegate.forEach(consumer);
    }

    public NavigableSet<E> headSet(E e, boolean z) {
        return Collections2.unmodifiableNavigableSet(this.delegate.headSet(e, z));
    }

    public E higher(E e) {
        return this.delegate.higher(e);
    }

    public E lower(E e) {
        return this.delegate.lower(e);
    }

    public Stream<E> parallelStream() {
        return this.delegate.parallelStream();
    }

    public E pollFirst() {
        throw new UnsupportedOperationException();
    }

    public E pollLast() {
        throw new UnsupportedOperationException();
    }

    public boolean removeIf(Predicate<? super E> predicate) {
        throw new UnsupportedOperationException();
    }

    public Stream<E> stream() {
        return this.delegate.stream();
    }

    public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        return Collections2.unmodifiableNavigableSet(this.delegate.subSet(e, z, e2, z2));
    }

    public NavigableSet<E> tailSet(E e, boolean z) {
        return Collections2.unmodifiableNavigableSet(this.delegate.tailSet(e, z));
    }

    /* access modifiers changed from: protected */
    public SortedSet<E> delegate() {
        return this.unmodifiableDelegate;
    }
}
