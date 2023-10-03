package p000;

/* renamed from: ffi */
/* compiled from: PG */
public final class ffi extends iix implements ikg {

    /* renamed from: c */
    public static final ffi f9452c;

    /* renamed from: d */
    private static volatile ikn f9453d;

    /* renamed from: a */
    public int f9454a;

    /* renamed from: b */
    public boolean f9455b;

    static {
        ffi ffi = new ffi();
        f9452c = ffi;
        iix.m13611a(ffi.class, (iix) ffi);
    }

    private ffi() {
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
            return m13609a((ikf) f9452c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0007\u0000", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new ffi();
        } else {
            if (i2 == 4) {
                return new iir((short[][][]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f9452c;
            }
            ikn ikn = f9453d;
            if (ikn == null) {
                synchronized (ffi.class) {
                    ikn = f9453d;
                    if (ikn == null) {
                        ikn = new iis(f9452c);
                        f9453d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
