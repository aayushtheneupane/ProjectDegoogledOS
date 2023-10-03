package p000;

/* renamed from: gwg */
/* compiled from: PG */
final class gwg extends gwk {

    /* renamed from: a */
    private final ihw f12184a;

    /* renamed from: b */
    private final gwi f12185b;

    /* renamed from: c */
    private final boolean f12186c;

    public /* synthetic */ gwg(ihw ihw, gwi gwi, boolean z) {
        this.f12184a = ihw;
        this.f12185b = gwi;
        this.f12186c = z;
    }

    /* renamed from: a */
    public final ihw mo7131a() {
        return this.f12184a;
    }

    /* renamed from: b */
    public final gwi mo7132b() {
        return this.f12185b;
    }

    /* renamed from: c */
    public final boolean mo7133c() {
        return this.f12186c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof gwk) {
            gwk gwk = (gwk) obj;
            return this.f12184a.equals(gwk.mo7131a()) && this.f12185b.equals(gwk.mo7132b()) && this.f12186c == gwk.mo7133c();
        }
    }

    public final int hashCode() {
        return ((((this.f12184a.hashCode() ^ 1000003) * 1000003) ^ this.f12185b.hashCode()) * 1000003) ^ (!this.f12186c ? 1237 : 1231);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f12184a);
        String valueOf2 = String.valueOf(this.f12185b);
        boolean z = this.f12186c;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 42 + String.valueOf(valueOf2).length());
        sb.append("DebugData{data=");
        sb.append(valueOf);
        sb.append(", type=");
        sb.append(valueOf2);
        sb.append(", containsPii=");
        sb.append(z);
        sb.append("}");
        return sb.toString();
    }
}
