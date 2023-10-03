package p000;

/* renamed from: iru */
/* compiled from: PG */
public final class iru extends iix implements ikg {

    /* renamed from: d */
    public static final iru f14921d;

    /* renamed from: e */
    private static volatile ikn f14922e;

    /* renamed from: a */
    public int f14923a;

    /* renamed from: b */
    public long f14924b;

    /* renamed from: c */
    public long f14925c;

    static {
        iru iru = new iru();
        f14921d = iru;
        iix.m13611a(iru.class, (iix) iru);
    }

    private iru() {
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
            return m13609a((ikf) f14921d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0000\u0002\u0002\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new iru();
        } else {
            if (i2 == 4) {
                return new iir((char[]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f14921d;
            }
            ikn ikn = f14922e;
            if (ikn == null) {
                synchronized (iru.class) {
                    ikn = f14922e;
                    if (ikn == null) {
                        ikn = new iis(f14921d);
                        f14922e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
