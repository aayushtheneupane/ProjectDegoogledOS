package p000;

/* renamed from: cis */
/* compiled from: PG */
public final class cis {

    /* renamed from: a */
    public final bpt f4472a;

    public cis(bpt bpt) {
        this.f4472a = bpt;
    }

    /* renamed from: a */
    public final ieh mo3161a(int i) {
        bpt bpt = this.f4472a;
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT a, b, c, d, h, i, j, k, l, e, f, ah, m, g, n, ai, aj, o, aa, ab, ac, af, ae, ad, w, x, y, ak, ao, ap, aq, at, al, am, an, ag FROM mt");
        hgn.mo7409a(" WHERE y = 0 ");
        hgn.mo7409a(" AND c = 1");
        hgn.mo7409a(" AND f >= ? ");
        hgn.mo7411b("480");
        hgn.mo7409a(" AND e >= ? ");
        hgn.mo7411b("480");
        hgn.mo7409a(" AND at IS NULL ");
        hgn.mo7409a(" ORDER BY i DESC, a DESC");
        hgn.mo7409a(" LIMIT ? ");
        hgn.mo7411b(String.valueOf(i));
        return bpt.mo2655a(hgn.mo7407a(), ciq.f4470a).mo6899b();
    }

    /* renamed from: a */
    public static void m4373a(hfz hfz, cyg cyg) {
        hfz.mo7386a("mt", cyg.mo3994S(), "a = ?", String.valueOf(((cwy) cyg).f5853a.get()));
    }
}
