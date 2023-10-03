package p003j$.util;

import java.io.Serializable;
import java.util.Comparator;
import p003j$.util.function.Function;

/* renamed from: j$.util.Comparator$$Lambda$2 */
final /* synthetic */ class Comparator$$Lambda$2 implements Comparator, Serializable, Comparator {
    private final Function arg$1;

    Comparator$$Lambda$2(Function function) {
        this.arg$1 = function;
    }

    public int compare(Object obj, Object obj2) {
        return ((Comparable) this.arg$1.apply(obj)).compareTo(this.arg$1.apply(obj2));
    }

    public Comparator reversed() {
        return Comparator$$CC.reversed$$dflt$$(this);
    }

    public Comparator thenComparing(Function function) {
        return Comparator$$CC.thenComparing$$dflt$$((Comparator) this, function);
    }

    public Comparator thenComparing(Comparator comparator) {
        return Comparator$$CC.thenComparing$$dflt$$((Comparator) this, comparator);
    }
}
