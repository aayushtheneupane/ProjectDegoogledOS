package p000;

/* renamed from: eue */
/* compiled from: PG */
public final class eue extends iix implements ikg {

    /* renamed from: n */
    public static final eue f9025n;

    /* renamed from: o */
    private static volatile ikn f9026o;

    /* renamed from: a */
    public int f9027a;

    /* renamed from: b */
    public int f9028b;

    /* renamed from: c */
    public String f9029c = "";

    /* renamed from: d */
    public String f9030d = "";

    /* renamed from: e */
    public String f9031e = "";

    /* renamed from: f */
    public String f9032f = "";

    /* renamed from: g */
    public int f9033g;

    /* renamed from: h */
    public long f9034h = -1;

    /* renamed from: i */
    public int f9035i;

    /* renamed from: j */
    public String f9036j = "";

    /* renamed from: k */
    public int f9037k;

    /* renamed from: l */
    public int f9038l;

    /* renamed from: m */
    public boolean f9039m;

    static {
        eue eue = new eue();
        f9025n = eue;
        iix.m13611a(eue.class, (iix) eue);
    }

    private eue() {
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
            return m13609a((ikf) f9025n, "\u0001\u000b\u0000\u0002\u0002$\u000b\u0000\u0000\u0000\u0002\b\u0001\u0003\b\u0002\u0005\b\u0006\u0013\u0002\u0018\u0017\u0004\u001e\u0019\f\b \b!!\b\u0004\"\u0004\"#\u0004#$\u0007$", new Object[]{"a", "b", "c", "d", "f", "h", "i", "g", imo.f14537a, "j", "e", "k", "l", "m"});
        } else if (i2 == 3) {
            return new eue();
        } else {
            if (i2 == 4) {
                return new iir((float[][]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f9025n;
            }
            ikn ikn = f9026o;
            if (ikn == null) {
                synchronized (eue.class) {
                    ikn = f9026o;
                    if (ikn == null) {
                        ikn = new iis(f9025n);
                        f9026o = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
