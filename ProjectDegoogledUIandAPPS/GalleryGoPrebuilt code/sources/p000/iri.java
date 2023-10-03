package p000;

/* renamed from: iri */
/* compiled from: PG */
public final class iri extends iix implements ikg {

    /* renamed from: h */
    public static final iri f14843h;

    /* renamed from: i */
    private static volatile ikn f14844i;

    /* renamed from: a */
    public int f14845a;

    /* renamed from: b */
    public long f14846b;

    /* renamed from: c */
    public int f14847c;

    /* renamed from: d */
    public ije f14848d = ikq.f14400b;

    /* renamed from: e */
    public float f14849e;

    /* renamed from: f */
    public irf f14850f;

    /* renamed from: g */
    public irm f14851g;

    static {
        iri iri = new iri();
        f14843h = iri;
        iix.m13611a(iri.class, (iix) iri);
    }

    private iri() {
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
            return m13609a((ikf) f14843h, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001\u0005\u0000\u0002\u001b\u0003\f\u0001\u0004\u0001\u0002\u0005\t\u0003\u0006\t\u0004", new Object[]{"a", "b", "d", irk.class, "c", irh.f14842a, "e", "f", "g"});
        } else if (i2 == 3) {
            return new iri();
        } else {
            if (i2 == 4) {
                return new iir((float[][]) null, (int[]) null);
            }
            if (i2 == 5) {
                return f14843h;
            }
            ikn ikn = f14844i;
            if (ikn == null) {
                synchronized (iri.class) {
                    ikn = f14844i;
                    if (ikn == null) {
                        ikn = new iis(f14843h);
                        f14844i = ikn;
                    }
                }
            }
            return ikn;
        }
    }

    /* renamed from: a */
    public final void mo9068a() {
        if (!this.f14848d.mo8521a()) {
            this.f14848d = iix.m13608a(this.f14848d);
        }
    }
}
