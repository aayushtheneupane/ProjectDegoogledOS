package p000;

/* renamed from: irl */
/* compiled from: PG */
public final class irl extends iix implements ikg {

    /* renamed from: f */
    public static final irl f14867f;

    /* renamed from: g */
    private static volatile ikn f14868g;

    /* renamed from: a */
    public int f14869a;

    /* renamed from: b */
    public String f14870b = "";

    /* renamed from: c */
    public long f14871c;

    /* renamed from: d */
    public long f14872d;

    /* renamed from: e */
    public long f14873e;

    static {
        irl irl = new irl();
        f14867f = irl;
        iix.m13611a(irl.class, (iix) irl);
    }

    private irl() {
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
            return m13609a((ikf) f14867f, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001\u0003\u0002\u0002\u0004\u0002\u0003", new Object[]{"a", "b", "c", "d", "e"});
        } else if (i2 == 3) {
            return new irl();
        } else {
            if (i2 == 4) {
                return new iir((int[][]) null, (byte[][]) null);
            }
            if (i2 == 5) {
                return f14867f;
            }
            ikn ikn = f14868g;
            if (ikn == null) {
                synchronized (irl.class) {
                    ikn = f14868g;
                    if (ikn == null) {
                        ikn = new iis(f14867f);
                        f14868g = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
