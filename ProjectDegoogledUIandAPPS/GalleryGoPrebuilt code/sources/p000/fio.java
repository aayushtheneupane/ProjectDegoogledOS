package p000;

/* renamed from: fio */
/* compiled from: PG */
public final class fio extends fms {

    /* renamed from: a */
    public final float f9742a;

    /* renamed from: b */
    public final int f9743b;

    /* renamed from: c */
    private final boolean f9744c;

    /* renamed from: d */
    private final hpy f9745d;

    public /* synthetic */ fio(boolean z, float f, int i, hpy hpy) {
        this.f9744c = z;
        this.f9742a = f;
        this.f9743b = i;
        this.f9745d = hpy;
    }

    /* renamed from: a */
    public final boolean mo5818a() {
        return this.f9744c;
    }

    /* renamed from: b */
    public final float mo5819b() {
        return this.f9742a;
    }

    /* renamed from: c */
    public final int mo5820c() {
        return this.f9743b;
    }

    /* renamed from: d */
    public final hpy mo5821d() {
        return this.f9745d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof fms) {
            fms fms = (fms) obj;
            return this.f9744c == fms.mo5818a() && Float.floatToIntBits(this.f9742a) == Float.floatToIntBits(fms.mo5819b()) && this.f9743b == fms.mo5820c() && this.f9745d.equals(fms.mo5821d());
        }
    }

    public final int hashCode() {
        return (((((((!this.f9744c ? 1237 : 1231) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.f9742a)) * 1000003) ^ this.f9743b) * 1000003) ^ 2040732332;
    }

    public final String toString() {
        boolean z = this.f9744c;
        float f = this.f9742a;
        int i = this.f9743b;
        String valueOf = String.valueOf(this.f9745d);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 132);
        sb.append("PrimesTimerConfigurations{enabled=");
        sb.append(z);
        sb.append(", samplingProbability=");
        sb.append(f);
        sb.append(", sampleRatePerSecond=");
        sb.append(i);
        sb.append(", perEventConfigFlags=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
