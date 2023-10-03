package p000;

/* renamed from: inu */
/* compiled from: PG */
public final class inu extends iix implements ikg {

    /* renamed from: f */
    public static final inu f14586f;

    /* renamed from: g */
    private static volatile ikn f14587g;

    /* renamed from: a */
    public int f14588a;

    /* renamed from: b */
    public int f14589b;

    /* renamed from: c */
    public String f14590c = "";

    /* renamed from: d */
    public long f14591d;

    /* renamed from: e */
    public long f14592e;

    static {
        inu inu = new inu();
        f14586f = inu;
        iix.m13611a(inu.class, (iix) inu);
    }

    private inu() {
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
            return m13609a((ikf) f14586f, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0004\u0000\u0002\b\u0001\u0003\u0002\u0002\u0004\u0002\u0003", new Object[]{"a", "b", "c", "d", "e"});
        } else if (i2 == 3) {
            return new inu();
        } else {
            if (i2 == 4) {
                return new iir((boolean[][]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f14586f;
            }
            ikn ikn = f14587g;
            if (ikn == null) {
                synchronized (inu.class) {
                    ikn = f14587g;
                    if (ikn == null) {
                        ikn = new iis(f14586f);
                        f14587g = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
