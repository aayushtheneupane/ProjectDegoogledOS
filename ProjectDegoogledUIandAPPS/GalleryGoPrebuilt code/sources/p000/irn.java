package p000;

/* renamed from: irn */
/* compiled from: PG */
public final class irn extends iix implements ikg {

    /* renamed from: f */
    public static final irn f14886f;

    /* renamed from: g */
    private static volatile ikn f14887g;

    /* renamed from: a */
    public int f14888a;

    /* renamed from: b */
    public long f14889b;

    /* renamed from: c */
    public boolean f14890c;

    /* renamed from: d */
    public int f14891d;

    /* renamed from: e */
    public String f14892e = "";

    static {
        irn irn = new irn();
        f14886f = irn;
        iix.m13611a(irn.class, (iix) irn);
    }

    private irn() {
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
            return m13609a((ikf) f14886f, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0002\u0000\u0002\u0007\u0001\u0003\u0004\u0002\u0004\b\u0003", new Object[]{"a", "b", "c", "d", "e"});
        } else if (i2 == 3) {
            return new irn();
        } else {
            if (i2 == 4) {
                return new iir((byte[][][]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f14886f;
            }
            ikn ikn = f14887g;
            if (ikn == null) {
                synchronized (irn.class) {
                    ikn = f14887g;
                    if (ikn == null) {
                        ikn = new iis(f14886f);
                        f14887g = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
