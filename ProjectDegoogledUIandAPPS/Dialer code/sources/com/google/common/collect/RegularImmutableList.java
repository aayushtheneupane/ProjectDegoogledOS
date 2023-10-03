package com.google.common.collect;

import java.util.Spliterator;
import java.util.Spliterators;

class RegularImmutableList<E> extends ImmutableList<E> {
    static final ImmutableList<Object> EMPTY = new RegularImmutableList(new Object[0]);
    private final transient Object[] array;

    RegularImmutableList(Object[] objArr) {
        this.array = objArr;
    }

    /* access modifiers changed from: package-private */
    public int copyIntoArray(Object[] objArr, int i) {
        Object[] objArr2 = this.array;
        System.arraycopy(objArr2, 0, objArr, i, objArr2.length);
        return i + this.array.length;
    }

    public E get(int i) {
        return this.array[i];
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }

    public int size() {
        return this.array.length;
    }

    public Spliterator<E> spliterator() {
        return Spliterators.spliterator(this.array, 1296);
    }

    public UnmodifiableListIterator<E> listIterator(int i) {
        Object[] objArr = this.array;
        return Collections2.forArray(objArr, 0, objArr.length, i);
    }
}
