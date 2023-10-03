package p000;

/* renamed from: fdx */
/* compiled from: PG */
public final class fdx extends iiu implements fdy {

    /* renamed from: e */
    public static final fdx f9338e;

    /* renamed from: g */
    private static volatile ikn f9339g;

    /* renamed from: a */
    public int f9340a;

    /* renamed from: b */
    public int f9341b;

    /* renamed from: c */
    public ijc f9342c = iiy.f14327b;

    /* renamed from: d */
    public fdh f9343d;

    /* renamed from: f */
    private byte f9344f = 2;

    static {
        fdx fdx = new fdx();
        f9338e = fdx;
        iix.m13611a(fdx.class, (iix) fdx);
    }

    private fdx() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f9344f);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f9344f = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f9338e, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0001\u0001\u0004\u0000\u0002\u0016\u0003Љ\u0001", new Object[]{"a", "b", "c", "d"});
        } else if (i2 == 3) {
            return new fdx();
        } else {
            if (i2 == 4) {
                return new iit((char[]) null);
            }
            if (i2 == 5) {
                return f9338e;
            }
            ikn ikn = f9339g;
            if (ikn == null) {
                synchronized (fdx.class) {
                    ikn = f9339g;
                    if (ikn == null) {
                        ikn = new iis(f9338e);
                        f9339g = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
