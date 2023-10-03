package p000;

/* renamed from: fin */
/* compiled from: PG */
final class fin extends fmq {

    /* renamed from: a */
    private final boolean f9738a;

    /* renamed from: b */
    private final int f9739b;

    /* renamed from: c */
    private final fmo f9740c;

    /* renamed from: d */
    private final boolean f9741d;

    public /* synthetic */ fin(boolean z, int i, fmo fmo, boolean z2) {
        this.f9738a = z;
        this.f9739b = i;
        this.f9740c = fmo;
        this.f9741d = z2;
    }

    /* renamed from: a */
    public final boolean mo5811a() {
        return this.f9738a;
    }

    /* renamed from: b */
    public final int mo5812b() {
        return this.f9739b;
    }

    /* renamed from: c */
    public final fmo mo5813c() {
        return this.f9740c;
    }

    /* renamed from: d */
    public final boolean mo5814d() {
        return this.f9741d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof fmq) {
            fmq fmq = (fmq) obj;
            return this.f9738a == fmq.mo5811a() && this.f9739b == fmq.mo5812b() && this.f9740c.equals(fmq.mo5813c()) && this.f9741d == fmq.mo5814d();
        }
    }

    public final int hashCode() {
        int i = 1237;
        int hashCode = ((((((!this.f9738a ? 1237 : 1231) ^ 1000003) * 1000003) ^ this.f9739b) * 1000003) ^ this.f9740c.hashCode()) * 1000003;
        if (this.f9741d) {
            i = 1231;
        }
        return hashCode ^ i;
    }

    public final String toString() {
        boolean z = this.f9738a;
        int i = this.f9739b;
        String valueOf = String.valueOf(this.f9740c);
        boolean z2 = this.f9741d;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 123);
        sb.append("PrimesTikTokTraceConfigurations{enabled=");
        sb.append(z);
        sb.append(", sampleRatePerSecond=");
        sb.append(i);
        sb.append(", dynamicSampler=");
        sb.append(valueOf);
        sb.append(", recordTimerDuration=");
        sb.append(z2);
        sb.append("}");
        return sb.toString();
    }
}
