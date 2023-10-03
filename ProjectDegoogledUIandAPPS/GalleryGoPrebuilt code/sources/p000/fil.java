package p000;

/* renamed from: fil */
/* compiled from: PG */
public final class fil extends fly {

    /* renamed from: a */
    private final boolean f9727a;

    /* renamed from: b */
    private final int f9728b;

    /* renamed from: c */
    private final boolean f9729c;

    /* renamed from: d */
    private final hpy f9730d;

    /* renamed from: e */
    private final boolean f9731e;

    /* renamed from: f */
    private final boolean f9732f;

    public /* synthetic */ fil(boolean z, int i, boolean z2, hpy hpy, boolean z3, boolean z4) {
        this.f9727a = z;
        this.f9728b = i;
        this.f9729c = z2;
        this.f9730d = hpy;
        this.f9731e = z3;
        this.f9732f = z4;
    }

    /* renamed from: a */
    public final boolean mo5792a() {
        return this.f9727a;
    }

    /* renamed from: b */
    public final int mo5793b() {
        return this.f9728b;
    }

    /* renamed from: c */
    public final boolean mo5794c() {
        return this.f9729c;
    }

    /* renamed from: d */
    public final hpy mo5795d() {
        return this.f9730d;
    }

    /* renamed from: e */
    public final boolean mo5796e() {
        return this.f9731e;
    }

    /* renamed from: f */
    public final boolean mo5798f() {
        return this.f9732f;
    }

    public final int hashCode() {
        int i = 1237;
        int i2 = ((((((((((!this.f9727a ? 1237 : 1231) ^ 1000003) * 1000003) ^ this.f9728b) * 1000003) ^ (!this.f9729c ? 1237 : 1231)) * 1000003) ^ 2040732332) * 1000003) ^ (!this.f9731e ? 1237 : 1231)) * 1000003;
        if (this.f9732f) {
            i = 1231;
        }
        return i2 ^ i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof fly) {
            fly fly = (fly) obj;
            return this.f9727a == fly.mo5792a() && this.f9728b == fly.mo5793b() && this.f9729c == fly.mo5794c() && this.f9730d.equals(fly.mo5795d()) && this.f9731e == fly.mo5796e() && this.f9732f == fly.mo5798f();
        }
    }

    public final String toString() {
        boolean z = this.f9727a;
        int i = this.f9728b;
        boolean z2 = this.f9729c;
        String valueOf = String.valueOf(this.f9730d);
        boolean z3 = this.f9731e;
        boolean z4 = this.f9732f;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 184);
        sb.append("PrimesMemoryConfigurations{enabled=");
        sb.append(z);
        sb.append(", sampleRatePerSecond=");
        sb.append(i);
        sb.append(", recordMetricPerProcess=");
        sb.append(z2);
        sb.append(", metricExtensionProvider=");
        sb.append(valueOf);
        sb.append(", forceGcBeforeRecordMemory=");
        sb.append(z3);
        sb.append(", captureRssHwm=");
        sb.append(z4);
        sb.append("}");
        return sb.toString();
    }
}
