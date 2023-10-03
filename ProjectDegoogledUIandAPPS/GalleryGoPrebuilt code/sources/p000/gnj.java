package p000;

/* renamed from: gnj */
/* compiled from: PG */
public final class gnj extends iix implements ikg {

    /* renamed from: e */
    public static final gnj f11683e;

    /* renamed from: f */
    private static volatile ikn f11684f;

    /* renamed from: a */
    public int f11685a;

    /* renamed from: b */
    public int f11686b;

    /* renamed from: c */
    public gle f11687c;

    /* renamed from: d */
    public int f11688d;

    static {
        gnj gnj = new gnj();
        f11683e = gnj;
        iix.m13611a(gnj.class, (iix) gnj);
    }

    private gnj() {
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
            return m13609a((ikf) f11683e, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0000\u0002\t\u0001\u0003\f\u0002", new Object[]{"a", "b", "c", "d", gly.f11601a});
        } else if (i2 == 3) {
            return new gnj();
        } else {
            if (i2 == 4) {
                return new iir((float[][][]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f11683e;
            }
            ikn ikn = f11684f;
            if (ikn == null) {
                synchronized (gnj.class) {
                    ikn = f11684f;
                    if (ikn == null) {
                        ikn = new iis(f11683e);
                        f11684f = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
