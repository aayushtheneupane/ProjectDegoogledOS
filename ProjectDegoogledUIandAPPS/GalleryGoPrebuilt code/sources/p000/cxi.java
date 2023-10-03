package p000;

/* renamed from: cxi */
/* compiled from: PG */
public final class cxi extends iix implements ikg {

    /* renamed from: B */
    private static volatile ikn f5907B;

    /* renamed from: x */
    public static final cxi f5908x;

    /* renamed from: a */
    public int f5909a;

    /* renamed from: b */
    public String f5910b = "";

    /* renamed from: c */
    public long f5911c;

    /* renamed from: d */
    public boolean f5912d = true;

    /* renamed from: e */
    public int f5913e;

    /* renamed from: f */
    public String f5914f = "";

    /* renamed from: g */
    public long f5915g;

    /* renamed from: h */
    public long f5916h;

    /* renamed from: i */
    public ehf f5917i;

    /* renamed from: j */
    public long f5918j;

    /* renamed from: k */
    public cxe f5919k;

    /* renamed from: l */
    public long f5920l;

    /* renamed from: m */
    public String f5921m = "";

    /* renamed from: n */
    public ihw f5922n = ihw.f14203b;

    /* renamed from: o */
    public int f5923o;

    /* renamed from: p */
    public int f5924p;

    /* renamed from: q */
    public int f5925q;

    /* renamed from: r */
    public String f5926r = "";

    /* renamed from: s */
    public String f5927s = "";

    /* renamed from: t */
    public int f5928t;

    /* renamed from: u */
    public ije f5929u = ikq.f14400b;

    /* renamed from: v */
    public String f5930v = "";

    /* renamed from: w */
    public long f5931w;

    static {
        cxi cxi = new cxi();
        f5908x = cxi;
        iix.m13611a(cxi.class, (iix) cxi);
    }

    private cxi() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        Class<cxi> cls = cxi.class;
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 1) {
            return null;
        }
        if (i2 == 2) {
            return m13609a((ikf) f5908x, "\u0001\u0016\u0000\u0001\u0001\u0018\u0016\u0000\u0001\u0000\u0001\b\u0000\u0002\u0007\u0002\u0003\f\u0003\u0004\u0002\u0005\u0005\u0002\u0006\u0006\t\u0007\u0007\b\u0004\b\t\t\t\u0002\n\n\b\u000b\u000b\u0002\u0001\f\n\f\r\u0002\b\u000e\u0004\r\u000f\u0004\u000e\u0010\u0004\u000f\u0011\b\u0010\u0012\b\u0011\u0013\f\u0012\u0014\u001b\u0017\b\u0014\u0018\u0002\u0015", new Object[]{"a", "b", "d", "e", cxh.m5593a(), "g", "h", "i", "f", "k", "l", "m", "c", "n", "j", "o", "p", "q", "r", "s", "t", cxf.f5900a, "u", cls, "v", "w"});
        } else if (i2 == 3) {
            return new cxi();
        } else {
            if (i2 == 4) {
                return new iir((byte[]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f5908x;
            }
            ikn ikn = f5907B;
            if (ikn == null) {
                synchronized (cls) {
                    ikn = f5907B;
                    if (ikn == null) {
                        ikn = new iis(f5908x);
                        f5907B = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
