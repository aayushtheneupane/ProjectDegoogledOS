package p000;

/* renamed from: igv */
/* compiled from: PG */
public final class igv extends iix implements ikg {

    /* renamed from: b */
    public static final igv f14156b;

    /* renamed from: d */
    private static volatile ikn f14157d;

    /* renamed from: a */
    public ije f14158a = ikq.f14400b;

    /* renamed from: c */
    private byte f14159c = 2;

    static {
        igv igv = new igv();
        f14156b = igv;
        iix.m13611a(igv.class, (iix) igv);
    }

    private igv() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f14159c);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f14159c = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f14156b, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0001Л", new Object[]{"a", igw.class});
        } else if (i2 == 3) {
            return new igv();
        } else {
            if (i2 == 4) {
                return new iir((char[][]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f14156b;
            }
            ikn ikn = f14157d;
            if (ikn == null) {
                synchronized (igv.class) {
                    ikn = f14157d;
                    if (ikn == null) {
                        ikn = new iis(f14156b);
                        f14157d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
