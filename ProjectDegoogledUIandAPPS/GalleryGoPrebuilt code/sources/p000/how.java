package p000;

/* renamed from: how */
/* compiled from: PG */
public final class how extends iix implements ikg {

    /* renamed from: e */
    public static final how f13173e;

    /* renamed from: f */
    private static volatile ikn f13174f;

    /* renamed from: a */
    public int f13175a;

    /* renamed from: b */
    public String f13176b = "";

    /* renamed from: c */
    public String f13177c = "";

    /* renamed from: d */
    public String f13178d = "";

    static {
        how how = new how();
        f13173e = how;
        iix.m13611a(how.class, (iix) how);
    }

    private how() {
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
            return m13609a((ikf) f13173e, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002", new Object[]{"a", "b", "c", "d"});
        } else if (i2 == 3) {
            return new how();
        } else {
            if (i2 == 4) {
                return new iir((boolean[][][]) null);
            }
            if (i2 == 5) {
                return f13173e;
            }
            ikn ikn = f13174f;
            if (ikn == null) {
                synchronized (how.class) {
                    ikn = f13174f;
                    if (ikn == null) {
                        ikn = new iis(f13173e);
                        f13174f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
