package p000;

/* renamed from: dkj */
/* compiled from: PG */
public final class dkj extends iix implements ikg {

    /* renamed from: c */
    public static final dkj f6723c;

    /* renamed from: d */
    private static volatile ikn f6724d;

    /* renamed from: a */
    public int f6725a;

    /* renamed from: b */
    public int f6726b = 1;

    static {
        dkj dkj = new dkj();
        f6723c = dkj;
        iix.m13611a(dkj.class, (iix) dkj);
    }

    private dkj() {
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
            return m13609a((ikf) f6723c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f\u0000", new Object[]{"a", "b", dki.f6722a});
        } else if (i2 == 3) {
            return new dkj();
        } else {
            if (i2 == 4) {
                return new iir((float[]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f6723c;
            }
            ikn ikn = f6724d;
            if (ikn == null) {
                synchronized (dkj.class) {
                    ikn = f6724d;
                    if (ikn == null) {
                        ikn = new iis(f6723c);
                        f6724d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
