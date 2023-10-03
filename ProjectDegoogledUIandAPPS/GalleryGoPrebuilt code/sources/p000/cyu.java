package p000;

/* renamed from: cyu */
/* compiled from: PG */
public final class cyu extends iix implements ikg {

    /* renamed from: c */
    public static final cyu f6048c;

    /* renamed from: d */
    private static volatile ikn f6049d;

    /* renamed from: a */
    public int f6050a;

    /* renamed from: b */
    public String f6051b = "";

    static {
        cyu cyu = new cyu();
        f6048c = cyu;
        iix.m13611a(cyu.class, (iix) cyu);
    }

    private cyu() {
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
            return m13609a((ikf) f6048c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\b\u0000", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new cyu();
        } else {
            if (i2 == 4) {
                return new iir((float[][][]) null, (byte[][]) null);
            }
            if (i2 == 5) {
                return f6048c;
            }
            ikn ikn = f6049d;
            if (ikn == null) {
                synchronized (cyu.class) {
                    ikn = f6049d;
                    if (ikn == null) {
                        ikn = new iis(f6048c);
                        f6049d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
