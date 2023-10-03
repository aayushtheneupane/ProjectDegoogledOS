package p000;

/* renamed from: egx */
/* compiled from: PG */
public final class egx extends iix implements ikg {

    /* renamed from: g */
    public static final egx f8238g;

    /* renamed from: h */
    private static volatile ikn f8239h;

    /* renamed from: a */
    public int f8240a;

    /* renamed from: b */
    public String f8241b = "";

    /* renamed from: c */
    public String f8242c = "";

    /* renamed from: d */
    public int f8243d;

    /* renamed from: e */
    public int f8244e;

    /* renamed from: f */
    public int f8245f;

    static {
        egx egx = new egx();
        f8238g = egx;
        iix.m13611a(egx.class, (iix) egx);
    }

    private egx() {
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
            return m13609a((ikf) f8238g, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u0004\u0002\u0004\u0004\u0003\u0005\u0004\u0004", new Object[]{"a", "b", "c", "d", "e", "f"});
        } else if (i2 == 3) {
            return new egx();
        } else {
            if (i2 == 4) {
                return new iir((short[][]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f8238g;
            }
            ikn ikn = f8239h;
            if (ikn == null) {
                synchronized (egx.class) {
                    ikn = f8239h;
                    if (ikn == null) {
                        ikn = new iis(f8238g);
                        f8239h = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
