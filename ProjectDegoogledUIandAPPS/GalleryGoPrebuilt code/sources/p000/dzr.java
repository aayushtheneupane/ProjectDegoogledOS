package p000;

/* renamed from: dzr */
/* compiled from: PG */
public final class dzr extends iix implements ikg {

    /* renamed from: d */
    public static final dzr f7736d;

    /* renamed from: e */
    private static volatile ikn f7737e;

    /* renamed from: a */
    public int f7738a;

    /* renamed from: b */
    public boolean f7739b;

    /* renamed from: c */
    public String f7740c = "";

    static {
        dzr dzr = new dzr();
        f7736d = dzr;
        iix.m13611a(dzr.class, (iix) dzr);
    }

    private dzr() {
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
            return m13609a((ikf) f7736d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0007\u0000\u0002\b\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new dzr();
        } else {
            if (i2 == 4) {
                return new iir((byte[]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f7736d;
            }
            ikn ikn = f7737e;
            if (ikn == null) {
                synchronized (dzr.class) {
                    ikn = f7737e;
                    if (ikn == null) {
                        ikn = new iis(f7736d);
                        f7737e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
