package p000;

/* renamed from: fey */
/* compiled from: PG */
public final class fey extends iix implements ikg {

    /* renamed from: c */
    public static final fey f9415c;

    /* renamed from: d */
    private static volatile ikn f9416d;

    /* renamed from: a */
    public int f9417a;

    /* renamed from: b */
    public int f9418b;

    static {
        fey fey = new fey();
        f9415c = fey;
        iix.m13611a(fey.class, (iix) fey);
    }

    private fey() {
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
            return m13609a((ikf) f9415c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f\u0000", new Object[]{"a", "b", iaw.f13769a});
        } else if (i2 == 3) {
            return new fey();
        } else {
            if (i2 == 4) {
                return new iir((float[][]) null, (byte[][]) null);
            }
            if (i2 == 5) {
                return f9415c;
            }
            ikn ikn = f9416d;
            if (ikn == null) {
                synchronized (fey.class) {
                    ikn = f9416d;
                    if (ikn == null) {
                        ikn = new iis(f9415c);
                        f9416d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
