package p000;

/* renamed from: bqc */
/* compiled from: PG */
public final class bqc extends iix implements ikg {

    /* renamed from: d */
    public static final bqc f3349d;

    /* renamed from: e */
    private static volatile ikn f3350e;

    /* renamed from: a */
    public int f3351a;

    /* renamed from: b */
    public ije f3352b = ikq.f14400b;

    /* renamed from: c */
    public boolean f3353c;

    static {
        bqc bqc = new bqc();
        f3349d = bqc;
        iix.m13611a(bqc.class, (iix) bqc);
    }

    private bqc() {
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
            return m13609a((ikf) f3349d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\u0007\u0000", new Object[]{"a", "b", cyd.class, "c"});
        } else if (i2 == 3) {
            return new bqc();
        } else {
            if (i2 == 4) {
                return new iir((short[][]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f3349d;
            }
            ikn ikn = f3350e;
            if (ikn == null) {
                synchronized (bqc.class) {
                    ikn = f3350e;
                    if (ikn == null) {
                        ikn = new iis(f3349d);
                        f3350e = ikn;
                    }
                }
            }
            return ikn;
        }
    }

    /* renamed from: a */
    public final void mo2663a() {
        if (!this.f3352b.mo8521a()) {
            this.f3352b = iix.m13608a(this.f3352b);
        }
    }
}
