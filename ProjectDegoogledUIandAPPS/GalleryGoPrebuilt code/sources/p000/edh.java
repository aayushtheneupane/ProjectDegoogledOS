package p000;

/* renamed from: edh */
/* compiled from: PG */
public final class edh extends iix implements ikg {

    /* renamed from: c */
    public static final edh f8035c;

    /* renamed from: d */
    private static volatile ikn f8036d;

    /* renamed from: a */
    public int f8037a;

    /* renamed from: b */
    public boolean f8038b;

    static {
        edh edh = new edh();
        f8035c = edh;
        iix.m13611a(edh.class, (iix) edh);
    }

    private edh() {
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
            return m13609a((ikf) f8035c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0007\u0000", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new edh();
        } else {
            if (i2 == 4) {
                return new iir((short[][][]) null);
            }
            if (i2 == 5) {
                return f8035c;
            }
            ikn ikn = f8036d;
            if (ikn == null) {
                synchronized (edh.class) {
                    ikn = f8036d;
                    if (ikn == null) {
                        ikn = new iis(f8035c);
                        f8036d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
