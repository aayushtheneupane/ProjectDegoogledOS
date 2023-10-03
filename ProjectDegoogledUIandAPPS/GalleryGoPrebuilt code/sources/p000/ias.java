package p000;

/* renamed from: ias */
/* compiled from: PG */
public final class ias extends iix implements ikg {

    /* renamed from: f */
    public static final ias f13749f;

    /* renamed from: h */
    private static volatile ikn f13750h;

    /* renamed from: a */
    public int f13751a;

    /* renamed from: b */
    public String f13752b = "";

    /* renamed from: c */
    public String f13753c = "";

    /* renamed from: d */
    public String f13754d = "";

    /* renamed from: e */
    public int f13755e;

    /* renamed from: g */
    private byte f13756g = 2;

    static {
        ias ias = new ias();
        f13749f = ias;
        iix.m13611a(ias.class, (iix) ias);
    }

    private ias() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f13756g);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f13756g = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f13749f, "\u0001\u0004\u0000\u0001\u0005\b\u0004\u0000\u0000\u0003\u0005Ԉ\u0000\u0006Ԉ\u0001\u0007\b\u0002\bԄ\u0003", new Object[]{"a", "b", "c", "d", "e"});
        } else if (i2 == 3) {
            return new ias();
        } else {
            if (i2 == 4) {
                return new iar((byte[]) null);
            }
            if (i2 == 5) {
                return f13749f;
            }
            ikn ikn = f13750h;
            if (ikn == null) {
                synchronized (ias.class) {
                    ikn = f13750h;
                    if (ikn == null) {
                        ikn = new iis(f13749f);
                        f13750h = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
