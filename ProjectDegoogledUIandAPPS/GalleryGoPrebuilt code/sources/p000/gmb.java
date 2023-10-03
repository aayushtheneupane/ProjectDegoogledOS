package p000;

/* renamed from: gmb */
/* compiled from: PG */
public final class gmb extends glg {

    /* renamed from: a */
    public final gkn f11617a;

    /* renamed from: b */
    private final gle f11618b;

    public gmb(gkn gkn, gle gle) {
        if (gkn != null) {
            this.f11617a = gkn;
            this.f11618b = gle;
            return;
        }
        throw new NullPointerException("Null id");
    }

    /* renamed from: a */
    public final gkn mo6830a() {
        return this.f11617a;
    }

    /* renamed from: b */
    public final gle mo6831b() {
        return this.f11618b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof glg) {
            glg glg = (glg) obj;
            return this.f11617a.equals(glg.mo6830a()) && this.f11618b.equals(glg.mo6831b());
        }
    }

    public final int hashCode() {
        int hashCode = (this.f11617a.hashCode() ^ 1000003) * 1000003;
        gle gle = this.f11618b;
        int i = gle.f14173y;
        if (i == 0) {
            i = ikp.f14397a.mo8876a((Object) gle).mo8862a(gle);
            gle.f14173y = i;
        }
        return hashCode ^ i;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f11617a);
        String valueOf2 = String.valueOf(this.f11618b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 26 + String.valueOf(valueOf2).length());
        sb.append("AccountContext{id=");
        sb.append(valueOf);
        sb.append(", info=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
