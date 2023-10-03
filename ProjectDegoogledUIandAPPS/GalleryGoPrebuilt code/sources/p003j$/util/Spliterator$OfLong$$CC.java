package p003j$.util;

import p003j$.util.Spliterator;
import p003j$.util.function.Consumer;
import p003j$.util.function.LongConsumer;

/* renamed from: j$.util.Spliterator$OfLong$$CC */
public abstract /* synthetic */ class Spliterator$OfLong$$CC {
    public static boolean tryAdvance$$dflt$$(Spliterator.OfLong ofLong, Consumer consumer) {
        if (consumer instanceof LongConsumer) {
            return ofLong.tryAdvance((LongConsumer) consumer);
        }
        if (!Tripwire.ENABLED) {
            consumer.getClass();
            return ofLong.tryAdvance(Spliterator$OfLong$$Lambda$0.get$Lambda(consumer));
        }
        Tripwire.trip(ofLong.getClass(), "{0} calling Spliterator.OfLong.tryAdvance((LongConsumer) action::accept)");
        throw null;
    }

    public static void forEachRemaining$$dflt$$(Spliterator.OfLong ofLong, Consumer consumer) {
        if (consumer instanceof LongConsumer) {
            ofLong.forEachRemaining((LongConsumer) consumer);
        } else if (!Tripwire.ENABLED) {
            consumer.getClass();
            ofLong.forEachRemaining(Spliterator$OfLong$$Lambda$1.get$Lambda(consumer));
        } else {
            Tripwire.trip(ofLong.getClass(), "{0} calling Spliterator.OfLong.forEachRemaining((LongConsumer) action::accept)");
            throw null;
        }
    }
}
