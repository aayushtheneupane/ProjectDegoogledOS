package p000;

import android.text.TextUtils;
import p003j$.util.function.Predicate;
import p003j$.util.function.Predicate$$CC;

/* renamed from: ctj */
/* compiled from: PG */
final /* synthetic */ class ctj implements Predicate {

    /* renamed from: a */
    public static final Predicate f5628a = new ctj();

    private ctj() {
    }

    public final Predicate and(Predicate predicate) {
        return Predicate$$CC.and$$dflt$$(this, predicate);
    }

    public final Predicate negate() {
        return Predicate$$CC.negate$$dflt$$(this);
    }

    /* renamed from: or */
    public final Predicate mo3828or(Predicate predicate) {
        return Predicate$$CC.or$$dflt$$(this, predicate);
    }

    public final boolean test(Object obj) {
        return !TextUtils.isEmpty((String) obj);
    }
}
