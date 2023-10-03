package p000;

/* renamed from: csl */
/* compiled from: PG */
public final class csl extends iix implements ikg {

    /* renamed from: b */
    public static final csl f5579b;

    /* renamed from: c */
    private static volatile ikn f5580c;

    /* renamed from: a */
    public ije f5581a = ikq.f14400b;

    static {
        csl csl = new csl();
        f5579b = csl;
        iix.m13611a(csl.class, (iix) csl);
    }

    private csl() {
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
            return m13609a((ikf) f5579b, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"a", csk.class});
        } else if (i2 == 3) {
            return new csl();
        } else {
            if (i2 == 4) {
                return new iir((byte[][][]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f5579b;
            }
            ikn ikn = f5580c;
            if (ikn == null) {
                synchronized (csl.class) {
                    ikn = f5580c;
                    if (ikn == null) {
                        ikn = new iis(f5579b);
                        f5580c = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
