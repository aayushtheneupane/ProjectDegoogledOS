package p000;

/* renamed from: hmt */
/* compiled from: PG */
public final class hmt extends iix implements ikg {

    /* renamed from: i */
    public static final hmt f13055i;

    /* renamed from: j */
    private static volatile ikn f13056j;

    /* renamed from: a */
    public int f13057a;

    /* renamed from: b */
    public long f13058b;

    /* renamed from: c */
    public long f13059c;

    /* renamed from: d */
    public ije f13060d = ikq.f14400b;

    /* renamed from: e */
    public long f13061e;

    /* renamed from: f */
    public long f13062f;

    /* renamed from: g */
    public hkq f13063g;

    /* renamed from: h */
    public int f13064h;

    static {
        hmt hmt = new hmt();
        f13055i = hmt;
        iix.m13611a(hmt.class, (iix) hmt);
    }

    private hmt() {
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
            return m13609a((ikf) f13055i, "\u0001\u0007\u0000\u0001\u0001\u000b\u0007\u0000\u0001\u0000\u0001\u0005\u0000\u0002\u0005\u0001\u0003\u001b\u0004\u0002\u0002\u0005\u0002\u0003\n\t\u0004\u000b\f\u0005", new Object[]{"a", "b", "c", "d", hlh.class, "e", "f", "g", "h", hms.m11752a()});
        } else if (i2 == 3) {
            return new hmt();
        } else {
            if (i2 == 4) {
                return new iir((float[][][]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f13055i;
            }
            ikn ikn = f13056j;
            if (ikn == null) {
                synchronized (hmt.class) {
                    ikn = f13056j;
                    if (ikn == null) {
                        ikn = new iis(f13055i);
                        f13056j = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
