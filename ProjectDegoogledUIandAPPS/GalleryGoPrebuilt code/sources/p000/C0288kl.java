package p000;

/* renamed from: kl */
/* compiled from: PG */
public final class C0288kl {

    /* renamed from: a */
    public static final C0281ke f15133a = new C0286kj(C0283kg.f15129a, false);

    /* renamed from: b */
    public static final C0281ke f15134b = new C0286kj(C0283kg.f15129a, true);

    static {
        new C0286kj((C0284kh) null, false);
        new C0286kj((C0284kh) null, true);
        new C0286kj(C0282kf.f15128a, false);
        int i = C0287kk.f15132b;
    }

    /* renamed from: a */
    static int m14506a(int i) {
        if (i != 0) {
            return (i == 1 || i == 2) ? 0 : 2;
        }
        return 1;
    }

    /* renamed from: b */
    static int m14507b(int i) {
        if (i != 0) {
            if (i == 1 || i == 2) {
                return 0;
            }
            switch (i) {
                case 14:
                case 15:
                    break;
                case 16:
                case 17:
                    return 0;
                default:
                    return 2;
            }
        }
        return 1;
    }
}
