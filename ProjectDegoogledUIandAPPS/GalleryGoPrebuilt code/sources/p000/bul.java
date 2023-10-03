package p000;

/* renamed from: bul */
/* compiled from: PG */
public final class bul extends iix implements ikg {

    /* renamed from: j */
    public static final bul f3628j;

    /* renamed from: k */
    private static volatile ikn f3629k;

    /* renamed from: a */
    public int f3630a;

    /* renamed from: b */
    public int f3631b = 0;

    /* renamed from: c */
    public Object f3632c;

    /* renamed from: d */
    public boolean f3633d;

    /* renamed from: e */
    public String f3634e = "";

    /* renamed from: f */
    public bwy f3635f;

    /* renamed from: g */
    public float f3636g;

    /* renamed from: h */
    public boolean f3637h;

    /* renamed from: i */
    public boolean f3638i = true;

    static {
        bul bul = new bul();
        f3628j = bul;
        iix.m13611a(bul.class, (iix) bul);
    }

    private bul() {
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
            return m13609a((ikf) f3628j, "\u0001\b\u0001\u0001\u0001\b\b\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003\u0007\u0002\u0004\b\u0003\u0005\t\u0004\u0006\u0001\u0005\u0007\u0007\u0006\b\u0007\u0007", new Object[]{"c", "b", "a", cxi.class, ceq.class, "d", "e", "f", "g", "h", "i"});
        } else if (i2 == 3) {
            return new bul();
        } else {
            if (i2 == 4) {
                return new iir((char[]) null, (byte[][]) null);
            }
            if (i2 == 5) {
                return f3628j;
            }
            ikn ikn = f3629k;
            if (ikn == null) {
                synchronized (bul.class) {
                    ikn = f3629k;
                    if (ikn == null) {
                        ikn = new iis(f3628j);
                        f3629k = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
