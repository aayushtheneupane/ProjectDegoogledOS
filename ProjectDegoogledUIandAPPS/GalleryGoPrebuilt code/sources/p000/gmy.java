package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: gmy */
/* compiled from: PG */
public final class gmy implements fzh {

    /* renamed from: a */
    public final AtomicReference f11640a = new AtomicReference();

    /* renamed from: b */
    public final /* synthetic */ gnb f11641b;

    /* renamed from: c */
    private final /* synthetic */ String f11642c;

    /* renamed from: d */
    private final /* synthetic */ gmz f11643d;

    public gmy(gnb gnb, String str, gmz gmz) {
        this.f11641b = gnb;
        this.f11642c = str;
        this.f11643d = gmz;
    }

    /* renamed from: b */
    public final ieh mo6357b() {
        return ife.m12884c(this.f11641b.f11663b.mo5933a((Callable) new gmw(this)), this.f11641b.f11664c.mo6360a(new gmx(this.f11642c), idh.f13918a)).mo8443a(ife.m12887c(), (Executor) idh.f13918a);
    }

    /* renamed from: a */
    public final ieh mo6356a(ikf ikf) {
        gne gne = (gne) this.f11643d;
        return ife.m12816a(hmq.m11743a((ice) new gnc(gne, (gna) this.f11640a.get(), (gng) ikf)), (Executor) gne.f11671a);
    }

    /* renamed from: a */
    public final ieh mo6355a() {
        return ibv.m12657a(ibv.m12658a(this.f11641b.f11663b.mo5933a((Callable) new gmt(this)), (icf) new gmu(ibv.m12657a(this.f11641b.f11664c.mo6359a(), (hpr) new C0181gms(this.f11642c), (Executor) idh.f13918a)), (Executor) idh.f13918a), (hpr) new gmv(this), (Executor) this.f11641b.f11663b);
    }
}
