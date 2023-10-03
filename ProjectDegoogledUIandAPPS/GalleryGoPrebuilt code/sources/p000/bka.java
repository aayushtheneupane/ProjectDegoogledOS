package p000;

/* renamed from: bka */
/* compiled from: PG */
public final class bka extends iix implements ikg {

    /* renamed from: h */
    public static final bka f2975h;

    /* renamed from: i */
    private static volatile ikn f2976i;

    /* renamed from: a */
    public int f2977a;

    /* renamed from: b */
    public String f2978b = "";

    /* renamed from: c */
    public int f2979c;

    /* renamed from: d */
    public int f2980d;

    /* renamed from: e */
    public int f2981e;

    /* renamed from: f */
    public int f2982f;

    /* renamed from: g */
    public boolean f2983g;

    static {
        bka bka = new bka();
        f2975h = bka;
        iix.m13611a(bka.class, (iix) bka);
    }

    private bka() {
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
            return m13609a((ikf) f2975h, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\b\u0000\u0002\u0004\u0001\u0003\f\u0002\u0004\f\u0003\u0005\u0004\u0004\u0006\u0007\u0005", new Object[]{"a", "b", "c", "d", bjy.f2972a, "e", bjz.f2973a, "f", "g"});
        } else if (i2 == 3) {
            return new bka();
        } else {
            if (i2 == 4) {
                return new iir((int[][]) null);
            }
            if (i2 == 5) {
                return f2975h;
            }
            ikn ikn = f2976i;
            if (ikn == null) {
                synchronized (bka.class) {
                    ikn = f2976i;
                    if (ikn == null) {
                        ikn = new iis(f2975h);
                        f2976i = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
