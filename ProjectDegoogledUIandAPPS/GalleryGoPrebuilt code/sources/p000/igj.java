package p000;

/* renamed from: igj */
/* compiled from: PG */
public final class igj extends iix implements ikg {

    /* renamed from: d */
    public static final igj f14107d;

    /* renamed from: e */
    private static volatile ikn f14108e;

    /* renamed from: a */
    public int f14109a;

    /* renamed from: b */
    public int f14110b;

    /* renamed from: c */
    public ije f14111c = ikq.f14400b;

    static {
        igj igj = new igj();
        f14107d = igj;
        iix.m13611a(igj.class, (iix) igj);
    }

    private igj() {
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
            return m13609a((ikf) f14107d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\f\u0000", new Object[]{"a", "c", igi.class, "b", igq.f14143a});
        } else if (i2 == 3) {
            return new igj();
        } else {
            if (i2 == 4) {
                return new iir((int[]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f14107d;
            }
            ikn ikn = f14108e;
            if (ikn == null) {
                synchronized (igj.class) {
                    ikn = f14108e;
                    if (ikn == null) {
                        ikn = new iis(f14107d);
                        f14108e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
