package p000;

/* renamed from: bwy */
/* compiled from: PG */
public final class bwy extends iix implements ikg {

    /* renamed from: d */
    public static final bwy f3795d;

    /* renamed from: e */
    private static volatile ikn f3796e;

    /* renamed from: a */
    public int f3797a;

    /* renamed from: b */
    public int f3798b;

    /* renamed from: c */
    public int f3799c;

    static {
        bwy bwy = new bwy();
        f3795d = bwy;
        iix.m13611a(bwy.class, (iix) bwy);
    }

    private bwy() {
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
            return m13609a((ikf) f3795d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new bwy();
        } else {
            if (i2 == 4) {
                return new iir((byte[]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f3795d;
            }
            ikn ikn = f3796e;
            if (ikn == null) {
                synchronized (bwy.class) {
                    ikn = f3796e;
                    if (ikn == null) {
                        ikn = new iis(f3795d);
                        f3796e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
