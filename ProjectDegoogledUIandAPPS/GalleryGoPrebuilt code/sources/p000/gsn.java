package p000;

/* renamed from: gsn */
/* compiled from: PG */
public final class gsn extends gss {

    /* renamed from: a */
    private final gst f11975a;

    /* renamed from: b */
    private final hpy f11976b;

    public gsn(gst gst, hpy hpy) {
        this.f11975a = gst;
        this.f11976b = hpy;
    }

    /* renamed from: a */
    public final gst mo7012a() {
        return this.f11975a;
    }

    /* renamed from: b */
    public final hpy mo7013b() {
        return this.f11976b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof gss) {
            gss gss = (gss) obj;
            return this.f11975a.equals(gss.mo7012a()) && this.f11976b.equals(gss.mo7013b());
        }
    }

    public final int hashCode() {
        return ((this.f11975a.hashCode() ^ 1000003) * 1000003) ^ this.f11976b.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f11975a);
        String valueOf2 = String.valueOf(this.f11976b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 48 + String.valueOf(valueOf2).length());
        sb.append("PeriodicWorkSpec{repeatInterval=");
        sb.append(valueOf);
        sb.append(", flexInterval=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
