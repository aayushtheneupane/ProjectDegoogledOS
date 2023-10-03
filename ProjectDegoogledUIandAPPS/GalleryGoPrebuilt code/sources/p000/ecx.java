package p000;

/* renamed from: ecx */
/* compiled from: PG */
public final class ecx extends iix implements ikg {

    /* renamed from: d */
    public static final ecx f7947d;

    /* renamed from: e */
    private static volatile ikn f7948e;

    /* renamed from: a */
    public int f7949a;

    /* renamed from: b */
    public ije f7950b = ikq.f14400b;

    /* renamed from: c */
    public boolean f7951c = true;

    static {
        ecx ecx = new ecx();
        f7947d = ecx;
        iix.m13611a(ecx.class, (iix) ecx);
    }

    private ecx() {
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
            return m13609a((ikf) f7947d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\u0007\u0000", new Object[]{"a", "b", cyd.class, "c"});
        } else if (i2 == 3) {
            return new ecx();
        } else {
            if (i2 == 4) {
                return new iir((int[][]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f7947d;
            }
            ikn ikn = f7948e;
            if (ikn == null) {
                synchronized (ecx.class) {
                    ikn = f7948e;
                    if (ikn == null) {
                        ikn = new iis(f7947d);
                        f7948e = ikn;
                    }
                }
            }
            return ikn;
        }
    }

    /* renamed from: a */
    public final void mo4686a() {
        if (!this.f7950b.mo8521a()) {
            this.f7950b = iix.m13608a(this.f7950b);
        }
    }
}
