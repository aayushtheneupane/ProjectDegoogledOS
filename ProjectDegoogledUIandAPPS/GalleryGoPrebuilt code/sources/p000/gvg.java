package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: gvg */
/* compiled from: PG */
final /* synthetic */ class gvg implements Runnable {

    /* renamed from: a */
    private final gud f12121a;

    /* renamed from: b */
    private final guy f12122b;

    /* renamed from: c */
    private final gvc f12123c;

    /* renamed from: d */
    private final gvi f12124d;

    public gvg(gvi gvi, gud gud, guy guy, gvc gvc) {
        this.f12124d = gvi;
        this.f12121a = gud;
        this.f12122b = guy;
        this.f12123c = gvc;
    }

    public final void run() {
        gvu gvu;
        int i;
        gvi gvi = this.f12124d;
        gud gud = this.f12121a;
        guy guy = this.f12122b;
        gvc gvc = this.f12123c;
        gvj a = gvi.mo7112a();
        gug gug = new gug(guy);
        fxk.m9830b();
        ife.m12869b((Object) gud, (Object) "Subscribed to a null DataSource. Are you mocking a DataService? Instead, use the real implementation and mock any stubs/interfaces it calls. See go/mocks#prefer-real-objects");
        Class<?> cls = gvc.getClass();
        gvu gvu2 = (gvu) a.f12135c.get(cls);
        if (gvu2 == null) {
            gvu gvu3 = new gvu(gud, a.f12132a, a.f12133aa, a.f12134b, a.f12131Z);
            a.f12135c.put(cls, gvu3);
            gvu = gvu3;
        } else {
            gvu = gvu2;
        }
        grs grs = a.f12136d;
        fxk.m9830b();
        Class<?> cls2 = gvc.getClass();
        if (grs.f11929e.containsKey(cls2)) {
            i = ((Integer) grs.f11929e.get(cls2)).intValue();
        } else {
            int andIncrement = grs.f11926b.getAndIncrement();
            grs.f11929e.put(cls2, Integer.valueOf(andIncrement));
            i = andIncrement;
        }
        Object put = grs.f11928d.put(Integer.valueOf(i), gvc);
        boolean z = false;
        boolean z2 = !(put != null);
        ife.m12869b(gud.mo2735c(), (Object) "Cannot subscribe with a null key");
        ife.m12890c(!(gvc instanceof gvb) || !(gvc instanceof gua));
        Object c = ((gtx) gvu.f12161g).f12048a.mo2735c();
        guz guz = gvu.f12161g;
        long a2 = gvu.f12155a.mo5370a();
        gtx gtx = (gtx) guz;
        if (gtx.f12050c != RecyclerView.FOREVER_NS) {
            z = true;
        }
        ife.m12876b(z, (Object) "You've just overflowed a long. Consider upgrading to a BigDecimal, if this happens more than once.");
        ife.m12898e((Object) gud);
        ife.m12898e((Object) gvc);
        gvu.f12161g = new gtx(gud, gug, gtx.f12050c + 1, 3, gtx.f12051d.mo7105a(gud, a2));
        gve gve = gvu.f12162h;
        Object obj = c;
        gvu gvu4 = gvu;
        gvu4.f12162h = new gtz(gve.mo7069a() + 1, gvc, gve.mo7071c(), gve.mo7072d(), hph.f13219a);
        if (gvu4.f12158d == null) {
            gvu4.f12158d = new gvt(gvu4);
            gvu4.f12165k.mo7098a(gud.mo2735c(), gvu4.f12158d);
        } else if (!gud.mo2735c().equals(obj)) {
            gvu4.f12165k.mo7100b(obj, gvu4.f12158d);
            gvu4.f12165k.mo7098a(gud.mo2735c(), gvu4.f12158d);
        }
        if (z2 && gvu4.f12162h.mo7072d().mo7646a()) {
            ife.m12876b(!gvu4.f12162h.mo7073e().mo7646a(), (Object) "Cannot be the case that subscription has data.");
            gve gve2 = gvu4.f12162h;
            gvu4.f12162h = gvu.m10899a(gve2, (guo) gve2.mo7072d().mo7647b());
            ife.m12876b(gvu4.f12162h.mo7073e().mo7646a(), (Object) "Callbacks did not accept pinned data after rotation.");
            if ((gvu4.f12162h.mo7070b() instanceof gua) && !gvu4.f12163i.mo7095b()) {
                gvu4.f12162h = gvu4.f12162h.mo7110a(true);
                gvu.m10900a((gua) gvu4.f12162h.mo7070b());
                return;
            }
            return;
        }
        gvu4.mo7125b(((gtx) gvu4.f12161g).f12051d);
    }
}
