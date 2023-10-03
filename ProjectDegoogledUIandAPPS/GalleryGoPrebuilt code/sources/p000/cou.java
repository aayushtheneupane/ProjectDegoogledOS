package p000;

/* renamed from: cou */
/* compiled from: PG */
public final class cou {

    /* renamed from: a */
    public final bpt f5327a;

    public cou(bpt bpt) {
        this.f5327a = bpt;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final ieh mo3723a(int i) {
        bpt bpt = this.f5327a;
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT a, b, c, d, h, i, j, k, l, e, f, ah, m, g, n, ai, aj, o, aa, ab, ac, af, ae, ad, w, x, y, ak, ao, ap, aq, at, al, am, an, ag FROM mt");
        hgn.mo7409a(" WHERE w = 0 ");
        hgn.mo7409a(" AND at IS NULL ");
        hgn.mo7409a(" ORDER BY i DESC, a DESC");
        hgn.mo7409a(" LIMIT ? ");
        hgn.mo7411b(String.valueOf(i));
        return bpt.mo2655a(hgn.mo7407a(), cos.f5325a).mo6899b();
    }
}
