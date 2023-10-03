package p000;

import com.google.android.apps.photosgo.media.Filter$Category;

/* renamed from: cxd */
/* compiled from: PG */
public final class cxd extends iix implements ikg {

    /* renamed from: h */
    public static final cxd f5884h;

    /* renamed from: i */
    private static volatile ikn f5885i;

    /* renamed from: a */
    public int f5886a;

    /* renamed from: b */
    public int f5887b = 0;

    /* renamed from: c */
    public Object f5888c;

    /* renamed from: d */
    public String f5889d = "";

    /* renamed from: e */
    public String f5890e = "";

    /* renamed from: f */
    public boolean f5891f;

    /* renamed from: g */
    public int f5892g = 1;

    static {
        cxd cxd = new cxd();
        f5884h = cxd;
        iix.m13611a(cxd.class, (iix) cxd);
    }

    private cxd() {
    }

    /* renamed from: a */
    public static /* synthetic */ void m5587a(cxd cxd) {
        cxd.f5886a |= 64;
        cxd.f5891f = true;
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
            return m13609a((ikf) f5884h, "\u0001\b\u0001\u0001\u0001\b\b\u0000\u0000\u0000\u0001;\u0000\u0002?\u0000\u0003<\u0000\u0004\b\u0004\u0005:\u0000\u0006\b\u0005\u0007\u0007\u0006\b\f\u0007", new Object[]{"c", "b", "a", Filter$Category.internalGetVerifier(), cwz.class, "d", "e", "f", "g", cxc.f5883a});
        } else if (i2 == 3) {
            return new cxd();
        } else {
            if (i2 == 4) {
                return new iir((int[][][]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f5884h;
            }
            ikn ikn = f5885i;
            if (ikn == null) {
                synchronized (cxd.class) {
                    ikn = f5885i;
                    if (ikn == null) {
                        ikn = new iis(f5884h);
                        f5885i = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
