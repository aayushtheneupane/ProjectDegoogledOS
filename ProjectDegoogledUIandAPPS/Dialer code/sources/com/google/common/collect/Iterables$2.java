package com.google.common.collect;

import com.google.common.base.MoreObjects;
import java.util.Iterator;
import java.util.List;

final class Iterables$2 extends FluentIterable<List<T>> {
    final /* synthetic */ Iterable val$iterable;
    final /* synthetic */ int val$size;

    Iterables$2(Iterable iterable, int i) {
        this.val$iterable = iterable;
        this.val$size = i;
    }

    public Iterator<List<T>> iterator() {
        Iterator it = this.val$iterable.iterator();
        int i = this.val$size;
        if (it != null) {
            MoreObjects.checkArgument(i > 0);
            return new Iterators$4(it, i, false);
        }
        throw new NullPointerException();
    }
}
