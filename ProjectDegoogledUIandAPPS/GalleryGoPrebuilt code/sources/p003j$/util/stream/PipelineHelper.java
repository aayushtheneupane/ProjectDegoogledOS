package p003j$.util.stream;

import p003j$.util.Spliterator;
import p003j$.util.function.IntFunction;
import p003j$.util.stream.Node;

/* renamed from: j$.util.stream.PipelineHelper */
abstract class PipelineHelper {
    /* access modifiers changed from: package-private */
    public abstract void copyInto(Sink sink, Spliterator spliterator);

    /* access modifiers changed from: package-private */
    public abstract long exactOutputSizeIfKnown(Spliterator spliterator);

    /* access modifiers changed from: package-private */
    public abstract int getStreamAndOpFlags();

    /* access modifiers changed from: package-private */
    public abstract Node.Builder makeNodeBuilder(long j, IntFunction intFunction);

    /* access modifiers changed from: package-private */
    public abstract Sink wrapAndCopyInto(Sink sink, Spliterator spliterator);

    /* access modifiers changed from: package-private */
    public abstract Sink wrapSink(Sink sink);

    PipelineHelper() {
    }
}
