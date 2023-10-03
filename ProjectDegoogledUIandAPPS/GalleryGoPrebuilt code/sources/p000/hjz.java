package p000;

/* renamed from: hjz */
/* compiled from: PG */
public final class hjz extends iix implements ikg {

    /* renamed from: c */
    public static final hjz f12898c;

    /* renamed from: d */
    private static volatile ikn f12899d;

    /* renamed from: a */
    public int f12900a;

    /* renamed from: b */
    public String f12901b = "";

    static {
        hjz hjz = new hjz();
        f12898c = hjz;
        iix.m13611a(hjz.class, (iix) hjz);
    }

    private hjz() {
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
            return m13609a((ikf) f12898c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\b\u0000", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new hjz();
        } else {
            if (i2 == 4) {
                return new iir((boolean[][]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f12898c;
            }
            ikn ikn = f12899d;
            if (ikn == null) {
                synchronized (hjz.class) {
                    ikn = f12899d;
                    if (ikn == null) {
                        ikn = new iis(f12898c);
                        f12899d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
