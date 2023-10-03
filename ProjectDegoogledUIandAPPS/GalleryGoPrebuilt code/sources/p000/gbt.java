package p000;

/* renamed from: gbt */
/* compiled from: PG */
public final class gbt extends iix implements ikg {

    /* renamed from: a */
    public static final gbt f10856a;

    /* renamed from: b */
    private static volatile ikn f10857b;

    static {
        gbt gbt = new gbt();
        f10856a = gbt;
        iix.m13611a(gbt.class, (iix) gbt);
    }

    private gbt() {
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
            return m13609a((ikf) f10856a, "\u0001\u0000", (Object[]) null);
        }
        if (i2 == 3) {
            return new gbt();
        }
        if (i2 == 4) {
            return new iir((char[][][]) null, (boolean[]) null);
        }
        if (i2 == 5) {
            return f10856a;
        }
        ikn ikn = f10857b;
        if (ikn == null) {
            synchronized (gbt.class) {
                ikn = f10857b;
                if (ikn == null) {
                    ikn = new iis(f10856a);
                    f10857b = ikn;
                }
            }
        }
        return ikn;
    }
}
