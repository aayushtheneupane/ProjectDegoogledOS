package p003j$.util.stream;

import java.util.concurrent.CountedCompleter;
import p003j$.util.Spliterator;
import p003j$.util.function.BiConsumer;
import p003j$.util.function.BinaryOperator;
import p003j$.util.function.Supplier;
import p003j$.util.stream.Collector;

/* renamed from: j$.util.stream.ReduceOps */
abstract class ReduceOps {

    /* renamed from: j$.util.stream.ReduceOps$AccumulatingSink */
    interface AccumulatingSink extends Sink, Supplier {
        void combine(AccumulatingSink accumulatingSink);
    }

    public static TerminalOp makeRef(Collector collector) {
        collector.getClass();
        final Supplier supplier = collector.supplier();
        final BiConsumer accumulator = collector.accumulator();
        final BinaryOperator combiner = collector.combiner();
        final Collector collector2 = collector;
        return new ReduceOp(StreamShape.REFERENCE) {
            public AnonymousClass3ReducingSink makeSink() {
                return new AccumulatingSink(accumulator, combiner) {
                    final /* synthetic */ BiConsumer val$accumulator;
                    final /* synthetic */ BinaryOperator val$combiner;

                    public boolean cancellationRequested() {
                        return Sink$$CC.cancellationRequested$$dflt$$(this);
                    }

                    public void end() {
                        Sink$$CC.end$$dflt$$(this);
                    }

                    {
                        this.val$accumulator = r2;
                        this.val$combiner = r3;
                    }

                    public void begin(long j) {
                        this.state = Supplier.this.get();
                    }

                    public void accept(Object obj) {
                        this.val$accumulator.accept(this.state, obj);
                    }

                    public void combine(AnonymousClass3ReducingSink r3) {
                        this.state = this.val$combiner.apply(this.state, r3.state);
                    }
                };
            }

            public int getOpFlags() {
                if (collector2.characteristics().contains(Collector.Characteristics.UNORDERED)) {
                    return StreamOpFlag.NOT_ORDERED;
                }
                return 0;
            }
        };
    }

    /* renamed from: j$.util.stream.ReduceOps$Box */
    abstract class Box {
        Object state;

        Box() {
        }

        public Object get() {
            return this.state;
        }
    }

    /* renamed from: j$.util.stream.ReduceOps$ReduceOp */
    abstract class ReduceOp implements TerminalOp {
        private final StreamShape inputShape;

        public abstract AccumulatingSink makeSink();

        ReduceOp(StreamShape streamShape) {
            this.inputShape = streamShape;
        }

        public Object evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
            AccumulatingSink makeSink = makeSink();
            pipelineHelper.wrapAndCopyInto(makeSink, spliterator);
            return makeSink.get();
        }

        public Object evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
            return ((AccumulatingSink) new ReduceTask(this, pipelineHelper, spliterator).invoke()).get();
        }
    }

    /* renamed from: j$.util.stream.ReduceOps$ReduceTask */
    final class ReduceTask extends AbstractTask {

        /* renamed from: op */
        private final ReduceOp f16486op;

        ReduceTask(ReduceOp reduceOp, PipelineHelper pipelineHelper, Spliterator spliterator) {
            super(pipelineHelper, spliterator);
            this.f16486op = reduceOp;
        }

        ReduceTask(ReduceTask reduceTask, Spliterator spliterator) {
            super((AbstractTask) reduceTask, spliterator);
            this.f16486op = reduceTask.f16486op;
        }

        /* access modifiers changed from: protected */
        public ReduceTask makeChild(Spliterator spliterator) {
            return new ReduceTask(this, spliterator);
        }

        /* access modifiers changed from: protected */
        public AccumulatingSink doLeaf() {
            PipelineHelper pipelineHelper = this.helper;
            AccumulatingSink makeSink = this.f16486op.makeSink();
            pipelineHelper.wrapAndCopyInto(makeSink, this.spliterator);
            return makeSink;
        }

        public void onCompletion(CountedCompleter countedCompleter) {
            if (!isLeaf()) {
                AccumulatingSink accumulatingSink = (AccumulatingSink) ((ReduceTask) this.leftChild).getLocalResult();
                accumulatingSink.combine((AccumulatingSink) ((ReduceTask) this.rightChild).getLocalResult());
                setLocalResult(accumulatingSink);
            }
            super.onCompletion(countedCompleter);
        }
    }
}
