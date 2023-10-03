package p000;

/* renamed from: gng */
/* compiled from: PG */
public final class gng extends iix implements ikg {

    /* renamed from: d */
    public static final gng f11674d;

    /* renamed from: e */
    private static volatile ikn f11675e;

    /* renamed from: a */
    public int f11676a;

    /* renamed from: b */
    public int f11677b = 1;

    /* renamed from: c */
    public ijy f11678c = ijy.f14368b;

    static {
        gng gng = new gng();
        f11674d = gng;
        iix.m13611a(gng.class, (iix) gng);
    }

    private gng() {
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
            return m13609a((ikf) f11674d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0001\u0000\u0000\u0001\u0004\u0000\u00022", new Object[]{"a", "b", "c", gnf.f11673a});
        } else if (i2 == 3) {
            return new gng();
        } else {
            if (i2 == 4) {
                return new iir((boolean[]) null, (byte[][]) null);
            }
            if (i2 == 5) {
                return f11674d;
            }
            ikn ikn = f11675e;
            if (ikn == null) {
                synchronized (gng.class) {
                    ikn = f11675e;
                    if (ikn == null) {
                        ikn = new iis(f11674d);
                        f11675e = ikn;
                    }
                }
            }
            return ikn;
        }
    }

    /* renamed from: a */
    public final ijy mo6859a() {
        ijy ijy = this.f11678c;
        if (!ijy.f14369a) {
            this.f11678c = ijy.mo8831a();
        }
        return this.f11678c;
    }
}
