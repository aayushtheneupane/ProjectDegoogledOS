package p000;

/* renamed from: hkp */
/* compiled from: PG */
public final class hkp extends iix implements ikg {

    /* renamed from: c */
    public static final hkp f12938c;

    /* renamed from: d */
    private static volatile ikn f12939d;

    /* renamed from: a */
    public int f12940a;

    /* renamed from: b */
    public int f12941b;

    static {
        hkp hkp = new hkp();
        f12938c = hkp;
        iix.m13611a(hkp.class, (iix) hkp);
    }

    private hkp() {
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
            return m13609a((ikf) f12938c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0004\u0000", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new hkp();
        } else {
            if (i2 == 4) {
                return new iir((short[]) null, (byte[][]) null);
            }
            if (i2 == 5) {
                return f12938c;
            }
            ikn ikn = f12939d;
            if (ikn == null) {
                synchronized (hkp.class) {
                    ikn = f12939d;
                    if (ikn == null) {
                        ikn = new iis(f12938c);
                        f12939d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
