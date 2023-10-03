package p000;

/* renamed from: gbw */
/* compiled from: PG */
public final class gbw extends iix implements ikg {

    /* renamed from: b */
    public static final gbw f10888b;

    /* renamed from: d */
    private static volatile ikn f10889d;

    /* renamed from: a */
    public ije f10890a = ikq.f14400b;

    /* renamed from: c */
    private byte f10891c = 2;

    static {
        gbw gbw = new gbw();
        f10888b = gbw;
        iix.m13611a(gbw.class, (iix) gbw);
    }

    private gbw() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f10891c);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f10891c = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f10888b, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0001Л", new Object[]{"a", gbv.class});
        } else if (i2 == 3) {
            return new gbw();
        } else {
            if (i2 == 4) {
                return new iir((boolean[]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f10888b;
            }
            ikn ikn = f10889d;
            if (ikn == null) {
                synchronized (gbw.class) {
                    ikn = f10889d;
                    if (ikn == null) {
                        ikn = new iis(f10888b);
                        f10889d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
