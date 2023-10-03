package p000;

import p003j$.util.function.Predicate;
import p003j$.util.function.Predicate$$CC;

/* renamed from: cbe */
/* compiled from: PG */
final /* synthetic */ class cbe implements Predicate {

    /* renamed from: a */
    public static final Predicate f4006a = new cbe();

    private cbe() {
    }

    public final Predicate and(Predicate predicate) {
        return Predicate$$CC.and$$dflt$$(this, predicate);
    }

    public final Predicate negate() {
        return Predicate$$CC.negate$$dflt$$(this);
    }

    /* renamed from: or */
    public final Predicate mo2990or(Predicate predicate) {
        return Predicate$$CC.or$$dflt$$(this, predicate);
    }

    public final boolean test(Object obj) {
        return !((cbd) obj).mo2974c();
    }
}
