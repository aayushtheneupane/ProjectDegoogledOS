package p000;

/* renamed from: irp */
/* compiled from: PG */
public final class irp extends iix implements ikg {

    /* renamed from: c */
    public static final irp f14897c;

    /* renamed from: d */
    private static volatile ikn f14898d;

    /* renamed from: a */
    public int f14899a;

    /* renamed from: b */
    public String f14900b = "";

    static {
        irp irp = new irp();
        f14897c = irp;
        iix.m13611a(irp.class, (iix) irp);
    }

    private irp() {
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
            return m13609a((ikf) f14897c, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\b\u0000", new Object[]{"a", "b"});
        } else if (i2 == 3) {
            return new irp();
        } else {
            if (i2 == 4) {
                return new iir((boolean[][]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f14897c;
            }
            ikn ikn = f14898d;
            if (ikn == null) {
                synchronized (irp.class) {
                    ikn = f14898d;
                    if (ikn == null) {
                        ikn = new iis(f14897c);
                        f14898d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
