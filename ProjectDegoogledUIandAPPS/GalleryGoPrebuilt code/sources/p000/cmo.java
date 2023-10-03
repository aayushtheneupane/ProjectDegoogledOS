package p000;

/* renamed from: cmo */
/* compiled from: PG */
public final class cmo extends iix implements ikg {

    /* renamed from: d */
    public static final cmo f4689d;

    /* renamed from: e */
    private static volatile ikn f4690e;

    /* renamed from: a */
    public int f4691a;

    /* renamed from: b */
    public boolean f4692b;

    /* renamed from: c */
    public ije f4693c = ikq.f14400b;

    static {
        cmo cmo = new cmo();
        f4689d = cmo;
        iix.m13611a(cmo.class, (iix) cmo);
    }

    private cmo() {
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
            return m13609a((ikf) f4689d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u0007\u0000\u0002\u001b", new Object[]{"a", "b", "c", cyd.class});
        } else if (i2 == 3) {
            return new cmo();
        } else {
            if (i2 == 4) {
                return new iir((char[][][]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f4689d;
            }
            ikn ikn = f4690e;
            if (ikn == null) {
                synchronized (cmo.class) {
                    ikn = f4690e;
                    if (ikn == null) {
                        ikn = new iis(f4689d);
                        f4690e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
