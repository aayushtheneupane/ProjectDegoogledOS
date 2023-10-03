package p000;

/* renamed from: fez */
/* compiled from: PG */
public final class fez extends iix implements ikg {

    /* renamed from: c */
    public static final fez f9419c;

    /* renamed from: d */
    private static volatile ikn f9420d;

    /* renamed from: a */
    public int f9421a;

    /* renamed from: b */
    public int f9422b;

    static {
        fez fez = new fez();
        f9419c = fez;
        iix.m13611a(fez.class, (iix) fez);
    }

    private fez() {
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
            return m13609a((ikf) f9419c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0004\u0000", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new fez();
        } else {
            if (i2 == 4) {
                return new iir((int[]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f9419c;
            }
            ikn ikn = f9420d;
            if (ikn == null) {
                synchronized (fez.class) {
                    ikn = f9420d;
                    if (ikn == null) {
                        ikn = new iis(f9419c);
                        f9420d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
