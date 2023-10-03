package p000;

/* renamed from: iai */
/* compiled from: PG */
public final class iai extends iix implements ikg {

    /* renamed from: g */
    public static final iai f13716g;

    /* renamed from: h */
    private static volatile ikn f13717h;

    /* renamed from: a */
    public int f13718a;

    /* renamed from: b */
    public int f13719b;

    /* renamed from: c */
    public int f13720c;

    /* renamed from: d */
    public ijc f13721d = iiy.f14327b;

    /* renamed from: e */
    public int f13722e;

    /* renamed from: f */
    public int f13723f;

    static {
        iai iai = new iai();
        f13716g = iai;
        iix.m13611a(iai.class, (iix) iai);
    }

    private iai() {
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
            return m13609a((ikf) f13716g, "\u0001\u0005\u0000\u0001\u0001\u0007\u0005\u0000\u0001\u0000\u0001\u0004\u0000\u0002\u0004\u0001\u0003\u0016\u0004\f\u0002\u0007\f\u0003", new Object[]{"a", "b", "c", "d", "e", iay.m12594a(), "f", iaw.f13769a});
        } else if (i2 == 3) {
            return new iai();
        } else {
            if (i2 == 4) {
                return new iah((byte[]) null);
            }
            if (i2 == 5) {
                return f13716g;
            }
            ikn ikn = f13717h;
            if (ikn == null) {
                synchronized (iai.class) {
                    ikn = f13717h;
                    if (ikn == null) {
                        ikn = new iis(f13716g);
                        f13717h = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
