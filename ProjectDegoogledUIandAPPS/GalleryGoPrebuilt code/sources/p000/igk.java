package p000;

/* renamed from: igk */
/* compiled from: PG */
public final class igk extends iix implements ikg {

    /* renamed from: e */
    public static final igk f14112e;

    /* renamed from: g */
    private static volatile ikn f14113g;

    /* renamed from: a */
    public int f14114a;

    /* renamed from: b */
    public igh f14115b;

    /* renamed from: c */
    public igl f14116c;

    /* renamed from: d */
    public igv f14117d;

    /* renamed from: f */
    private byte f14118f = 2;

    static {
        igk igk = new igk();
        f14112e = igk;
        iix.m13611a(igk.class, (iix) igk);
    }

    private igk() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f14118f);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f14118f = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f14112e, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0003\u0001Љ\u0000\u0002Љ\u0001\u0003Љ\u0002", new Object[]{"a", "b", "c", "d"});
        } else if (i2 == 3) {
            return new igk();
        } else {
            if (i2 == 4) {
                return new iir((int[][][]) null);
            }
            if (i2 == 5) {
                return f14112e;
            }
            ikn ikn = f14113g;
            if (ikn == null) {
                synchronized (igk.class) {
                    ikn = f14113g;
                    if (ikn == null) {
                        ikn = new iis(f14112e);
                        f14113g = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
