package p000;

/* renamed from: cqe */
/* compiled from: PG */
public final class cqe extends iix implements ikg {

    /* renamed from: d */
    public static final cqe f5414d;

    /* renamed from: e */
    private static volatile ikn f5415e;

    /* renamed from: a */
    public int f5416a;

    /* renamed from: b */
    public int f5417b = 3;

    /* renamed from: c */
    public int f5418c = -1;

    static {
        cqe cqe = new cqe();
        f5414d = cqe;
        iix.m13611a(cqe.class, (iix) cqe);
    }

    private cqe() {
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
            return m13609a((ikf) f5414d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\u0004\u0001", new Object[]{"a", "b", cqf.f5419a, "c"});
        } else if (i2 == 3) {
            return new cqe();
        } else {
            if (i2 == 4) {
                return new iir((boolean[][][]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f5414d;
            }
            ikn ikn = f5415e;
            if (ikn == null) {
                synchronized (cqe.class) {
                    ikn = f5415e;
                    if (ikn == null) {
                        ikn = new iis(f5414d);
                        f5415e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
