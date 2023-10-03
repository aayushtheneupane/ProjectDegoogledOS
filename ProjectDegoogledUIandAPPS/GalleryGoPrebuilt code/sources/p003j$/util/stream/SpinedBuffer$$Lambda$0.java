package p003j$.util.stream;

import java.util.List;
import p003j$.util.function.Consumer;

/* renamed from: j$.util.stream.SpinedBuffer$$Lambda$0 */
final /* synthetic */ class SpinedBuffer$$Lambda$0 implements Consumer {
    private final List arg$1;

    private SpinedBuffer$$Lambda$0(List list) {
        this.arg$1 = list;
    }

    static Consumer get$Lambda(List list) {
        return new SpinedBuffer$$Lambda$0(list);
    }

    public void accept(Object obj) {
        this.arg$1.add(obj);
    }
}
