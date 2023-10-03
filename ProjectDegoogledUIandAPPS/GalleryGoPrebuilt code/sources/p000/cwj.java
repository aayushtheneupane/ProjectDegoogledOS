package p000;

/* renamed from: cwj */
/* compiled from: PG */
public final class cwj extends iix implements ikg {

    /* renamed from: c */
    public static final cwj f5798c;

    /* renamed from: d */
    private static volatile ikn f5799d;

    /* renamed from: a */
    public int f5800a;

    /* renamed from: b */
    public boolean f5801b;

    static {
        cwj cwj = new cwj();
        f5798c = cwj;
        iix.m13611a(cwj.class, (iix) cwj);
    }

    private cwj() {
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
            return m13609a((ikf) f5798c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0007\u0000", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new cwj();
        } else {
            if (i2 == 4) {
                return new iir((float[][][]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f5798c;
            }
            ikn ikn = f5799d;
            if (ikn == null) {
                synchronized (cwj.class) {
                    ikn = f5799d;
                    if (ikn == null) {
                        ikn = new iis(f5798c);
                        f5799d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
