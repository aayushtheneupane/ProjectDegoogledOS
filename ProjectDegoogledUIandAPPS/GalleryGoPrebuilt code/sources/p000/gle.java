package p000;

/* renamed from: gle */
/* compiled from: PG */
public final class gle extends iix implements ikg {

    /* renamed from: i */
    public static final gle f11566i;

    /* renamed from: j */
    private static volatile ikn f11567j;

    /* renamed from: a */
    public int f11568a;

    /* renamed from: b */
    public String f11569b = "";

    /* renamed from: c */
    public String f11570c = "";

    /* renamed from: d */
    public String f11571d = "";

    /* renamed from: e */
    public String f11572e = "";

    /* renamed from: f */
    public boolean f11573f;

    /* renamed from: g */
    public String f11574g = "";

    /* renamed from: h */
    public String f11575h = "";

    static {
        gle gle = new gle();
        f11566i = gle;
        iix.m13611a(gle.class, (iix) gle);
    }

    private gle() {
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
            return m13609a((ikf) f11566i, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\u0007\u0004\u0006\b\u0005\u0007\b\u0006", new Object[]{"a", "b", "c", "d", "e", "f", "g", "h"});
        } else if (i2 == 3) {
            return new gle();
        } else {
            if (i2 == 4) {
                return new iir((char[]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f11566i;
            }
            ikn ikn = f11567j;
            if (ikn == null) {
                synchronized (gle.class) {
                    ikn = f11567j;
                    if (ikn == null) {
                        ikn = new iis(f11566i);
                        f11567j = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
