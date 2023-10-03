package p000;

/* renamed from: huw */
/* compiled from: PG */
final class huw extends hso {

    /* renamed from: a */
    public static final hso f13440a = new huw(new Object[0], 0);

    /* renamed from: b */
    private final transient Object[] f13441b;

    /* renamed from: c */
    private final transient int f13442c;

    /* renamed from: b */
    public final Object[] mo7879b() {
        return this.f13441b;
    }

    /* renamed from: c */
    public final int mo7880c() {
        return 0;
    }

    /* renamed from: d */
    public final int mo7883d() {
        return this.f13442c;
    }

    /* renamed from: h */
    public final boolean mo7885h() {
        return false;
    }

    public final int size() {
        return this.f13442c;
    }

    public huw(Object[] objArr, int i) {
        this.f13441b = objArr;
        this.f13442c = i;
    }

    /* renamed from: a */
    public final int mo7875a(Object[] objArr, int i) {
        System.arraycopy(this.f13441b, 0, objArr, i, this.f13442c);
        return i + this.f13442c;
    }

    public final Object get(int i) {
        ife.m12873b(i, this.f13442c);
        return this.f13441b[i];
    }
}
