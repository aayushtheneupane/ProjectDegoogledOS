package com.google.common.collect;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;

class CollectSpliterators$1WithCharacteristics implements Spliterator<T> {
    private final Spliterator<T> delegate;
    final /* synthetic */ Comparator val$comparator;
    final /* synthetic */ int val$extraCharacteristics;

    CollectSpliterators$1WithCharacteristics(Spliterator spliterator, int i, Comparator comparator) {
        this.val$extraCharacteristics = i;
        this.val$comparator = comparator;
        this.delegate = spliterator;
    }

    public int characteristics() {
        return this.val$extraCharacteristics | this.delegate.characteristics();
    }

    public long estimateSize() {
        return this.delegate.estimateSize();
    }

    public void forEachRemaining(Consumer<? super T> consumer) {
        this.delegate.forEachRemaining(consumer);
    }

    public Comparator<? super T> getComparator() {
        if (hasCharacteristics(4)) {
            return this.val$comparator;
        }
        throw new IllegalStateException();
    }

    public boolean tryAdvance(Consumer<? super T> consumer) {
        return this.delegate.tryAdvance(consumer);
    }

    public Spliterator<T> trySplit() {
        Spliterator<T> trySplit = this.delegate.trySplit();
        if (trySplit == null) {
            return null;
        }
        return new CollectSpliterators$1WithCharacteristics(trySplit, this.val$extraCharacteristics, this.val$comparator);
    }
}
