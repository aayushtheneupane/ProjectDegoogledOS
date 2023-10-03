package p000;

/* renamed from: zp */
/* compiled from: PG */
final class C0697zp {

    /* renamed from: a */
    private int f16467a = 0;

    /* renamed from: b */
    private int f16468b;

    /* renamed from: c */
    private int f16469c;

    /* renamed from: d */
    private int f16470d;

    /* renamed from: e */
    private int f16471e;

    /* renamed from: a */
    private static final int m16251a(int i, int i2) {
        if (i > i2) {
            return 1;
        }
        return i == i2 ? 2 : 4;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10746a(int i) {
        this.f16467a = i | this.f16467a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final boolean mo10748b() {
        int i = this.f16467a;
        if ((i & 7) != 0 && (i & m16251a(this.f16470d, this.f16468b)) == 0) {
            return false;
        }
        int i2 = this.f16467a;
        if ((i2 & 112) != 0 && (i2 & (m16251a(this.f16470d, this.f16469c) << 4)) == 0) {
            return false;
        }
        int i3 = this.f16467a;
        if ((i3 & 1792) != 0 && (i3 & (m16251a(this.f16471e, this.f16468b) << 8)) == 0) {
            return false;
        }
        int i4 = this.f16467a;
        if ((i4 & 28672) == 0 || (i4 & (m16251a(this.f16471e, this.f16469c) << 12)) != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10745a() {
        this.f16467a = 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10747a(int i, int i2, int i3, int i4) {
        this.f16468b = i;
        this.f16469c = i2;
        this.f16470d = i3;
        this.f16471e = i4;
    }
}
