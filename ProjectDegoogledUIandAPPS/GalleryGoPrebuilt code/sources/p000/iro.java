package p000;

/* renamed from: iro */
/* compiled from: PG */
public final class iro extends iix implements ikg {

    /* renamed from: c */
    public static final iro f14893c;

    /* renamed from: d */
    private static volatile ikn f14894d;

    /* renamed from: a */
    public int f14895a;

    /* renamed from: b */
    public irn f14896b;

    static {
        iro iro = new iro();
        f14893c = iro;
        iix.m13611a(iro.class, (iix) iro);
    }

    private iro() {
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
            return m13609a((ikf) f14893c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t\u0000", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new iro();
        } else {
            if (i2 == 4) {
                return new iir((byte[][]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f14893c;
            }
            ikn ikn = f14894d;
            if (ikn == null) {
                synchronized (iro.class) {
                    ikn = f14894d;
                    if (ikn == null) {
                        ikn = new iis(f14893c);
                        f14894d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
