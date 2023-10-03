package p000;

/* renamed from: ikr */
/* compiled from: PG */
final class ikr implements ikc {

    /* renamed from: a */
    public final ikf f14403a;

    /* renamed from: b */
    public final String f14404b;

    /* renamed from: c */
    public final Object[] f14405c;

    /* renamed from: d */
    private final int f14406d;

    public ikr(ikf ikf, String str, Object[] objArr) {
        this.f14403a = ikf;
        this.f14404b = str;
        this.f14405c = objArr;
        char charAt = str.charAt(0);
        if (charAt >= 55296) {
            char c = charAt & 8191;
            int i = 13;
            int i2 = 1;
            while (true) {
                int i3 = i2 + 1;
                char charAt2 = str.charAt(i2);
                if (charAt2 >= 55296) {
                    c |= (charAt2 & 8191) << i;
                    i += 13;
                    i2 = i3;
                } else {
                    this.f14406d = c | (charAt2 << i);
                    return;
                }
            }
        } else {
            this.f14406d = charAt;
        }
    }

    /* renamed from: a */
    public final boolean mo8859a() {
        return (this.f14406d & 2) == 2;
    }

    /* renamed from: b */
    public final ikf mo8860b() {
        return this.f14403a;
    }

    /* renamed from: c */
    public final int mo8861c() {
        return (this.f14406d & 1) == 0 ? 2 : 1;
    }
}
