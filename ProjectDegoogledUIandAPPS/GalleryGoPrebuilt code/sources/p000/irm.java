package p000;

/* renamed from: irm */
/* compiled from: PG */
public final class irm extends iix implements ikg {

    /* renamed from: k */
    public static final irm f14874k;

    /* renamed from: l */
    private static volatile ikn f14875l;

    /* renamed from: a */
    public int f14876a;

    /* renamed from: b */
    public long f14877b;

    /* renamed from: c */
    public long f14878c;

    /* renamed from: d */
    public long f14879d;

    /* renamed from: e */
    public long f14880e;

    /* renamed from: f */
    public long f14881f;

    /* renamed from: g */
    public long f14882g;

    /* renamed from: h */
    public boolean f14883h;

    /* renamed from: i */
    public irl f14884i;

    /* renamed from: j */
    public irl f14885j;

    static {
        irm irm = new irm();
        f14874k = irm;
        iix.m13611a(irm.class, (iix) irm);
    }

    private irm() {
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
            return m13609a((ikf) f14874k, "\u0001\t\u0000\u0001\u0002\r\t\u0000\u0000\u0000\u0002\u0002\u0001\u0003\u0002\u0002\u0004\u0002\u0003\u0005\u0002\u0004\t\u0002\u0005\n\u0002\u0006\u000b\u0007\u0007\f\t\b\r\t\t", new Object[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"});
        } else if (i2 == 3) {
            return new irm();
        } else {
            if (i2 == 4) {
                return new iir((boolean[]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f14874k;
            }
            ikn ikn = f14875l;
            if (ikn == null) {
                synchronized (irm.class) {
                    ikn = f14875l;
                    if (ikn == null) {
                        ikn = new iis(f14874k);
                        f14875l = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
