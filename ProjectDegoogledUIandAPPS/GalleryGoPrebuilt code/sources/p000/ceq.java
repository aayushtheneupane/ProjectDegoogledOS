package p000;

/* renamed from: ceq */
/* compiled from: PG */
public final class ceq extends iix implements ikg {

    /* renamed from: g */
    public static final ceq f4197g;

    /* renamed from: h */
    private static volatile ikn f4198h;

    /* renamed from: a */
    public int f4199a;

    /* renamed from: b */
    public String f4200b = "";

    /* renamed from: c */
    public String f4201c = "";

    /* renamed from: d */
    public String f4202d = "";

    /* renamed from: e */
    public ijd f4203e = ijs.f14357b;

    /* renamed from: f */
    public String f4204f = "";

    static {
        ceq ceq = new ceq();
        f4197g = ceq;
        iix.m13611a(ceq.class, (iix) ceq);
    }

    private ceq() {
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
            return m13609a((ikf) f4197g, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u0014\u0005\b\u0003", new Object[]{"a", "b", "c", "d", "e", "f"});
        } else if (i2 == 3) {
            return new ceq();
        } else {
            if (i2 == 4) {
                return new iir((char[][]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f4197g;
            }
            ikn ikn = f4198h;
            if (ikn == null) {
                synchronized (ceq.class) {
                    ikn = f4198h;
                    if (ikn == null) {
                        ikn = new iis(f4197g);
                        f4198h = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
