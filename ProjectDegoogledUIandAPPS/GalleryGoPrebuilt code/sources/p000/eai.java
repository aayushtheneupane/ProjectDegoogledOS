package p000;

/* renamed from: eai */
/* compiled from: PG */
public final class eai extends iix implements ikg {

    /* renamed from: e */
    public static final eai f7772e;

    /* renamed from: f */
    private static volatile ikn f7773f;

    /* renamed from: a */
    public int f7774a;

    /* renamed from: b */
    public int f7775b;

    /* renamed from: c */
    public int f7776c = -1;

    /* renamed from: d */
    public boolean f7777d;

    static {
        eai eai = new eai();
        f7772e = eai;
        iix.m13611a(eai.class, (iix) eai);
    }

    private eai() {
    }

    /* renamed from: a */
    public static /* synthetic */ void m7019a(eai eai) {
        eai.f7774a |= 4;
        eai.f7777d = true;
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
            return m13609a((ikf) f7772e, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001\u0003\u0007\u0002", new Object[]{"a", "b", "c", "d"});
        } else if (i2 == 3) {
            return new eai();
        } else {
            if (i2 == 4) {
                return new iir((char[][][]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f7772e;
            }
            ikn ikn = f7773f;
            if (ikn == null) {
                synchronized (eai.class) {
                    ikn = f7773f;
                    if (ikn == null) {
                        ikn = new iis(f7772e);
                        f7773f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
