package p000;

/* renamed from: djh */
/* compiled from: PG */
public final class djh {

    /* renamed from: a */
    public final bpt f6664a;

    public djh(bpt bpt) {
        this.f6664a = bpt;
    }

    /* renamed from: a */
    public final ieh mo4161a(int i) {
        bpt bpt = this.f6664a;
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT a, b, c, d, h, i, j, k, l, e, f, ah, m, g, n, ai, aj, o, aa, ab, ac, af, ae, ad, w, x, y, ak, ao, ap, aq, at, al, am, an FROM mt");
        hgn.mo7409a(" WHERE ak IS NULL ");
        hgn.mo7409a(" ORDER BY i DESC, a DESC");
        hgn.mo7409a(" LIMIT ? ");
        hgn.mo7411b(String.valueOf(i));
        return bpt.mo2655a(hgn.mo7407a(), djf.f6662a).mo6899b();
    }
}
