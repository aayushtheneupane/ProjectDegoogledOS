package p000;

/* renamed from: ffy */
/* compiled from: PG */
public final class ffy extends iix implements ikg {

    /* renamed from: a */
    public static final ffy f9493a;

    /* renamed from: b */
    private static volatile ikn f9494b;

    static {
        ffy ffy = new ffy();
        f9493a = ffy;
        iix.m13611a(ffy.class, (iix) ffy);
    }

    private ffy() {
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
            return m13609a((ikf) f9493a, "\u0001\u0000", (Object[]) null);
        }
        if (i2 == 3) {
            return new ffy();
        }
        if (i2 == 4) {
            return new iir((short[][][]) null, (float[]) null);
        }
        if (i2 == 5) {
            return f9493a;
        }
        ikn ikn = f9494b;
        if (ikn == null) {
            synchronized (ffy.class) {
                ikn = f9494b;
                if (ikn == null) {
                    ikn = new iis(f9493a);
                    f9494b = ikn;
                }
            }
        }
        return ikn;
    }
}
