package p000;

/* renamed from: glz */
/* compiled from: PG */
public final class glz extends iix implements ikg {

    /* renamed from: c */
    public static final glz f11602c;

    /* renamed from: d */
    private static volatile ikn f11603d;

    /* renamed from: a */
    public int f11604a;

    /* renamed from: b */
    public long f11605b;

    static {
        glz glz = new glz();
        f11602c = glz;
        iix.m13611a(glz.class, (iix) glz);
    }

    private glz() {
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
            return m13609a((ikf) f11602c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0002\u0000", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new glz();
        } else {
            if (i2 == 4) {
                return new iir((byte[]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f11602c;
            }
            ikn ikn = f11603d;
            if (ikn == null) {
                synchronized (glz.class) {
                    ikn = f11603d;
                    if (ikn == null) {
                        ikn = new iis(f11602c);
                        f11603d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
