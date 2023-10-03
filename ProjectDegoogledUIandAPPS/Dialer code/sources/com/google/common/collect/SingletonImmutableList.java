package com.google.common.collect;

import com.google.common.base.MoreObjects;
import java.util.Collections;
import java.util.Spliterator;

final class SingletonImmutableList<E> extends ImmutableList<E> {
    final transient E element;

    SingletonImmutableList(E e) {
        if (e != null) {
            this.element = e;
            return;
        }
        throw new NullPointerException();
    }

    public E get(int i) {
        MoreObjects.checkElementIndex(i, 1);
        return this.element;
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }

    public int size() {
        return 1;
    }

    public Spliterator<E> spliterator() {
        return Collections.singleton(this.element).spliterator();
    }

    public String toString() {
        return '[' + this.element.toString() + ']';
    }

    public UnmodifiableIterator<E> iterator() {
        return new Iterators$9(this.element);
    }

    public ImmutableList<E> subList(int i, int i2) {
        MoreObjects.checkPositionIndexes(i, i2, 1);
        return i == i2 ? RegularImmutableList.EMPTY : this;
    }
}
