package p000;

/* renamed from: bkb */
/* compiled from: PG */
public final class bkb extends iix implements ikg {

    /* renamed from: d */
    public static final bkb f2984d;

    /* renamed from: e */
    private static volatile ikn f2985e;

    /* renamed from: a */
    public int f2986a;

    /* renamed from: b */
    public iai f2987b;

    /* renamed from: c */
    public bkg f2988c;

    static {
        bkb bkb = new bkb();
        f2984d = bkb;
        iix.m13611a(bkb.class, (iix) bkb);
    }

    private bkb() {
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
            return m13609a((ikf) f2984d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new bkb();
        } else {
            if (i2 == 4) {
                return new iir((byte[]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f2984d;
            }
            ikn ikn = f2985e;
            if (ikn == null) {
                synchronized (bkb.class) {
                    ikn = f2985e;
                    if (ikn == null) {
                        ikn = new iis(f2984d);
                        f2985e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
