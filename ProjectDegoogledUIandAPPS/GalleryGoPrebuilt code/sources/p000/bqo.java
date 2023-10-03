package p000;

/* renamed from: bqo */
/* compiled from: PG */
public final class bqo extends iix implements ikg {

    /* renamed from: c */
    public static final bqo f3381c;

    /* renamed from: d */
    private static volatile ikn f3382d;

    /* renamed from: a */
    public int f3383a;

    /* renamed from: b */
    public int f3384b;

    static {
        bqo bqo = new bqo();
        f3381c = bqo;
        iix.m13611a(bqo.class, (iix) bqo);
    }

    private bqo() {
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
            return m13609a((ikf) f3381c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0004\u0000", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new bqo();
        } else {
            if (i2 == 4) {
                return new iir((int[][]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f3381c;
            }
            ikn ikn = f3382d;
            if (ikn == null) {
                synchronized (bqo.class) {
                    ikn = f3382d;
                    if (ikn == null) {
                        ikn = new iis(f3381c);
                        f3382d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
