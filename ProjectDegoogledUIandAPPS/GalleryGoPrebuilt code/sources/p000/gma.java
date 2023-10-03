package p000;

/* renamed from: gma */
/* compiled from: PG */
public final class gma extends gkx {

    /* renamed from: a */
    public final gle f11614a;

    /* renamed from: b */
    private final gkn f11615b;

    /* renamed from: c */
    private final int f11616c;

    public gma(gkn gkn, gle gle, int i) {
        this.f11615b = gkn;
        if (gle != null) {
            this.f11614a = gle;
            this.f11616c = i;
            return;
        }
        throw new NullPointerException("Null info");
    }

    /* renamed from: a */
    public final gkn mo6816a() {
        return this.f11615b;
    }

    /* renamed from: b */
    public final gle mo6817b() {
        return this.f11614a;
    }

    /* renamed from: c */
    public final int mo6818c() {
        return this.f11616c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof gkx) {
            gkx gkx = (gkx) obj;
            return this.f11615b.equals(gkx.mo6816a()) && this.f11614a.equals(gkx.mo6817b()) && this.f11616c == gkx.mo6818c();
        }
    }

    public final int hashCode() {
        int hashCode = (this.f11615b.hashCode() ^ 1000003) * 1000003;
        gle gle = this.f11614a;
        int i = gle.f14173y;
        if (i == 0) {
            i = ikp.f14397a.mo8876a((Object) gle).mo8862a(gle);
            gle.f14173y = i;
        }
        return ((hashCode ^ i) * 1000003) ^ this.f11616c;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f11615b);
        String valueOf2 = String.valueOf(this.f11614a);
        String valueOf3 = String.valueOf(Integer.toString(this.f11616c - 1));
        int length = String.valueOf(valueOf).length();
        StringBuilder sb = new StringBuilder(length + 27 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append("Account{id=");
        sb.append(valueOf);
        sb.append(", info=");
        sb.append(valueOf2);
        sb.append(", state=");
        sb.append(valueOf3);
        sb.append("}");
        return sb.toString();
    }
}
