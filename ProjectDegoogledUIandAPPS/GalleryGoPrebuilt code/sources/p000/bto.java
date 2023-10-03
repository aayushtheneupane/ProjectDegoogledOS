package p000;

/* renamed from: bto */
/* compiled from: PG */
public final class bto extends iix implements ikg {

    /* renamed from: c */
    public static final bto f3554c;

    /* renamed from: d */
    private static volatile ikn f3555d;

    /* renamed from: a */
    public int f3556a;

    /* renamed from: b */
    public boolean f3557b;

    static {
        bto bto = new bto();
        f3554c = bto;
        iix.m13611a(bto.class, (iix) bto);
    }

    private bto() {
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
            return m13609a((ikf) f3554c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0007\u0000", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new bto();
        } else {
            if (i2 == 4) {
                return new iir((boolean[]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f3554c;
            }
            ikn ikn = f3555d;
            if (ikn == null) {
                synchronized (bto.class) {
                    ikn = f3555d;
                    if (ikn == null) {
                        ikn = new iis(f3554c);
                        f3555d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
