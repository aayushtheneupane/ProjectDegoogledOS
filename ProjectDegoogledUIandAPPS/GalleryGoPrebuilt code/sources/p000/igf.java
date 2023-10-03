package p000;

/* renamed from: igf */
/* compiled from: PG */
public final class igf extends iix implements ikg {

    /* renamed from: d */
    public static final igf f14078d;

    /* renamed from: e */
    private static volatile ikn f14079e;

    /* renamed from: a */
    public int f14080a;

    /* renamed from: b */
    public boolean f14081b;

    /* renamed from: c */
    public float f14082c;

    static {
        igf igf = new igf();
        f14078d = igf;
        iix.m13611a(igf.class, (iix) igf);
    }

    private igf() {
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
            return m13609a((ikf) f14078d, "\u0001\u0002\u0000\u0001\u0003\u0004\u0002\u0000\u0000\u0000\u0003\u0007\b\u0004\u0001\t", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new igf();
        } else {
            if (i2 == 4) {
                return new iir((float[]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f14078d;
            }
            ikn ikn = f14079e;
            if (ikn == null) {
                synchronized (igf.class) {
                    ikn = f14079e;
                    if (ikn == null) {
                        ikn = new iis(f14078d);
                        f14079e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
