package p000;

/* renamed from: cxe */
/* compiled from: PG */
public final class cxe extends iix implements ikg {

    /* renamed from: f */
    public static final cxe f5893f;

    /* renamed from: g */
    private static volatile ikn f5894g;

    /* renamed from: a */
    public int f5895a;

    /* renamed from: b */
    public String f5896b = "";

    /* renamed from: c */
    public String f5897c = "";

    /* renamed from: d */
    public String f5898d = "";

    /* renamed from: e */
    public boolean f5899e;

    static {
        cxe cxe = new cxe();
        f5893f = cxe;
        iix.m13611a(cxe.class, (iix) cxe);
    }

    private cxe() {
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
            return m13609a((ikf) f5893f, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u0007\u0003\u0004\b\u0002", new Object[]{"a", "b", "c", "e", "d"});
        } else if (i2 == 3) {
            return new cxe();
        } else {
            if (i2 == 4) {
                return new iir((short[][]) null, (byte[][]) null);
            }
            if (i2 == 5) {
                return f5893f;
            }
            ikn ikn = f5894g;
            if (ikn == null) {
                synchronized (cxe.class) {
                    ikn = f5894g;
                    if (ikn == null) {
                        ikn = new iis(f5893f);
                        f5894g = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
