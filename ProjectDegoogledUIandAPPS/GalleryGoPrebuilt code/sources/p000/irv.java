package p000;

/* renamed from: irv */
/* compiled from: PG */
public final class irv extends iix implements ikg {

    /* renamed from: e */
    public static final irv f14926e;

    /* renamed from: f */
    private static volatile ikn f14927f;

    /* renamed from: a */
    public int f14928a;

    /* renamed from: b */
    public int f14929b;

    /* renamed from: c */
    public int f14930c;

    /* renamed from: d */
    public int f14931d;

    static {
        irv irv = new irv();
        f14926e = irv;
        iix.m13611a(irv.class, (iix) irv);
    }

    private irv() {
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
            return m13609a((ikf) f14926e, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001\u0003\u0004\u0002", new Object[]{"a", "b", "c", "d"});
        } else if (i2 == 3) {
            return new irv();
        } else {
            if (i2 == 4) {
                return new iir((char[][][]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f14926e;
            }
            ikn ikn = f14927f;
            if (ikn == null) {
                synchronized (irv.class) {
                    ikn = f14927f;
                    if (ikn == null) {
                        ikn = new iis(f14926e);
                        f14927f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
