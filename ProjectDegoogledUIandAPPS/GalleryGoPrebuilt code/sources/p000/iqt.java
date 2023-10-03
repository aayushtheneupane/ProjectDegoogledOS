package p000;

/* renamed from: iqt */
/* compiled from: PG */
public final class iqt extends iix implements ikg {

    /* renamed from: i */
    public static final iqt f14694i;

    /* renamed from: j */
    private static volatile ikn f14695j;

    /* renamed from: a */
    public int f14696a;

    /* renamed from: b */
    public long f14697b;

    /* renamed from: c */
    public long f14698c;

    /* renamed from: d */
    public long f14699d;

    /* renamed from: e */
    public long f14700e;

    /* renamed from: f */
    public long f14701f;

    /* renamed from: g */
    public long f14702g;

    /* renamed from: h */
    public iqq f14703h;

    static {
        iqt iqt = new iqt();
        f14694i = iqt;
        iix.m13611a(iqt.class, (iix) iqt);
    }

    private iqt() {
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
            return m13609a((ikf) f14694i, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001\u0002\u0000\u0002\u0002\u0001\u0003\u0002\u0002\u0004\u0002\u0003\u0005\u0002\u0004\u0006\u0002\u0005\u0007\t\u0006", new Object[]{"a", "b", "c", "d", "e", "f", "g", "h"});
        } else if (i2 == 3) {
            return new iqt();
        } else {
            if (i2 == 4) {
                return new iir((short[][][]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f14694i;
            }
            ikn ikn = f14695j;
            if (ikn == null) {
                synchronized (iqt.class) {
                    ikn = f14695j;
                    if (ikn == null) {
                        ikn = new iis(f14694i);
                        f14695j = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
