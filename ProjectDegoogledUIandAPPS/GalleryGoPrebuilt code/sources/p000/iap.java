package p000;

/* renamed from: iap */
/* compiled from: PG */
public final class iap extends iix implements ikg {

    /* renamed from: i */
    public static final iap f13738i;

    /* renamed from: k */
    private static volatile ikn f13739k;

    /* renamed from: a */
    public int f13740a;

    /* renamed from: b */
    public ian f13741b;

    /* renamed from: c */
    public String f13742c = "";

    /* renamed from: d */
    public int f13743d;

    /* renamed from: e */
    public String f13744e = "";

    /* renamed from: f */
    public String f13745f = "";

    /* renamed from: g */
    public String f13746g = "";

    /* renamed from: h */
    public iav f13747h;

    /* renamed from: j */
    private byte f13748j = 2;

    static {
        iap iap = new iap();
        f13738i = iap;
        iix.m13611a(iap.class, (iix) iap);
    }

    private iap() {
        ikq ikq = ikq.f14400b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f13748j);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f13748j = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f13738i, "\u0001\u0007\u0000\u0001\u0001\b\u0007\u0000\u0000\u0004\u0001ԉ\u0000\u0002Ԉ\u0001\u0003Ԅ\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0006\bЉ\b", new Object[]{"a", "b", "c", "d", "e", "f", "g", "h"});
        } else if (i2 == 3) {
            return new iap();
        } else {
            if (i2 == 4) {
                return new iao((byte[]) null);
            }
            if (i2 == 5) {
                return f13738i;
            }
            ikn ikn = f13739k;
            if (ikn == null) {
                synchronized (iap.class) {
                    ikn = f13739k;
                    if (ikn == null) {
                        ikn = new iis(f13738i);
                        f13739k = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
