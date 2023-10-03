package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.List;

final class SingletonImmutableList<E> extends ImmutableList<E> {
    final transient E element;

    public boolean isEmpty() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }

    public ImmutableList<E> reverse() {
        return this;
    }

    public int size() {
        return 1;
    }

    SingletonImmutableList(E e) {
        Preconditions.checkNotNull(e);
        this.element = e;
    }

    public E get(int i) {
        Preconditions.checkElementIndex(i, 1);
        return this.element;
    }

    public int indexOf(Object obj) {
        return this.element.equals(obj) ? 0 : -1;
    }

    public UnmodifiableIterator<E> iterator() {
        return Iterators.singletonIterator(this.element);
    }

    public int lastIndexOf(Object obj) {
        return indexOf(obj);
    }

    public ImmutableList<E> subList(int i, int i2) {
        Preconditions.checkPositionIndexes(i, i2, 1);
        return i == i2 ? ImmutableList.m19of() : this;
    }

    public boolean contains(Object obj) {
        return this.element.equals(obj);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list = (List) obj;
        if (list.size() != 1 || !this.element.equals(list.get(0))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.element.hashCode() + 31;
    }

    public String toString() {
        String obj = this.element.toString();
        StringBuilder sb = new StringBuilder(obj.length() + 2);
        sb.append('[');
        sb.append(obj);
        sb.append(']');
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public int copyIntoArray(Object[] objArr, int i) {
        objArr[i] = this.element;
        return i + 1;
    }
}
