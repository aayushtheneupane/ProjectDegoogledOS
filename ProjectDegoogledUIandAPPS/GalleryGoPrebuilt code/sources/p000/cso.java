package p000;

/* renamed from: cso */
/* compiled from: PG */
public final class cso {

    /* renamed from: a */
    private final bpt f5584a;

    public cso(bpt bpt) {
        this.f5584a = bpt;
    }

    /* renamed from: a */
    public final ieh mo3801a(int i) {
        bpt bpt = this.f5584a;
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT a, b, c, d, h, i, j, k, l, e, f, ah, m, g, n, ai, aj, o, aa, ab, ac, af, ae, ad, w, x, y, ak, ao, ap, aq, at, al, am, an, ag, v FROM mt");
        hgn.mo7409a(" WHERE x != ? ");
        hgn.mo7411b(Integer.toString(1));
        hgn.mo7409a(" AND c = 1");
        hgn.mo7409a(" AND f >= ? ");
        hgn.mo7411b("480");
        hgn.mo7409a(" AND e >= ? ");
        hgn.mo7411b("480");
        hgn.mo7409a(" AND at IS NULL ");
        hgn.mo7409a(" ORDER BY i DESC, a DESC");
        hgn.mo7409a(" LIMIT ? ");
        hgn.mo7411b(String.valueOf(i));
        return bpt.mo2655a(hgn.mo7407a(), csm.f5582a).mo6899b();
    }

    /* renamed from: a */
    public final ieh mo3802a(cyg cyg) {
        return this.f5584a.mo2656a(new csn(cyg));
    }
}
