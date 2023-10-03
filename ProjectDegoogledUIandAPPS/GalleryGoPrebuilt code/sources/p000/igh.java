package p000;

/* renamed from: igh */
/* compiled from: PG */
public final class igh extends iiu implements iiv {

    /* renamed from: c */
    public static final igh f14094c;

    /* renamed from: e */
    private static volatile ikn f14095e;

    /* renamed from: a */
    public int f14096a;

    /* renamed from: b */
    public igg f14097b;

    /* renamed from: d */
    private byte f14098d = 2;

    static {
        igh igh = new igh();
        f14094c = igh;
        iix.m13611a(igh.class, (iix) igh);
    }

    private igh() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f14098d);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f14098d = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f14094c, "\u0001\u0001\u0000\u0001\u0003\u0003\u0001\u0000\u0000\u0001\u0003Љ\u0002", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new igh();
        } else {
            if (i2 == 4) {
                return new iit((float[]) null);
            }
            if (i2 == 5) {
                return f14094c;
            }
            ikn ikn = f14095e;
            if (ikn == null) {
                synchronized (igh.class) {
                    ikn = f14095e;
                    if (ikn == null) {
                        ikn = new iis(f14094c);
                        f14095e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
