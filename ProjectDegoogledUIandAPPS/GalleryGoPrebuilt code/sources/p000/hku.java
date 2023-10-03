package p000;

import java.util.UUID;

/* renamed from: hku */
/* compiled from: PG */
abstract class hku extends hke {

    /* renamed from: a */
    private final hln f12947a;

    public hku(String str, hlp hlp, hln hln) {
        super(str, hlp);
        ife.m12890c(hln.f12990c);
        this.f12947a = hln;
    }

    public hku(String str, UUID uuid, hln hln) {
        super(str, uuid);
        ife.m12890c(hln.f12990c);
        this.f12947a = hln;
    }

    /* renamed from: a */
    public final hlk mo7536a(hok hok) {
        return hln.m11700a(hok, this.f12947a, hnf.f13084a);
    }
}
