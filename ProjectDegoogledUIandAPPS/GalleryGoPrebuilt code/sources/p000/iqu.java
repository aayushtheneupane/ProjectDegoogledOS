package p000;

/* renamed from: iqu */
/* compiled from: PG */
public final class iqu extends iix implements ikg {

    /* renamed from: e */
    public static final iqu f14704e;

    /* renamed from: f */
    private static volatile ikn f14705f;

    /* renamed from: a */
    public int f14706a;

    /* renamed from: b */
    public int f14707b;

    /* renamed from: c */
    public int f14708c;

    /* renamed from: d */
    public iqq f14709d;

    static {
        iqu iqu = new iqu();
        f14704e = iqu;
        iix.m13611a(iqu.class, (iix) iqu);
    }

    private iqu() {
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
            return m13609a((ikf) f14704e, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001\u0003\t\u0002", new Object[]{"a", "b", "c", "d"});
        } else if (i2 == 3) {
            return new iqu();
        } else {
            if (i2 == 4) {
                return new iir((short[]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f14704e;
            }
            ikn ikn = f14705f;
            if (ikn == null) {
                synchronized (iqu.class) {
                    ikn = f14705f;
                    if (ikn == null) {
                        ikn = new iis(f14704e);
                        f14705f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
