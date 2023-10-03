package p000;

/* renamed from: hjx */
/* compiled from: PG */
public final class hjx extends iix implements ikg {

    /* renamed from: f */
    public static final hjx f12884f;

    /* renamed from: g */
    private static volatile ikn f12885g;

    /* renamed from: a */
    public int f12886a;

    /* renamed from: b */
    public hka f12887b;

    /* renamed from: c */
    public long f12888c;

    /* renamed from: d */
    public long f12889d;

    /* renamed from: e */
    public int f12890e;

    static {
        hjx hjx = new hjx();
        f12884f = hjx;
        iix.m13611a(hjx.class, (iix) hjx);
    }

    private hjx() {
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
            return m13609a((ikf) f12884f, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0000\u0002\u0002\u0001\u0003\u0002\u0002\u0004\u0004\u0003", new Object[]{"a", "b", "c", "d", "e"});
        } else if (i2 == 3) {
            return new hjx();
        } else {
            if (i2 == 4) {
                return new iir((char[][][]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f12884f;
            }
            ikn ikn = f12885g;
            if (ikn == null) {
                synchronized (hjx.class) {
                    ikn = f12885g;
                    if (ikn == null) {
                        ikn = new iis(f12884f);
                        f12885g = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
