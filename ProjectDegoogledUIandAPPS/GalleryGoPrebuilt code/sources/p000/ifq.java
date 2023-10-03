package p000;

/* renamed from: ifq */
/* compiled from: PG */
public final class ifq extends iix implements ikg {

    /* renamed from: k */
    public static final ifq f14004k;

    /* renamed from: l */
    private static volatile ikn f14005l;

    /* renamed from: a */
    public int f14006a;

    /* renamed from: b */
    public String f14007b = "";

    /* renamed from: c */
    public int f14008c;

    /* renamed from: d */
    public ije f14009d = ikq.f14400b;

    /* renamed from: e */
    public ijc f14010e = iiy.f14327b;

    /* renamed from: f */
    public ihw f14011f = ihw.f14203b;

    /* renamed from: g */
    public boolean f14012g;

    /* renamed from: h */
    public String f14013h = "";

    /* renamed from: i */
    public int f14014i;

    /* renamed from: j */
    public int f14015j;

    static {
        ifq ifq = new ifq();
        f14004k = ifq;
        iix.m13611a(ifq.class, (iix) ifq);
    }

    private ifq() {
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
            return m13609a((ikf) f14004k, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0002\u0000\u0001\b\u0000\u0002\u0004\u0001\u0003\u001a\u0004'\u0005\n\u0002\u0006\u0007\u0003\u0007\b\u0004\b\f\u0005\t\f\u0006", new Object[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", ifp.f14003a, "j", ifo.f14002a});
        } else if (i2 == 3) {
            return new ifq();
        } else {
            if (i2 == 4) {
                return new iir((char[]) null, (char[][]) null);
            }
            if (i2 == 5) {
                return f14004k;
            }
            ikn ikn = f14005l;
            if (ikn == null) {
                synchronized (ifq.class) {
                    ikn = f14005l;
                    if (ikn == null) {
                        ikn = new iis(f14004k);
                        f14005l = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
