package p000;

/* renamed from: gcf */
/* compiled from: PG */
public final class gcf extends iix implements ikg {

    /* renamed from: c */
    public static final gcf f10920c;

    /* renamed from: d */
    private static volatile ikn f10921d;

    /* renamed from: a */
    public int f10922a = 0;

    /* renamed from: b */
    public Object f10923b;

    static {
        gcf gcf = new gcf();
        f10920c = gcf;
        iix.m13611a(gcf.class, (iix) gcf);
    }

    private gcf() {
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
            return m13609a((ikf) f10920c, "\u0001\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001;\u0000\u0002=\u0000", new Object[]{"b", "a"});
        } else if (i2 == 3) {
            return new gcf();
        } else {
            if (i2 == 4) {
                return new iir((float[]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f10920c;
            }
            ikn ikn = f10921d;
            if (ikn == null) {
                synchronized (gcf.class) {
                    ikn = f10921d;
                    if (ikn == null) {
                        ikn = new iis(f10920c);
                        f10921d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
