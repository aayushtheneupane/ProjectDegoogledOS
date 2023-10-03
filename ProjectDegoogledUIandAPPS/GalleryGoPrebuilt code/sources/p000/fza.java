package p000;

import java.util.concurrent.Executor;

/* renamed from: fza */
/* compiled from: PG */
final /* synthetic */ class fza implements ice {

    /* renamed from: a */
    private final fzg f10710a;

    /* renamed from: b */
    private final ieh f10711b;

    /* renamed from: c */
    private final icf f10712c;

    /* renamed from: d */
    private final Executor f10713d;

    public fza(fzg fzg, ieh ieh, icf icf, Executor executor) {
        this.f10710a = fzg;
        this.f10711b = ieh;
        this.f10712c = icf;
        this.f10713d = executor;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        fzg fzg = this.f10710a;
        ieh ieh = this.f10711b;
        icf icf = this.f10712c;
        Executor executor = this.f10713d;
        ieh a = ibv.m12658a(ieh, (icf) new fzb(fzg), (Executor) idh.f13918a);
        ieh a2 = ibv.m12658a(a, icf, executor);
        return ibv.m12658a(a2, hmq.m11744a((icf) new fzd(fzg, a, a2)), (Executor) idh.f13918a);
    }
}
