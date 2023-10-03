package p000;

/* renamed from: dii */
/* compiled from: PG */
public final class dii extends iix implements ikg {

    /* renamed from: d */
    public static final dii f6597d;

    /* renamed from: e */
    private static volatile ikn f6598e;

    /* renamed from: a */
    public int f6599a;

    /* renamed from: b */
    public String f6600b = "";

    /* renamed from: c */
    public String f6601c = "";

    static {
        dii dii = new dii();
        f6597d = dii;
        iix.m13611a(dii.class, (iix) dii);
    }

    private dii() {
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
            return m13609a((ikf) f6597d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new dii();
        } else {
            if (i2 == 4) {
                return new iir((int[]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f6597d;
            }
            ikn ikn = f6598e;
            if (ikn == null) {
                synchronized (dii.class) {
                    ikn = f6598e;
                    if (ikn == null) {
                        ikn = new iis(f6597d);
                        f6598e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
