package p000;

/* renamed from: fdw */
/* compiled from: PG */
public final class fdw extends iiu implements iiv {

    /* renamed from: c */
    public static final fdw f9333c;

    /* renamed from: e */
    private static volatile ikn f9334e;

    /* renamed from: a */
    public int f9335a;

    /* renamed from: b */
    public int f9336b;

    /* renamed from: d */
    private byte f9337d = 2;

    static {
        fdw fdw = new fdw();
        f9333c = fdw;
        iix.m13611a(fdw.class, (iix) fdw);
    }

    private fdw() {
        iiy iiy = iiy.f14327b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f9337d);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f9337d = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f9333c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f\u0000", new Object[]{"a", "b", iay.m12594a()});
        } else if (i2 == 3) {
            return new fdw();
        } else {
            if (i2 == 4) {
                return new iit((byte[]) null);
            }
            if (i2 == 5) {
                return f9333c;
            }
            ikn ikn = f9334e;
            if (ikn == null) {
                synchronized (fdw.class) {
                    ikn = f9334e;
                    if (ikn == null) {
                        ikn = new iis(f9333c);
                        f9334e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
