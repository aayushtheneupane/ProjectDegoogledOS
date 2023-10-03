package p000;

/* renamed from: ire */
/* compiled from: PG */
public final class ire extends iix implements ikg {

    /* renamed from: b */
    public static final ire f14833b;

    /* renamed from: c */
    private static volatile ikn f14834c;

    /* renamed from: a */
    public ije f14835a = ikq.f14400b;

    static {
        ire ire = new ire();
        f14833b = ire;
        iix.m13611a(ire.class, (iix) ire);
    }

    private ire() {
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
            return m13609a((ikf) f14833b, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"a", ird.class});
        } else if (i2 == 3) {
            return new ire();
        } else {
            if (i2 == 4) {
                return new iir((char[][][]) null, (byte[][]) null);
            }
            if (i2 == 5) {
                return f14833b;
            }
            ikn ikn = f14834c;
            if (ikn == null) {
                synchronized (ire.class) {
                    ikn = f14834c;
                    if (ikn == null) {
                        ikn = new iis(f14833b);
                        f14834c = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
