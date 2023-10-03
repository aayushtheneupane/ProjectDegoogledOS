package p000;

/* renamed from: iml */
/* compiled from: PG */
public final class iml extends iix implements ikg {

    /* renamed from: d */
    public static final iml f14512d;

    /* renamed from: e */
    private static volatile ikn f14513e;

    /* renamed from: a */
    public int f14514a;

    /* renamed from: b */
    public imm f14515b;

    /* renamed from: c */
    public imm f14516c;

    static {
        iml iml = new iml();
        f14512d = iml;
        iix.m13611a(iml.class, (iix) iml);
    }

    private iml() {
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
            return m13609a((ikf) f14512d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new iml();
        } else {
            if (i2 == 4) {
                return new iir((short[][]) null);
            }
            if (i2 == 5) {
                return f14512d;
            }
            ikn ikn = f14513e;
            if (ikn == null) {
                synchronized (iml.class) {
                    ikn = f14513e;
                    if (ikn == null) {
                        ikn = new iis(f14512d);
                        f14513e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
