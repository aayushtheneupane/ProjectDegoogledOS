package p000;

/* renamed from: egv */
/* compiled from: PG */
public final class egv extends iix implements ikg {

    /* renamed from: d */
    public static final egv f8228d;

    /* renamed from: e */
    private static volatile ikn f8229e;

    /* renamed from: a */
    public int f8230a;

    /* renamed from: b */
    public int f8231b;

    /* renamed from: c */
    public int f8232c;

    static {
        egv egv = new egv();
        f8228d = egv;
        iix.m13611a(egv.class, (iix) egv);
    }

    private egv() {
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
            return m13609a((ikf) f8228d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new egv();
        } else {
            if (i2 == 4) {
                return new iir((byte[]) null, (byte[][]) null);
            }
            if (i2 == 5) {
                return f8228d;
            }
            ikn ikn = f8229e;
            if (ikn == null) {
                synchronized (egv.class) {
                    ikn = f8229e;
                    if (ikn == null) {
                        ikn = new iis(f8228d);
                        f8229e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
