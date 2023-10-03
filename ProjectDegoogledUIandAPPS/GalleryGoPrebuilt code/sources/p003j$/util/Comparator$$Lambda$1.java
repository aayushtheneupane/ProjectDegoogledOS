package p003j$.util;

import java.io.Serializable;
import java.util.Comparator;
import p003j$.util.function.Function;

/* renamed from: j$.util.Comparator$$Lambda$1 */
final /* synthetic */ class Comparator$$Lambda$1 implements Comparator, Serializable, Comparator {
    private final Comparator arg$1;
    private final Function arg$2;

    Comparator$$Lambda$1(Comparator comparator, Function function) {
        this.arg$1 = comparator;
        this.arg$2 = function;
    }

    public int compare(Object obj, Object obj2) {
        return this.arg$1.compare(this.arg$2.apply(obj), this.arg$2.apply(obj2));
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
