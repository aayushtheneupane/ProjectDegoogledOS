package p000;

import java.util.List;

/* renamed from: bju */
/* compiled from: PG */
final class bju implements ddb, ioz {

    /* renamed from: a */
    public volatile Object f2654a = new iok();

    /* renamed from: b */
    public volatile iqk f2655b;

    /* renamed from: c */
    public volatile Object f2656c = new iok();

    /* renamed from: d */
    public final iqk f2657d;

    /* renamed from: e */
    public final /* synthetic */ bjw f2658e;

    /* renamed from: f */
    private final imq f2659f;

    /* renamed from: g */
    private volatile iqk f2660g;

    /* renamed from: h */
    private volatile Object f2661h = new iok();

    /* renamed from: i */
    private volatile iqk f2662i;

    /* renamed from: j */
    private volatile iqk f2663j;

    /* renamed from: k */
    private volatile iqk f2664k;

    /* renamed from: l */
    private final ioq f2665l;

    /* renamed from: m */
    private final ioq f2666m;

    /* renamed from: n */
    private final iqk f2667n;

    /* renamed from: o */
    private final ioq f2668o;

    /* renamed from: p */
    private final ioq f2669p;

    /* renamed from: q */
    private final ioq f2670q;

    /* renamed from: r */
    private final iqk f2671r;

    /* renamed from: s */
    private final ioq f2672s;

    /* renamed from: t */
    private final ioq f2673t;

    public /* synthetic */ bju(bjw bjw, List list, List list2, imq imq) {
        this.f2658e = bjw;
        this.f2659f = imq;
        this.f2657d = ioi.m14221a(this);
        iqk iqk = this.f2663j;
        if (iqk == null) {
            iqk = new bjt(this, 3);
            this.f2663j = iqk;
        }
        this.f2666m = ipd.m14276a(iqk);
        ioh a = ioi.m14221a(list);
        this.f2667n = a;
        this.f2668o = ipd.m14276a((iqk) a);
        iqk iqk2 = this.f2664k;
        if (iqk2 == null) {
            iqk2 = new bjt(this, 4);
            this.f2664k = iqk2;
        }
        this.f2669p = ipd.m14276a(iqk2);
        this.f2670q = new ddg(m2709e(), m2710f(), this.f2668o, this.f2658e.f2733ae, this.f2669p);
        ioh a2 = ioi.m14221a(list2);
        this.f2671r = a2;
        this.f2672s = ipd.m14276a((iqk) a2);
        iqk e = m2709e();
        iqk f = m2710f();
        bjw bjw2 = this.f2658e;
        ddh ddh = new ddh(e, f, bjw2.f2701Z, this.f2666m, bjw2.f2749au, this.f2670q, this.f2672s, this.f2669p);
        this.f2673t = ddh;
        this.f2665l = ipd.m14275a((ioq) ddh, (ioz) this);
    }

    /* renamed from: c */
    public final long mo2148c() {
        Object obj;
        Object obj2 = this.f2661h;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2661h;
                if (obj instanceof iok) {
                    obj = Long.valueOf(mo2147b().mo9002a());
                    this.f2661h = iog.m14219a(this.f2661h, obj);
                }
            }
            obj2 = obj;
        }
        return ((Long) obj2).longValue();
    }

    /* renamed from: f */
    private final iqk m2710f() {
        iqk iqk = this.f2662i;
        if (iqk != null) {
            return iqk;
        }
        bjt bjt = new bjt(this, 1);
        this.f2662i = bjt;
        return bjt;
    }

    /* renamed from: e */
    private final iqk m2709e() {
        iqk iqk = this.f2660g;
        if (iqk != null) {
            return iqk;
        }
        bjt bjt = new bjt(this, 0);
        this.f2660g = bjt;
        return bjt;
    }

    /* renamed from: d */
    public final ieh mo2149d() {
        return this.f2665l.mo9044b();
    }

    /* renamed from: b */
    public final imr mo2147b() {
        return inp.m14199a(this.f2659f);
    }

    /* renamed from: a */
    public final inc mo2146a() {
        return inq.m14201a(this.f2659f);
    }

    /* renamed from: a */
    public final void mo2099a(boolean z) {
        ipd.m14277a(this.f2673t, z);
        ipd.m14277a(this.f2672s, z);
        ipd.m14277a(this.f2670q, z);
        ipd.m14277a(this.f2669p, z);
        ipd.m14277a(this.f2668o, z);
        ipd.m14277a(this.f2666m, z);
    }
}
