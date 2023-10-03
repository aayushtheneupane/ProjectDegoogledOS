package p000;

/* renamed from: bkg */
/* compiled from: PG */
public final class bkg extends iix implements ikg {

    /* renamed from: d */
    public static final bkg f2999d;

    /* renamed from: e */
    private static volatile ikn f3000e;

    /* renamed from: a */
    public int f3001a;

    /* renamed from: b */
    public bkd f3002b;

    /* renamed from: c */
    public bkf f3003c;

    static {
        bkg bkg = new bkg();
        f2999d = bkg;
        iix.m13611a(bkg.class, (iix) bkg);
    }

    private bkg() {
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
            return m13609a((ikf) f2999d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new bkg();
        } else {
            if (i2 == 4) {
                return new iir((short[]) null);
            }
            if (i2 == 5) {
                return f2999d;
            }
            ikn ikn = f3000e;
            if (ikn == null) {
                synchronized (bkg.class) {
                    ikn = f3000e;
                    if (ikn == null) {
                        ikn = new iis(f2999d);
                        f3000e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
