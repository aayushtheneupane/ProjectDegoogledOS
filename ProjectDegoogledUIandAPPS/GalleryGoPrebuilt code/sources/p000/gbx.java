package p000;

/* renamed from: gbx */
/* compiled from: PG */
public final class gbx extends iix implements ikg {

    /* renamed from: f */
    public static final gbx f10892f;

    /* renamed from: g */
    private static volatile ikn f10893g;

    /* renamed from: a */
    public int f10894a;

    /* renamed from: b */
    public float f10895b;

    /* renamed from: c */
    public float f10896c;

    /* renamed from: d */
    public float f10897d;

    /* renamed from: e */
    public int f10898e = 15000;

    static {
        gbx gbx = new gbx();
        f10892f = gbx;
        iix.m13611a(gbx.class, (iix) gbx);
    }

    private gbx() {
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
            return m13609a((ikf) f10892f, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0001\u0000\u0002\u0001\u0001\u0003\f\u0003\u0004\u0001\u0002", new Object[]{"a", "b", "c", "e", gby.f10899a, "d"});
        } else if (i2 == 3) {
            return new gbx();
        } else {
            if (i2 == 4) {
                return new iir((boolean[][][]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f10892f;
            }
            ikn ikn = f10893g;
            if (ikn == null) {
                synchronized (gbx.class) {
                    ikn = f10893g;
                    if (ikn == null) {
                        ikn = new iis(f10892f);
                        f10893g = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
