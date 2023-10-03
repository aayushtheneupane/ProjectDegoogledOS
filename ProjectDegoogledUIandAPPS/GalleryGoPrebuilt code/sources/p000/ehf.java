package p000;

/* renamed from: ehf */
/* compiled from: PG */
public final class ehf extends iix implements ikg {

    /* renamed from: d */
    public static final ehf f8283d;

    /* renamed from: e */
    private static volatile ikn f8284e;

    /* renamed from: a */
    public int f8285a;

    /* renamed from: b */
    public long f8286b;

    /* renamed from: c */
    public long f8287c;

    static {
        ehf ehf = new ehf();
        f8283d = ehf;
        iix.m13611a(ehf.class, (iix) ehf);
    }

    private ehf() {
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
            return m13609a((ikf) f8283d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0000\u0002\u0002\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new ehf();
        } else {
            if (i2 == 4) {
                return new iir((byte[][]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f8283d;
            }
            ikn ikn = f8284e;
            if (ikn == null) {
                synchronized (ehf.class) {
                    ikn = f8284e;
                    if (ikn == null) {
                        ikn = new iis(f8283d);
                        f8284e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
