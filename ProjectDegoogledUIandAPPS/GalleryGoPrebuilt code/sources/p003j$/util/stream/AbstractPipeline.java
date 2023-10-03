package p003j$.util.stream;

import p003j$.util.Spliterator;
import p003j$.util.function.IntFunction;
import p003j$.util.function.Supplier;

/* renamed from: j$.util.stream.AbstractPipeline */
abstract class AbstractPipeline extends PipelineHelper implements AutoCloseable {
    private int combinedFlags;
    private int depth;
    private boolean linkedOrConsumed;
    private AbstractPipeline nextStage;
    private boolean parallel;
    private final AbstractPipeline previousStage;
    private boolean sourceAnyStateful;
    private Runnable sourceCloseAction;
    protected final int sourceOrOpFlags;
    private Spliterator sourceSpliterator;
    private final AbstractPipeline sourceStage;
    private Supplier sourceSupplier;

    /* access modifiers changed from: package-private */
    public abstract void forEachWithCancel(Spliterator spliterator, Sink sink);

    /* access modifiers changed from: package-private */
    public abstract boolean opIsStateful();

    /* access modifiers changed from: package-private */
    public abstract Sink opWrapSink(int i, Sink sink);

    static {
        Class<AbstractPipeline> cls = AbstractPipeline.class;
    }

    AbstractPipeline(Spliterator spliterator, int i, boolean z) {
        this.previousStage = null;
        this.sourceSpliterator = spliterator;
        this.sourceStage = this;
        int i2 = StreamOpFlag.STREAM_MASK & i;
        this.sourceOrOpFlags = i2;
        this.combinedFlags = ((i2 << 1) ^ -1) & StreamOpFlag.INITIAL_OPS_VALUE;
        this.depth = 0;
        this.parallel = z;
    }

    AbstractPipeline(AbstractPipeline abstractPipeline, int i) {
        if (!abstractPipeline.linkedOrConsumed) {
            abstractPipeline.linkedOrConsumed = true;
            abstractPipeline.nextStage = this;
            this.previousStage = abstractPipeline;
            this.sourceOrOpFlags = StreamOpFlag.OP_MASK & i;
            this.combinedFlags = StreamOpFlag.combineOpFlags(i, abstractPipeline.combinedFlags);
            this.sourceStage = abstractPipeline.sourceStage;
            if (opIsStateful()) {
                this.sourceStage.sourceAnyStateful = true;
            }
            this.depth = abstractPipeline.depth + 1;
            return;
        }
        throw new IllegalStateException("stream has already been operated upon or closed");
    }

    /* access modifiers changed from: package-private */
    public final Object evaluate(TerminalOp terminalOp) {
        if (!this.linkedOrConsumed) {
            this.linkedOrConsumed = true;
            if (isParallel()) {
                return terminalOp.evaluateParallel(this, sourceSpliterator(terminalOp.getOpFlags()));
            }
            return terminalOp.evaluateSequential(this, sourceSpliterator(terminalOp.getOpFlags()));
        }
        throw new IllegalStateException("stream has already been operated upon or closed");
    }

    /* access modifiers changed from: package-private */
    public final Spliterator sourceStageSpliterator() {
        AbstractPipeline abstractPipeline = this.sourceStage;
        if (this != abstractPipeline) {
            throw new IllegalStateException();
        } else if (!this.linkedOrConsumed) {
            this.linkedOrConsumed = true;
            Spliterator spliterator = abstractPipeline.sourceSpliterator;
            if (spliterator != null) {
                abstractPipeline.sourceSpliterator = null;
                return spliterator;
            }
            Supplier supplier = abstractPipeline.sourceSupplier;
            if (supplier != null) {
                Spliterator spliterator2 = (Spliterator) supplier.get();
                this.sourceStage.sourceSupplier = null;
                return spliterator2;
            }
            throw new IllegalStateException("source already consumed or closed");
        } else {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
    }

    public void close() {
        this.linkedOrConsumed = true;
        this.sourceSupplier = null;
        this.sourceSpliterator = null;
        AbstractPipeline abstractPipeline = this.sourceStage;
        Runnable runnable = abstractPipeline.sourceCloseAction;
        if (runnable != null) {
            abstractPipeline.sourceCloseAction = null;
            runnable.run();
        }
    }

    public final boolean isParallel() {
        return this.sourceStage.parallel;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: j$.util.Spliterator} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private p003j$.util.Spliterator sourceSpliterator(int r9) {
        /*
            r8 = this;
            j$.util.stream.AbstractPipeline r0 = r8.sourceStage
            j$.util.Spliterator r1 = r0.sourceSpliterator
            r2 = 0
            if (r1 == 0) goto L_0x000a
            r0.sourceSpliterator = r2
            goto L_0x0019
        L_0x000a:
            j$.util.function.Supplier r0 = r0.sourceSupplier
            if (r0 == 0) goto L_0x005e
            java.lang.Object r0 = r0.get()
            r1 = r0
            j$.util.Spliterator r1 = (p003j$.util.Spliterator) r1
            j$.util.stream.AbstractPipeline r0 = r8.sourceStage
            r0.sourceSupplier = r2
        L_0x0019:
            boolean r0 = r8.isParallel()
            if (r0 == 0) goto L_0x0053
            j$.util.stream.AbstractPipeline r0 = r8.sourceStage
            boolean r3 = r0.sourceAnyStateful
            if (r3 == 0) goto L_0x0053
            j$.util.stream.AbstractPipeline r3 = r0.nextStage
            r4 = 1
        L_0x0028:
            if (r0 == r8) goto L_0x0053
            int r5 = r3.sourceOrOpFlags
            boolean r6 = r3.opIsStateful()
            if (r6 == 0) goto L_0x0040
            j$.util.stream.StreamOpFlag r9 = p003j$.util.stream.StreamOpFlag.SHORT_CIRCUIT
            boolean r9 = r9.isKnown(r5)
            if (r9 == 0) goto L_0x003c
            int r9 = p003j$.util.stream.StreamOpFlag.IS_SHORT_CIRCUIT
        L_0x003c:
            r3.opEvaluateParallelLazy(r0, r1)
            throw r2
        L_0x0040:
            int r6 = r4 + 1
            r3.depth = r4
            int r0 = r0.combinedFlags
            int r0 = p003j$.util.stream.StreamOpFlag.combineOpFlags(r5, r0)
            r3.combinedFlags = r0
            j$.util.stream.AbstractPipeline r0 = r3.nextStage
            r4 = r6
            r7 = r3
            r3 = r0
            r0 = r7
            goto L_0x0028
        L_0x0053:
            if (r9 == 0) goto L_0x005d
            int r0 = r8.combinedFlags
            int r9 = p003j$.util.stream.StreamOpFlag.combineOpFlags(r9, r0)
            r8.combinedFlags = r9
        L_0x005d:
            return r1
        L_0x005e:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "source already consumed or closed"
            r9.<init>(r0)
            goto L_0x0067
        L_0x0066:
            throw r9
        L_0x0067:
            goto L_0x0066
        */
        throw new UnsupportedOperationException("Method not decompiled: p003j$.util.stream.AbstractPipeline.sourceSpliterator(int):j$.util.Spliterator");
    }

    /* access modifiers changed from: package-private */
    public final long exactOutputSizeIfKnown(Spliterator spliterator) {
        if (StreamOpFlag.SIZED.isKnown(getStreamAndOpFlags())) {
            return spliterator.getExactSizeIfKnown();
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public final Sink wrapAndCopyInto(Sink sink, Spliterator spliterator) {
        sink.getClass();
        copyInto(wrapSink(sink), spliterator);
        return sink;
    }

    /* access modifiers changed from: package-private */
    public final void copyInto(Sink sink, Spliterator spliterator) {
        sink.getClass();
        if (!StreamOpFlag.SHORT_CIRCUIT.isKnown(getStreamAndOpFlags())) {
            sink.begin(spliterator.getExactSizeIfKnown());
            spliterator.forEachRemaining(sink);
            sink.end();
            return;
        }
        copyIntoWithCancel(sink, spliterator);
    }

    /* access modifiers changed from: package-private */
    public final void copyIntoWithCancel(Sink sink, Spliterator spliterator) {
        AbstractPipeline abstractPipeline = this;
        while (abstractPipeline.depth > 0) {
            abstractPipeline = abstractPipeline.previousStage;
        }
        sink.begin(spliterator.getExactSizeIfKnown());
        abstractPipeline.forEachWithCancel(spliterator, sink);
        sink.end();
    }

    /* access modifiers changed from: package-private */
    public final int getStreamAndOpFlags() {
        return this.combinedFlags;
    }

    /* access modifiers changed from: package-private */
    public final boolean isOrdered() {
        return StreamOpFlag.ORDERED.isKnown(this.combinedFlags);
    }

    /* access modifiers changed from: package-private */
    public final Sink wrapSink(Sink sink) {
        sink.getClass();
        for (AbstractPipeline abstractPipeline = this; abstractPipeline.depth > 0; abstractPipeline = abstractPipeline.previousStage) {
            sink = abstractPipeline.opWrapSink(abstractPipeline.previousStage.combinedFlags, sink);
        }
        return sink;
    }

    /* access modifiers changed from: package-private */
    public Node opEvaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator, IntFunction intFunction) {
        throw new UnsupportedOperationException("Parallel evaluation is not supported");
    }

    static final /* synthetic */ Object[] lambda$opEvaluateParallelLazy$2$AbstractPipeline(int i) {
        return new Object[i];
    }

    /* access modifiers changed from: package-private */
    public Spliterator opEvaluateParallelLazy(PipelineHelper pipelineHelper, Spliterator spliterator) {
        opEvaluateParallel(pipelineHelper, spliterator, AbstractPipeline$$Lambda$2.$instance);
        throw null;
    }
}
