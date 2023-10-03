package p000;

/* renamed from: gcc */
/* compiled from: PG */
public final class gcc extends iix implements ikg {

    /* renamed from: b */
    public static final gcc f10911b;

    /* renamed from: c */
    private static volatile ikn f10912c;

    /* renamed from: a */
    public ije f10913a = ikq.f14400b;

    static {
        gcc gcc = new gcc();
        f10911b = gcc;
        iix.m13611a(gcc.class, (iix) gcc);
    }

    private gcc() {
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
            return m13609a((ikf) f10911b, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"a", gcd.class});
        } else if (i2 == 3) {
            return new gcc();
        } else {
            if (i2 == 4) {
                return new iir((byte[]) null, (char[][]) null);
            }
            if (i2 == 5) {
                return f10911b;
            }
            ikn ikn = f10912c;
            if (ikn == null) {
                synchronized (gcc.class) {
                    ikn = f10912c;
                    if (ikn == null) {
                        ikn = new iis(f10911b);
                        f10912c = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
