package p000;

import android.view.View;
import java.util.Comparator;
import p003j$.util.Comparator$$CC;
import p003j$.util.function.Function;
import p003j$.util.function.ToDoubleFunction;
import p003j$.util.function.ToIntFunction;
import p003j$.util.function.ToLongFunction;

/* renamed from: abq */
/* compiled from: PG */
public final class abq implements Comparator, p003j$.util.Comparator {
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

    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        float x = C0340mj.m14733x((View) obj);
        float x2 = C0340mj.m14733x((View) obj2);
        if (x <= x2) {
            return x >= x2 ? 0 : 1;
        }
        return -1;
    }
}
