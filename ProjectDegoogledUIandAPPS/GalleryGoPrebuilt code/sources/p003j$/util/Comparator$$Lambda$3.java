package p003j$.util;

import java.io.Serializable;
import java.util.Comparator;
import p003j$.util.function.Function;
import p003j$.util.function.ToIntFunction;

/* renamed from: j$.util.Comparator$$Lambda$3 */
final /* synthetic */ class Comparator$$Lambda$3 implements Comparator, Serializable, Comparator {
    private final ToIntFunction arg$1;

    Comparator$$Lambda$3(ToIntFunction toIntFunction) {
        this.arg$1 = toIntFunction;
    }

    public int compare(Object obj, Object obj2) {
        return Integer.compare(this.arg$1.applyAsInt(obj), this.arg$1.applyAsInt(obj2));
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
