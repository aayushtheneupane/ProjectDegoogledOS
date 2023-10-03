package p000;

/* renamed from: isc */
/* compiled from: PG */
public final class isc extends iix implements ikg {

    /* renamed from: r */
    public static final isc f14974r;

    /* renamed from: t */
    private static volatile ikn f14975t;

    /* renamed from: a */
    public int f14976a;

    /* renamed from: b */
    public irc f14977b;

    /* renamed from: c */
    public long f14978c;

    /* renamed from: d */
    public String f14979d = "";

    /* renamed from: e */
    public isd f14980e;

    /* renamed from: f */
    public irr f14981f;

    /* renamed from: g */
    public ire f14982g;

    /* renamed from: h */
    public irt f14983h;

    /* renamed from: i */
    public isb f14984i;

    /* renamed from: j */
    public iry f14985j;

    /* renamed from: k */
    public iqo f14986k;

    /* renamed from: l */
    public irw f14987l;

    /* renamed from: m */
    public iqx f14988m;

    /* renamed from: n */
    public iri f14989n;

    /* renamed from: o */
    public String f14990o = "";

    /* renamed from: p */
    public irp f14991p;

    /* renamed from: q */
    public iru f14992q;

    /* renamed from: s */
    private byte f14993s = 2;

    static {
        isc isc = new isc();
        f14974r = isc;
        iix.m13611a(isc.class, (iix) isc);
    }

    private isc() {
        ikq ikq = ikq.f14400b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f14993s);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f14993s = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f14974r, "\u0001\u0010\u0000\u0001\u0001\u0017\u0010\u0000\u0000\u0001\u0001Љ\u0000\u0002\u0005\u0001\u0003\b\u0002\u0004\t\u0003\u0005\t\u0004\u0006\t\u0005\u0007\t\u0006\b\t\u0007\t\t\b\n\t\t\f\t\u000b\u000e\t\r\u0010\t\u000f\u0011\b\u0010\u0015\t\u0014\u0017\t\u0016", new Object[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q"});
        } else if (i2 == 3) {
            return new isc();
        } else {
            if (i2 == 4) {
                return new iir((char[][]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f14974r;
            }
            ikn ikn = f14975t;
            if (ikn == null) {
                synchronized (isc.class) {
                    ikn = f14975t;
                    if (ikn == null) {
                        ikn = new iis(f14974r);
                        f14975t = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
