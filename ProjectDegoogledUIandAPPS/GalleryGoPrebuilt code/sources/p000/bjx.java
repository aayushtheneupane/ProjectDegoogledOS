package p000;

/* renamed from: bjx */
/* compiled from: PG */
public final class bjx extends iix implements ikg {

    /* renamed from: d */
    public static final bjx f2967d;

    /* renamed from: e */
    private static volatile ikn f2968e;

    /* renamed from: a */
    public int f2969a;

    /* renamed from: b */
    public bkb f2970b;

    /* renamed from: c */
    public bka f2971c;

    static {
        bjx bjx = new bjx();
        f2967d = bjx;
        iix.m13611a(bjx.class, (iix) bjx);
    }

    private bjx() {
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
            return m13609a((ikf) f2967d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new bjx();
        } else {
            if (i2 == 4) {
                return new iir((byte[]) null);
            }
            if (i2 == 5) {
                return f2967d;
            }
            ikn ikn = f2968e;
            if (ikn == null) {
                synchronized (bjx.class) {
                    ikn = f2968e;
                    if (ikn == null) {
                        ikn = new iis(f2967d);
                        f2968e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
