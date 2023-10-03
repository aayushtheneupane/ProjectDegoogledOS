package p003j$.util;

import p003j$.util.Spliterator;
import p003j$.util.function.Consumer;
import p003j$.util.function.DoubleConsumer;

/* renamed from: j$.util.Spliterator$OfDouble$$CC */
public abstract /* synthetic */ class Spliterator$OfDouble$$CC {
    public static boolean tryAdvance$$dflt$$(Spliterator.OfDouble ofDouble, Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            return ofDouble.tryAdvance((DoubleConsumer) consumer);
        }
        if (!Tripwire.ENABLED) {
            consumer.getClass();
            return ofDouble.tryAdvance(Spliterator$OfDouble$$Lambda$0.get$Lambda(consumer));
        }
        Tripwire.trip(ofDouble.getClass(), "{0} calling Spliterator.OfDouble.tryAdvance((DoubleConsumer) action::accept)");
        throw null;
    }

    public static void forEachRemaining$$dflt$$(Spliterator.OfDouble ofDouble, Consumer consumer) {
        if (consumer instanceof DoubleConsumer) {
            ofDouble.forEachRemaining((DoubleConsumer) consumer);
        } else if (!Tripwire.ENABLED) {
            consumer.getClass();
            ofDouble.forEachRemaining(Spliterator$OfDouble$$Lambda$1.get$Lambda(consumer));
        } else {
            Tripwire.trip(ofDouble.getClass(), "{0} calling Spliterator.OfDouble.forEachRemaining((DoubleConsumer) action::accept)");
            throw null;
        }
    }
}
