package p000;

/* renamed from: dik */
/* compiled from: PG */
public final class dik extends iix implements ikg {

    /* renamed from: j */
    public static final dik f6607j;

    /* renamed from: k */
    private static volatile ikn f6608k;

    /* renamed from: a */
    public int f6609a;

    /* renamed from: b */
    public String f6610b = "";

    /* renamed from: c */
    public String f6611c = "";

    /* renamed from: d */
    public String f6612d = "";

    /* renamed from: e */
    public String f6613e = "";

    /* renamed from: f */
    public int f6614f;

    /* renamed from: g */
    public dii f6615g;

    /* renamed from: h */
    public dij f6616h;

    /* renamed from: i */
    public String f6617i = "";

    static {
        dik dik = new dik();
        f6607j = dik;
        iix.m13611a(dik.class, (iix) dik);
    }

    private dik() {
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
            return m13609a((ikf) f6607j, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\u0004\u0004\u0006\t\u0005\u0007\t\u0006\b\b\u0007", new Object[]{"a", "b", "c", "d", "e", "f", "g", "h", "i"});
        } else if (i2 == 3) {
            return new dik();
        } else {
            if (i2 == 4) {
                return new iir((char[]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f6607j;
            }
            ikn ikn = f6608k;
            if (ikn == null) {
                synchronized (dik.class) {
                    ikn = f6608k;
                    if (ikn == null) {
                        ikn = new iis(f6607j);
                        f6608k = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
