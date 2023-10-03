package p000;

/* renamed from: ira */
/* compiled from: PG */
public final class ira extends iix implements ikg {

    /* renamed from: c */
    public static final ira f14814c;

    /* renamed from: d */
    private static volatile ikn f14815d;

    /* renamed from: a */
    public int f14816a;

    /* renamed from: b */
    public iqy f14817b;

    static {
        ira ira = new ira();
        f14814c = ira;
        iix.m13611a(ira.class, (iix) ira);
    }

    private ira() {
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
            return m13609a((ikf) f14814c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t\u0000", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new ira();
        } else {
            if (i2 == 4) {
                return new iir((byte[][]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f14814c;
            }
            ikn ikn = f14815d;
            if (ikn == null) {
                synchronized (ira.class) {
                    ikn = f14815d;
                    if (ikn == null) {
                        ikn = new iis(f14814c);
                        f14815d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
