package p000;

/* renamed from: gxa */
/* compiled from: PG */
public final class gxa extends iix implements ikg {

    /* renamed from: e */
    public static final gxa f12234e;

    /* renamed from: f */
    private static volatile ikn f12235f;

    /* renamed from: a */
    public int f12236a;

    /* renamed from: b */
    public int f12237b = 0;

    /* renamed from: c */
    public Object f12238c;

    /* renamed from: d */
    public String f12239d = "";

    static {
        gxa gxa = new gxa();
        f12234e = gxa;
        iix.m13611a(gxa.class, (iix) gxa);
    }

    private gxa() {
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
            return m13609a((ikf) f12234e, "\u0001\u0007\u0001\u0001\u0001\n\u0007\u0000\u0000\u0000\u00018\u0000\u0002:\u0000\u00033\u0000\u0004;\u0000\u0005=\u0000\u0006=\u0000\n\b\u0000", new Object[]{"c", "b", "a", "d"});
        } else if (i2 == 3) {
            return new gxa();
        } else {
            if (i2 == 4) {
                return new iir((char[]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f12234e;
            }
            ikn ikn = f12235f;
            if (ikn == null) {
                synchronized (gxa.class) {
                    ikn = f12235f;
                    if (ikn == null) {
                        ikn = new iis(f12234e);
                        f12235f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
