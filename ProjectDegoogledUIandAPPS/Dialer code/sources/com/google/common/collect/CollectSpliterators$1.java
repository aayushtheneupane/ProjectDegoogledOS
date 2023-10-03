package com.google.common.collect;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;

final class CollectSpliterators$1 implements Spliterator<T> {
    final /* synthetic */ Spliterator val$fromSpliterator;
    final /* synthetic */ Function val$function;

    CollectSpliterators$1(Spliterator spliterator, Function function) {
        this.val$fromSpliterator = spliterator;
        this.val$function = function;
    }

    public int characteristics() {
        return this.val$fromSpliterator.characteristics() & -262;
    }

    public long estimateSize() {
        return this.val$fromSpliterator.estimateSize();
    }

    public void forEachRemaining(Consumer<? super T> consumer) {
        this.val$fromSpliterator.forEachRemaining(new Consumer(consumer, this.val$function) {
            private final /* synthetic */ Consumer f$0;
            private final /* synthetic */ Function f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                this.f$0.accept(this.f$1.apply(obj));
            }
        });
    }

    public boolean tryAdvance(Consumer<? super T> consumer) {
        return this.val$fromSpliterator.tryAdvance(new Consumer(consumer, this.val$function) {
            private final /* synthetic */ Consumer f$0;
            private final /* synthetic */ Function f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                this.f$0.accept(this.f$1.apply(obj));
            }
        });
    }

    public Spliterator<T> trySplit() {
        Spliterator trySplit = this.val$fromSpliterator.trySplit();
        if (trySplit != null) {
            return Collections2.map(trySplit, this.val$function);
        }
        return null;
    }
}
