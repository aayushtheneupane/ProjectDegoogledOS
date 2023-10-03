package p000;

/* renamed from: hkq */
/* compiled from: PG */
public final class hkq extends iix implements ikg {

    /* renamed from: d */
    public static final hkq f12942d;

    /* renamed from: e */
    private static volatile ikn f12943e;

    /* renamed from: a */
    public int f12944a;

    /* renamed from: b */
    public hkp f12945b;

    /* renamed from: c */
    public hko f12946c;

    static {
        hkq hkq = new hkq();
        f12942d = hkq;
        iix.m13611a(hkq.class, (iix) hkq);
    }

    private hkq() {
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
            return m13609a((ikf) f12942d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new hkq();
        } else {
            if (i2 == 4) {
                return new iir((boolean[][]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f12942d;
            }
            ikn ikn = f12943e;
            if (ikn == null) {
                synchronized (hkq.class) {
                    ikn = f12943e;
                    if (ikn == null) {
                        ikn = new iis(f12942d);
                        f12943e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
