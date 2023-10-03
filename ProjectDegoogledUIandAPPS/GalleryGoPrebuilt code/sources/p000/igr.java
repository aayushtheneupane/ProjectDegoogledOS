package p000;

/* renamed from: igr */
/* compiled from: PG */
public final class igr extends iix implements ikg {

    /* renamed from: e */
    public static final igr f14144e;

    /* renamed from: f */
    private static volatile ikn f14145f;

    /* renamed from: a */
    public int f14146a;

    /* renamed from: b */
    public String f14147b = "";

    /* renamed from: c */
    public long f14148c;

    /* renamed from: d */
    public igu f14149d;

    static {
        igr igr = new igr();
        f14144e = igr;
        iix.m13611a(igr.class, (iix) igr);
    }

    private igr() {
        ihw ihw = ihw.f14203b;
        ikq ikq = ikq.f14400b;
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
            return m13609a((ikf) f14144e, "\u0001\u0003\u0000\u0001\u0001\u0007\u0003\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001\u0007\t\u0006", new Object[]{"a", "b", "c", "d"});
        } else if (i2 == 3) {
            return new igr();
        } else {
            if (i2 == 4) {
                return new iir((byte[][][]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f14144e;
            }
            ikn ikn = f14145f;
            if (ikn == null) {
                synchronized (igr.class) {
                    ikn = f14145f;
                    if (ikn == null) {
                        ikn = new iis(f14144e);
                        f14145f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
