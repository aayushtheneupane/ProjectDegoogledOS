package p000;

/* renamed from: igc */
/* compiled from: PG */
public final class igc extends iix implements ikg {

    /* renamed from: d */
    public static final igc f14053d;

    /* renamed from: e */
    private static volatile ikn f14054e;

    /* renamed from: a */
    public int f14055a;

    /* renamed from: b */
    public int f14056b;

    /* renamed from: c */
    public String f14057c = "";

    static {
        igc igc = new igc();
        f14053d = igc;
        iix.m13611a(igc.class, (iix) igc);
    }

    private igc() {
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
            return m13609a((ikf) f14053d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0000\u0002\b\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new igc();
        } else {
            if (i2 == 4) {
                return new iir((byte[][]) null, (byte[][]) null);
            }
            if (i2 == 5) {
                return f14053d;
            }
            ikn ikn = f14054e;
            if (ikn == null) {
                synchronized (igc.class) {
                    ikn = f14054e;
                    if (ikn == null) {
                        ikn = new iis(f14053d);
                        f14054e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
