package p000;

/* renamed from: iqo */
/* compiled from: PG */
public final class iqo extends iix implements ikg {

    /* renamed from: c */
    public static final iqo f14672c;

    /* renamed from: d */
    private static volatile ikn f14673d;

    /* renamed from: a */
    public int f14674a;

    /* renamed from: b */
    public iqn f14675b;

    static {
        iqo iqo = new iqo();
        f14672c = iqo;
        iix.m13611a(iqo.class, (iix) iqo);
    }

    private iqo() {
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
            return m13609a((ikf) f14672c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t\u0000", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new iqo();
        } else {
            if (i2 == 4) {
                return new iir((short[][][]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f14672c;
            }
            ikn ikn = f14673d;
            if (ikn == null) {
                synchronized (iqo.class) {
                    ikn = f14673d;
                    if (ikn == null) {
                        ikn = new iis(f14672c);
                        f14673d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
