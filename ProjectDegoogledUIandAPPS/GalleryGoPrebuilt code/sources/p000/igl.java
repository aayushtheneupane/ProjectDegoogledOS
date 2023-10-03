package p000;

/* renamed from: igl */
/* compiled from: PG */
public final class igl extends iix implements ikg {

    /* renamed from: d */
    public static final igl f14119d;

    /* renamed from: f */
    private static volatile ikn f14120f;

    /* renamed from: a */
    public int f14121a;

    /* renamed from: b */
    public igj f14122b;

    /* renamed from: c */
    public igo f14123c;

    /* renamed from: e */
    private byte f14124e = 2;

    static {
        igl igl = new igl();
        f14119d = igl;
        iix.m13611a(igl.class, (iix) igl);
    }

    private igl() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f14124e);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f14124e = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f14119d, "\u0001\u0002\u0000\u0001\u0001\u0003\u0002\u0000\u0000\u0001\u0001\t\u0000\u0003Љ\u0002", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new igl();
        } else {
            if (i2 == 4) {
                return new iir((boolean[][][]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f14119d;
            }
            ikn ikn = f14120f;
            if (ikn == null) {
                synchronized (igl.class) {
                    ikn = f14120f;
                    if (ikn == null) {
                        ikn = new iis(f14119d);
                        f14120f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
