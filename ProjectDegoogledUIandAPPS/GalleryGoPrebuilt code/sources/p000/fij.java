package p000;

/* renamed from: fij */
/* compiled from: PG */
public final class fij extends flo {

    /* renamed from: a */
    private final boolean f9719a;

    /* renamed from: b */
    private final boolean f9720b;

    /* renamed from: c */
    private final boolean f9721c;

    public /* synthetic */ fij(boolean z, boolean z2, boolean z3) {
        this.f9719a = z;
        this.f9720b = z2;
        this.f9721c = z3;
    }

    /* renamed from: a */
    public final boolean mo5778a() {
        return this.f9719a;
    }

    /* renamed from: b */
    public final boolean mo5779b() {
        return this.f9720b;
    }

    /* renamed from: c */
    public final boolean mo5780c() {
        return this.f9721c;
    }

    public final int hashCode() {
        int i = 1237;
        int i2 = ((((!this.f9719a ? 1237 : 1231) ^ 1000003) * 1000003) ^ (!this.f9720b ? 1237 : 1231)) * 1000003;
        if (this.f9721c) {
            i = 1231;
        }
        return i2 ^ i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof flo) {
            flo flo = (flo) obj;
            return this.f9719a == flo.mo5778a() && this.f9720b == flo.mo5779b() && this.f9721c == flo.mo5780c();
        }
    }

    public final String toString() {
        boolean z = this.f9719a;
        boolean z2 = this.f9720b;
        boolean z3 = this.f9721c;
        StringBuilder sb = new StringBuilder(112);
        sb.append("PrimesFlags{useCompactStartupTrace=");
        sb.append(z);
        sb.append(", logProcessCreationTime=");
        sb.append(z2);
        sb.append(", useProcessGetStartElapsedRealtime=");
        sb.append(z3);
        sb.append("}");
        return sb.toString();
    }
}
