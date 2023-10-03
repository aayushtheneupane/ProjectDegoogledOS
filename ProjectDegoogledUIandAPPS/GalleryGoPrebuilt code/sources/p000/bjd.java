package p000;

/* renamed from: bjd */
/* compiled from: PG */
final class bjd implements dev, ioz {

    /* renamed from: a */
    public volatile Object f2545a = new iok();

    /* renamed from: b */
    public volatile iqk f2546b;

    /* renamed from: c */
    public volatile Object f2547c = new iok();

    /* renamed from: d */
    public final iqk f2548d;

    /* renamed from: e */
    public final /* synthetic */ bjw f2549e;

    /* renamed from: f */
    private final imq f2550f;

    /* renamed from: g */
    private volatile iqk f2551g;

    /* renamed from: h */
    private volatile Object f2552h = new iok();

    /* renamed from: i */
    private volatile iqk f2553i;

    /* renamed from: j */
    private volatile iqk f2554j;

    /* renamed from: k */
    private final ioq f2555k;

    /* renamed from: l */
    private final ioq f2556l;

    /* renamed from: m */
    private final ioq f2557m;

    /* renamed from: n */
    private final ioq f2558n;

    /* renamed from: o */
    private final ioq f2559o;

    /* renamed from: p */
    private final ioq f2560p;

    /* renamed from: q */
    private final ioq f2561q;

    /* renamed from: r */
    private final ioq f2562r;

    /* renamed from: s */
    private final ioq f2563s;

    /* renamed from: t */
    private final ioq f2564t;

    /* renamed from: u */
    private final ioq f2565u;

    /* renamed from: v */
    private final ioq f2566v;

    /* renamed from: w */
    private final ioq f2567w;

    /* renamed from: x */
    private final ioq f2568x;

    /* renamed from: y */
    private final ioq f2569y;

    public /* synthetic */ bjd(bjw bjw, imq imq) {
        this.f2549e = bjw;
        this.f2550f = imq;
        this.f2548d = ioi.m14221a(this);
        iqk iqk = this.f2554j;
        if (iqk == null) {
            iqk = new bjc(this, 3);
            this.f2554j = iqk;
        }
        this.f2556l = ipd.m14276a(iqk);
        this.f2557m = new dfn(m2663e(), m2664f(), this.f2549e.f2701Z, this.f2556l);
        this.f2558n = new dfs(m2663e(), m2664f(), this.f2549e.f2730ab);
        iqk e = m2663e();
        iqk f = m2664f();
        ioq ioq = this.f2557m;
        bjw bjw2 = this.f2549e;
        this.f2559o = new dft(e, f, ioq, bjw2.f2729aa, this.f2558n, bjw2.f2700Y, bjw2.f2731ac, bjw2.f2732ad);
        this.f2560p = new dfm(m2663e(), m2664f(), this.f2559o, this.f2549e.f2700Y);
        iqk e2 = m2663e();
        iqk f2 = m2664f();
        bjw bjw3 = this.f2549e;
        this.f2561q = new dfo(e2, f2, bjw3.f2733ae, this.f2556l, bjw3.f2734af);
        this.f2562r = new dfp(m2663e(), m2664f(), this.f2549e.f2735ag);
        this.f2563s = new dfr(m2663e(), m2664f(), this.f2562r);
        this.f2564t = new dfq(m2663e(), m2664f(), this.f2560p, this.f2561q, this.f2563s);
        iqk e3 = m2663e();
        iqk f3 = m2664f();
        bjw bjw4 = this.f2549e;
        ioq ioq2 = bjw4.f2700Y;
        ioq ioq3 = this.f2564t;
        iqk iqk2 = bjw4.f2960t;
        if (iqk2 == null) {
            iqk2 = new bjv(bjw4, 89);
            bjw4.f2960t = iqk2;
        }
        this.f2565u = new dfv(e3, f3, ioq2, ioq3, iqk2, this.f2556l, this.f2549e.f2736ah);
        this.f2566v = new dfw(m2663e(), m2664f(), this.f2549e.f2700Y, this.f2564t);
        this.f2567w = new dfu(m2663e(), m2664f(), this.f2549e.f2700Y, this.f2564t);
        this.f2568x = new dfk(m2663e(), m2664f(), this.f2566v, this.f2567w, this.f2549e.f2731ac);
        dfl dfl = new dfl(m2663e(), m2664f(), this.f2565u, this.f2566v, this.f2567w, this.f2568x);
        this.f2569y = dfl;
        this.f2555k = ipd.m14275a((ioq) dfl, (ioz) this);
    }

    /* renamed from: c */
    public final long mo2114c() {
        Object obj;
        Object obj2 = this.f2552h;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2552h;
                if (obj instanceof iok) {
                    obj = Long.valueOf(mo2113b().mo9002a());
                    this.f2552h = iog.m14219a(this.f2552h, obj);
                }
            }
            obj2 = obj;
        }
        return ((Long) obj2).longValue();
    }

    /* renamed from: f */
    private final iqk m2664f() {
        iqk iqk = this.f2553i;
        if (iqk != null) {
            return iqk;
        }
        bjc bjc = new bjc(this, 1);
        this.f2553i = bjc;
        return bjc;
    }

    /* renamed from: e */
    private final iqk m2663e() {
        iqk iqk = this.f2551g;
        if (iqk != null) {
            return iqk;
        }
        bjc bjc = new bjc(this, 0);
        this.f2551g = bjc;
        return bjc;
    }

    /* renamed from: b */
    public final imr mo2113b() {
        return inp.m14199a(this.f2550f);
    }

    /* renamed from: a */
    public final inc mo2112a() {
        return inq.m14201a(this.f2550f);
    }

    /* renamed from: a */
    public final void mo2099a(boolean z) {
        ipd.m14277a(this.f2569y, z);
        ipd.m14277a(this.f2568x, z);
        ipd.m14277a(this.f2567w, z);
        ipd.m14277a(this.f2566v, z);
        ipd.m14277a(this.f2565u, z);
        ipd.m14277a(this.f2564t, z);
        ipd.m14277a(this.f2563s, z);
        ipd.m14277a(this.f2562r, z);
        ipd.m14277a(this.f2561q, z);
        ipd.m14277a(this.f2560p, z);
        ipd.m14277a(this.f2559o, z);
        ipd.m14277a(this.f2558n, z);
        ipd.m14277a(this.f2557m, z);
        ipd.m14277a(this.f2556l, z);
    }

    /* renamed from: d */
    public final ieh mo2115d() {
        return this.f2555k.mo9044b();
    }
}
