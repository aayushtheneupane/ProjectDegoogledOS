package p000;

/* renamed from: nj */
/* compiled from: PG */
final class C0367nj {

    /* renamed from: a */
    public int f15264a;

    /* renamed from: b */
    public int f15265b;

    /* renamed from: c */
    public float f15266c;

    /* renamed from: d */
    public float f15267d;

    /* renamed from: e */
    public long f15268e = Long.MIN_VALUE;

    /* renamed from: f */
    public long f15269f = 0;

    /* renamed from: g */
    public int f15270g = 0;

    /* renamed from: h */
    public int f15271h = 0;

    /* renamed from: i */
    public long f15272i = -1;

    /* renamed from: j */
    public float f15273j;

    /* renamed from: k */
    public int f15274k;

    /* renamed from: a */
    public final float mo9456a(long j) {
        long j2 = this.f15268e;
        if (j < j2) {
            return 0.0f;
        }
        long j3 = this.f15272i;
        if (j3 < 0 || j < j3) {
            return C0369nl.m14810a(((float) (j - j2)) / ((float) this.f15264a), 0.0f, 1.0f) * 0.5f;
        }
        float f = this.f15273j;
        return (1.0f - f) + (f * C0369nl.m14810a(((float) (j - j3)) / ((float) this.f15274k), 0.0f, 1.0f));
    }
}
