package p000;

/* renamed from: igw */
/* compiled from: PG */
public final class igw extends iiu implements iiv {

    /* renamed from: d */
    public static final igw f14160d;

    /* renamed from: f */
    private static volatile ikn f14161f;

    /* renamed from: a */
    public int f14162a;

    /* renamed from: b */
    public long f14163b;

    /* renamed from: c */
    public ije f14164c = ikq.f14400b;

    /* renamed from: e */
    private byte f14165e = 2;

    static {
        igw igw = new igw();
        f14160d = igw;
        iix.m13611a(igw.class, (iix) igw);
    }

    private igw() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f14165e);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f14165e = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f14160d, "\u0001\u0002\u0000\u0001\u0001\u0004\u0002\u0000\u0001\u0000\u0001\u0005\u0000\u0004\u001b", new Object[]{"a", "b", "c", igx.class});
        } else if (i2 == 3) {
            return new igw();
        } else {
            if (i2 == 4) {
                return new iit((int[]) null);
            }
            if (i2 == 5) {
                return f14160d;
            }
            ikn ikn = f14161f;
            if (ikn == null) {
                synchronized (igw.class) {
                    ikn = f14161f;
                    if (ikn == null) {
                        ikn = new iis(f14160d);
                        f14161f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
