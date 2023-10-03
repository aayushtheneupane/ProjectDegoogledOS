package p000;

/* renamed from: irk */
/* compiled from: PG */
public final class irk extends iix implements ikg {

    /* renamed from: m */
    public static final irk f14853m;

    /* renamed from: n */
    private static volatile ikn f14854n;

    /* renamed from: a */
    public int f14855a;

    /* renamed from: b */
    public String f14856b = "";

    /* renamed from: c */
    public long f14857c;

    /* renamed from: d */
    public String f14858d = "";

    /* renamed from: e */
    public long f14859e;

    /* renamed from: f */
    public long f14860f;

    /* renamed from: g */
    public long f14861g;

    /* renamed from: h */
    public long f14862h;

    /* renamed from: i */
    public int f14863i;

    /* renamed from: j */
    public long f14864j;

    /* renamed from: k */
    public int f14865k;

    /* renamed from: l */
    public iqx f14866l;

    static {
        irk irk = new irk();
        f14853m = irk;
        iix.m13611a(irk.class, (iix) irk);
    }

    private irk() {
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
            return m13609a((ikf) f14853m, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0000\u0000\u0001\b\u0000\u0002\u0005\u0003\u0003\u0005\u0004\u0004\u0002\u0005\u0005\u0002\u0006\u0006\u0002\b\u0007\f\t\b\u0005\u0001\t\b\u0002\n\u0004\u0007\u000b\t\n", new Object[]{"a", "b", "e", "f", "g", "h", "j", "k", irj.f14852a, "c", "d", "i", "l"});
        } else if (i2 == 3) {
            return new irk();
        } else {
            if (i2 == 4) {
                return new iir((char[]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f14853m;
            }
            ikn ikn = f14854n;
            if (ikn == null) {
                synchronized (irk.class) {
                    ikn = f14854n;
                    if (ikn == null) {
                        ikn = new iis(f14853m);
                        f14854n = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
