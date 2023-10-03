package p000;

/* renamed from: igo */
/* compiled from: PG */
public final class igo extends iix implements ikg {

    /* renamed from: b */
    public static final igo f14138b;

    /* renamed from: d */
    private static volatile ikn f14139d;

    /* renamed from: a */
    public ije f14140a = ikq.f14400b;

    /* renamed from: c */
    private byte f14141c = 2;

    static {
        igo igo = new igo();
        f14138b = igo;
        iix.m13611a(igo.class, (iix) igo);
    }

    private igo() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f14141c);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f14141c = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f14138b, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0001Л", new Object[]{"a", ign.class});
        } else if (i2 == 3) {
            return new igo();
        } else {
            if (i2 == 4) {
                return new iir((byte[][][]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f14138b;
            }
            ikn ikn = f14139d;
            if (ikn == null) {
                synchronized (igo.class) {
                    ikn = f14139d;
                    if (ikn == null) {
                        ikn = new iis(f14138b);
                        f14139d = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
