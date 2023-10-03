package p000;

/* renamed from: gbs */
/* compiled from: PG */
public final class gbs extends iix implements ikg {

    /* renamed from: f */
    public static final gbs f10849f;

    /* renamed from: g */
    private static volatile ikn f10850g;

    /* renamed from: a */
    public int f10851a;

    /* renamed from: b */
    public float f10852b;

    /* renamed from: c */
    public float f10853c;

    /* renamed from: d */
    public float f10854d;

    /* renamed from: e */
    public float f10855e;

    static {
        gbs gbs = new gbs();
        f10849f = gbs;
        iix.m13611a(gbs.class, (iix) gbs);
    }

    private gbs() {
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
            return m13609a((ikf) f10849f, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0001\u0000\u0002\u0001\u0001\u0003\u0001\u0002\u0004\u0001\u0003", new Object[]{"a", "b", "c", "d", "e"});
        } else if (i2 == 3) {
            return new gbs();
        } else {
            if (i2 == 4) {
                return new iir((int[][][]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f10849f;
            }
            ikn ikn = f10850g;
            if (ikn == null) {
                synchronized (gbs.class) {
                    ikn = f10850g;
                    if (ikn == null) {
                        ikn = new iis(f10849f);
                        f10850g = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
