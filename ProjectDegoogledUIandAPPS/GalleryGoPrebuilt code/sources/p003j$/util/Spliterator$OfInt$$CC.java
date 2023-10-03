package p003j$.util;

import p003j$.util.Spliterator;
import p003j$.util.function.Consumer;
import p003j$.util.function.IntConsumer;

/* renamed from: j$.util.Spliterator$OfInt$$CC */
public abstract /* synthetic */ class Spliterator$OfInt$$CC {
    public static boolean tryAdvance$$dflt$$(Spliterator.OfInt ofInt, Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            return ofInt.tryAdvance((IntConsumer) consumer);
        }
        if (!Tripwire.ENABLED) {
            consumer.getClass();
            return ofInt.tryAdvance(Spliterator$OfInt$$Lambda$0.get$Lambda(consumer));
        }
        Tripwire.trip(ofInt.getClass(), "{0} calling Spliterator.OfInt.tryAdvance((IntConsumer) action::accept)");
        throw null;
    }

    public static void forEachRemaining$$dflt$$(Spliterator.OfInt ofInt, Consumer consumer) {
        if (consumer instanceof IntConsumer) {
            ofInt.forEachRemaining((IntConsumer) consumer);
        } else if (!Tripwire.ENABLED) {
            consumer.getClass();
            ofInt.forEachRemaining(Spliterator$OfInt$$Lambda$1.get$Lambda(consumer));
        } else {
            Tripwire.trip(ofInt.getClass(), "{0} calling Spliterator.OfInt.forEachRemaining((IntConsumer) action::accept)");
            throw null;
        }
    }
}
