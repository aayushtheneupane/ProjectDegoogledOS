package p000;

import p003j$.util.function.Predicate;
import p003j$.util.function.Predicate$$CC;

/* renamed from: dob */
/* compiled from: PG */
final /* synthetic */ class dob implements Predicate {

    /* renamed from: a */
    private final cyd f6929a;

    public dob(cyd cyd) {
        this.f6929a = cyd;
    }

    public final Predicate and(Predicate predicate) {
        return Predicate$$CC.and$$dflt$$(this, predicate);
    }

    public final Predicate negate() {
        return Predicate$$CC.negate$$dflt$$(this);
    }

    /* renamed from: or */
    public final Predicate mo4289or(Predicate predicate) {
        return Predicate$$CC.or$$dflt$$(this, predicate);
    }

    public final boolean test(Object obj) {
        return cyc.m5648b(((dls) obj).f6796a).equals(this.f6929a);
    }
}
