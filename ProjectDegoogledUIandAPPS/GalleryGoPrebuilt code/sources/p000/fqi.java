package p000;

/* renamed from: fqi */
/* compiled from: PG */
public final class fqi extends iix implements ikg {

    /* renamed from: g */
    public static final fqi f10258g;

    /* renamed from: h */
    private static volatile ikn f10259h;

    /* renamed from: a */
    public int f10260a;

    /* renamed from: b */
    public String f10261b = "";

    /* renamed from: c */
    public ihw f10262c = ihw.f14203b;

    /* renamed from: d */
    public String f10263d = "";

    /* renamed from: e */
    public ije f10264e = ikq.f14400b;

    /* renamed from: f */
    public ije f10265f = ikq.f14400b;

    static {
        fqi fqi = new fqi();
        f10258g = fqi;
        iix.m13611a(fqi.class, (iix) fqi);
    }

    private fqi() {
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
            return m13609a((ikf) f10258g, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001\b\u0002\u0002\b\u0000\u0003\n\u0001\u0004\u001b\u0005\u001a", new Object[]{"a", "d", "b", "c", "e", fqj.class, "f"});
        } else if (i2 == 3) {
            return new fqi();
        } else {
            if (i2 == 4) {
                return new iir((float[]) null, (byte[][]) null);
            }
            if (i2 == 5) {
                return f10258g;
            }
            ikn ikn = f10259h;
            if (ikn == null) {
                synchronized (fqi.class) {
                    ikn = f10259h;
                    if (ikn == null) {
                        ikn = new iis(f10258g);
                        f10259h = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
