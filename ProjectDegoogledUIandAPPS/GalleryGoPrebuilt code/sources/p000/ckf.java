package p000;

import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: ckf */
/* compiled from: PG */
final /* synthetic */ class ckf implements icf {

    /* renamed from: a */
    private final ckk f4552a;

    /* renamed from: b */
    private final cyd f4553b;

    public ckf(ckk ckk, cyd cyd) {
        this.f4552a = ckk;
        this.f4553b = cyd;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        ckk ckk = this.f4552a;
        cyd cyd = this.f4553b;
        List list = (List) obj;
        if (!list.isEmpty()) {
            return ckk.mo3202a(gte.m10770a(ckk.f4562c.mo4012a(cyd), (hpr) new ckh(list), (Executor) ckk.f4567h), list);
        }
        return ife.m12820a((Object) list);
    }
}
