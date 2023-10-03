package p000;

/* renamed from: iav */
/* compiled from: PG */
public final class iav extends iix implements ikg {

    /* renamed from: d */
    public static final iav f13763d;

    /* renamed from: f */
    private static volatile ikn f13764f;

    /* renamed from: a */
    public int f13765a;

    /* renamed from: b */
    public iat f13766b;

    /* renamed from: c */
    public ije f13767c = ikq.f14400b;

    /* renamed from: e */
    private byte f13768e = 2;

    static {
        iav iav = new iav();
        f13763d = iav;
        iix.m13611a(iav.class, (iix) iav);
    }

    private iav() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f13768e);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f13768e = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f13763d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0002\u0001ԉ\u0000\u0002Л", new Object[]{"a", "b", "c", iat.class});
        } else if (i2 == 3) {
            return new iav();
        } else {
            if (i2 == 4) {
                return new iau((byte[]) null);
            }
            if (i2 == 5) {
                return f13763d;
            }
            ikn ikn = f13764f;
            if (ikn == null) {
                synchronized (iav.class) {
                    ikn = f13764f;
                    if (ikn == null) {
                        ikn = new iis(f13763d);
                        f13764f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
