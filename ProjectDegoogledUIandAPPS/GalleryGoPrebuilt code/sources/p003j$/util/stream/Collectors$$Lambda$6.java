package p003j$.util.stream;

import java.util.List;
import p003j$.util.function.BinaryOperator;

/* renamed from: j$.util.stream.Collectors$$Lambda$6 */
final /* synthetic */ class Collectors$$Lambda$6 implements BinaryOperator {
    static final BinaryOperator $instance = new Collectors$$Lambda$6();

    private Collectors$$Lambda$6() {
    }

    public Object apply(Object obj, Object obj2) {
        List list = (List) obj;
        List unused = list.addAll((List) obj2);
        return list;
    }
}
