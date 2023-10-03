package p000;

/* renamed from: iqr */
/* compiled from: PG */
public final class iqr extends iix implements ikg {

    /* renamed from: e */
    public static final iqr f14686e;

    /* renamed from: f */
    private static volatile ikn f14687f;

    /* renamed from: a */
    public int f14688a;

    /* renamed from: b */
    public ije f14689b = ikq.f14400b;

    /* renamed from: c */
    public ije f14690c = ikq.f14400b;

    /* renamed from: d */
    public iqq f14691d;

    static {
        iqr iqr = new iqr();
        f14686e = iqr;
        iix.m13611a(iqr.class, (iix) iqr);
    }

    private iqr() {
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
            return m13609a((ikf) f14686e, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001\u001b\u0002\u001b\u0003\t\u0000", new Object[]{"a", "b", iqu.class, "c", iqp.class, "d"});
        } else if (i2 == 3) {
            return new iqr();
        } else {
            if (i2 == 4) {
                return new iir((float[]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f14686e;
            }
            ikn ikn = f14687f;
            if (ikn == null) {
                synchronized (iqr.class) {
                    ikn = f14687f;
                    if (ikn == null) {
                        ikn = new iis(f14686e);
                        f14687f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
