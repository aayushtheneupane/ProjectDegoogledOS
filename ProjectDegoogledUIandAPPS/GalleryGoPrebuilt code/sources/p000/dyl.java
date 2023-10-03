package p000;

import java.util.Comparator;
import p003j$.util.Comparator$$CC;
import p003j$.util.function.Function;
import p003j$.util.function.ToDoubleFunction;
import p003j$.util.function.ToIntFunction;
import p003j$.util.function.ToLongFunction;

/* renamed from: dyl */
/* compiled from: PG */
final /* synthetic */ class dyl implements Comparator, p003j$.util.Comparator {

    /* renamed from: a */
    public static final Comparator f7655a = new dyl();

    private dyl() {
    }

    public final int compare(Object obj, Object obj2) {
        cxi cxi = (cxi) obj;
        ehf ehf = ((cxi) obj2).f5917i;
        if (ehf == null) {
            ehf = ehf.f8283d;
        }
        long j = ehf.f8286b;
        ehf ehf2 = cxi.f5917i;
        if (ehf2 == null) {
            ehf2 = ehf.f8283d;
        }
        return (j > ehf2.f8286b ? 1 : (j == ehf2.f8286b ? 0 : -1));
    }

    public final Comparator reversed() {
        return Comparator$$CC.reversed$$dflt$$(this);
    }

    public final Comparator thenComparing(Function function) {
        return Comparator$$CC.thenComparing$$dflt$$((Comparator) this, function);
    }

    public final Comparator thenComparing(Function function, Comparator comparator) {
        return Comparator$$CC.thenComparing$$dflt$$(this, function, comparator);
    }

    public final Comparator thenComparing(Comparator comparator) {
        return Comparator$$CC.thenComparing$$dflt$$((Comparator) this, comparator);
    }

    public final Comparator thenComparingDouble(ToDoubleFunction toDoubleFunction) {
        return Comparator$$CC.thenComparingDouble$$dflt$$(this, toDoubleFunction);
    }

    public final Comparator thenComparingInt(ToIntFunction toIntFunction) {
        return Comparator$$CC.thenComparingInt$$dflt$$(this, toIntFunction);
    }

    public final Comparator thenComparingLong(ToLongFunction toLongFunction) {
        return Comparator$$CC.thenComparingLong$$dflt$$(this, toLongFunction);
    }
}
