package p000;

/* renamed from: cur */
/* compiled from: PG */
public final class cur extends iix implements ikg {

    /* renamed from: b */
    public static final cur f5691b;

    /* renamed from: c */
    private static volatile ikn f5692c;

    /* renamed from: a */
    public ijy f5693a = ijy.f14368b;

    static {
        cur cur = new cur();
        f5691b = cur;
        iix.m13611a(cur.class, (iix) cur);
    }

    private cur() {
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
            return m13609a((ikf) f5691b, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"a", cuq.f5690a});
        } else if (i2 == 3) {
            return new cur();
        } else {
            if (i2 == 4) {
                return new iir((char[]) null, (boolean[]) null);
            }
            if (i2 == 5) {
                return f5691b;
            }
            ikn ikn = f5692c;
            if (ikn == null) {
                synchronized (cur.class) {
                    ikn = f5692c;
                    if (ikn == null) {
                        ikn = new iis(f5691b);
                        f5692c = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
