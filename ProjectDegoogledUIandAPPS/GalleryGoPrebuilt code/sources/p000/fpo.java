package p000;

/* renamed from: fpo */
/* compiled from: PG */
public final class fpo extends iix implements ikg {

    /* renamed from: k */
    public static final fpo f10212k;

    /* renamed from: l */
    private static volatile ikn f10213l;

    /* renamed from: a */
    public int f10214a;

    /* renamed from: b */
    public iqw f10215b;

    /* renamed from: c */
    public long f10216c;

    /* renamed from: d */
    public long f10217d;

    /* renamed from: e */
    public long f10218e;

    /* renamed from: f */
    public long f10219f;

    /* renamed from: g */
    public int f10220g;

    /* renamed from: h */
    public String f10221h = "";

    /* renamed from: i */
    public boolean f10222i;

    /* renamed from: j */
    public iqx f10223j;

    static {
        fpo fpo = new fpo();
        f10212k = fpo;
        iix.m13611a(fpo.class, (iix) fpo);
    }

    private fpo() {
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
            return m13609a((ikf) f10212k, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001\t\u0000\u0002\u0002\u0001\u0003\u0002\u0002\u0004\u0002\u0003\u0005\u0005\u0004\u0006\u0004\u0005\u0007\b\u0006\b\u0007\u0007\t\t\b", new Object[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"});
        } else if (i2 == 3) {
            return new fpo();
        } else {
            if (i2 == 4) {
                return new iir((short[]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f10212k;
            }
            ikn ikn = f10213l;
            if (ikn == null) {
                synchronized (fpo.class) {
                    ikn = f10213l;
                    if (ikn == null) {
                        ikn = new iis(f10212k);
                        f10213l = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
