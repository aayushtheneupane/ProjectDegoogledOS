package p000;

/* renamed from: ial */
/* compiled from: PG */
public final class ial extends iix implements ikg {

    /* renamed from: d */
    public static final ial f13725d;

    /* renamed from: f */
    private static volatile ikn f13726f;

    /* renamed from: a */
    public int f13727a;

    /* renamed from: b */
    public ian f13728b;

    /* renamed from: c */
    public long f13729c;

    /* renamed from: e */
    private byte f13730e = 2;

    static {
        ial ial = new ial();
        f13725d = ial;
        iix.m13611a(ial.class, (iix) ial);
    }

    private ial() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f13730e);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f13730e = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f13725d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001Љ\u0000\u0002\u0002\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new ial();
        } else {
            if (i2 == 4) {
                return new iak((byte[]) null);
            }
            if (i2 == 5) {
                return f13725d;
            }
            ikn ikn = f13726f;
            if (ikn == null) {
                synchronized (ial.class) {
                    ikn = f13726f;
                    if (ikn == null) {
                        ikn = new iis(f13725d);
                        f13726f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
