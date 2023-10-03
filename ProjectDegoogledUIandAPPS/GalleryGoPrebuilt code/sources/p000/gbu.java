package p000;

/* renamed from: gbu */
/* compiled from: PG */
public final class gbu extends iix implements ikg {

    /* renamed from: k */
    public static final gbu f10858k;

    /* renamed from: m */
    private static volatile ikn f10859m;

    /* renamed from: a */
    public int f10860a;

    /* renamed from: b */
    public float f10861b;

    /* renamed from: c */
    public float f10862c;

    /* renamed from: d */
    public float f10863d;

    /* renamed from: e */
    public float f10864e;

    /* renamed from: f */
    public float f10865f;

    /* renamed from: g */
    public float f10866g;

    /* renamed from: h */
    public float f10867h;

    /* renamed from: i */
    public float f10868i;

    /* renamed from: j */
    public float f10869j;

    /* renamed from: l */
    private byte f10870l = 2;

    static {
        gbu gbu = new gbu();
        f10858k = gbu;
        iix.m13611a(gbu.class, (iix) gbu);
    }

    private gbu() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f10870l);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f10870l = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f10858k, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\t\u0001ԁ\u0000\u0002ԁ\u0001\u0003ԁ\u0002\u0004ԁ\u0003\u0005ԁ\u0004\u0006ԁ\u0005\u0007ԁ\u0006\bԁ\u0007\tԁ\b", new Object[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"});
        } else if (i2 == 3) {
            return new gbu();
        } else {
            if (i2 == 4) {
                return new iir((byte[][]) null);
            }
            if (i2 == 5) {
                return f10858k;
            }
            ikn ikn = f10859m;
            if (ikn == null) {
                synchronized (gbu.class) {
                    ikn = f10859m;
                    if (ikn == null) {
                        ikn = new iis(f10858k);
                        f10859m = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
