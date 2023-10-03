package p000;

/* renamed from: inv */
/* compiled from: PG */
public final class inv extends iix implements ikg {

    /* renamed from: b */
    public static final inv f14593b;

    /* renamed from: c */
    private static volatile ikn f14594c;

    /* renamed from: a */
    public ije f14595a = ikq.f14400b;

    static {
        inv inv = new inv();
        f14593b = inv;
        iix.m13611a(inv.class, (iix) inv);
    }

    private inv() {
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
            return m13609a((ikf) f14593b, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"a", inu.class});
        } else if (i2 == 3) {
            return new inv();
        } else {
            if (i2 == 4) {
                return new iir((boolean[][][]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f14593b;
            }
            ikn ikn = f14594c;
            if (ikn == null) {
                synchronized (inv.class) {
                    ikn = f14594c;
                    if (ikn == null) {
                        ikn = new iis(f14593b);
                        f14594c = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
