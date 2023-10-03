package p003j$.util.stream;

import p003j$.util.function.Consumer;

/* renamed from: j$.util.stream.Node */
interface Node {

    /* renamed from: j$.util.stream.Node$Builder */
    public interface Builder extends Sink {
        Node build();
    }

    void forEach(Consumer consumer);
}
