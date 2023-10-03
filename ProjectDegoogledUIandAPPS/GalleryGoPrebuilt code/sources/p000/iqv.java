package p000;

/* renamed from: iqv */
/* compiled from: PG */
public final class iqv extends iix implements ikg {

    /* renamed from: e */
    public static final iqv f14710e;

    /* renamed from: f */
    private static volatile ikn f14711f;

    /* renamed from: a */
    public int f14712a;

    /* renamed from: b */
    public int f14713b;

    /* renamed from: c */
    public long f14714c;

    /* renamed from: d */
    public iqq f14715d;

    static {
        iqv iqv = new iqv();
        f14710e = iqv;
        iix.m13611a(iqv.class, (iix) iqv);
    }

    private iqv() {
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
            return m13609a((ikf) f14710e, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0002\u0001\u0003\t\u0002", new Object[]{"a", "b", "c", "d"});
        } else if (i2 == 3) {
            return new iqv();
        } else {
            if (i2 == 4) {
                return new iir((boolean[]) null);
            }
            if (i2 == 5) {
                return f14710e;
            }
            ikn ikn = f14711f;
            if (ikn == null) {
                synchronized (iqv.class) {
                    ikn = f14711f;
                    if (ikn == null) {
                        ikn = new iis(f14710e);
                        f14711f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
