package p000;

/* renamed from: hgu */
/* compiled from: PG */
final class hgu extends hhe {

    /* renamed from: a */
    private final hha f12711a;

    /* renamed from: b */
    private final hgw f12712b;

    /* renamed from: c */
    private final iqk f12713c;

    public hgu(hha hha, hgw hgw, iqk iqk) {
        if (hha != null) {
            this.f12711a = hha;
            if (hgw != null) {
                this.f12712b = hgw;
                if (iqk != null) {
                    this.f12713c = iqk;
                    return;
                }
                throw new NullPointerException("Null syncletProvider");
            }
            throw new NullPointerException("Null syncConfig");
        }
        throw new NullPointerException("Null syncKey");
    }

    /* renamed from: a */
    public final hha mo7424a() {
        return this.f12711a;
    }

    /* renamed from: b */
    public final hgw mo7425b() {
        return this.f12712b;
    }

    /* renamed from: c */
    public final iqk mo7426c() {
        return this.f12713c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof hhe) {
            hhe hhe = (hhe) obj;
            return this.f12711a.equals(hhe.mo7424a()) && this.f12712b.equals(hhe.mo7425b()) && this.f12713c.equals(hhe.mo7426c());
        }
    }

    public final int hashCode() {
        return ((((this.f12711a.hashCode() ^ 1000003) * 1000003) ^ this.f12712b.hashCode()) * 1000003) ^ this.f12713c.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f12711a);
        String valueOf2 = String.valueOf(this.f12712b);
        String valueOf3 = String.valueOf(this.f12713c);
        int length = String.valueOf(valueOf).length();
        StringBuilder sb = new StringBuilder(length + 55 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append("SyncletBinding{syncKey=");
        sb.append(valueOf);
        sb.append(", syncConfig=");
        sb.append(valueOf2);
        sb.append(", syncletProvider=");
        sb.append(valueOf3);
        sb.append("}");
        return sb.toString();
    }
}
