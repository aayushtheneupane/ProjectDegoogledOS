package p000;

/* renamed from: bkd */
/* compiled from: PG */
public final class bkd extends iix implements ikg {

    /* renamed from: c */
    public static final bkd f2990c;

    /* renamed from: d */
    private static volatile ikn f2991d;

    /* renamed from: a */
    public int f2992a;

    /* renamed from: b */
    public int f2993b;

    static {
        bkd bkd = new bkd();
        f2990c = bkd;
        iix.m13611a(bkd.class, (iix) bkd);
    }

    private bkd() {
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
            return m13609a((ikf) f2990c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f\u0000", new Object[]{"a", "b", bkc.f2989a});
        } else if (i2 == 3) {
            return new bkd();
        } else {
            if (i2 == 4) {
                return new iir((int[]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f2990c;
            }
            ikn ikn = f2991d;
            if (ikn == null) {
                synchronized (bkd.class) {
                    ikn = f2991d;
                    if (ikn == null) {
                        ikn = new iis(f2990c);
                        f2991d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
