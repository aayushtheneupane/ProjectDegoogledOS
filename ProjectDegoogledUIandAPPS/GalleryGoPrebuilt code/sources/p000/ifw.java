package p000;

/* renamed from: ifw */
/* compiled from: PG */
public final class ifw extends iix implements ikg {

    /* renamed from: d */
    public static final ifw f14023d;

    /* renamed from: e */
    private static volatile ikn f14024e;

    /* renamed from: a */
    public int f14025a;

    /* renamed from: b */
    public ify f14026b;

    /* renamed from: c */
    public igb f14027c;

    static {
        ifw ifw = new ifw();
        f14023d = ifw;
        iix.m13611a(ifw.class, (iix) ifw);
    }

    private ifw() {
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
            return m13609a((ikf) f14023d, "\u0001\u0002\u0000\u0001\u0002\u0006\u0002\u0000\u0000\u0000\u0002\t\u0000\u0006\t\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new ifw();
        } else {
            if (i2 == 4) {
                return new iir((float[][]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f14023d;
            }
            ikn ikn = f14024e;
            if (ikn == null) {
                synchronized (ifw.class) {
                    ikn = f14024e;
                    if (ikn == null) {
                        ikn = new iis(f14023d);
                        f14024e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
