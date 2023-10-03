package p000;

/* renamed from: igg */
/* compiled from: PG */
public final class igg extends iiu implements iiv {

    /* renamed from: i */
    public static final igg f14083i;

    /* renamed from: l */
    private static volatile ikn f14084l;

    /* renamed from: a */
    public int f14085a;

    /* renamed from: b */
    public int f14086b;

    /* renamed from: c */
    public int f14087c;

    /* renamed from: d */
    public float f14088d;

    /* renamed from: e */
    public float f14089e;

    /* renamed from: f */
    public float f14090f;

    /* renamed from: g */
    public float f14091g;

    /* renamed from: h */
    public igf f14092h;

    /* renamed from: k */
    private byte f14093k = 2;

    static {
        igg igg = new igg();
        f14083i = igg;
        iix.m13611a(igg.class, (iix) igg);
    }

    private igg() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f14093k);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f14093k = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f14083i, "\u0001\u0005\u0000\u0003 _\u0005\u0000\u0000\u0000 \u0001\r3\u000164\u00017X\tM_\u0001\n", new Object[]{"a", "b", "c", "e", "f", "g", "h", "d"});
        } else if (i2 == 3) {
            return new igg();
        } else {
            if (i2 == 4) {
                return new iit((short[]) null);
            }
            if (i2 == 5) {
                return f14083i;
            }
            ikn ikn = f14084l;
            if (ikn == null) {
                synchronized (igg.class) {
                    ikn = f14084l;
                    if (ikn == null) {
                        ikn = new iis(f14083i);
                        f14084l = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
