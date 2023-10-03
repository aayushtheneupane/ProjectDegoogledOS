package com.google.common.collect;

import com.google.common.base.Predicate;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

class Sets$FilteredSortedSet<E> extends Sets$FilteredSet<E> implements SortedSet<E> {
    Sets$FilteredSortedSet(SortedSet<E> sortedSet, Predicate<? super E> predicate) {
        super(sortedSet, predicate);
    }

    public Comparator<? super E> comparator() {
        return ((SortedSet) this.unfiltered).comparator();
    }

    public E first() {
        Iterator<E> it = this.unfiltered.iterator();
        Predicate<? super E> predicate = this.predicate;
        if (it == null) {
            throw new NullPointerException();
        } else if (predicate != null) {
            while (it.hasNext()) {
                E next = it.next();
                if (predicate.apply(next)) {
                    return next;
                }
            }
            throw new NoSuchElementException();
        } else {
            throw new NullPointerException();
        }
    }

    public SortedSet<E> headSet(E e) {
        return new Sets$FilteredSortedSet(((SortedSet) this.unfiltered).headSet(e), this.predicate);
    }

    public E last() {
        SortedSet sortedSet = (SortedSet) this.unfiltered;
        while (true) {
            E last = sortedSet.last();
            if (this.predicate.apply(last)) {
                return last;
            }
            sortedSet = sortedSet.headSet(last);
        }
    }

    public SortedSet<E> subSet(E e, E e2) {
        return new Sets$FilteredSortedSet(((SortedSet) this.unfiltered).subSet(e, e2), this.predicate);
    }

    public SortedSet<E> tailSet(E e) {
        return new Sets$FilteredSortedSet(((SortedSet) this.unfiltered).tailSet(e), this.predicate);
    }
}
