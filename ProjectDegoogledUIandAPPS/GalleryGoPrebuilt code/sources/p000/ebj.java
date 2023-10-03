package p000;

/* renamed from: ebj */
/* compiled from: PG */
public final class ebj extends iix implements ikg {

    /* renamed from: c */
    public static final ebj f7847c;

    /* renamed from: d */
    private static volatile ikn f7848d;

    /* renamed from: a */
    public int f7849a;

    /* renamed from: b */
    public int f7850b;

    static {
        ebj ebj = new ebj();
        f7847c = ebj;
        iix.m13611a(ebj.class, (iix) ebj);
    }

    private ebj() {
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
            return m13609a((ikf) f7847c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f\u0000", new Object[]{"a", "b", cjm.m4400a()});
        } else if (i2 == 3) {
            return new ebj();
        } else {
            if (i2 == 4) {
                return new iir((short[]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f7847c;
            }
            ikn ikn = f7848d;
            if (ikn == null) {
                synchronized (ebj.class) {
                    ikn = f7848d;
                    if (ikn == null) {
                        ikn = new iis(f7847c);
                        f7848d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
