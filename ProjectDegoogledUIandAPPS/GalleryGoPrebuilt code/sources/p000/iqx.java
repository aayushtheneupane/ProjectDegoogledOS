package p000;

/* renamed from: iqx */
/* compiled from: PG */
public final class iqx extends iix implements ikg {

    /* renamed from: a */
    public static final iqx f14783a;

    /* renamed from: b */
    private static volatile ikn f14784b;

    static {
        iqx iqx = new iqx();
        f14783a = iqx;
        iix.m13611a(iqx.class, (iix) iqx);
    }

    private iqx() {
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
            return m13609a((ikf) f14783a, "\u0001\u0000", (Object[]) null);
        }
        if (i2 == 3) {
            return new iqx();
        }
        if (i2 == 4) {
            return new iir((int[]) null, (byte[][]) null);
        }
        if (i2 == 5) {
            return f14783a;
        }
        ikn ikn = f14784b;
        if (ikn == null) {
            synchronized (iqx.class) {
                ikn = f14784b;
                if (ikn == null) {
                    ikn = new iis(f14783a);
                    f14784b = ikn;
                }
            }
        }
        return ikn;
    }
}
