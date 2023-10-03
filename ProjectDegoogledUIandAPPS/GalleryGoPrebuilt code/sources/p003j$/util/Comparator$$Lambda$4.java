package p003j$.util;

import java.io.Serializable;
import java.util.Comparator;
import p003j$.util.function.Function;
import p003j$.util.function.ToLongFunction;

/* renamed from: j$.util.Comparator$$Lambda$4 */
final /* synthetic */ class Comparator$$Lambda$4 implements Comparator, Serializable, Comparator {
    private final ToLongFunction arg$1;

    Comparator$$Lambda$4(ToLongFunction toLongFunction) {
        this.arg$1 = toLongFunction;
    }

    public int compare(Object obj, Object obj2) {
        return Comparator$$CC.lambda$comparingLong$6043328a$1$Comparator$$CC(this.arg$1, obj, obj2);
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
