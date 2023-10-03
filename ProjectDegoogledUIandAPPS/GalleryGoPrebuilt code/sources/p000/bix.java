package p000;

import java.util.List;

/* renamed from: bix */
/* compiled from: PG */
final class bix implements czg, ioz {

    /* renamed from: a */
    public volatile Object f2487a = new iok();

    /* renamed from: b */
    public volatile iqk f2488b;

    /* renamed from: c */
    public volatile Object f2489c = new iok();

    /* renamed from: d */
    public final iqk f2490d;

    /* renamed from: e */
    public final /* synthetic */ bjw f2491e;

    /* renamed from: f */
    private final imq f2492f;

    /* renamed from: g */
    private volatile iqk f2493g;

    /* renamed from: h */
    private volatile Object f2494h = new iok();

    /* renamed from: i */
    private volatile iqk f2495i;

    /* renamed from: j */
    private volatile iqk f2496j;

    /* renamed from: k */
    private volatile iqk f2497k;

    /* renamed from: l */
    private final ioq f2498l;

    /* renamed from: m */
    private final iqk f2499m;

    /* renamed from: n */
    private final ioq f2500n;

    /* renamed from: o */
    private final ioq f2501o;

    /* renamed from: p */
    private final ioq f2502p;

    /* renamed from: q */
    private final ioq f2503q;

    /* renamed from: r */
    private final ioq f2504r;

    /* renamed from: s */
    private final ioq f2505s;

    /* renamed from: t */
    private final ioq f2506t;

    /* renamed from: u */
    private final ioq f2507u;

    /* renamed from: v */
    private final ioq f2508v;

    /* renamed from: w */
    private final ioq f2509w;

    /* renamed from: x */
    private final ioq f2510x;

    /* renamed from: y */
    private final ioq f2511y;

    public /* synthetic */ bix(bjw bjw, List list, imq imq) {
        this.f2491e = bjw;
        this.f2492f = imq;
        this.f2490d = ioi.m14221a(this);
        ioh a = ioi.m14221a(list);
        this.f2499m = a;
        this.f2500n = ipd.m14276a((iqk) a);
        iqk iqk = this.f2496j;
        if (iqk == null) {
            iqk = new biw(this, 3);
            this.f2496j = iqk;
        }
        this.f2501o = ipd.m14276a(iqk);
        this.f2502p = new dac(m2632e(), m2633f(), this.f2500n, this.f2491e.f2750av, this.f2501o);
        this.f2503q = new dad(m2632e(), m2633f(), this.f2502p, this.f2491e.f2700Y);
        this.f2504r = new czx(m2632e(), m2633f(), this.f2503q, this.f2491e.f2733ae);
        this.f2505s = new czy(m2632e(), m2633f(), this.f2502p, this.f2491e.f2700Y, this.f2504r);
        this.f2506t = new czz(m2632e(), m2633f(), this.f2505s, this.f2491e.f2731ac);
        this.f2507u = new dab(m2632e(), m2633f(), this.f2502p, this.f2491e.f2733ae, this.f2501o);
        iqk iqk2 = this.f2497k;
        if (iqk2 == null) {
            iqk2 = new biw(this, 4);
            this.f2497k = iqk2;
        }
        this.f2508v = ipd.m14276a(iqk2);
        this.f2509w = new daa(m2632e(), m2633f(), this.f2504r, this.f2507u, this.f2508v, this.f2501o);
        this.f2510x = new czw(m2632e(), m2633f(), this.f2509w, this.f2501o);
        czv czv = new czv(m2632e(), m2633f(), this.f2504r, this.f2505s, this.f2506t, this.f2510x);
        this.f2511y = czv;
        this.f2498l = ipd.m14275a((ioq) czv, (ioz) this);
    }

    /* renamed from: d */
    public final ieh mo2102d() {
        return this.f2498l.mo9044b();
    }

    /* renamed from: c */
    public final long mo2101c() {
        Object obj;
        Object obj2 = this.f2494h;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2494h;
                if (obj instanceof iok) {
                    obj = Long.valueOf(mo2100b().mo9002a());
                    this.f2494h = iog.m14219a(this.f2494h, obj);
                }
            }
            obj2 = obj;
        }
        return ((Long) obj2).longValue();
    }

    /* renamed from: f */
    private final iqk m2633f() {
        iqk iqk = this.f2495i;
        if (iqk != null) {
            return iqk;
        }
        biw biw = new biw(this, 1);
        this.f2495i = biw;
        return biw;
    }

    /* renamed from: e */
    private final iqk m2632e() {
        iqk iqk = this.f2493g;
        if (iqk != null) {
            return iqk;
        }
        biw biw = new biw(this, 0);
        this.f2493g = biw;
        return biw;
    }

    /* renamed from: b */
    public final imr mo2100b() {
        return inp.m14199a(this.f2492f);
    }

    /* renamed from: a */
    public final inc mo2098a() {
        return inq.m14201a(this.f2492f);
    }

    /* renamed from: a */
    public final void mo2099a(boolean z) {
        ipd.m14277a(this.f2511y, z);
        ipd.m14277a(this.f2510x, z);
        ipd.m14277a(this.f2509w, z);
        ipd.m14277a(this.f2508v, z);
        ipd.m14277a(this.f2507u, z);
        ipd.m14277a(this.f2506t, z);
        ipd.m14277a(this.f2505s, z);
        ipd.m14277a(this.f2504r, z);
        ipd.m14277a(this.f2503q, z);
        ipd.m14277a(this.f2502p, z);
        ipd.m14277a(this.f2501o, z);
        ipd.m14277a(this.f2500n, z);
    }
}
