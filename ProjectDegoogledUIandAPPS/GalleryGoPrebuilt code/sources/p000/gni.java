package p000;

/* renamed from: gni */
/* compiled from: PG */
public final class gni extends iix implements ikg {

    /* renamed from: b */
    public static final gni f11680b;

    /* renamed from: c */
    private static volatile ikn f11681c;

    /* renamed from: a */
    public ijy f11682a = ijy.f14368b;

    static {
        gni gni = new gni();
        f11680b = gni;
        iix.m13611a(gni.class, (iix) gni);
    }

    private gni() {
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
            return m13609a((ikf) f11680b, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"a", gnh.f11679a});
        } else if (i2 == 3) {
            return new gni();
        } else {
            if (i2 == 4) {
                return new iir((short[][]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f11680b;
            }
            ikn ikn = f11681c;
            if (ikn == null) {
                synchronized (gni.class) {
                    ikn = f11681c;
                    if (ikn == null) {
                        ikn = new iis(f11680b);
                        f11681c = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
