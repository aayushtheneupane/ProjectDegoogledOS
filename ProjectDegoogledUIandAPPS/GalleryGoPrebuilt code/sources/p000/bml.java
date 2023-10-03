package p000;

import java.util.concurrent.Executor;

/* renamed from: bml */
/* compiled from: PG */
final /* synthetic */ class bml implements cvl {

    /* renamed from: a */
    private final bmp f3146a;

    public bml(bmp bmp) {
        this.f3146a = bmp;
    }

    /* renamed from: a */
    public final ieh mo2590a(Object obj) {
        bmp bmp = this.f3146a;
        cyg cyg = (cyg) obj;
        bmc bmc = bmp.f3150a;
        String m = cyg.mo3921m();
        bpt bpt = bmc.f3136a;
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT a, b, c, d, h, i, j, k, l, e, f, ah, m, g, n, ai, aj, o, aa, ab, ac, af, ae, ad, w, x, y, ak, ao, ap, aq, at, al, am, an FROM mt");
        hgn.mo7409a(" WHERE l = ? ");
        hgn.mo7411b(m);
        hgn.mo7409a(" AND ai = ? ");
        hgn.mo7411b((String) cyg.mo3924p().get());
        hgn.mo7409a(" ORDER BY aj DESC ");
        return gte.m10771a(bpt.mo2655a(hgn.mo7407a(), bma.f3134a).mo6899b(), (icf) new bmj(bmp), (Executor) bmp.f3154e);
    }
}
