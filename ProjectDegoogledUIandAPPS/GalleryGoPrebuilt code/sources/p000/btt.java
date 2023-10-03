package p000;

/* renamed from: btt */
/* compiled from: PG */
public final class btt extends iix implements ikg {

    /* renamed from: g */
    public static final btt f3568g;

    /* renamed from: h */
    private static volatile ikn f3569h;

    /* renamed from: a */
    public int f3570a;

    /* renamed from: b */
    public cxe f3571b;

    /* renamed from: c */
    public cqe f3572c;

    /* renamed from: d */
    public cxd f3573d;

    /* renamed from: e */
    public boolean f3574e = true;

    /* renamed from: f */
    public int f3575f = 2;

    static {
        btt btt = new btt();
        f3568g = btt;
        iix.m13611a(btt.class, (iix) btt);
    }

    private btt() {
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
            return m13609a((ikf) f3568g, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002\u0004\u0007\u0003\u0005\f\u0004", new Object[]{"a", "b", "c", "d", "e", "f", cpi.m5218a()});
        } else if (i2 == 3) {
            return new btt();
        } else {
            if (i2 == 4) {
                return new iir((short[][][]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f3568g;
            }
            ikn ikn = f3569h;
            if (ikn == null) {
                synchronized (btt.class) {
                    ikn = f3569h;
                    if (ikn == null) {
                        ikn = new iis(f3568g);
                        f3569h = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
