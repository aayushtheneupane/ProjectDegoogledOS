package com.google.common.collect;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

class CollectSpliterators$1Splitr implements Spliterator<T>, Consumer<T> {
    T holder = null;
    final /* synthetic */ Spliterator val$fromSpliterator;
    final /* synthetic */ Predicate val$predicate;

    CollectSpliterators$1Splitr(Spliterator spliterator, Predicate predicate) {
        this.val$fromSpliterator = spliterator;
        this.val$predicate = predicate;
    }

    public void accept(T t) {
        this.holder = t;
    }

    public int characteristics() {
        return this.val$fromSpliterator.characteristics() & 277;
    }

    public long estimateSize() {
        return this.val$fromSpliterator.estimateSize() / 2;
    }

    public Comparator<? super T> getComparator() {
        return this.val$fromSpliterator.getComparator();
    }

    public boolean tryAdvance(Consumer<? super T> consumer) {
        while (this.val$fromSpliterator.tryAdvance(this)) {
            try {
                if (this.val$predicate.test(this.holder)) {
                    consumer.accept(this.holder);
                    this.holder = null;
                    return true;
                }
            } finally {
                this.holder = null;
            }
        }
        return false;
    }

    public Spliterator<T> trySplit() {
        Spliterator trySplit = this.val$fromSpliterator.trySplit();
        if (trySplit == null) {
            return null;
        }
        return Collections2.filter(trySplit, this.val$predicate);
    }
}
