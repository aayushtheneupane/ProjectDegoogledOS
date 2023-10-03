package p000;

/* renamed from: iqs */
/* compiled from: PG */
public final class iqs extends iix implements ikg {

    /* renamed from: a */
    public static final iqs f14692a;

    /* renamed from: b */
    private static volatile ikn f14693b;

    static {
        iqs iqs = new iqs();
        f14692a = iqs;
        iix.m13611a(iqs.class, (iix) iqs);
    }

    private iqs() {
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
            return m13609a((ikf) f14692a, "\u0001\u0000", (Object[]) null);
        }
        if (i2 == 3) {
            return new iqs();
        }
        if (i2 == 4) {
            return new iir((boolean[][][]) null, (byte[][]) null);
        }
        if (i2 == 5) {
            return f14692a;
        }
        ikn ikn = f14693b;
        if (ikn == null) {
            synchronized (iqs.class) {
                ikn = f14693b;
                if (ikn == null) {
                    ikn = new iis(f14692a);
                    f14693b = ikn;
                }
            }
        }
        return ikn;
    }
}
