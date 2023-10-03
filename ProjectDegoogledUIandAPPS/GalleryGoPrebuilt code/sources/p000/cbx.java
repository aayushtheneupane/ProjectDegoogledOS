package p000;

/* renamed from: cbx */
/* compiled from: PG */
public final class cbx extends iix implements ikg {

    /* renamed from: f */
    public static final cbx f4030f;

    /* renamed from: g */
    private static volatile ikn f4031g;

    /* renamed from: a */
    public int f4032a;

    /* renamed from: b */
    public int f4033b = 0;

    /* renamed from: c */
    public Object f4034c;

    /* renamed from: d */
    public boolean f4035d;

    /* renamed from: e */
    public String f4036e = "";

    static {
        cbx cbx = new cbx();
        f4030f = cbx;
        iix.m13611a(cbx.class, (iix) cbx);
    }

    private cbx() {
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
            return m13609a((ikf) f4030f, "\u0001\u0004\u0001\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001<\u0000\u0002\u0007\u0002\u0003\b\u0003\u0004<\u0000", new Object[]{"c", "b", "a", cxi.class, "d", "e", ceq.class});
        } else if (i2 == 3) {
            return new cbx();
        } else {
            if (i2 == 4) {
                return new iir((byte[][][]) null);
            }
            if (i2 == 5) {
                return f4030f;
            }
            ikn ikn = f4031g;
            if (ikn == null) {
                synchronized (cbx.class) {
                    ikn = f4031g;
                    if (ikn == null) {
                        ikn = new iis(f4030f);
                        f4031g = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
