package p000;

/* renamed from: ifz */
/* compiled from: PG */
public final class ifz extends iix implements ikg {

    /* renamed from: f */
    public static final ifz f14037f;

    /* renamed from: h */
    private static volatile ikn f14038h;

    /* renamed from: a */
    public int f14039a;

    /* renamed from: b */
    public ifw f14040b;

    /* renamed from: c */
    public iap f14041c;

    /* renamed from: d */
    public ifx f14042d;

    /* renamed from: e */
    public ije f14043e;

    /* renamed from: g */
    private byte f14044g = 2;

    static {
        ifz ifz = new ifz();
        f14037f = ifz;
        iix.m13611a(ifz.class, (iix) ifz);
    }

    private ifz() {
        ihw ihw = ihw.f14203b;
        this.f14043e = ikq.f14400b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f14044g);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f14044g = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f14037f, "\u0001\u0004\u0000\u0001\u0002\t\u0004\u0000\u0001\u0001\u0002\t\u0000\u0003Љ\u0001\u0007\t\u0002\t\u001b", new Object[]{"a", "b", "c", "d", "e", igc.class});
        } else if (i2 == 3) {
            return new ifz();
        } else {
            if (i2 == 4) {
                return new iir((boolean[][][]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f14037f;
            }
            ikn ikn = f14038h;
            if (ikn == null) {
                synchronized (ifz.class) {
                    ikn = f14038h;
                    if (ikn == null) {
                        ikn = new iis(f14037f);
                        f14038h = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
