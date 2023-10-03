package p003j$.util.stream;

import java.util.concurrent.CountedCompleter;
import p003j$.util.Spliterator;
import p003j$.util.concurrent.ConcurrentHashMap;
import p003j$.util.function.Consumer;
import p003j$.util.function.IntFunction;
import p003j$.util.function.Supplier;
import p003j$.util.stream.Node;

/* renamed from: j$.util.stream.ForEachOps */
abstract class ForEachOps {
    public static TerminalOp makeRef(Consumer consumer, boolean z) {
        consumer.getClass();
        return new ForEachOp.OfRef(consumer, z);
    }

    /* renamed from: j$.util.stream.ForEachOps$ForEachOp */
    abstract class ForEachOp implements TerminalOp, Sink, Supplier {
        private final boolean ordered;

        public void begin(long j) {
            Sink$$CC.begin$$dflt$$(this, j);
        }

        public boolean cancellationRequested() {
            return Sink$$CC.cancellationRequested$$dflt$$(this);
        }

        public void end() {
            Sink$$CC.end$$dflt$$(this);
        }

        public Void get() {
            return null;
        }

        protected ForEachOp(boolean z) {
            this.ordered = z;
        }

        public int getOpFlags() {
            if (this.ordered) {
                return 0;
            }
            return StreamOpFlag.NOT_ORDERED;
        }

        public Void evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
            pipelineHelper.wrapAndCopyInto(this, spliterator);
            return get();
        }

        public Void evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
            if (this.ordered) {
                new ForEachOrderedTask(pipelineHelper, spliterator, (Sink) this).invoke();
                return null;
            }
            new ForEachTask(pipelineHelper, spliterator, pipelineHelper.wrapSink(this)).invoke();
            return null;
        }

        /* renamed from: j$.util.stream.ForEachOps$ForEachOp$OfRef */
        final class OfRef extends ForEachOp {
            final Consumer consumer;

            OfRef(Consumer consumer2, boolean z) {
                super(z);
                this.consumer = consumer2;
            }

            public void accept(Object obj) {
                this.consumer.accept(obj);
            }
        }
    }

    /* renamed from: j$.util.stream.ForEachOps$ForEachTask */
    final class ForEachTask extends CountedCompleter {
        private final PipelineHelper helper;
        private final Sink sink;
        private Spliterator spliterator;
        private long targetSize;

        ForEachTask(PipelineHelper pipelineHelper, Spliterator spliterator2, Sink sink2) {
            super((CountedCompleter) null);
            this.sink = sink2;
            this.helper = pipelineHelper;
            this.spliterator = spliterator2;
            this.targetSize = 0;
        }

        ForEachTask(ForEachTask forEachTask, Spliterator spliterator2) {
            super(forEachTask);
            this.spliterator = spliterator2;
            this.sink = forEachTask.sink;
            this.targetSize = forEachTask.targetSize;
            this.helper = forEachTask.helper;
        }

        public void compute() {
            Spliterator trySplit;
            Spliterator spliterator2 = this.spliterator;
            long estimateSize = spliterator2.estimateSize();
            long j = this.targetSize;
            if (j == 0) {
                j = AbstractTask.suggestTargetSize(estimateSize);
                this.targetSize = j;
            }
            boolean isKnown = StreamOpFlag.SHORT_CIRCUIT.isKnown(this.helper.getStreamAndOpFlags());
            boolean z = false;
            Sink sink2 = this.sink;
            ForEachTask forEachTask = this;
            while (true) {
                if (isKnown && sink2.cancellationRequested()) {
                    break;
                } else if (estimateSize <= j || (trySplit = spliterator2.trySplit()) == null) {
                    forEachTask.helper.copyInto(sink2, spliterator2);
                } else {
                    ForEachTask forEachTask2 = new ForEachTask(forEachTask, trySplit);
                    forEachTask.addToPendingCount(1);
                    if (z) {
                        spliterator2 = trySplit;
                    } else {
                        ForEachTask forEachTask3 = forEachTask;
                        forEachTask = forEachTask2;
                        forEachTask2 = forEachTask3;
                    }
                    z = !z;
                    forEachTask.fork();
                    forEachTask = forEachTask2;
                    estimateSize = spliterator2.estimateSize();
                }
            }
            forEachTask.helper.copyInto(sink2, spliterator2);
            forEachTask.spliterator = null;
            forEachTask.propagateCompletion();
        }
    }

    /* renamed from: j$.util.stream.ForEachOps$ForEachOrderedTask */
    final class ForEachOrderedTask extends CountedCompleter {
        private final Sink action;
        private final ConcurrentHashMap completionMap;
        private final PipelineHelper helper;
        private final ForEachOrderedTask leftPredecessor;
        private Node node;
        private Spliterator spliterator;
        private final long targetSize;

        protected ForEachOrderedTask(PipelineHelper pipelineHelper, Spliterator spliterator2, Sink sink) {
            super((CountedCompleter) null);
            this.helper = pipelineHelper;
            this.spliterator = spliterator2;
            this.targetSize = AbstractTask.suggestTargetSize(spliterator2.estimateSize());
            this.completionMap = new ConcurrentHashMap(Math.max(16, AbstractTask.LEAF_TARGET << 1));
            this.action = sink;
            this.leftPredecessor = null;
        }

        ForEachOrderedTask(ForEachOrderedTask forEachOrderedTask, Spliterator spliterator2, ForEachOrderedTask forEachOrderedTask2) {
            super(forEachOrderedTask);
            this.helper = forEachOrderedTask.helper;
            this.spliterator = spliterator2;
            this.targetSize = forEachOrderedTask.targetSize;
            this.completionMap = forEachOrderedTask.completionMap;
            this.action = forEachOrderedTask.action;
            this.leftPredecessor = forEachOrderedTask2;
        }

        public final void compute() {
            doCompute(this);
        }

        private static void doCompute(ForEachOrderedTask forEachOrderedTask) {
            Spliterator trySplit;
            Spliterator spliterator2 = forEachOrderedTask.spliterator;
            long j = forEachOrderedTask.targetSize;
            boolean z = false;
            while (spliterator2.estimateSize() > j && (trySplit = spliterator2.trySplit()) != null) {
                ForEachOrderedTask forEachOrderedTask2 = new ForEachOrderedTask(forEachOrderedTask, trySplit, forEachOrderedTask.leftPredecessor);
                ForEachOrderedTask forEachOrderedTask3 = new ForEachOrderedTask(forEachOrderedTask, spliterator2, forEachOrderedTask2);
                forEachOrderedTask.addToPendingCount(1);
                forEachOrderedTask3.addToPendingCount(1);
                forEachOrderedTask.completionMap.put(forEachOrderedTask2, forEachOrderedTask3);
                if (forEachOrderedTask.leftPredecessor != null) {
                    forEachOrderedTask2.addToPendingCount(1);
                    if (forEachOrderedTask.completionMap.replace(forEachOrderedTask.leftPredecessor, forEachOrderedTask, forEachOrderedTask2)) {
                        forEachOrderedTask.addToPendingCount(-1);
                    } else {
                        forEachOrderedTask2.addToPendingCount(-1);
                    }
                }
                if (z) {
                    spliterator2 = trySplit;
                    forEachOrderedTask = forEachOrderedTask2;
                    forEachOrderedTask2 = forEachOrderedTask3;
                } else {
                    forEachOrderedTask = forEachOrderedTask3;
                }
                z = !z;
                forEachOrderedTask2.fork();
            }
            if (forEachOrderedTask.getPendingCount() > 0) {
                IntFunction intFunction = ForEachOps$ForEachOrderedTask$$Lambda$0.$instance;
                PipelineHelper pipelineHelper = forEachOrderedTask.helper;
                Node.Builder makeNodeBuilder = pipelineHelper.makeNodeBuilder(pipelineHelper.exactOutputSizeIfKnown(spliterator2), intFunction);
                forEachOrderedTask.helper.wrapAndCopyInto(makeNodeBuilder, spliterator2);
                forEachOrderedTask.node = makeNodeBuilder.build();
                forEachOrderedTask.spliterator = null;
            }
            forEachOrderedTask.tryComplete();
        }

        static final /* synthetic */ Object[] lambda$doCompute$0$ForEachOps$ForEachOrderedTask(int i) {
            return new Object[i];
        }

        public void onCompletion(CountedCompleter countedCompleter) {
            Node node2 = this.node;
            if (node2 != null) {
                node2.forEach(this.action);
                this.node = null;
            } else {
                Spliterator spliterator2 = this.spliterator;
                if (spliterator2 != null) {
                    this.helper.wrapAndCopyInto(this.action, spliterator2);
                    this.spliterator = null;
                }
            }
            ForEachOrderedTask forEachOrderedTask = (ForEachOrderedTask) this.completionMap.remove(this);
            if (forEachOrderedTask != null) {
                forEachOrderedTask.tryComplete();
            }
        }
    }
}
