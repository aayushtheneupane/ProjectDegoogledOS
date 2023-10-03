package p000;

/* renamed from: hox */
/* compiled from: PG */
public final class hox extends iiu implements iiv {

    /* renamed from: e */
    public static final hox f13179e;

    /* renamed from: g */
    private static volatile ikn f13180g;

    /* renamed from: a */
    public int f13181a;

    /* renamed from: b */
    public int f13182b = 0;

    /* renamed from: c */
    public Object f13183c;

    /* renamed from: d */
    public int f13184d;

    /* renamed from: f */
    private byte f13185f = 2;

    static {
        hox hox = new hox();
        f13179e = hox;
        iix.m13611a(hox.class, (iix) hox);
    }

    private hox() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f13185f);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f13185f = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f13179e, "\u0001\u0003\u0001\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001;\u0000\u0002<\u0000\u0003\u0004\u0002", new Object[]{"c", "b", "a", how.class, "d"});
        } else if (i2 == 3) {
            return new hox();
        } else {
            if (i2 == 4) {
                return new iit((boolean[]) null);
            }
            if (i2 == 5) {
                return f13179e;
            }
            ikn ikn = f13180g;
            if (ikn == null) {
                synchronized (hox.class) {
                    ikn = f13180g;
                    if (ikn == null) {
                        ikn = new iis(f13179e);
                        f13180g = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
