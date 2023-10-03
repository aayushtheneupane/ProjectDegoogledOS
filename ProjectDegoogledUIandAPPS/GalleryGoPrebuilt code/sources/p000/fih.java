package p000;

/* renamed from: fih */
/* compiled from: PG */
public final class fih extends flf {

    /* renamed from: a */
    private final boolean f9712a;

    /* renamed from: b */
    private final float f9713b;

    /* renamed from: c */
    private final fpa f9714c;

    /* renamed from: d */
    private final boolean f9715d;

    public /* synthetic */ fih(boolean z, float f, fpa fpa, boolean z2) {
        this.f9712a = z;
        this.f9713b = f;
        this.f9714c = fpa;
        this.f9715d = z2;
    }

    /* renamed from: a */
    public final boolean mo5764a() {
        return this.f9712a;
    }

    /* renamed from: b */
    public final float mo5765b() {
        return this.f9713b;
    }

    /* renamed from: c */
    public final fpa mo5766c() {
        return this.f9714c;
    }

    /* renamed from: d */
    public final boolean mo5767d() {
        return this.f9715d;
    }

    /* renamed from: e */
    public final iqk mo5768e() {
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof flf) {
            flf flf = (flf) obj;
            if (this.f9712a == flf.mo5764a() && Float.floatToIntBits(this.f9713b) == Float.floatToIntBits(flf.mo5765b()) && this.f9714c.equals(flf.mo5766c()) && this.f9715d == flf.mo5767d()) {
                flf.mo5768e();
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 1237;
        int floatToIntBits = ((((((!this.f9712a ? 1237 : 1231) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.f9713b)) * 1000003) ^ this.f9714c.hashCode()) * 1000003;
        if (this.f9715d) {
            i = 1231;
        }
        return (floatToIntBits ^ i) * 1000003;
    }

    public final String toString() {
        boolean z = this.f9712a;
        float f = this.f9713b;
        String valueOf = String.valueOf(this.f9714c);
        boolean z2 = this.f9715d;
        String valueOf2 = String.valueOf((Object) null);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 158 + String.valueOf(valueOf2).length());
        sb.append("PrimesCrashConfigurations{enabled=");
        sb.append(z);
        sb.append(", startupSamplePercentage=");
        sb.append(f);
        sb.append(", stackTraceTransmitter=");
        sb.append(valueOf);
        sb.append(", deferredInitLogging=");
        sb.append(z2);
        sb.append(", metricExtensionProvider=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
