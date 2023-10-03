package p000;

/* renamed from: ifx */
/* compiled from: PG */
public final class ifx extends iix implements ikg {

    /* renamed from: b */
    public static final ifx f14028b;

    /* renamed from: c */
    private static volatile ikn f14029c;

    /* renamed from: a */
    public ije f14030a = ikq.f14400b;

    static {
        ifx ifx = new ifx();
        f14028b = ifx;
        iix.m13611a(ifx.class, (iix) ifx);
    }

    private ifx() {
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
            return m13609a((ikf) f14028b, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"a"});
        } else if (i2 == 3) {
            return new ifx();
        } else {
            if (i2 == 4) {
                return new iir((boolean[]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f14028b;
            }
            ikn ikn = f14029c;
            if (ikn == null) {
                synchronized (ifx.class) {
                    ikn = f14029c;
                    if (ikn == null) {
                        ikn = new iis(f14028b);
                        f14029c = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
