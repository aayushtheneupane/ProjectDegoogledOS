package p003j$.util.stream;

import p003j$.util.Spliterator;

/* renamed from: j$.util.stream.TerminalOp */
interface TerminalOp {
    Object evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator);

    Object evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator);

    int getOpFlags();
}
