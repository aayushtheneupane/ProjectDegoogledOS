package p000;

/* renamed from: bja */
/* compiled from: PG */
final class bja implements cfr, ioz {

    /* renamed from: a */
    public volatile Object f2519a = new iok();

    /* renamed from: b */
    public volatile Object f2520b = new iok();

    /* renamed from: c */
    public volatile iqk f2521c;

    /* renamed from: d */
    public volatile Object f2522d = new iok();

    /* renamed from: e */
    public final iqk f2523e;

    /* renamed from: f */
    public final /* synthetic */ bjw f2524f;

    /* renamed from: g */
    private final imq f2525g;

    /* renamed from: h */
    private volatile iqk f2526h;

    /* renamed from: i */
    private volatile iqk f2527i;

    /* renamed from: j */
    private volatile iqk f2528j;

    /* renamed from: k */
    private final ioq f2529k;

    /* renamed from: l */
    private final ioq f2530l;

    /* renamed from: m */
    private final iqk f2531m;

    /* renamed from: n */
    private final ioq f2532n;

    /* renamed from: o */
    private final ioq f2533o;

    /* renamed from: p */
    private final ioq f2534p;

    /* renamed from: q */
    private final ioq f2535q;

    /* renamed from: r */
    private final ioq f2536r;

    /* renamed from: s */
    private final ioq f2537s;

    /* renamed from: t */
    private final ioq f2538t;

    /* renamed from: u */
    private final ioq f2539u;

    /* renamed from: v */
    private final ioq f2540v;

    public /* synthetic */ bja(bjw bjw, hso hso, imq imq) {
        this.f2524f = bjw;
        this.f2525g = imq;
        this.f2523e = ioi.m14221a(this);
        iqk iqk = this.f2528j;
        if (iqk == null) {
            iqk = new biz(this, 3);
            this.f2528j = iqk;
        }
        this.f2530l = ipd.m14276a(iqk);
        ioh a = ioi.m14221a(hso);
        this.f2531m = a;
        this.f2532n = ipd.m14276a((iqk) a);
        this.f2533o = new cga(m2654d(), m2655e(), this.f2524f.f2731ac);
        this.f2534p = new cfv(m2654d(), m2655e(), this.f2530l, this.f2532n, this.f2533o);
        this.f2535q = new cgb(m2654d(), m2655e(), this.f2534p, this.f2524f.f2747as);
        this.f2536r = new cfx(m2654d(), m2655e(), this.f2534p);
        this.f2537s = new cfz(m2654d(), m2655e(), this.f2535q, this.f2524f.f2731ac, this.f2536r);
        this.f2538t = new cfw(m2654d(), m2655e(), this.f2535q, this.f2524f.f2745aq, this.f2536r);
        this.f2539u = new cgc(m2654d(), m2655e(), this.f2535q, this.f2524f.f2731ac, this.f2537s, this.f2538t);
        iqk d = m2654d();
        iqk e = m2655e();
        ioq ioq = this.f2535q;
        ioq ioq2 = this.f2539u;
        bjw bjw2 = this.f2524f;
        cfy cfy = new cfy(d, e, ioq, ioq2, bjw2.f2748at, bjw2.f2746ar);
        this.f2540v = cfy;
        this.f2529k = ipd.m14275a((ioq) cfy, (ioz) this);
    }

    /* renamed from: e */
    private final iqk m2655e() {
        iqk iqk = this.f2527i;
        if (iqk != null) {
            return iqk;
        }
        biz biz = new biz(this, 1);
        this.f2527i = biz;
        return biz;
    }

    /* renamed from: d */
    private final iqk m2654d() {
        iqk iqk = this.f2526h;
        if (iqk != null) {
            return iqk;
        }
        biz biz = new biz(this, 0);
        this.f2526h = biz;
        return biz;
    }

    /* renamed from: b */
    public final imr mo2108b() {
        return inp.m14199a(this.f2525g);
    }

    /* renamed from: a */
    public final inc mo2107a() {
        return inq.m14201a(this.f2525g);
    }

    /* renamed from: a */
    public final void mo2099a(boolean z) {
        ipd.m14277a(this.f2540v, z);
        ipd.m14277a(this.f2539u, z);
        ipd.m14277a(this.f2538t, z);
        ipd.m14277a(this.f2537s, z);
        ipd.m14277a(this.f2536r, z);
        ipd.m14277a(this.f2535q, z);
        ipd.m14277a(this.f2534p, z);
        ipd.m14277a(this.f2533o, z);
        ipd.m14277a(this.f2532n, z);
        ipd.m14277a(this.f2530l, z);
    }

    /* renamed from: c */
    public final ieh mo2109c() {
        return this.f2529k.mo9044b();
    }
}
