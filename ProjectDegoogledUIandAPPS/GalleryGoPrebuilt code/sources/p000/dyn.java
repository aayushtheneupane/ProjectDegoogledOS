package p000;

import java.util.Comparator;
import p003j$.util.Comparator$$CC;
import p003j$.util.function.Function;
import p003j$.util.function.ToDoubleFunction;
import p003j$.util.function.ToIntFunction;
import p003j$.util.function.ToLongFunction;

/* renamed from: dyn */
/* compiled from: PG */
final /* synthetic */ class dyn implements Comparator, p003j$.util.Comparator {

    /* renamed from: a */
    public static final Comparator f7657a = new dyn();

    private dyn() {
    }

    public final int compare(Object obj, Object obj2) {
        return (((cxi) obj2).f5931w > ((cxi) obj).f5931w ? 1 : (((cxi) obj2).f5931w == ((cxi) obj).f5931w ? 0 : -1));
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
