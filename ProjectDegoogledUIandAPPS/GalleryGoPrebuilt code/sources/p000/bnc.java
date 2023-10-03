package p000;

/* renamed from: bnc */
/* compiled from: PG */
public final class bnc extends iix implements ikg {

    /* renamed from: g */
    public static final bnc f3179g;

    /* renamed from: h */
    private static volatile ikn f3180h;

    /* renamed from: a */
    public int f3181a;

    /* renamed from: b */
    public int f3182b = 0;

    /* renamed from: c */
    public Object f3183c;

    /* renamed from: d */
    public int f3184d;

    /* renamed from: e */
    public int f3185e;

    /* renamed from: f */
    public cxd f3186f;

    static {
        bnc bnc = new bnc();
        f3179g = bnc;
        iix.m13611a(bnc.class, (iix) bnc);
    }

    private bnc() {
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
            return m13609a((ikf) f3179g, "\u0001\u0005\u0001\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001\u0003;\u0000\u0004\t\u0004\u0005=\u0000", new Object[]{"c", "b", "a", "d", "e", "f"});
        } else if (i2 == 3) {
            return new bnc();
        } else {
            if (i2 == 4) {
                return new iir((boolean[][]) null);
            }
            if (i2 == 5) {
                return f3179g;
            }
            ikn ikn = f3180h;
            if (ikn == null) {
                synchronized (bnc.class) {
                    ikn = f3180h;
                    if (ikn == null) {
                        ikn = new iis(f3179g);
                        f3180h = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
