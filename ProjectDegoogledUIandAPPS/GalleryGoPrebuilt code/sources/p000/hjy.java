package p000;

/* renamed from: hjy */
/* compiled from: PG */
public final class hjy extends iix implements ikg {

    /* renamed from: f */
    public static final hjy f12891f;

    /* renamed from: g */
    private static volatile ikn f12892g;

    /* renamed from: a */
    public int f12893a;

    /* renamed from: b */
    public long f12894b = -1;

    /* renamed from: c */
    public ije f12895c = ikq.f14400b;

    /* renamed from: d */
    public long f12896d;

    /* renamed from: e */
    public ijc f12897e = iiy.f14327b;

    static {
        hjy hjy = new hjy();
        f12891f = hjy;
        iix.m13611a(hjy.class, (iix) hjy);
    }

    private hjy() {
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
            return m13609a((ikf) f12891f, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001\u0002\u0000\u0002\u001b\u0003\u0002\u0001\u0004\u0016", new Object[]{"a", "b", "c", hjx.class, "d", "e"});
        } else if (i2 == 3) {
            return new hjy();
        } else {
            if (i2 == 4) {
                return new iir((float[][][]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f12891f;
            }
            ikn ikn = f12892g;
            if (ikn == null) {
                synchronized (hjy.class) {
                    ikn = f12892g;
                    if (ikn == null) {
                        ikn = new iis(f12891f);
                        f12892g = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
