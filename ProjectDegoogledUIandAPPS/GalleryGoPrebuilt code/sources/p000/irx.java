package p000;

/* renamed from: irx */
/* compiled from: PG */
public final class irx extends iix implements ikg {

    /* renamed from: e */
    public static final irx f14941e;

    /* renamed from: f */
    private static volatile ikn f14942f;

    /* renamed from: a */
    public int f14943a;

    /* renamed from: b */
    public String f14944b = "";

    /* renamed from: c */
    public ijd f14945c = ijs.f14357b;

    /* renamed from: d */
    public long f14946d;

    static {
        irx irx = new irx();
        f14941e = irx;
        iix.m13611a(irx.class, (iix) irx);
    }

    private irx() {
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
            return m13609a((ikf) f14941e, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\b\u0000\u0002\u0002\u0001\u0003(", new Object[]{"a", "b", "d", "c"});
        } else if (i2 == 3) {
            return new irx();
        } else {
            if (i2 == 4) {
                return new iir((short[]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f14941e;
            }
            ikn ikn = f14942f;
            if (ikn == null) {
                synchronized (irx.class) {
                    ikn = f14942f;
                    if (ikn == null) {
                        ikn = new iis(f14941e);
                        f14942f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
