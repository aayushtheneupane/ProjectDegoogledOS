package p000;

/* renamed from: brn */
/* compiled from: PG */
public final class brn extends iix implements ikg {

    /* renamed from: h */
    public static final brn f3429h;

    /* renamed from: i */
    private static volatile ikn f3430i;

    /* renamed from: a */
    public int f3431a;

    /* renamed from: b */
    public int f3432b;

    /* renamed from: c */
    public cqe f3433c;

    /* renamed from: d */
    public cxd f3434d;

    /* renamed from: e */
    public boolean f3435e = true;

    /* renamed from: f */
    public boolean f3436f;

    /* renamed from: g */
    public int f3437g = 2;

    static {
        brn brn = new brn();
        f3429h = brn;
        iix.m13611a(brn.class, (iix) brn);
    }

    private brn() {
    }

    /* renamed from: a */
    public static /* synthetic */ void m3488a(brn brn) {
        brn.f3431a |= 8;
        brn.f3435e = false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 1) {
            return null;
        }
        if (i2 == 2) {
            return m13609a((ikf) f3429h, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001\u0003\t\u0002\u0004\u0007\u0003\u0005\f\u0005\u0006\u0007\u0004", new Object[]{"a", "b", brm.m3487a(), "c", "d", "e", "g", cpi.m5218a(), "f"});
        } else if (i2 == 3) {
            return new brn();
        } else {
            if (i2 == 4) {
                return new iir((short[]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f3429h;
            }
            ikn ikn = f3430i;
            if (ikn == null) {
                synchronized (brn.class) {
                    ikn = f3430i;
                    if (ikn == null) {
                        ikn = new iis(f3429h);
                        f3430i = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
