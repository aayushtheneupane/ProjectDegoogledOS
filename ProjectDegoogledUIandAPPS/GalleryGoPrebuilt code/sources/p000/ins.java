package p000;

/* renamed from: ins */
/* compiled from: PG */
public final class ins extends iiu implements iiv {

    /* renamed from: h */
    public static final ins f14576h;

    /* renamed from: k */
    private static volatile ikn f14577k;

    /* renamed from: a */
    public int f14578a;

    /* renamed from: b */
    public long f14579b;

    /* renamed from: c */
    public long f14580c;

    /* renamed from: d */
    public int f14581d;

    /* renamed from: e */
    public ihw f14582e;

    /* renamed from: f */
    public long f14583f;

    /* renamed from: g */
    public boolean f14584g;

    /* renamed from: i */
    private byte f14585i = 2;

    static {
        ins ins = new ins();
        f14576h = ins;
        iix.m13611a(ins.class, (iix) ins);
    }

    private ins() {
        ikq ikq = ikq.f14400b;
        ihw ihw = ihw.f14203b;
        this.f14582e = ihw.f14203b;
        this.f14583f = 180000;
        iiy iiy = iiy.f14327b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f14585i);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f14585i = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f14576h, "\u0001\u0006\u0000\u0001\u0001\u0019\u0006\u0000\u0000\u0000\u0001\u0002\u0000\u0006\n\n\u000b\u0004\u0004\u000f\u0010\u0010\u0011\u0002\u0001\u0019\u0007\u0017", new Object[]{"a", "b", "e", "d", "f", "c", "g"});
        } else if (i2 == 3) {
            return new ins();
        } else {
            if (i2 == 4) {
                return new iit((char[][]) null);
            }
            if (i2 == 5) {
                return f14576h;
            }
            ikn ikn = f14577k;
            if (ikn == null) {
                synchronized (ins.class) {
                    ikn = f14577k;
                    if (ikn == null) {
                        ikn = new iis(f14576h);
                        f14577k = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
