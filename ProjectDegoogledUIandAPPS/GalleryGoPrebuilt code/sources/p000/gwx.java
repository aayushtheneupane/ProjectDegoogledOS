package p000;

/* renamed from: gwx */
/* compiled from: PG */
public final class gwx extends iix implements ikg {

    /* renamed from: i */
    public static final gwx f12223i;

    /* renamed from: j */
    private static volatile ikn f12224j;

    /* renamed from: a */
    public int f12225a;

    /* renamed from: b */
    public String f12226b = "";

    /* renamed from: c */
    public String f12227c = "";

    /* renamed from: d */
    public ihw f12228d = ihw.f14203b;

    /* renamed from: e */
    public ije f12229e = ikq.f14400b;

    /* renamed from: f */
    public ije f12230f = ikq.f14400b;

    /* renamed from: g */
    public int f12231g;

    /* renamed from: h */
    public long f12232h;

    static {
        gwx gwx = new gwx();
        f12223i = gwx;
        iix.m13611a(gwx.class, (iix) gwx);
    }

    private gwx() {
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
            return m13609a((ikf) f12223i, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0002\u0000\u0001\b\u0000\u0002\b\u0001\u0003\n\u0002\u0004\u001b\u0005\u001a\u0006\u0006\u0003\u0007\u0005\u0004", new Object[]{"a", "b", "c", "d", "e", gxa.class, "f", "g", "h"});
        } else if (i2 == 3) {
            return new gwx();
        } else {
            if (i2 == 4) {
                return new iir((boolean[][]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f12223i;
            }
            ikn ikn = f12224j;
            if (ikn == null) {
                synchronized (gwx.class) {
                    ikn = f12224j;
                    if (ikn == null) {
                        ikn = new iis(f12223i);
                        f12224j = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
