package p000;

/* renamed from: ign */
/* compiled from: PG */
public final class ign extends iiu implements iiv {

    /* renamed from: g */
    public static final ign f14129g;

    /* renamed from: i */
    private static volatile ikn f14130i;

    /* renamed from: a */
    public int f14131a;

    /* renamed from: b */
    public String f14132b = "";

    /* renamed from: c */
    public int f14133c;

    /* renamed from: d */
    public String f14134d = "";

    /* renamed from: e */
    public int f14135e;

    /* renamed from: f */
    public ije f14136f = ikq.f14400b;

    /* renamed from: h */
    private byte f14137h = 2;

    static {
        ign ign = new ign();
        f14129g = ign;
        iix.m13611a(ign.class, (iix) ign);
    }

    private ign() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f14137h);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f14137h = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f14129g, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\b\u0000\u0002\u0004\u0001\u0003\u001b\u0004\b\u0002\u0005\u0004\u0003", new Object[]{"a", "b", "c", "f", igr.class, "d", "e"});
        } else if (i2 == 3) {
            return new ign();
        } else {
            if (i2 == 4) {
                return new iit((byte[][]) null);
            }
            if (i2 == 5) {
                return f14129g;
            }
            ikn ikn = f14130i;
            if (ikn == null) {
                synchronized (ign.class) {
                    ikn = f14130i;
                    if (ikn == null) {
                        ikn = new iis(f14129g);
                        f14130i = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
