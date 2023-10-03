package p000;

/* renamed from: hka */
/* compiled from: PG */
public final class hka extends iix implements ikg {

    /* renamed from: d */
    public static final hka f12906d;

    /* renamed from: e */
    private static volatile ikn f12907e;

    /* renamed from: a */
    public int f12908a;

    /* renamed from: b */
    public hjz f12909b;

    /* renamed from: c */
    public int f12910c;

    static {
        hka hka = new hka();
        f12906d = hka;
        iix.m13611a(hka.class, (iix) hka);
    }

    private hka() {
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
            return m13609a((ikf) f12906d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0000\u0002\u0004\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new hka();
        } else {
            if (i2 == 4) {
                return new iir((byte[][]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f12906d;
            }
            ikn ikn = f12907e;
            if (ikn == null) {
                synchronized (hka.class) {
                    ikn = f12907e;
                    if (ikn == null) {
                        ikn = new iis(f12906d);
                        f12907e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
