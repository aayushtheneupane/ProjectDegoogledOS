package p000;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: cyr */
/* compiled from: PG */
public final class cyr {

    /* renamed from: a */
    public final bpt f6045a;

    public cyr(bpt bpt) {
        this.f6045a = bpt;
    }

    /* renamed from: a */
    public static hgn m5742a() {
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT a, b, c, d, h, i, j, k, l, e, f, ah, m, g, n, ai, aj, o, aa, ab, ac, af, ae, ad, w, x, y, ak, ao, ap, aq, at, al, am, an FROM mt");
        return hgn;
    }

    /* renamed from: a */
    public final ieh mo3997a(List list) {
        return this.f6045a.mo2656a(new cyh(list));
    }

    /* renamed from: c */
    public final ieh mo3999c(List list) {
        if (!list.isEmpty()) {
            return this.f6045a.mo2656a(new cyo(list));
        }
        return ife.m12820a((Object) null);
    }

    /* renamed from: a */
    public final ieh mo3996a(cxd cxd) {
        bpt bpt = this.f6045a;
        hgn a = dcm.m5894a(m5742a(), cxd);
        a.mo7409a(" ORDER BY i DESC, a DESC");
        return bpt.mo2655a(a.mo7407a(), cyq.f6044a).mo6899b();
    }

    /* renamed from: a */
    public final ieh mo3995a(long j) {
        return ibv.m12657a(mo3998b(hso.m12033a((Object) Long.valueOf(j))), hmq.m11742a(cym.f6040a), (Executor) idh.f13918a);
    }

    /* renamed from: b */
    public final ieh mo3998b(List list) {
        bpt bpt = this.f6045a;
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT * FROM mt WHERE a IN(?");
        hgn.mo7408a((Long) list.get(0));
        for (int i = 1; i < list.size(); i++) {
            hgn.mo7409a(", ?");
            hgn.mo7408a((Long) list.get(i));
        }
        hgn.mo7409a(")");
        return bpt.mo2655a(hgn.mo7407a(), cyl.f6039a).mo6899b();
    }

    /* renamed from: a */
    public static void m5743a(hfz hfz, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            cyg cyg = (cyg) it.next();
            hfz.mo7386a("mt", cyg.mo3994S(), "a = ?", String.valueOf(cyg.mo3907a().get()));
        }
    }
}
