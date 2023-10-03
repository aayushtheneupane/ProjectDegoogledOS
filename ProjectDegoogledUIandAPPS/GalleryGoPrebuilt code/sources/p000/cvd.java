package p000;

/* renamed from: cvd */
/* compiled from: PG */
public final class cvd extends iix implements ikg {

    /* renamed from: e */
    public static final cvd f5717e;

    /* renamed from: f */
    private static volatile ikn f5718f;

    /* renamed from: a */
    public int f5719a;

    /* renamed from: b */
    public int f5720b;

    /* renamed from: c */
    public long f5721c;

    /* renamed from: d */
    public long f5722d;

    static {
        cvd cvd = new cvd();
        f5717e = cvd;
        iix.m13611a(cvd.class, (iix) cvd);
    }

    private cvd() {
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
            return m13609a((ikf) f5717e, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\u0002\u0001\u0003\u0002\u0002", new Object[]{"a", "b", cup.m5455a(), "c", "d"});
        } else if (i2 == 3) {
            return new cvd();
        } else {
            if (i2 == 4) {
                return new iir((boolean[]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f5717e;
            }
            ikn ikn = f5718f;
            if (ikn == null) {
                synchronized (cvd.class) {
                    ikn = f5718f;
                    if (ikn == null) {
                        ikn = new iis(f5717e);
                        f5718f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
