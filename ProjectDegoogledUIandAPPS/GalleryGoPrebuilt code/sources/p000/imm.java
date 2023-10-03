package p000;

/* renamed from: imm */
/* compiled from: PG */
public final class imm extends iix implements ikg {

    /* renamed from: d */
    public static final imm f14517d;

    /* renamed from: e */
    private static volatile ikn f14518e;

    /* renamed from: a */
    public int f14519a;

    /* renamed from: b */
    public int f14520b;

    /* renamed from: c */
    public int f14521c;

    static {
        imm imm = new imm();
        f14517d = imm;
        iix.m13611a(imm.class, (iix) imm);
    }

    private imm() {
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
            return m13609a((ikf) f14517d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new imm();
        } else {
            if (i2 == 4) {
                return new iir((byte[][]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f14517d;
            }
            ikn ikn = f14518e;
            if (ikn == null) {
                synchronized (imm.class) {
                    ikn = f14518e;
                    if (ikn == null) {
                        ikn = new iis(f14517d);
                        f14518e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
