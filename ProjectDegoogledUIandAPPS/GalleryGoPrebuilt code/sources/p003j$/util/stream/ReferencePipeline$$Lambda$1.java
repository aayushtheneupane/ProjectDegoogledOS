package p003j$.util.stream;

import p003j$.util.function.BiConsumer;
import p003j$.util.function.Consumer;

/* renamed from: j$.util.stream.ReferencePipeline$$Lambda$1 */
final /* synthetic */ class ReferencePipeline$$Lambda$1 implements Consumer {
    private final BiConsumer arg$1;
    private final Object arg$2;

    ReferencePipeline$$Lambda$1(BiConsumer biConsumer, Object obj) {
        this.arg$1 = biConsumer;
        this.arg$2 = obj;
    }

    public void accept(Object obj) {
        this.arg$1.accept(this.arg$2, obj);
    }
}
