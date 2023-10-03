package p000;

/* renamed from: csk */
/* compiled from: PG */
public final class csk extends iix implements ikg {

    /* renamed from: d */
    public static final csk f5574d;

    /* renamed from: e */
    private static volatile ikn f5575e;

    /* renamed from: a */
    public int f5576a;

    /* renamed from: b */
    public float f5577b;

    /* renamed from: c */
    public long f5578c;

    static {
        csk csk = new csk();
        f5574d = csk;
        iix.m13611a(csk.class, (iix) csk);
    }

    private csk() {
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
            return m13609a((ikf) f5574d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0001\u0000\u0002\u0003\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new csk();
        } else {
            if (i2 == 4) {
                return new iir((float[][]) null);
            }
            if (i2 == 5) {
                return f5574d;
            }
            ikn ikn = f5575e;
            if (ikn == null) {
                synchronized (csk.class) {
                    ikn = f5575e;
                    if (ikn == null) {
                        ikn = new iis(f5574d);
                        f5575e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
