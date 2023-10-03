package p000;

/* renamed from: irc */
/* compiled from: PG */
public final class irc extends iiu implements iiv {

    /* renamed from: g */
    public static final irc f14819g;

    /* renamed from: i */
    private static volatile ikn f14820i;

    /* renamed from: a */
    public int f14821a;

    /* renamed from: b */
    public ira f14822b;

    /* renamed from: c */
    public iro f14823c;

    /* renamed from: d */
    public int f14824d;

    /* renamed from: e */
    public iqz f14825e;

    /* renamed from: f */
    public String f14826f = "";

    /* renamed from: h */
    private byte f14827h = 2;

    static {
        irc irc = new irc();
        f14819g = irc;
        iix.m13611a(irc.class, (iix) irc);
    }

    private irc() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f14827h);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f14827h = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f14819g, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\f\u0002\u0004\t\u0003\u0005\b\u0004", new Object[]{"a", "b", "c", "d", irb.f14818a, "e", "f"});
        } else if (i2 == 3) {
            return new irc();
        } else {
            if (i2 == 4) {
                return new iit((short[][]) null);
            }
            if (i2 == 5) {
                return f14819g;
            }
            ikn ikn = f14820i;
            if (ikn == null) {
                synchronized (irc.class) {
                    ikn = f14820i;
                    if (ikn == null) {
                        ikn = new iis(f14819g);
                        f14820i = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
