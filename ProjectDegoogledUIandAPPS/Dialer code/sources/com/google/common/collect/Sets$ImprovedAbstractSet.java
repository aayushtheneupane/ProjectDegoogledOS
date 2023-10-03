package com.google.common.collect;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Set;

abstract class Sets$ImprovedAbstractSet<E> extends AbstractSet<E> {
    Sets$ImprovedAbstractSet() {
    }

    public boolean removeAll(Collection<?> collection) {
        return Collections2.removeAllImpl((Set<?>) this, collection);
    }

    public boolean retainAll(Collection<?> collection) {
        if (collection != null) {
            return super.retainAll(collection);
        }
        throw new NullPointerException();
    }
}
