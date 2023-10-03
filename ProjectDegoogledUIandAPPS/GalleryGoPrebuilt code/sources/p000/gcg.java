package p000;

/* renamed from: gcg */
/* compiled from: PG */
public final class gcg extends iix implements ikg {

    /* renamed from: d */
    public static final gcg f10924d;

    /* renamed from: e */
    private static volatile ikn f10925e;

    /* renamed from: a */
    public int f10926a;

    /* renamed from: b */
    public gcf f10927b;

    /* renamed from: c */
    public gcf f10928c;

    static {
        gcg gcg = new gcg();
        f10924d = gcg;
        iix.m13611a(gcg.class, (iix) gcg);
    }

    private gcg() {
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
            return m13609a((ikf) f10924d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new gcg();
        } else {
            if (i2 == 4) {
                return new iir((char[][][]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f10924d;
            }
            ikn ikn = f10925e;
            if (ikn == null) {
                synchronized (gcg.class) {
                    ikn = f10925e;
                    if (ikn == null) {
                        ikn = new iis(f10924d);
                        f10925e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
