package p000;

/* renamed from: clb */
/* compiled from: PG */
public final class clb extends iix implements ikg {

    /* renamed from: d */
    public static final clb f4598d;

    /* renamed from: e */
    private static volatile ikn f4599e;

    /* renamed from: a */
    public int f4600a;

    /* renamed from: b */
    public boolean f4601b;

    /* renamed from: c */
    public ije f4602c = ikq.f14400b;

    static {
        clb clb = new clb();
        f4598d = clb;
        iix.m13611a(clb.class, (iix) clb);
    }

    private clb() {
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
            return m13609a((ikf) f4598d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u0007\u0000\u0002\u001b", new Object[]{"a", "b", "c", cyd.class});
        } else if (i2 == 3) {
            return new clb();
        } else {
            if (i2 == 4) {
                return new iir((int[][][]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f4598d;
            }
            ikn ikn = f4599e;
            if (ikn == null) {
                synchronized (clb.class) {
                    ikn = f4599e;
                    if (ikn == null) {
                        ikn = new iis(f4598d);
                        f4599e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
