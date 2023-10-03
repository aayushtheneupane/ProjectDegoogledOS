package p000;

/* renamed from: imn */
/* compiled from: PG */
public final class imn extends iix implements ikg {

    /* renamed from: n */
    public static final imn f14522n;

    /* renamed from: o */
    private static volatile ikn f14523o;

    /* renamed from: a */
    public int f14524a;

    /* renamed from: b */
    public iml f14525b;

    /* renamed from: c */
    public int f14526c;

    /* renamed from: d */
    public int f14527d;

    /* renamed from: e */
    public float f14528e;

    /* renamed from: f */
    public float f14529f;

    /* renamed from: g */
    public float f14530g;

    /* renamed from: h */
    public float f14531h;

    /* renamed from: i */
    public float f14532i;

    /* renamed from: j */
    public float f14533j;

    /* renamed from: k */
    public float f14534k;

    /* renamed from: l */
    public float f14535l;

    /* renamed from: m */
    public ihw f14536m = ihw.f14203b;

    static {
        imn imn = new imn();
        f14522n = imn;
        iix.m13611a(imn.class, (iix) imn);
    }

    private imn() {
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
            return m13609a((ikf) f14522n, "\u0001\f\u0000\u0001\u0001\u001f\f\u0000\u0000\u0000\u0001\t\u0000\u0003\u0004\u0003\u0004\u0004\u0004\u0007\u0001\u0005\b\u0001\u0006\t\u0001\u0007\u000b\u0001\t\u0018\u0001\u0015\u0019\u0001\u0016\u001a\u0001\u0017\u001e\u0001\u001b\u001f\n\u001d", new Object[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"});
        } else if (i2 == 3) {
            return new imn();
        } else {
            if (i2 == 4) {
                return new iir((char[][]) null, (byte[][]) null);
            }
            if (i2 == 5) {
                return f14522n;
            }
            ikn ikn = f14523o;
            if (ikn == null) {
                synchronized (imn.class) {
                    ikn = f14523o;
                    if (ikn == null) {
                        ikn = new iis(f14522n);
                        f14523o = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
