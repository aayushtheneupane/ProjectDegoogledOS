package p000;

/* renamed from: iqy */
/* compiled from: PG */
public final class iqy extends iix implements ikg {

    /* renamed from: v */
    public static final iqy f14785v;

    /* renamed from: w */
    private static volatile ikn f14786w;

    /* renamed from: a */
    public int f14787a;

    /* renamed from: b */
    public int f14788b;

    /* renamed from: c */
    public int f14789c;

    /* renamed from: d */
    public int f14790d;

    /* renamed from: e */
    public int f14791e;

    /* renamed from: f */
    public int f14792f;

    /* renamed from: g */
    public int f14793g;

    /* renamed from: h */
    public int f14794h;

    /* renamed from: i */
    public int f14795i;

    /* renamed from: j */
    public int f14796j;

    /* renamed from: k */
    public int f14797k;

    /* renamed from: l */
    public int f14798l;

    /* renamed from: m */
    public int f14799m;

    /* renamed from: n */
    public int f14800n;

    /* renamed from: o */
    public int f14801o;

    /* renamed from: p */
    public int f14802p;

    /* renamed from: q */
    public int f14803q;

    /* renamed from: r */
    public int f14804r;

    /* renamed from: s */
    public int f14805s;

    /* renamed from: t */
    public int f14806t;

    /* renamed from: u */
    public long f14807u;

    static {
        iqy iqy = new iqy();
        f14785v = iqy;
        iix.m13611a(iqy.class, (iix) iqy);
    }

    private iqy() {
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
            return m13609a((ikf) f14785v, "\u0001\u0014\u0000\u0001\u0001\u0014\u0014\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001\u0003\u0004\u0002\u0004\u0004\u0003\u0005\u0004\u0004\u0006\u0004\u0005\u0007\u0004\u0007\b\u0004\b\t\u0004\t\n\u0004\n\u000b\u0004\u000b\f\u0004\f\r\u0004\r\u000e\u0004\u000e\u000f\u0004\u000f\u0010\u0004\u0010\u0011\u0004\u0011\u0012\u0004\u0012\u0013\u0004\u0006\u0014\u0002\u0013", new Object[]{"a", "b", "c", "d", "e", "f", "g", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "h", "u"});
        } else if (i2 == 3) {
            return new iqy();
        } else {
            if (i2 == 4) {
                return new iir((int[][][]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f14785v;
            }
            ikn ikn = f14786w;
            if (ikn == null) {
                synchronized (iqy.class) {
                    ikn = f14786w;
                    if (ikn == null) {
                        ikn = new iis(f14785v);
                        f14786w = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
