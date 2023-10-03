package p000;

/* renamed from: iqq */
/* compiled from: PG */
public final class iqq extends iix implements ikg {

    /* renamed from: d */
    public static final iqq f14681d;

    /* renamed from: e */
    private static volatile ikn f14682e;

    /* renamed from: a */
    public int f14683a;

    /* renamed from: b */
    public long f14684b;

    /* renamed from: c */
    public String f14685c = "";

    static {
        iqq iqq = new iqq();
        f14681d = iqq;
        iix.m13611a(iqq.class, (iix) iqq);
    }

    private iqq() {
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
            return m13609a((ikf) f14681d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0005\u0000\u0002\b\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new iqq();
        } else {
            if (i2 == 4) {
                return new iir((int[]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f14681d;
            }
            ikn ikn = f14682e;
            if (ikn == null) {
                synchronized (iqq.class) {
                    ikn = f14682e;
                    if (ikn == null) {
                        ikn = new iis(f14681d);
                        f14682e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
