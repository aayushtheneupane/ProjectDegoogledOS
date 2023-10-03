package p000;

/* renamed from: dzm */
/* compiled from: PG */
public final class dzm extends iix implements ikg {

    /* renamed from: b */
    public static final dzm f7725b;

    /* renamed from: c */
    private static volatile ikn f7726c;

    /* renamed from: a */
    public ije f7727a = ikq.f14400b;

    static {
        dzm dzm = new dzm();
        f7725b = dzm;
        iix.m13611a(dzm.class, (iix) dzm);
    }

    private dzm() {
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
            return m13609a((ikf) f7725b, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"a", cxi.class});
        } else if (i2 == 3) {
            return new dzm();
        } else {
            if (i2 == 4) {
                return new iir((short[][]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f7725b;
            }
            ikn ikn = f7726c;
            if (ikn == null) {
                synchronized (dzm.class) {
                    ikn = f7726c;
                    if (ikn == null) {
                        ikn = new iis(f7725b);
                        f7726c = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
