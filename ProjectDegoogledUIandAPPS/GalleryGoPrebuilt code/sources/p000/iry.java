package p000;

/* renamed from: iry */
/* compiled from: PG */
public final class iry extends iix implements ikg {

    /* renamed from: k */
    public static final iry f14947k;

    /* renamed from: l */
    private static volatile ikn f14948l;

    /* renamed from: a */
    public int f14949a;

    /* renamed from: b */
    public long f14950b;

    /* renamed from: c */
    public long f14951c;

    /* renamed from: d */
    public long f14952d;

    /* renamed from: e */
    public long f14953e;

    /* renamed from: f */
    public long f14954f;

    /* renamed from: g */
    public long f14955g;

    /* renamed from: h */
    public long f14956h;

    /* renamed from: i */
    public long f14957i;

    /* renamed from: j */
    public ije f14958j = ikq.f14400b;

    static {
        iry iry = new iry();
        f14947k = iry;
        iix.m13611a(iry.class, (iix) iry);
    }

    private iry() {
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
            return m13609a((ikf) f14947k, "\u0001\t\u0000\u0001\u0001\n\t\u0000\u0001\u0000\u0001\u0002\u0000\u0002\u0002\u0001\u0003\u0002\u0002\u0004\u0002\u0003\u0005\u0002\u0004\u0006\u0002\u0005\u0007\u0002\u0006\b\u0002\u0007\n\u001b", new Object[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", irx.class});
        } else if (i2 == 3) {
            return new iry();
        } else {
            if (i2 == 4) {
                return new iir((float[][][]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f14947k;
            }
            ikn ikn = f14948l;
            if (ikn == null) {
                synchronized (iry.class) {
                    ikn = f14948l;
                    if (ikn == null) {
                        ikn = new iis(f14947k);
                        f14948l = ikn;
                    }
                }
            }
            return ikn;
        }
    }

    /* renamed from: a */
    public final void mo9069a() {
        if (!this.f14958j.mo8521a()) {
            this.f14958j = iix.m13608a(this.f14958j);
        }
    }
}
