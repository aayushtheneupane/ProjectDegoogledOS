package p000;

/* renamed from: dwi */
/* compiled from: PG */
public final class dwi extends iix implements ikg {

    /* renamed from: h */
    public static final dwi f7492h;

    /* renamed from: i */
    private static volatile ikn f7493i;

    /* renamed from: a */
    public int f7494a;

    /* renamed from: b */
    public cxd f7495b;

    /* renamed from: c */
    public boolean f7496c;

    /* renamed from: d */
    public cqe f7497d;

    /* renamed from: e */
    public int f7498e = 2;

    /* renamed from: f */
    public int f7499f;

    /* renamed from: g */
    public int f7500g;

    static {
        dwi dwi = new dwi();
        f7492h = dwi;
        iix.m13611a(dwi.class, (iix) dwi);
    }

    private dwi() {
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
            return m13609a((ikf) f7492h, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\t\u0000\u0002\u0007\u0001\u0003\t\u0002\u0004\f\u0003\u0005\f\u0004\u0006\u0004\u0005", new Object[]{"a", "b", "c", "d", "e", cpi.m5218a(), "f", dxy.m6882a(), "g"});
        } else if (i2 == 3) {
            return new dwi();
        } else {
            if (i2 == 4) {
                return new iir((short[][]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f7492h;
            }
            ikn ikn = f7493i;
            if (ikn == null) {
                synchronized (dwi.class) {
                    ikn = f7493i;
                    if (ikn == null) {
                        ikn = new iis(f7492h);
                        f7493i = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
