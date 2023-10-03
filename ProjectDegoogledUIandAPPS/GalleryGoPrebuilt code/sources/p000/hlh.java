package p000;

/* renamed from: hlh */
/* compiled from: PG */
public final class hlh extends iix implements ikg {

    /* renamed from: i */
    public static final hlh f12966i;

    /* renamed from: j */
    private static volatile ikn f12967j;

    /* renamed from: a */
    public int f12968a;

    /* renamed from: b */
    public String f12969b = "";

    /* renamed from: c */
    public int f12970c;

    /* renamed from: d */
    public int f12971d;

    /* renamed from: e */
    public long f12972e;

    /* renamed from: f */
    public long f12973f;

    /* renamed from: g */
    public int f12974g;

    /* renamed from: h */
    public boolean f12975h;

    static {
        hlh hlh = new hlh();
        f12966i = hlh;
        iix.m13611a(hlh.class, (iix) hlh);
    }

    private hlh() {
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
            return m13609a((ikf) f12966i, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001\b\u0000\u0002\u0004\u0001\u0003\u0004\u0002\u0004\u0002\u0003\u0005\u0002\u0004\u0006\u0007\u0006\u0007\u0004\u0005", new Object[]{"a", "b", "c", "d", "e", "f", "h", "g"});
        } else if (i2 == 3) {
            return new hlh();
        } else {
            if (i2 == 4) {
                return new iir((int[][]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f12966i;
            }
            ikn ikn = f12967j;
            if (ikn == null) {
                synchronized (hlh.class) {
                    ikn = f12967j;
                    if (ikn == null) {
                        ikn = new iis(f12966i);
                        f12967j = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
