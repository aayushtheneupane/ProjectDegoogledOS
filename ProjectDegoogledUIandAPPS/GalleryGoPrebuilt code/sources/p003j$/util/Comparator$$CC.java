package p003j$.util;

import java.util.Collections;
import java.util.Comparator;
import p003j$.util.function.Function;
import p003j$.util.function.ToDoubleFunction;
import p003j$.util.function.ToIntFunction;
import p003j$.util.function.ToLongFunction;

/* renamed from: j$.util.Comparator$$CC */
public abstract /* synthetic */ class Comparator$$CC {
    public static Comparator reversed$$dflt$$(Comparator comparator) {
        return Collections.reverseOrder(comparator);
    }

    public static Comparator thenComparing$$dflt$$(Comparator comparator, Comparator comparator2) {
        comparator2.getClass();
        return new Comparator$$Lambda$0(comparator, comparator2);
    }

    static /* synthetic */ int lambda$thenComparing$36697e65$1$Comparator$$CC(Comparator comparator, Comparator comparator2, Object obj, Object obj2) {
        int compare = comparator.compare(obj, obj2);
        return compare != 0 ? compare : comparator2.compare(obj, obj2);
    }

    public static Comparator thenComparing$$dflt$$(Comparator comparator, Function function, Comparator comparator2) {
        return Comparator$$Dispatch.thenComparing(comparator, comparing$$STATIC$$(function, comparator2));
    }

    public static Comparator thenComparing$$dflt$$(Comparator comparator, Function function) {
        return Comparator$$Dispatch.thenComparing(comparator, comparing$$STATIC$$(function));
    }

    public static Comparator thenComparingInt$$dflt$$(Comparator comparator, ToIntFunction toIntFunction) {
        return Comparator$$Dispatch.thenComparing(comparator, comparingInt$$STATIC$$(toIntFunction));
    }

    public static Comparator thenComparingLong$$dflt$$(Comparator comparator, ToLongFunction toLongFunction) {
        return Comparator$$Dispatch.thenComparing(comparator, comparingLong$$STATIC$$(toLongFunction));
    }

    public static Comparator thenComparingDouble$$dflt$$(Comparator comparator, ToDoubleFunction toDoubleFunction) {
        return Comparator$$Dispatch.thenComparing(comparator, comparingDouble$$STATIC$$(toDoubleFunction));
    }

    public static Comparator comparing$$STATIC$$(Function function, Comparator comparator) {
        function.getClass();
        comparator.getClass();
        return new Comparator$$Lambda$1(comparator, function);
    }

    public static Comparator comparing$$STATIC$$(Function function) {
        function.getClass();
        return new Comparator$$Lambda$2(function);
    }

    public static Comparator comparingInt$$STATIC$$(ToIntFunction toIntFunction) {
        toIntFunction.getClass();
        return new Comparator$$Lambda$3(toIntFunction);
    }

    public static Comparator comparingLong$$STATIC$$(ToLongFunction toLongFunction) {
        toLongFunction.getClass();
        return new Comparator$$Lambda$4(toLongFunction);
    }

    static /* synthetic */ int lambda$comparingLong$6043328a$1$Comparator$$CC(ToLongFunction toLongFunction, Object obj, Object obj2) {
        return (toLongFunction.applyAsLong(obj) > toLongFunction.applyAsLong(obj2) ? 1 : (toLongFunction.applyAsLong(obj) == toLongFunction.applyAsLong(obj2) ? 0 : -1));
    }

    public static Comparator comparingDouble$$STATIC$$(ToDoubleFunction toDoubleFunction) {
        toDoubleFunction.getClass();
        return new Comparator$$Lambda$5(toDoubleFunction);
    }
}
