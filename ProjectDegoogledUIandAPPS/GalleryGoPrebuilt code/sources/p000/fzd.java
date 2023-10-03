package p000;

import java.util.concurrent.Future;

/* renamed from: fzd */
/* compiled from: PG */
final /* synthetic */ class fzd implements icf {

    /* renamed from: a */
    private final fzg f10716a;

    /* renamed from: b */
    private final ieh f10717b;

    /* renamed from: c */
    private final ieh f10718c;

    public fzd(fzg fzg, ieh ieh, ieh ieh2) {
        this.f10716a = fzg;
        this.f10717b = ieh;
        this.f10718c = ieh2;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        fzg fzg = this.f10716a;
        ieh ieh = this.f10717b;
        ieh ieh2 = this.f10718c;
        ikf ikf = (ikf) obj;
        if (!((ikf) ife.m12871b((Future) ieh)).equals(ife.m12871b((Future) ieh2))) {
            return ibv.m12658a(ieh2, hmq.m11744a((icf) new fze(fzg, ieh2)), fzg.f10725d);
        }
        return ife.m12820a((Object) null);
    }
}
