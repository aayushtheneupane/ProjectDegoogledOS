package com.google.common.collect;

import com.google.common.base.Predicate;
import java.util.Iterator;

final class Iterators$5 extends AbstractIterator<T> {
    final /* synthetic */ Predicate val$retainIfTrue;
    final /* synthetic */ Iterator val$unfiltered;

    Iterators$5(Iterator it, Predicate predicate) {
        this.val$unfiltered = it;
        this.val$retainIfTrue = predicate;
    }

    /* access modifiers changed from: protected */
    public T computeNext() {
        while (this.val$unfiltered.hasNext()) {
            T next = this.val$unfiltered.next();
            if (this.val$retainIfTrue.apply(next)) {
                return next;
            }
        }
        endOfData();
        return null;
    }
}
