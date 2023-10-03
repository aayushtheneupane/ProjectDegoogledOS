package p000;

/* renamed from: igm */
/* compiled from: PG */
public final class igm extends iix implements ikg {

    /* renamed from: c */
    public static final igm f14125c;

    /* renamed from: d */
    private static volatile ikn f14126d;

    /* renamed from: a */
    public int f14127a;

    /* renamed from: b */
    public long f14128b;

    static {
        igm igm = new igm();
        f14125c = igm;
        iix.m13611a(igm.class, (iix) igm);
    }

    private igm() {
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
            return m13609a((ikf) f14125c, "\u0001\u0001\u0000\u0001\u0004\u0004\u0001\u0000\u0000\u0000\u0004\u0002\u0001", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new igm();
        } else {
            if (i2 == 4) {
                return new iir((short[][]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f14125c;
            }
            ikn ikn = f14126d;
            if (ikn == null) {
                synchronized (igm.class) {
                    ikn = f14126d;
                    if (ikn == null) {
                        ikn = new iis(f14125c);
                        f14126d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
