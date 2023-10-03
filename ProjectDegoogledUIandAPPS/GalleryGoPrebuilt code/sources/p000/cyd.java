package p000;

/* renamed from: cyd */
/* compiled from: PG */
public final class cyd extends iix implements ikg {

    /* renamed from: i */
    public static final cyd f5985i;

    /* renamed from: j */
    private static volatile ikn f5986j;

    /* renamed from: a */
    public int f5987a;

    /* renamed from: b */
    public String f5988b = "";

    /* renamed from: c */
    public long f5989c;

    /* renamed from: d */
    public long f5990d;

    /* renamed from: e */
    public boolean f5991e = true;

    /* renamed from: f */
    public int f5992f;

    /* renamed from: g */
    public String f5993g = "";

    /* renamed from: h */
    public ehf f5994h;

    static {
        cyd cyd = new cyd();
        f5985i = cyd;
        iix.m13611a(cyd.class, (iix) cyd);
    }

    private cyd() {
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
            return m13609a((ikf) f5985i, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001\u0003\u0002\u0002\u0004\u0007\u0003\u0005\f\u0004\u0006\b\u0005\u0007\t\u0006", new Object[]{"a", "b", "c", "d", "e", "f", cxh.m5593a(), "g", "h"});
        } else if (i2 == 3) {
            return new cyd();
        } else {
            if (i2 == 4) {
                return new iir((byte[][][]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f5985i;
            }
            ikn ikn = f5986j;
            if (ikn == null) {
                synchronized (cyd.class) {
                    ikn = f5986j;
                    if (ikn == null) {
                        ikn = new iis(f5985i);
                        f5986j = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
