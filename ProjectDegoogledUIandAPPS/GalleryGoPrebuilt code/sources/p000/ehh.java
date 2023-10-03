package p000;

/* renamed from: ehh */
/* compiled from: PG */
public final class ehh extends iix implements ikg {

    /* renamed from: d */
    public static final ehh f8289d;

    /* renamed from: e */
    private static volatile ikn f8290e;

    /* renamed from: a */
    public int f8291a;

    /* renamed from: b */
    public int f8292b;

    /* renamed from: c */
    public int f8293c;

    static {
        ehh ehh = new ehh();
        f8289d = ehh;
        iix.m13611a(ehh.class, (iix) ehh);
    }

    private ehh() {
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
            return m13609a((ikf) f8289d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001", new Object[]{"a", "b", cjm.m4400a(), "c", ehg.f8288a});
        } else if (i2 == 3) {
            return new ehh();
        } else {
            if (i2 == 4) {
                return new iir((float[][]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f8289d;
            }
            ikn ikn = f8290e;
            if (ikn == null) {
                synchronized (ehh.class) {
                    ikn = f8290e;
                    if (ikn == null) {
                        ikn = new iis(f8289d);
                        f8290e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
