package p003j$.util.stream;

import p003j$.util.function.Consumer;

/* renamed from: j$.util.stream.Sink */
interface Sink extends Consumer {
    void begin(long j);

    boolean cancellationRequested();

    void end();

    /* renamed from: j$.util.stream.Sink$ChainedReference */
    public abstract class ChainedReference implements Sink {
        protected final Sink downstream;

        public ChainedReference(Sink sink) {
            sink.getClass();
            this.downstream = sink;
        }

        public void end() {
            this.downstream.end();
        }

        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }
    }
}
