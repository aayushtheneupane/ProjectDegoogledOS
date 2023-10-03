package p000;

/* renamed from: irw */
/* compiled from: PG */
public final class irw extends iix implements ikg {

    /* renamed from: h */
    public static final irw f14932h;

    /* renamed from: i */
    private static volatile ikn f14933i;

    /* renamed from: a */
    public int f14934a;

    /* renamed from: b */
    public int f14935b;

    /* renamed from: c */
    public int f14936c;

    /* renamed from: d */
    public int f14937d;

    /* renamed from: e */
    public int f14938e;

    /* renamed from: f */
    public ije f14939f = ikq.f14400b;

    /* renamed from: g */
    public int f14940g;

    static {
        irw irw = new irw();
        f14932h = irw;
        iix.m13611a(irw.class, (iix) irw);
    }

    private irw() {
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
            return m13609a((ikf) f14932h, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001\u0004\u0000\u0002\u0004\u0001\u0003\u0004\u0002\u0004\u0004\u0003\u0005\u001b\u0006\u0004\u0004", new Object[]{"a", "b", "c", "d", "e", "f", irv.class, "g"});
        } else if (i2 == 3) {
            return new irw();
        } else {
            if (i2 == 4) {
                return new iir((float[][][]) null);
            }
            if (i2 == 5) {
                return f14932h;
            }
            ikn ikn = f14933i;
            if (ikn == null) {
                synchronized (irw.class) {
                    ikn = f14933i;
                    if (ikn == null) {
                        ikn = new iis(f14932h);
                        f14933i = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
