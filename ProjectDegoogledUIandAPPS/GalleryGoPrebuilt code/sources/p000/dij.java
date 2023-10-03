package p000;

/* renamed from: dij */
/* compiled from: PG */
public final class dij extends iix implements ikg {

    /* renamed from: d */
    public static final dij f6602d;

    /* renamed from: e */
    private static volatile ikn f6603e;

    /* renamed from: a */
    public int f6604a;

    /* renamed from: b */
    public String f6605b = "";

    /* renamed from: c */
    public String f6606c = "";

    static {
        dij dij = new dij();
        f6602d = dij;
        iix.m13611a(dij.class, (iix) dij);
    }

    private dij() {
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
            return m13609a((ikf) f6602d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new dij();
        } else {
            if (i2 == 4) {
                return new iir((int[][]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f6602d;
            }
            ikn ikn = f6603e;
            if (ikn == null) {
                synchronized (dij.class) {
                    ikn = f6603e;
                    if (ikn == null) {
                        ikn = new iis(f6602d);
                        f6603e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
