package p003j$.util;

import java.util.Comparator;
import p003j$.util.function.Function;

/* renamed from: j$.util.Comparator$$Dispatch */
public /* synthetic */ class Comparator$$Dispatch {
    public static Comparator thenComparing(Comparator comparator, Function function) {
        return comparator instanceof Comparator ? ((Comparator) comparator).thenComparing(function) : Comparator$$CC.thenComparing$$dflt$$(comparator, function);
    }

    public static Comparator thenComparing(Comparator comparator, Comparator comparator2) {
        return comparator instanceof Comparator ? ((Comparator) comparator).thenComparing(comparator2) : Comparator$$CC.thenComparing$$dflt$$(comparator, comparator2);
    }
}
