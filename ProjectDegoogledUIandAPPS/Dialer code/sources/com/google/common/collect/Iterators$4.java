package com.google.common.collect;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

final class Iterators$4 extends UnmodifiableIterator<List<T>> {
    final /* synthetic */ Iterator val$iterator;
    final /* synthetic */ boolean val$pad;
    final /* synthetic */ int val$size;

    Iterators$4(Iterator it, int i, boolean z) {
        this.val$iterator = it;
        this.val$size = i;
        this.val$pad = z;
    }

    public boolean hasNext() {
        return this.val$iterator.hasNext();
    }

    public Object next() {
        if (this.val$iterator.hasNext()) {
            Object[] objArr = new Object[this.val$size];
            int i = 0;
            while (i < this.val$size && this.val$iterator.hasNext()) {
                objArr[i] = this.val$iterator.next();
                i++;
            }
            for (int i2 = i; i2 < this.val$size; i2++) {
                objArr[i2] = null;
            }
            List unmodifiableList = Collections.unmodifiableList(Arrays.asList(objArr));
            return (this.val$pad || i == this.val$size) ? unmodifiableList : unmodifiableList.subList(0, i);
        }
        throw new NoSuchElementException();
    }
}
