package p000;

/* renamed from: hko */
/* compiled from: PG */
public final class hko extends iix implements ikg {

    /* renamed from: d */
    public static final hko f12933d;

    /* renamed from: e */
    private static volatile ikn f12934e;

    /* renamed from: a */
    public int f12935a;

    /* renamed from: b */
    public long f12936b;

    /* renamed from: c */
    public int f12937c;

    static {
        hko hko = new hko();
        f12933d = hko;
        iix.m13611a(hko.class, (iix) hko);
    }

    private hko() {
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
            return m13609a((ikf) f12933d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0000\u0002\u0004\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new hko();
        } else {
            if (i2 == 4) {
                return new iir((char[][][]) null);
            }
            if (i2 == 5) {
                return f12933d;
            }
            ikn ikn = f12934e;
            if (ikn == null) {
                synchronized (hko.class) {
                    ikn = f12934e;
                    if (ikn == null) {
                        ikn = new iis(f12933d);
                        f12934e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
