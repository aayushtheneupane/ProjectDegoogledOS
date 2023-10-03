package p003j$.util.stream;

import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ForkJoinPool;
import p003j$.util.Spliterator;

/* renamed from: j$.util.stream.AbstractTask */
abstract class AbstractTask extends CountedCompleter {
    static final int LEAF_TARGET = (ForkJoinPool.getCommonPoolParallelism() << 2);
    protected final PipelineHelper helper;
    protected AbstractTask leftChild;
    private Object localResult;
    protected AbstractTask rightChild;
    protected Spliterator spliterator;
    protected long targetSize;

    /* access modifiers changed from: protected */
    public abstract Object doLeaf();

    /* access modifiers changed from: protected */
    public abstract AbstractTask makeChild(Spliterator spliterator2);

    protected AbstractTask(PipelineHelper pipelineHelper, Spliterator spliterator2) {
        super((CountedCompleter) null);
        this.helper = pipelineHelper;
        this.spliterator = spliterator2;
        this.targetSize = 0;
    }

    protected AbstractTask(AbstractTask abstractTask, Spliterator spliterator2) {
        super(abstractTask);
        this.spliterator = spliterator2;
        this.helper = abstractTask.helper;
        this.targetSize = abstractTask.targetSize;
    }

    public static long suggestTargetSize(long j) {
        long j2 = j / ((long) LEAF_TARGET);
        if (j2 > 0) {
            return j2;
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public final long getTargetSize(long j) {
        long j2 = this.targetSize;
        if (j2 != 0) {
            return j2;
        }
        long suggestTargetSize = suggestTargetSize(j);
        this.targetSize = suggestTargetSize;
        return suggestTargetSize;
    }

    public Object getRawResult() {
        return this.localResult;
    }

    /* access modifiers changed from: protected */
    public void setRawResult(Object obj) {
        if (obj != null) {
            throw new IllegalStateException();
        }
    }

    /* access modifiers changed from: protected */
    public Object getLocalResult() {
        return this.localResult;
    }

    /* access modifiers changed from: protected */
    public void setLocalResult(Object obj) {
        this.localResult = obj;
    }

    /* access modifiers changed from: protected */
    public boolean isLeaf() {
        return this.leftChild == null;
    }

    public void compute() {
        Spliterator trySplit;
        Spliterator spliterator2 = this.spliterator;
        long estimateSize = spliterator2.estimateSize();
        long targetSize2 = getTargetSize(estimateSize);
        boolean z = false;
        AbstractTask abstractTask = this;
        while (estimateSize > targetSize2 && (trySplit = spliterator2.trySplit()) != null) {
            AbstractTask makeChild = abstractTask.makeChild(trySplit);
            abstractTask.leftChild = makeChild;
            AbstractTask makeChild2 = abstractTask.makeChild(spliterator2);
            abstractTask.rightChild = makeChild2;
            abstractTask.setPendingCount(1);
            if (z) {
                spliterator2 = trySplit;
                abstractTask = makeChild;
                makeChild = makeChild2;
            } else {
                abstractTask = makeChild2;
            }
            z = !z;
            makeChild.fork();
            estimateSize = spliterator2.estimateSize();
        }
        abstractTask.setLocalResult(abstractTask.doLeaf());
        abstractTask.tryComplete();
    }

    public void onCompletion(CountedCompleter countedCompleter) {
        this.spliterator = null;
        this.rightChild = null;
        this.leftChild = null;
    }
}
