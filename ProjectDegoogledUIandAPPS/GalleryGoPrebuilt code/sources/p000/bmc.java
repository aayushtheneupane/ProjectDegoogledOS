package p000;

/* renamed from: bmc */
/* compiled from: PG */
public final class bmc {

    /* renamed from: a */
    public final bpt f3136a;

    public bmc(bpt bpt) {
        this.f3136a = bpt;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final ieh mo2585a(int i) {
        bpt bpt = this.f3136a;
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT a, b, c, d, h, i, j, k, l, e, f, ah, m, g, n, ai, aj, o, aa, ab, ac, af, ae, ad, w, x, y, ak, ao, ap, aq, at, al, am, an, ag FROM mt");
        hgn.mo7409a(" WHERE aj = -1");
        hgn.mo7409a(" AND at IS NULL ");
        hgn.mo7409a(" ORDER BY i DESC, a DESC");
        hgn.mo7409a(" LIMIT ? ");
        hgn.mo7411b(String.valueOf(i));
        return bpt.mo2655a(hgn.mo7407a(), bly.f3126a).mo6899b();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final ieh mo2587b(int i) {
        bpt bpt = this.f3136a;
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT a, b, c, d, h, i, j, k, l, e, f, ah, m, g, n, ai, aj, o, aa, ab, ac, af, ae, ad, w, x, y, ak, ao, ap, aq, at, al, am, an, ag FROM mt");
        hgn.mo7409a(" WHERE ai IS NOT NULL  AND aj != 0");
        hgn.mo7409a(" GROUP BY l , ai");
        hgn.mo7409a(" HAVING COUNT ( * ) > 1 ");
        hgn.mo7409a(" ORDER BY i DESC, a DESC");
        hgn.mo7409a(" LIMIT ? ");
        hgn.mo7411b(String.valueOf(i));
        return bpt.mo2655a(hgn.mo7407a(), blz.f3127a).mo6899b();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final ieh mo2586a(cyg cyg) {
        return this.f3136a.mo2656a(new bmb(cyg));
    }
}
