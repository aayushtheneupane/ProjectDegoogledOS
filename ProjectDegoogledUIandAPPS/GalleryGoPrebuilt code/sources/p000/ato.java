package p000;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: ato */
/* compiled from: PG */
public final class ato implements ass, bfv {

    /* renamed from: a */
    public final atn f1630a = new atn();

    /* renamed from: b */
    public final bfy f1631b = bfy.m2451a();

    /* renamed from: c */
    public final atr f1632c;

    /* renamed from: d */
    public final atp f1633d;

    /* renamed from: e */
    public aqu f1634e;

    /* renamed from: f */
    public boolean f1635f;

    /* renamed from: g */
    public boolean f1636g;

    /* renamed from: h */
    public aua f1637h;

    /* renamed from: i */
    public boolean f1638i;

    /* renamed from: j */
    public atu f1639j;

    /* renamed from: k */
    public boolean f1640k;

    /* renamed from: l */
    public ats f1641l;

    /* renamed from: m */
    public volatile boolean f1642m;

    /* renamed from: n */
    public int f1643n;

    /* renamed from: o */
    private final C0306lc f1644o;

    /* renamed from: p */
    private final avz f1645p;

    /* renamed from: q */
    private final avz f1646q;

    /* renamed from: r */
    private final avz f1647r;

    /* renamed from: s */
    private final AtomicInteger f1648s = new AtomicInteger();

    /* renamed from: t */
    private boolean f1649t;

    /* renamed from: u */
    private asx f1650u;

    public ato(avz avz, avz avz2, avz avz3, atp atp, atr atr, C0306lc lcVar) {
        this.f1645p = avz;
        this.f1646q = avz2;
        this.f1647r = avz3;
        this.f1633d = atp;
        this.f1632c = atr;
        this.f1644o = lcVar;
    }

    /* renamed from: d */
    private final avz m1629d() {
        return this.f1649t ? this.f1647r : this.f1646q;
    }

    /* renamed from: e */
    private final boolean m1630e() {
        return this.f1640k || this.f1638i || this.f1642m;
    }

    /* renamed from: af */
    public final bfy mo1573af() {
        return this.f1631b;
    }

    /* renamed from: a */
    public final synchronized void mo1600a(bef bef, Executor executor) {
        this.f1631b.mo1973b();
        this.f1630a.f1629a.add(new atm(bef, executor));
        if (this.f1638i) {
            mo1597a(1);
            executor.execute(new atl(this, bef));
        } else if (this.f1640k) {
            mo1597a(1);
            executor.execute(new atk(this, bef));
        } else {
            cns.m4637a(!this.f1642m, "Cannot add callbacks to a cancelled EngineJob");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo1596a() {
        boolean z;
        ats ats;
        synchronized (this) {
            this.f1631b.mo1973b();
            cns.m4637a(m1630e(), "Not yet complete!");
            int decrementAndGet = this.f1648s.decrementAndGet();
            if (decrementAndGet >= 0) {
                z = true;
            } else {
                z = false;
            }
            cns.m4637a(z, "Can't decrement below 0");
            if (decrementAndGet == 0) {
                ats = this.f1641l;
                mo1602c();
            } else {
                ats = null;
            }
        }
        if (ats != null) {
            ats.mo1609f();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void mo1597a(int i) {
        ats ats;
        cns.m4637a(m1630e(), "Not yet complete!");
        if (this.f1648s.getAndAdd(i) == 0 && (ats = this.f1641l) != null) {
            ats.mo1608e();
        }
    }

    /* renamed from: a */
    public final synchronized void mo1598a(aqu aqu, boolean z, boolean z2, boolean z3) {
        this.f1634e = aqu;
        this.f1635f = z;
        this.f1649t = z2;
        this.f1636g = z3;
    }

    /* renamed from: c */
    public final synchronized void mo1602c() {
        if (this.f1634e != null) {
            this.f1630a.f1629a.clear();
            this.f1634e = null;
            this.f1641l = null;
            this.f1637h = null;
            this.f1640k = false;
            this.f1642m = false;
            this.f1638i = false;
            asx asx = this.f1650u;
            if (asx.f1568c.mo1570d()) {
                asx.mo1572a();
            }
            this.f1650u = null;
            this.f1639j = null;
            this.f1643n = 0;
            this.f1644o.mo1972a(this);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /* renamed from: a */
    public final synchronized void mo1599a(bef bef) {
        this.f1631b.mo1973b();
        this.f1630a.f1629a.remove(atn.m1624b(bef));
        if (this.f1630a.mo1591a()) {
            if (!m1630e()) {
                this.f1642m = true;
                asx asx = this.f1650u;
                asx.f1581p = true;
                asq asq = asx.f1580o;
                if (asq != null) {
                    asq.mo1551b();
                }
                this.f1633d.mo1585a(this, this.f1634e);
            }
            if (!this.f1638i) {
                if (!this.f1640k) {
                }
            }
            if (this.f1648s.get() == 0) {
                mo1602c();
            }
        }
    }

    /* renamed from: a */
    public final void mo1564a(asx asx) {
        m1629d().execute(asx);
    }

    /* renamed from: b */
    public final synchronized void mo1601b(asx asx) {
        avz avz;
        this.f1650u = asx;
        int a = asx.mo1571a(1);
        if (a != 2) {
            if (a != 3) {
                avz = m1629d();
                avz.execute(asx);
            }
        }
        avz = this.f1645p;
        avz.execute(asx);
    }
}
