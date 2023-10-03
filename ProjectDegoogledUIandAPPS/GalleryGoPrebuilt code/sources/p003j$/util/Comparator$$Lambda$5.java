package p003j$.util;

import java.io.Serializable;
import java.util.Comparator;
import p003j$.util.function.Function;
import p003j$.util.function.ToDoubleFunction;

/* renamed from: j$.util.Comparator$$Lambda$5 */
final /* synthetic */ class Comparator$$Lambda$5 implements Comparator, Serializable, Comparator {
    private final ToDoubleFunction arg$1;

    Comparator$$Lambda$5(ToDoubleFunction toDoubleFunction) {
        this.arg$1 = toDoubleFunction;
    }

    public int compare(Object obj, Object obj2) {
        return Double.compare(this.arg$1.applyAsDouble(obj), this.arg$1.applyAsDouble(obj2));
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
