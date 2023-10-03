package p000;

/* renamed from: igu */
/* compiled from: PG */
public final class igu extends iix implements ikg {

    /* renamed from: c */
    public static final igu f14152c;

    /* renamed from: d */
    private static volatile ikn f14153d;

    /* renamed from: a */
    public ijy f14154a = ijy.f14368b;

    /* renamed from: b */
    public ijy f14155b = ijy.f14368b;

    static {
        igu igu = new igu();
        f14152c = igu;
        iix.m13611a(igu.class, (iix) igu);
    }

    private igu() {
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
            return m13609a((ikf) f14152c, "\u0001\u0002\u0000\u0000\u0006\u0007\u0002\u0002\u0000\u0000\u00062\u00072", new Object[]{"a", igt.f14151a, "b", igs.f14150a});
        } else if (i2 == 3) {
            return new igu();
        } else {
            if (i2 == 4) {
                return new iir((float[][][]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f14152c;
            }
            ikn ikn = f14153d;
            if (ikn == null) {
                synchronized (igu.class) {
                    ikn = f14153d;
                    if (ikn == null) {
                        ikn = new iis(f14152c);
                        f14153d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
