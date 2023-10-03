package p000;

/* renamed from: fig */
/* compiled from: PG */
final class fig extends fky {

    /* renamed from: a */
    private final boolean f9710a;

    /* renamed from: b */
    private final fiq f9711b;

    public /* synthetic */ fig(boolean z, fiq fiq) {
        this.f9710a = z;
        this.f9711b = fiq;
    }

    /* renamed from: a */
    public final boolean mo5759a() {
        return this.f9710a;
    }

    /* renamed from: b */
    public final fiq mo5760b() {
        return this.f9711b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof fky) {
            fky fky = (fky) obj;
            return this.f9710a == fky.mo5759a() && this.f9711b.equals(fky.mo5760b());
        }
    }

    public final int hashCode() {
        return (((!this.f9710a ? 1237 : 1231) ^ 1000003) * 1000003) ^ this.f9711b.hashCode();
    }

    public final String toString() {
        boolean z = this.f9710a;
        String valueOf = String.valueOf(this.f9711b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 68);
        sb.append("PrimesBatteryConfigurations{enabled=");
        sb.append(z);
        sb.append(", metricExtensionProvider=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
