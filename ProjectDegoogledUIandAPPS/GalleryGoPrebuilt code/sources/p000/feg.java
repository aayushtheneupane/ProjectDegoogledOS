package p000;

/* renamed from: feg */
/* compiled from: PG */
public final class feg extends iix implements ikg {

    /* renamed from: c */
    public static final feg f9366c;

    /* renamed from: d */
    private static volatile ikn f9367d;

    /* renamed from: a */
    public int f9368a;

    /* renamed from: b */
    public String f9369b = "";

    static {
        feg feg = new feg();
        f9366c = feg;
        iix.m13611a(feg.class, (iix) feg);
    }

    private feg() {
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
            return m13609a((ikf) f9366c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\b\u0000", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new feg();
        } else {
            if (i2 == 4) {
                return new iir((char[][]) null);
            }
            if (i2 == 5) {
                return f9366c;
            }
            ikn ikn = f9367d;
            if (ikn == null) {
                synchronized (feg.class) {
                    ikn = f9367d;
                    if (ikn == null) {
                        ikn = new iis(f9366c);
                        f9367d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
