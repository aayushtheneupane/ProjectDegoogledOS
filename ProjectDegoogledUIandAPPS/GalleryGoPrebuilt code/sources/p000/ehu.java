package p000;

/* renamed from: ehu */
/* compiled from: PG */
public final class ehu extends iix implements ikg {

    /* renamed from: d */
    public static final ehu f8311d;

    /* renamed from: e */
    private static volatile ikn f8312e;

    /* renamed from: a */
    public int f8313a;

    /* renamed from: b */
    public String f8314b = "";

    /* renamed from: c */
    public String f8315c = "";

    static {
        ehu ehu = new ehu();
        f8311d = ehu;
        iix.m13611a(ehu.class, (iix) ehu);
    }

    private ehu() {
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
            return m13609a((ikf) f8311d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new ehu();
        } else {
            if (i2 == 4) {
                return new iir((boolean[]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f8311d;
            }
            ikn ikn = f8312e;
            if (ikn == null) {
                synchronized (ehu.class) {
                    ikn = f8312e;
                    if (ikn == null) {
                        ikn = new iis(f8311d);
                        f8312e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
