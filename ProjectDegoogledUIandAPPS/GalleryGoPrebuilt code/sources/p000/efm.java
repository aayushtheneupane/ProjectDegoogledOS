package p000;

import java.util.List;

/* renamed from: efm */
/* compiled from: PG */
final class efm extends eft {

    /* renamed from: a */
    public final abt f8159a;

    /* renamed from: b */
    public final hso f8160b;

    public efm(abt abt, hso hso) {
        this.f8159a = abt;
        if (hso != null) {
            this.f8160b = hso;
            return;
        }
        throw new NullPointerException("Null segments");
    }

    /* renamed from: a */
    public final abt mo4780a() {
        return this.f8159a;
    }

    /* renamed from: b */
    public final hso mo4781b() {
        return this.f8160b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof eft) {
            eft eft = (eft) obj;
            return this.f8159a.equals(eft.mo4780a()) && ife.m12856a((List) this.f8160b, (Object) eft.mo4781b());
        }
    }

    public final int hashCode() {
        return ((this.f8159a.hashCode() ^ 1000003) * 1000003) ^ this.f8160b.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f8159a);
        String valueOf2 = String.valueOf(this.f8160b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47 + String.valueOf(valueOf2).length());
        sb.append("DocumentFilePath{ancestorDirectory=");
        sb.append(valueOf);
        sb.append(", segments=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
