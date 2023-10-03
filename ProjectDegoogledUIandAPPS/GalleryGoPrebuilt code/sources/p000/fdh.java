package p000;

/* renamed from: fdh */
/* compiled from: PG */
public final class fdh extends iix implements ikg {

    /* renamed from: e */
    public static final fdh f9308e;

    /* renamed from: g */
    private static volatile ikn f9309g;

    /* renamed from: a */
    public int f9310a;

    /* renamed from: b */
    public ial f9311b;

    /* renamed from: c */
    public int f9312c;

    /* renamed from: d */
    public int f9313d;

    /* renamed from: f */
    private byte f9314f = 2;

    static {
        fdh fdh = new fdh();
        f9308e = fdh;
        iix.m13611a(fdh.class, (iix) fdh);
    }

    private fdh() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f9314f);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f9314f = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f9308e, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0001\u0001Љ\u0000\u0002\u0004\u0001\u0003\f\u0002", new Object[]{"a", "b", "c", "d", iaj.f13724a});
        } else if (i2 == 3) {
            return new fdh();
        } else {
            if (i2 == 4) {
                return new iir((float[][]) null, (short[]) null);
            }
            if (i2 == 5) {
                return f9308e;
            }
            ikn ikn = f9309g;
            if (ikn == null) {
                synchronized (fdh.class) {
                    ikn = f9309g;
                    if (ikn == null) {
                        ikn = new iis(f9308e);
                        f9309g = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
