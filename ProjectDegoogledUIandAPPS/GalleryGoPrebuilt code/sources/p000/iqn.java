package p000;

/* renamed from: iqn */
/* compiled from: PG */
public final class iqn extends iix implements ikg {

    /* renamed from: k */
    public static final iqn f14660k;

    /* renamed from: l */
    private static volatile ikn f14661l;

    /* renamed from: a */
    public int f14662a;

    /* renamed from: b */
    public int f14663b;

    /* renamed from: c */
    public long f14664c;

    /* renamed from: d */
    public String f14665d = "";

    /* renamed from: e */
    public String f14666e = "";

    /* renamed from: f */
    public iqx f14667f;

    /* renamed from: g */
    public int f14668g;

    /* renamed from: h */
    public long f14669h;

    /* renamed from: i */
    public iqw f14670i;

    /* renamed from: j */
    public long f14671j;

    static {
        iqn iqn = new iqn();
        f14660k = iqn;
        iix.m13611a(iqn.class, (iix) iqn);
    }

    private iqn() {
        ikq ikq = ikq.f14400b;
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
            return m13609a((ikf) f14660k, "\u0001\t\u0000\u0001\u0001\u000b\t\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0005\u0003\u0002\u0006\u0006\t\u0007\u0007\u0002\b\b\u0005\u0001\t\b\u0002\n\b\u0003\u000b\t\u0004", new Object[]{"a", "b", iqm.m14320a(), "g", iqm.m14320a(), "h", "i", "j", "c", "d", "e", "f"});
        } else if (i2 == 3) {
            return new iqn();
        } else {
            if (i2 == 4) {
                return new iir((int[][]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f14660k;
            }
            ikn ikn = f14661l;
            if (ikn == null) {
                synchronized (iqn.class) {
                    ikn = f14661l;
                    if (ikn == null) {
                        ikn = new iis(f14660k);
                        f14661l = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
