package p000;

/* renamed from: ird */
/* compiled from: PG */
public final class ird extends iix implements ikg {

    /* renamed from: d */
    public static final ird f14828d;

    /* renamed from: e */
    private static volatile ikn f14829e;

    /* renamed from: a */
    public int f14830a;

    /* renamed from: b */
    public String f14831b = "";

    /* renamed from: c */
    public ijd f14832c = ijs.f14357b;

    static {
        ird ird = new ird();
        f14828d = ird;
        iix.m13611a(ird.class, (iix) ird);
    }

    private ird() {
        ikq ikq = ikq.f14400b;
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
            return m13609a((ikf) f14828d, "\u0001\u0002\u0000\u0001\u0011\u0015\u0002\u0000\u0001\u0000\u0011\b\u0012\u0015(", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new ird();
        } else {
            if (i2 == 4) {
                return new iir((byte[][]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f14828d;
            }
            ikn ikn = f14829e;
            if (ikn == null) {
                synchronized (ird.class) {
                    ikn = f14829e;
                    if (ikn == null) {
                        ikn = new iis(f14828d);
                        f14829e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
