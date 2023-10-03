package p000;

/* renamed from: fip */
/* compiled from: PG */
final class fip extends fmv {

    /* renamed from: a */
    public final float f9746a;

    /* renamed from: b */
    private final boolean f9747b;

    /* renamed from: c */
    private final int f9748c;

    /* renamed from: d */
    private final int f9749d;

    public /* synthetic */ fip(boolean z, float f, int i, int i2) {
        this.f9747b = z;
        this.f9746a = f;
        this.f9748c = i;
        this.f9749d = i2;
    }

    /* renamed from: a */
    public final boolean mo5825a() {
        return this.f9747b;
    }

    /* renamed from: b */
    public final float mo5826b() {
        return this.f9746a;
    }

    /* renamed from: c */
    public final int mo5827c() {
        return this.f9748c;
    }

    /* renamed from: d */
    public final int mo5828d() {
        return this.f9749d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof fmv) {
            fmv fmv = (fmv) obj;
            return this.f9747b == fmv.mo5825a() && Float.floatToIntBits(this.f9746a) == Float.floatToIntBits(fmv.mo5826b()) && this.f9748c == fmv.mo5827c() && this.f9749d == fmv.mo5828d();
        }
    }

    public final int hashCode() {
        return (((((((!this.f9747b ? 1237 : 1231) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.f9746a)) * 1000003) ^ this.f9748c) * 1000003) ^ this.f9749d;
    }

    public final String toString() {
        boolean z = this.f9747b;
        float f = this.f9746a;
        int i = this.f9748c;
        int i2 = this.f9749d;
        StringBuilder sb = new StringBuilder(142);
        sb.append("PrimesTraceConfigurations{enabled=");
        sb.append(z);
        sb.append(", samplingProbability=");
        sb.append(f);
        sb.append(", minSpanDurationMs=");
        sb.append(i);
        sb.append(", maxTracingBufferSize=");
        sb.append(i2);
        sb.append("}");
        return sb.toString();
    }
}
