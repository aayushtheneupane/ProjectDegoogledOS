package p000;

/* renamed from: gcb */
/* compiled from: PG */
public final class gcb extends iix implements ikg {

    /* renamed from: d */
    public static final gcb f10906d;

    /* renamed from: e */
    private static volatile ikn f10907e;

    /* renamed from: a */
    public int f10908a;

    /* renamed from: b */
    public int f10909b;

    /* renamed from: c */
    public float f10910c;

    static {
        gcb gcb = new gcb();
        f10906d = gcb;
        iix.m13611a(gcb.class, (iix) gcb);
    }

    private gcb() {
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
            return m13609a((ikf) f10906d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0001\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new gcb();
        } else {
            if (i2 == 4) {
                return new iir((short[]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f10906d;
            }
            ikn ikn = f10907e;
            if (ikn == null) {
                synchronized (gcb.class) {
                    ikn = f10907e;
                    if (ikn == null) {
                        ikn = new iis(f10906d);
                        f10907e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
