package p000;

/* renamed from: hva */
/* compiled from: PG */
final class hva extends hso {

    /* renamed from: a */
    private final transient Object[] f13451a;

    /* renamed from: b */
    private final transient int f13452b;

    /* renamed from: c */
    private final transient int f13453c;

    public hva(Object[] objArr, int i, int i2) {
        this.f13451a = objArr;
        this.f13452b = i;
        this.f13453c = i2;
    }

    /* renamed from: h */
    public final boolean mo7885h() {
        return true;
    }

    public final int size() {
        return this.f13453c;
    }

    public final Object get(int i) {
        ife.m12873b(i, this.f13453c);
        return this.f13451a[i + i + this.f13452b];
    }
}
