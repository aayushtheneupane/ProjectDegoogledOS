package p003j$.util;

import java.io.Serializable;
import java.util.Comparator;
import p003j$.util.function.Function;

/* renamed from: j$.util.Comparator$$Lambda$0 */
final /* synthetic */ class Comparator$$Lambda$0 implements Comparator, Serializable, Comparator {
    private final Comparator arg$1;
    private final Comparator arg$2;

    Comparator$$Lambda$0(Comparator comparator, Comparator comparator2) {
        this.arg$1 = comparator;
        this.arg$2 = comparator2;
    }

    public int compare(Object obj, Object obj2) {
        return Comparator$$CC.lambda$thenComparing$36697e65$1$Comparator$$CC(this.arg$1, this.arg$2, obj, obj2);
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
