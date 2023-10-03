package p000;

import java.util.Comparator;
import java.util.Iterator;
import p003j$.util.Comparator$$CC;
import p003j$.util.function.Function;
import p003j$.util.function.ToDoubleFunction;
import p003j$.util.function.ToIntFunction;
import p003j$.util.function.ToLongFunction;

/* renamed from: huv */
/* compiled from: PG */
public abstract class huv implements Comparator, p003j$.util.Comparator {
    protected huv() {
    }

    public abstract int compare(Object obj, Object obj2);

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

    /* renamed from: a */
    public static huv m12191a(Comparator comparator) {
        if (!(comparator instanceof huv)) {
            return new hrv(comparator);
        }
        return (huv) comparator;
    }

    /* renamed from: b */
    public Object mo8120b(Iterable iterable) {
        return mo8122b(iterable.iterator());
    }

    /* renamed from: b */
    public Object mo8121b(Object obj, Object obj2) {
        return compare(obj, obj2) < 0 ? obj2 : obj;
    }

    /* renamed from: b */
    public Object mo8122b(Iterator it) {
        Object next = it.next();
        while (it.hasNext()) {
            next = mo8121b(next, it.next());
        }
        return next;
    }

    /* renamed from: a */
    public Object mo8117a(Iterable iterable) {
        return mo8119a(iterable.iterator());
    }

    /* renamed from: a */
    public Object mo8118a(Object obj, Object obj2) {
        return compare(obj, obj2) > 0 ? obj2 : obj;
    }

    /* renamed from: a */
    public Object mo8119a(Iterator it) {
        Object next = it.next();
        while (it.hasNext()) {
            next = mo8118a(next, it.next());
        }
        return next;
    }

    /* renamed from: a */
    public huv mo8116a() {
        return new hvg(this);
    }
}
