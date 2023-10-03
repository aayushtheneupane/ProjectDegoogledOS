package p003j$.util.stream;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import p003j$.util.function.BiConsumer;
import p003j$.util.function.BinaryOperator;
import p003j$.util.function.Function;
import p003j$.util.function.Supplier;
import p003j$.util.stream.Collector;

/* renamed from: j$.util.stream.Collectors */
public final class Collectors {
    static final Set CH_ID = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));

    static final /* synthetic */ Object lambda$castingIdentity$1$Collectors(Object obj) {
        return obj;
    }

    static {
        Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.CONCURRENT, Collector.Characteristics.UNORDERED, Collector.Characteristics.IDENTITY_FINISH));
        Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.CONCURRENT, Collector.Characteristics.UNORDERED));
        Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.UNORDERED, Collector.Characteristics.IDENTITY_FINISH));
        Collections.emptySet();
    }

    /* access modifiers changed from: private */
    public static Function castingIdentity() {
        return Collectors$$Lambda$1.$instance;
    }

    /* renamed from: j$.util.stream.Collectors$CollectorImpl */
    class CollectorImpl implements Collector {
        private final BiConsumer accumulator;
        private final Set characteristics;
        private final BinaryOperator combiner;
        private final Function finisher;
        private final Supplier supplier;

        CollectorImpl(Supplier supplier2, BiConsumer biConsumer, BinaryOperator binaryOperator, Function function, Set set) {
            this.supplier = supplier2;
            this.accumulator = biConsumer;
            this.combiner = binaryOperator;
            this.finisher = function;
            this.characteristics = set;
        }

        CollectorImpl(Supplier supplier2, BiConsumer biConsumer, BinaryOperator binaryOperator, Set set) {
            this(supplier2, biConsumer, binaryOperator, Collectors.castingIdentity(), set);
        }

        public BiConsumer accumulator() {
            return this.accumulator;
        }

        public Supplier supplier() {
            return this.supplier;
        }

        public BinaryOperator combiner() {
            return this.combiner;
        }

        public Function finisher() {
            return this.finisher;
        }

        public Set characteristics() {
            return this.characteristics;
        }
    }

    public static Collector toList() {
        return new CollectorImpl(Collectors$$Lambda$4.$instance, Collectors$$Lambda$5.$instance, Collectors$$Lambda$6.$instance, CH_ID);
    }
}
