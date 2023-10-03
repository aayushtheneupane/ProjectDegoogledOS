package p003j$.util;

import java.util.Comparator;
import p003j$.util.function.Consumer;
import p003j$.util.function.DoubleConsumer;
import p003j$.util.function.IntConsumer;
import p003j$.util.function.LongConsumer;

/* renamed from: j$.util.Spliterator */
public interface Spliterator {

    /* renamed from: j$.util.Spliterator$OfDouble */
    public interface OfDouble extends Spliterator {
        void forEachRemaining(DoubleConsumer doubleConsumer);

        boolean tryAdvance(DoubleConsumer doubleConsumer);
    }

    /* renamed from: j$.util.Spliterator$OfInt */
    public interface OfInt extends Spliterator {
        void forEachRemaining(IntConsumer intConsumer);

        boolean tryAdvance(IntConsumer intConsumer);
    }

    /* renamed from: j$.util.Spliterator$OfLong */
    public interface OfLong extends Spliterator {
        void forEachRemaining(LongConsumer longConsumer);

        boolean tryAdvance(LongConsumer longConsumer);
    }

    int characteristics();

    long estimateSize();

    void forEachRemaining(Consumer consumer);

    Comparator getComparator();

    long getExactSizeIfKnown();

    boolean tryAdvance(Consumer consumer);

    Spliterator trySplit();
}
