package com.google.common.collect;

final class Iterators$ArrayItr<T> extends AbstractIndexedListIterator<T> {
    static final UnmodifiableListIterator<Object> EMPTY = new Iterators$ArrayItr(new Object[0], 0, 0, 0);
    private final T[] array;
    private final int offset;

    Iterators$ArrayItr(T[] tArr, int i, int i2, int i3) {
        super(i2, i3);
        this.array = tArr;
        this.offset = i;
    }

    /* access modifiers changed from: protected */
    public T get(int i) {
        return this.array[this.offset + i];
    }
}
