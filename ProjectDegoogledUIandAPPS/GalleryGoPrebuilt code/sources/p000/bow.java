package p000;

/* renamed from: bow */
/* compiled from: PG */
public final class bow {

    /* renamed from: a */
    public final bpt f3281a;

    public bow(bpt bpt) {
        this.f3281a = bpt;
    }

    /* renamed from: a */
    public final ieh mo2646a(int i) {
        bpt bpt = this.f3281a;
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT a, b, c, d, h, i, j, k, l, e, f, ah, m, g, n, ai, aj, o, aa, ab, ac, af, ae, ad, w, x, y, ak, ao, ap, aq, at, al, am, an FROM mt");
        hgn.mo7409a(" WHERE c = 1");
        hgn.mo7409a(" AND d = 'image/jpeg'");
        hgn.mo7409a(" AND ap <= 5");
        hgn.mo7409a(" AND (");
        hgn.mo7409a("ao IS NULL OR ");
        hgn.mo7409a("ao > ? ");
        hgn.mo7411b("80");
        hgn.mo7409a(" ) ");
        hgn.mo7409a(" AND f * e > 10000");
        hgn.mo7409a(" ORDER BY i");
        hgn.mo7409a(" LIMIT ? ");
        hgn.mo7411b(String.valueOf(i));
        return bpt.mo2655a(hgn.mo7407a(), bot.f3277a).mo6899b();
    }
}
