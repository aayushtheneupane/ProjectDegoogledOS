package p000;

/* renamed from: igx */
/* compiled from: PG */
public final class igx extends iix implements ikg {

    /* renamed from: e */
    public static final igx f14166e;

    /* renamed from: f */
    private static volatile ikn f14167f;

    /* renamed from: a */
    public int f14168a;

    /* renamed from: b */
    public long f14169b;

    /* renamed from: c */
    public String f14170c = "";

    /* renamed from: d */
    public imn f14171d;

    static {
        igx igx = new igx();
        f14166e = igx;
        iix.m13611a(igx.class, (iix) igx);
    }

    private igx() {
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
            return m13609a((ikf) f14166e, "\u0001\u0003\u0000\u0001\u0001\t\u0003\u0000\u0000\u0000\u0001\u0002\u0000\u0003\t\u0002\t\b\u0001", new Object[]{"a", "b", "d", "c"});
        } else if (i2 == 3) {
            return new igx();
        } else {
            if (i2 == 4) {
                return new iir((int[][][]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f14166e;
            }
            ikn ikn = f14167f;
            if (ikn == null) {
                synchronized (igx.class) {
                    ikn = f14167f;
                    if (ikn == null) {
                        ikn = new iis(f14166e);
                        f14167f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
