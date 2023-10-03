package p000;

/* renamed from: ify */
/* compiled from: PG */
public final class ify extends iix implements ikg {

    /* renamed from: e */
    public static final ify f14031e;

    /* renamed from: f */
    private static volatile ikn f14032f;

    /* renamed from: a */
    public int f14033a;

    /* renamed from: b */
    public int f14034b;

    /* renamed from: c */
    public String f14035c = "";

    /* renamed from: d */
    public String f14036d = "";

    static {
        ify ify = new ify();
        f14031e = ify;
        iix.m13611a(ify.class, (iix) ify);
    }

    private ify() {
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
            return m13609a((ikf) f14031e, "\u0001\u0003\u0000\u0001\u0002\u0005\u0003\u0000\u0000\u0000\u0002\u0004\u0000\u0004\b\u0001\u0005\b\u0002", new Object[]{"a", "b", "c", "d"});
        } else if (i2 == 3) {
            return new ify();
        } else {
            if (i2 == 4) {
                return new iir((byte[][][]) null, (byte[][]) null);
            }
            if (i2 == 5) {
                return f14031e;
            }
            ikn ikn = f14032f;
            if (ikn == null) {
                synchronized (ify.class) {
                    ikn = f14032f;
                    if (ikn == null) {
                        ikn = new iis(f14031e);
                        f14032f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
