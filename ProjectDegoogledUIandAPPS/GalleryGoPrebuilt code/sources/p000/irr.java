package p000;

/* renamed from: irr */
/* compiled from: PG */
public final class irr extends iix implements ikg {

    /* renamed from: g */
    public static final irr f14902g;

    /* renamed from: h */
    private static volatile ikn f14903h;

    /* renamed from: a */
    public int f14904a;

    /* renamed from: b */
    public String f14905b = "";

    /* renamed from: c */
    public String f14906c = "";

    /* renamed from: d */
    public int f14907d;

    /* renamed from: e */
    public long f14908e;

    /* renamed from: f */
    public String f14909f = "";

    static {
        irr irr = new irr();
        f14902g = irr;
        iix.m13611a(irr.class, (iix) irr);
    }

    private irr() {
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
            return m13609a((ikf) f14902g, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\f\u0002\u0004\u0002\u0003\u0005\b\u0004", new Object[]{"a", "b", "c", "d", irq.f14901a, "e", "f"});
        } else if (i2 == 3) {
            return new irr();
        } else {
            if (i2 == 4) {
                return new iir((int[]) null);
            }
            if (i2 == 5) {
                return f14902g;
            }
            ikn ikn = f14903h;
            if (ikn == null) {
                synchronized (irr.class) {
                    ikn = f14903h;
                    if (ikn == null) {
                        ikn = new iis(f14902g);
                        f14903h = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
