package p000;

/* renamed from: dhu */
/* compiled from: PG */
public final class dhu extends iix implements ikg {

    /* renamed from: g */
    public static final dhu f6565g;

    /* renamed from: h */
    private static volatile ikn f6566h;

    /* renamed from: a */
    public int f6567a;

    /* renamed from: b */
    public long f6568b;

    /* renamed from: c */
    public int f6569c;

    /* renamed from: d */
    public int f6570d;

    /* renamed from: e */
    public String f6571e = "";

    /* renamed from: f */
    public String f6572f = "";

    static {
        dhu dhu = new dhu();
        f6565g = dhu;
        iix.m13611a(dhu.class, (iix) dhu);
    }

    private dhu() {
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
            return m13609a((ikf) f6565g, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\u0002\u0000\u0002\u0004\u0001\u0003\u0004\u0002\u0004\b\u0003\u0005\b\u0004", new Object[]{"a", "b", "c", "d", "e", "f"});
        } else if (i2 == 3) {
            return new dhu();
        } else {
            if (i2 == 4) {
                return new iir((short[][][]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f6565g;
            }
            ikn ikn = f6566h;
            if (ikn == null) {
                synchronized (dhu.class) {
                    ikn = f6566h;
                    if (ikn == null) {
                        ikn = new iis(f6565g);
                        f6566h = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
