package p000;

/* renamed from: cwz */
/* compiled from: PG */
public final class cwz extends iix implements ikg {

    /* renamed from: b */
    public static final cwz f5879b;

    /* renamed from: c */
    private static volatile ikn f5880c;

    /* renamed from: a */
    public ijd f5881a = ijs.f14357b;

    static {
        cwz cwz = new cwz();
        f5879b = cwz;
        iix.m13611a(cwz.class, (iix) cwz);
    }

    private cwz() {
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
            return m13609a((ikf) f5879b, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u0014", new Object[]{"a"});
        } else if (i2 == 3) {
            return new cwz();
        } else {
            if (i2 == 4) {
                return new iir((float[]) null);
            }
            if (i2 == 5) {
                return f5879b;
            }
            ikn ikn = f5880c;
            if (ikn == null) {
                synchronized (cwz.class) {
                    ikn = f5880c;
                    if (ikn == null) {
                        ikn = new iis(f5879b);
                        f5880c = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
