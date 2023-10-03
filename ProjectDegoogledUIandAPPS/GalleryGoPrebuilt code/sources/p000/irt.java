package p000;

/* renamed from: irt */
/* compiled from: PG */
public final class irt extends iix implements ikg {

    /* renamed from: i */
    public static final irt f14911i;

    /* renamed from: j */
    private static volatile ikn f14912j;

    /* renamed from: a */
    public int f14913a;

    /* renamed from: b */
    public boolean f14914b;

    /* renamed from: c */
    public iro f14915c;

    /* renamed from: d */
    public String f14916d = "";

    /* renamed from: e */
    public String f14917e = "";

    /* renamed from: f */
    public int f14918f;

    /* renamed from: g */
    public long f14919g;

    /* renamed from: h */
    public String f14920h = "";

    static {
        irt irt = new irt();
        f14911i = irt;
        iix.m13611a(irt.class, (iix) irt);
    }

    private irt() {
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
            return m13609a((ikf) f14911i, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001\u0007\u0000\u0002\t\u0001\u0003\b\u0002\u0004\b\u0003\u0005\f\u0004\u0006\u0005\u0005\u0007\b\u0006", new Object[]{"a", "b", "c", "d", "e", "f", irs.f14910a, "g", "h"});
        } else if (i2 == 3) {
            return new irt();
        } else {
            if (i2 == 4) {
                return new iir((char[][]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f14911i;
            }
            ikn ikn = f14912j;
            if (ikn == null) {
                synchronized (irt.class) {
                    ikn = f14912j;
                    if (ikn == null) {
                        ikn = new iis(f14911i);
                        f14912j = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
