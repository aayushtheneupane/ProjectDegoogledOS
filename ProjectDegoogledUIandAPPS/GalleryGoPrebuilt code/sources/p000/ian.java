package p000;

/* renamed from: ian */
/* compiled from: PG */
public final class ian extends iix implements ikg {

    /* renamed from: e */
    public static final ian f13731e;

    /* renamed from: g */
    private static volatile ikn f13732g;

    /* renamed from: a */
    public int f13733a;

    /* renamed from: b */
    public long f13734b;

    /* renamed from: c */
    public int f13735c;

    /* renamed from: d */
    public int f13736d;

    /* renamed from: f */
    private byte f13737f = 2;

    static {
        ian ian = new ian();
        f13731e = ian;
        iix.m13611a(ian.class, (iix) ian);
    }

    private ian() {
    }

    /* renamed from: a */
    public static /* synthetic */ void m12584a(ian ian) {
        ian.f13733a |= 2;
        ian.f13735c = 0;
    }

    /* renamed from: b */
    public static /* synthetic */ void m12585b(ian ian) {
        ian.f13733a |= 4;
        ian.f13736d = 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.f13737f);
        }
        byte b = 0;
        if (i2 == 1) {
            if (obj != null) {
                b = 1;
            }
            this.f13737f = b;
            return null;
        } else if (i2 == 2) {
            return m13609a((ikf) f13731e, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0003\u0001Ԃ\u0000\u0002Ԇ\u0001\u0003Ԇ\u0002", new Object[]{"a", "b", "c", "d"});
        } else if (i2 == 3) {
            return new ian();
        } else {
            if (i2 == 4) {
                return new iam((byte[]) null);
            }
            if (i2 == 5) {
                return f13731e;
            }
            ikn ikn = f13732g;
            if (ikn == null) {
                synchronized (ian.class) {
                    ikn = f13732g;
                    if (ikn == null) {
                        ikn = new iis(f13731e);
                        f13732g = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
