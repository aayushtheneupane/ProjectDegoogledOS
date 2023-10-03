package com.google.common.collect;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import java.util.Set;

class Sets$FilteredSet<E> extends Collections2.FilteredCollection<E> implements Set<E> {
    Sets$FilteredSet(Set<E> set, Predicate<? super E> predicate) {
        super(set, predicate);
    }

    public boolean equals(Object obj) {
        return Collections2.equalsImpl(this, obj);
    }

    public int hashCode() {
        return Collections2.hashCodeImpl(this);
    }
}
