package p000;

/* renamed from: gbv */
/* compiled from: PG */
public final class gbv extends iix implements ikg {

    /* renamed from: o */
    public static final gbv f10871o;

    /* renamed from: q */
    private static volatile ikn f10872q;

    /* renamed from: a */
    public int f10873a;

    /* renamed from: b */
    public gbs f10874b;

    /* renamed from: c */
    public ije f10875c = ikq.f14400b;

    /* renamed from: d */
    public float f10876d;

    /* renamed from: e */
    public float f10877e;

    /* renamed from: f */
    public float f10878f;

    /* renamed from: g */
    public float f10879g;

    /* renamed from: h */
    public ihw f10880h = ihw.f14203b;

    /* renamed from: i */
    public float f10881i;

    /* renamed from: j */
    public float f10882j;

    /* renamed from: k */
    public float f10883k;

    /* renamed from: l */
    public float f10884l;

    /* renamed from: m */
    public gbu f10885m;

    /* renamed from: n */
    public gbt f10886n;

    /* renamed from: p */
    private byte f10887p = 2;

    static {
        gbv gbv = new gbv();
        f10871o = gbv;
        iix.m13611a(gbv.class, (iix) gbv);
    }

    private gbv() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f10887p);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f10887p = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f10871o, "\u0001\r\u0000\u0001\u0001\u001e\r\u0000\u0001\u0001\u0001\t\u0000\u0002\u001b\u0003\u0001\u0001\u0004\u0001\u0002\u0005\u0001\u0003\u0006\u0001\u0004\u0007\n\u0005\u0012\u0001\u0010\u0013\u0001\u0011\u0014\u0001\u0012\u0018\u0001\u0016\u001bЉ\u0019\u001e\t\u001b", new Object[]{"a", "b", "c", gbx.class, "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"});
        } else if (i2 == 3) {
            return new gbv();
        } else {
            if (i2 == 4) {
                return new iir((float[][]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f10871o;
            }
            ikn ikn = f10872q;
            if (ikn == null) {
                synchronized (gbv.class) {
                    ikn = f10872q;
                    if (ikn == null) {
                        ikn = new iis(f10871o);
                        f10872q = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
