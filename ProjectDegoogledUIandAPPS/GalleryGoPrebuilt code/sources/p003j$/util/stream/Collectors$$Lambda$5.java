package p003j$.util.stream;

import java.util.List;
import p003j$.util.function.BiConsumer;

/* renamed from: j$.util.stream.Collectors$$Lambda$5 */
final /* synthetic */ class Collectors$$Lambda$5 implements BiConsumer {
    static final BiConsumer $instance = new Collectors$$Lambda$5();

    private Collectors$$Lambda$5() {
    }

    public void accept(Object obj, Object obj2) {
        ((List) obj).add(obj2);
    }
}
