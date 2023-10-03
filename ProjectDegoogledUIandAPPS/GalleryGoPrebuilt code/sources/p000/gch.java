package p000;

/* renamed from: gch */
/* compiled from: PG */
public final class gch extends iix implements ikg {

    /* renamed from: f */
    public static final gch f10929f;

    /* renamed from: g */
    private static volatile ikn f10930g;

    /* renamed from: a */
    public int f10931a;

    /* renamed from: b */
    public int f10932b = 0;

    /* renamed from: c */
    public Object f10933c;

    /* renamed from: d */
    public int f10934d = 5;

    /* renamed from: e */
    public float f10935e;

    static {
        gch gch = new gch();
        f10929f = gch;
        iix.m13611a(gch.class, (iix) gch);
    }

    private gch() {
        ikq ikq = ikq.f14400b;
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
            return m13609a((ikf) f10929f, "\u0001\u0004\u0001\u0001\u0001\u0006\u0004\u0000\u0000\u0000\u0001;\u0000\u0002\u0004\u0002\u0003\u0001\u0003\u0006<\u0000", new Object[]{"c", "b", "a", "d", "e", gcg.class});
        } else if (i2 == 3) {
            return new gch();
        } else {
            if (i2 == 4) {
                return new iir((int[][]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f10929f;
            }
            ikn ikn = f10930g;
            if (ikn == null) {
                synchronized (gch.class) {
                    ikn = f10930g;
                    if (ikn == null) {
                        ikn = new iis(f10929f);
                        f10930g = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
