package p003j$.util.stream;

import p003j$.util.Spliterator;
import p003j$.util.function.Consumer;
import p003j$.util.function.IntFunction;
import p003j$.util.function.Predicate;
import p003j$.util.stream.Collector;
import p003j$.util.stream.Node;
import p003j$.util.stream.Sink;

/* renamed from: j$.util.stream.ReferencePipeline */
abstract class ReferencePipeline extends AbstractPipeline implements Stream {
    ReferencePipeline(Spliterator spliterator, int i, boolean z) {
        super(spliterator, i, z);
    }

    ReferencePipeline(AbstractPipeline abstractPipeline, int i) {
        super(abstractPipeline, i);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:3:0x000a, LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void forEachWithCancel(p003j$.util.Spliterator r2, p003j$.util.stream.Sink r3) {
        /*
            r1 = this;
        L_0x0000:
            boolean r0 = r3.cancellationRequested()
            if (r0 != 0) goto L_0x000c
            boolean r0 = r2.tryAdvance(r3)
            if (r0 != 0) goto L_0x0000
        L_0x000c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p003j$.util.stream.ReferencePipeline.forEachWithCancel(j$.util.Spliterator, j$.util.stream.Sink):void");
    }

    /* access modifiers changed from: package-private */
    public final Node.Builder makeNodeBuilder(long j, IntFunction intFunction) {
        return Nodes.builder(j, intFunction);
    }

    public final Stream filter(Predicate predicate) {
        predicate.getClass();
        final Predicate predicate2 = predicate;
        return new StatelessOp(this, this, StreamShape.REFERENCE, StreamOpFlag.NOT_SIZED) {
            /* access modifiers changed from: package-private */
            public Sink opWrapSink(int i, Sink sink) {
                return new Sink.ChainedReference(sink) {
                    public void begin(long j) {
                        this.downstream.begin(-1);
                    }

                    public void accept(Object obj) {
                        if (predicate2.test(obj)) {
                            this.downstream.accept(obj);
                        }
                    }
                };
            }
        };
    }

    public void forEach(Consumer consumer) {
        evaluate(ForEachOps.makeRef(consumer, false));
    }

    public final Object collect(Collector collector) {
        Object obj;
        if (!isParallel() || !collector.characteristics().contains(Collector.Characteristics.CONCURRENT) || (isOrdered() && !collector.characteristics().contains(Collector.Characteristics.UNORDERED))) {
            obj = evaluate(ReduceOps.makeRef(collector));
        } else {
            obj = collector.supplier().get();
            forEach(new ReferencePipeline$$Lambda$1(collector.accumulator(), obj));
        }
        return collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH) ? obj : collector.finisher().apply(obj);
    }

    /* renamed from: j$.util.stream.ReferencePipeline$Head */
    class Head extends ReferencePipeline {
        Head(Spliterator spliterator, int i, boolean z) {
            super(spliterator, i, z);
        }

        /* access modifiers changed from: package-private */
        public final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        /* access modifiers changed from: package-private */
        public final Sink opWrapSink(int i, Sink sink) {
            throw new UnsupportedOperationException();
        }

        public void forEach(Consumer consumer) {
            if (!isParallel()) {
                sourceStageSpliterator().forEachRemaining(consumer);
            } else {
                ReferencePipeline.super.forEach(consumer);
            }
        }
    }

    /* renamed from: j$.util.stream.ReferencePipeline$StatelessOp */
    abstract class StatelessOp extends ReferencePipeline {
        /* access modifiers changed from: package-private */
        public final boolean opIsStateful() {
            return false;
        }

        static {
            Class<ReferencePipeline> cls = ReferencePipeline.class;
        }

        StatelessOp(AbstractPipeline abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }
    }
}
