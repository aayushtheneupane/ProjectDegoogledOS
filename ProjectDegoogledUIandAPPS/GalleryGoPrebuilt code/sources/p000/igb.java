package p000;

/* renamed from: igb */
/* compiled from: PG */
public final class igb extends iix implements ikg {

    /* renamed from: c */
    public static final igb f14049c;

    /* renamed from: d */
    private static volatile ikn f14050d;

    /* renamed from: a */
    public int f14051a;

    /* renamed from: b */
    public int f14052b;

    static {
        igb igb = new igb();
        f14049c = igb;
        iix.m13611a(igb.class, (iix) igb);
    }

    private igb() {
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
            return m13609a((ikf) f14049c, "\u0001\u0001\u0000\u0001\u0006\u0006\u0001\u0000\u0000\u0000\u0006\f\u0000", new Object[]{"a", "b", iga.f14048a});
        } else if (i2 == 3) {
            return new igb();
        } else {
            if (i2 == 4) {
                return new iir((int[][][]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f14049c;
            }
            ikn ikn = f14050d;
            if (ikn == null) {
                synchronized (igb.class) {
                    ikn = f14050d;
                    if (ikn == null) {
                        ikn = new iis(f14049c);
                        f14050d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
