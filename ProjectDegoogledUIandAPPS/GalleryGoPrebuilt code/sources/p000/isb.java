package p000;

/* renamed from: isb */
/* compiled from: PG */
public final class isb extends iix implements ikg {

    /* renamed from: d */
    public static final isb f14969d;

    /* renamed from: e */
    private static volatile ikn f14970e;

    /* renamed from: a */
    public int f14971a;

    /* renamed from: b */
    public int f14972b;

    /* renamed from: c */
    public int f14973c = 1;

    static {
        isb isb = new isb();
        f14969d = isb;
        iix.m13611a(isb.class, (iix) isb);
    }

    private isb() {
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
            return m13609a((ikf) f14969d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\u0004\u0001", new Object[]{"a", "b", isa.m14375a(), "c"});
        } else if (i2 == 3) {
            return new isb();
        } else {
            if (i2 == 4) {
                return new iir((char[]) null);
            }
            if (i2 == 5) {
                return f14969d;
            }
            ikn ikn = f14970e;
            if (ikn == null) {
                synchronized (isb.class) {
                    ikn = f14970e;
                    if (ikn == null) {
                        ikn = new iis(f14969d);
                        f14970e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
