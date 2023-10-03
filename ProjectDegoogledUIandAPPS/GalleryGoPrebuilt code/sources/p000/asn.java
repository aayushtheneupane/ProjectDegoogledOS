package p000;

import java.security.MessageDigest;

/* renamed from: asn */
/* compiled from: PG */
final class asn implements aqu {

    /* renamed from: b */
    private final aqu f1532b;

    /* renamed from: c */
    private final aqu f1533c;

    public asn(aqu aqu, aqu aqu2) {
        this.f1532b = aqu;
        this.f1533c = aqu2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof asn) {
            asn asn = (asn) obj;
            if (!this.f1532b.equals(asn.f1532b) || !this.f1533c.equals(asn.f1533c)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (this.f1532b.hashCode() * 31) + this.f1533c.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f1532b);
        String valueOf2 = String.valueOf(this.f1533c);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36 + String.valueOf(valueOf2).length());
        sb.append("DataCacheKey{sourceKey=");
        sb.append(valueOf);
        sb.append(", signature=");
        sb.append(valueOf2);
        sb.append('}');
        return sb.toString();
    }

    /* renamed from: a */
    public final void mo1494a(MessageDigest messageDigest) {
        this.f1532b.mo1494a(messageDigest);
        this.f1533c.mo1494a(messageDigest);
    }
}
