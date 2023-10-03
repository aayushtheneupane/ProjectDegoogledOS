package p000;

/* renamed from: gcd */
/* compiled from: PG */
public final class gcd extends iix implements ikg {

    /* renamed from: d */
    public static final gcd f10914d;

    /* renamed from: e */
    private static volatile ikn f10915e;

    /* renamed from: a */
    public int f10916a;

    /* renamed from: b */
    public ije f10917b = ikq.f14400b;

    /* renamed from: c */
    public int f10918c;

    static {
        gcd gcd = new gcd();
        f10914d = gcd;
        iix.m13611a(gcd.class, (iix) gcd);
    }

    private gcd() {
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
            return m13609a((ikf) f10914d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\u0004\u0000", new Object[]{"a", "b", gcb.class, "c"});
        } else if (i2 == 3) {
            return new gcd();
        } else {
            if (i2 == 4) {
                return new iir((int[]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f10914d;
            }
            ikn ikn = f10915e;
            if (ikn == null) {
                synchronized (gcd.class) {
                    ikn = f10915e;
                    if (ikn == null) {
                        ikn = new iis(f10914d);
                        f10915e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
