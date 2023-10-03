package p000;

/* renamed from: efy */
/* compiled from: PG */
public final class efy extends iix implements ikg {

    /* renamed from: b */
    public static final efy f8178b;

    /* renamed from: c */
    private static volatile ikn f8179c;

    /* renamed from: a */
    public ijy f8180a = ijy.f14368b;

    static {
        efy efy = new efy();
        f8178b = efy;
        iix.m13611a(efy.class, (iix) efy);
    }

    private efy() {
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
            return m13609a((ikf) f8178b, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"a", efx.f8177a});
        } else if (i2 == 3) {
            return new efy();
        } else {
            if (i2 == 4) {
                return new iir((char[][]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f8178b;
            }
            ikn ikn = f8179c;
            if (ikn == null) {
                synchronized (efy.class) {
                    ikn = f8179c;
                    if (ikn == null) {
                        ikn = new iis(f8178b);
                        f8179c = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
