package p000;

/* renamed from: cig */
/* compiled from: PG */
public final class cig extends iix implements ikg {

    /* renamed from: d */
    public static final cig f4455d;

    /* renamed from: f */
    private static volatile ikn f4456f;

    /* renamed from: a */
    public int f4457a;

    /* renamed from: b */
    public igj f4458b;

    /* renamed from: c */
    public igo f4459c;

    /* renamed from: e */
    private byte f4460e = 2;

    static {
        cig cig = new cig();
        f4455d = cig;
        iix.m13611a(cig.class, (iix) cig);
    }

    private cig() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f4460e);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f4460e = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f4455d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001\t\u0000\u0002Љ\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new cig();
        } else {
            if (i2 == 4) {
                return new iir((int[][][]) null, (byte[][]) null);
            }
            if (i2 == 5) {
                return f4455d;
            }
            ikn ikn = f4456f;
            if (ikn == null) {
                synchronized (cig.class) {
                    ikn = f4456f;
                    if (ikn == null) {
                        ikn = new iis(f4455d);
                        f4456f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
