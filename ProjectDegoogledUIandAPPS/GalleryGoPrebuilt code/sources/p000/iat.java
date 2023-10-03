package p000;

/* renamed from: iat */
/* compiled from: PG */
public final class iat extends iix implements ikg {

    /* renamed from: d */
    public static final iat f13757d;

    /* renamed from: f */
    private static volatile ikn f13758f;

    /* renamed from: a */
    public int f13759a;

    /* renamed from: b */
    public String f13760b = "";

    /* renamed from: c */
    public ije f13761c = ikq.f14400b;

    /* renamed from: e */
    private byte f13762e = 2;

    static {
        iat iat = new iat();
        f13757d = iat;
        iix.m13611a(iat.class, (iix) iat);
    }

    private iat() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f13762e);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f13762e = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f13757d, "\u0001\u0002\u0000\u0001\u0001\u0004\u0002\u0000\u0001\u0002\u0001Ԉ\u0000\u0004б", new Object[]{"a", "b", "c", ias.class});
        } else if (i2 == 3) {
            return new iat();
        } else {
            if (i2 == 4) {
                return new iaq((byte[]) null);
            }
            if (i2 == 5) {
                return f13757d;
            }
            ikn ikn = f13758f;
            if (ikn == null) {
                synchronized (iat.class) {
                    ikn = f13758f;
                    if (ikn == null) {
                        ikn = new iis(f13757d);
                        f13758f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
