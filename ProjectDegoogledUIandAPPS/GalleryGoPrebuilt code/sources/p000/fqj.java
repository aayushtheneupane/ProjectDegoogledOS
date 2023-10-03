package p000;

/* renamed from: fqj */
/* compiled from: PG */
public final class fqj extends iix implements ikg {

    /* renamed from: e */
    public static final fqj f10266e;

    /* renamed from: f */
    private static volatile ikn f10267f;

    /* renamed from: a */
    public int f10268a;

    /* renamed from: b */
    public int f10269b = 0;

    /* renamed from: c */
    public Object f10270c;

    /* renamed from: d */
    public String f10271d = "";

    static {
        fqj fqj = new fqj();
        f10266e = fqj;
        iix.m13611a(fqj.class, (iix) fqj);
    }

    private fqj() {
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
            return m13609a((ikf) f10266e, "\u0001\u0006\u0001\u0001\u0001\n\u0006\u0000\u0000\u0000\u00018\u0000\u0002:\u0000\u00033\u0000\u0004;\u0000\u0005=\u0000\n\b\u0000", new Object[]{"c", "b", "a", "d"});
        } else if (i2 == 3) {
            return new fqj();
        } else {
            if (i2 == 4) {
                return new iir((byte[]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f10266e;
            }
            ikn ikn = f10267f;
            if (ikn == null) {
                synchronized (fqj.class) {
                    ikn = f10267f;
                    if (ikn == null) {
                        ikn = new iis(f10266e);
                        f10267f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
