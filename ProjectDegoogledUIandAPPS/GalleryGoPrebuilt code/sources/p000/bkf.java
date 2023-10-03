package p000;

/* renamed from: bkf */
/* compiled from: PG */
public final class bkf extends iix implements ikg {

    /* renamed from: c */
    public static final bkf f2995c;

    /* renamed from: d */
    private static volatile ikn f2996d;

    /* renamed from: a */
    public int f2997a;

    /* renamed from: b */
    public int f2998b;

    static {
        bkf bkf = new bkf();
        f2995c = bkf;
        iix.m13611a(bkf.class, (iix) bkf);
    }

    private bkf() {
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
            return m13609a((ikf) f2995c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f\u0000", new Object[]{"a", "b", bke.f2994a});
        } else if (i2 == 3) {
            return new bkf();
        } else {
            if (i2 == 4) {
                return new iir((byte[][][]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f2995c;
            }
            ikn ikn = f2996d;
            if (ikn == null) {
                synchronized (bkf.class) {
                    ikn = f2996d;
                    if (ikn == null) {
                        ikn = new iis(f2995c);
                        f2996d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
