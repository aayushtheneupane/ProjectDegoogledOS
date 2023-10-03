package p000;

/* renamed from: iqz */
/* compiled from: PG */
public final class iqz extends iix implements ikg {

    /* renamed from: c */
    public static final iqz f14808c;

    /* renamed from: d */
    private static volatile ikn f14809d;

    /* renamed from: a */
    public int f14810a;

    /* renamed from: b */
    public boolean f14811b;

    static {
        iqz iqz = new iqz();
        f14808c = iqz;
        iix.m13611a(iqz.class, (iix) iqz);
    }

    private iqz() {
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
            return m13609a((ikf) f14808c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0007\u0000", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new iqz();
        } else {
            if (i2 == 4) {
                return new iir((boolean[][]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f14808c;
            }
            ikn ikn = f14809d;
            if (ikn == null) {
                synchronized (iqz.class) {
                    ikn = f14809d;
                    if (ikn == null) {
                        ikn = new iis(f14808c);
                        f14809d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
