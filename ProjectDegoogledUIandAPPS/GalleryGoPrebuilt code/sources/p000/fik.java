package p000;

/* renamed from: fik */
/* compiled from: PG */
public final class fik extends flr {

    /* renamed from: a */
    private final boolean f9722a;

    /* renamed from: b */
    private final boolean f9723b;

    /* renamed from: c */
    private final boolean f9724c;

    /* renamed from: d */
    private final int f9725d;

    /* renamed from: e */
    private final hpy f9726e;

    public /* synthetic */ fik(boolean z, boolean z2, boolean z3, int i, hpy hpy) {
        this.f9722a = z;
        this.f9723b = z2;
        this.f9724c = z3;
        this.f9725d = i;
        this.f9726e = hpy;
    }

    /* renamed from: a */
    public final boolean mo5784a() {
        return this.f9722a;
    }

    /* renamed from: b */
    public final boolean mo5785b() {
        return this.f9723b;
    }

    /* renamed from: c */
    public final boolean mo5786c() {
        return this.f9724c;
    }

    /* renamed from: d */
    public final int mo5787d() {
        return this.f9725d;
    }

    /* renamed from: e */
    public final hpy mo5788e() {
        return this.f9726e;
    }

    public final int hashCode() {
        int i = 1237;
        int i2 = ((((!this.f9722a ? 1237 : 1231) ^ 1000003) * 1000003) ^ (!this.f9723b ? 1237 : 1231)) * 1000003;
        if (this.f9724c) {
            i = 1231;
        }
        return ((((i2 ^ i) * 1000003) ^ this.f9725d) * 1000003) ^ 2040732332;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof flr) {
            flr flr = (flr) obj;
            return this.f9722a == flr.mo5784a() && this.f9723b == flr.mo5785b() && this.f9724c == flr.mo5786c() && this.f9725d == flr.mo5787d() && this.f9726e.equals(flr.mo5788e());
        }
    }

    public final String toString() {
        boolean z = this.f9722a;
        boolean z2 = this.f9723b;
        boolean z3 = this.f9724c;
        int i = this.f9725d;
        String valueOf = String.valueOf(this.f9726e);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 142);
        sb.append("PrimesJankConfigurations{enabled=");
        sb.append(z);
        sb.append(", monitorActivities=");
        sb.append(z2);
        sb.append(", useAnimator=");
        sb.append(z3);
        sb.append(", sampleRatePerSecond=");
        sb.append(i);
        sb.append(", metricExtensionProvider=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
