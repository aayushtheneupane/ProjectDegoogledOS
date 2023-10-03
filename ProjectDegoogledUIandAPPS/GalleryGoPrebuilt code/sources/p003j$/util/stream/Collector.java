package p003j$.util.stream;

import java.util.Set;
import p003j$.util.function.BiConsumer;
import p003j$.util.function.BinaryOperator;
import p003j$.util.function.Function;
import p003j$.util.function.Supplier;

/* renamed from: j$.util.stream.Collector */
public interface Collector {

    /* renamed from: j$.util.stream.Collector$Characteristics */
    public enum Characteristics {
        CONCURRENT,
        UNORDERED,
        IDENTITY_FINISH
    }

    BiConsumer accumulator();

    Set characteristics();

    BinaryOperator combiner();

    Function finisher();

    Supplier supplier();
}
