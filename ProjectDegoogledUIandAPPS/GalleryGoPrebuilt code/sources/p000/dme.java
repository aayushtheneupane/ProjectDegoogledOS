package p000;

/* renamed from: dme */
/* compiled from: PG */
public final class dme extends iix implements ikg {

    /* renamed from: k */
    public static final dme f6823k;

    /* renamed from: l */
    private static volatile ikn f6824l;

    /* renamed from: a */
    public int f6825a;

    /* renamed from: b */
    public int f6826b = 0;

    /* renamed from: c */
    public Object f6827c;

    /* renamed from: d */
    public cxd f6828d;

    /* renamed from: e */
    public int f6829e;

    /* renamed from: f */
    public boolean f6830f;

    /* renamed from: g */
    public boolean f6831g = true;

    /* renamed from: h */
    public boolean f6832h = true;

    /* renamed from: i */
    public boolean f6833i;

    /* renamed from: j */
    public boolean f6834j;

    static {
        dme dme = new dme();
        f6823k = dme;
        iix.m13611a(dme.class, (iix) dme);
    }

    private dme() {
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
            return m13609a((ikf) f6823k, "\u0001\t\u0001\u0001\u0001\t\t\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003\t\u0002\u0004\f\u0003\u0005\u0007\u0004\u0006\u0007\u0005\u0007\u0007\u0006\b\u0007\u0007\t\u0007\b", new Object[]{"c", "b", "a", cxi.class, ceq.class, "d", "e", dmd.m6352a(), "f", "g", "h", "i", "j"});
        } else if (i2 == 3) {
            return new dme();
        } else {
            if (i2 == 4) {
                return new iir((float[]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f6823k;
            }
            ikn ikn = f6824l;
            if (ikn == null) {
                synchronized (dme.class) {
                    ikn = f6824l;
                    if (ikn == null) {
                        ikn = new iis(f6823k);
                        f6824l = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
