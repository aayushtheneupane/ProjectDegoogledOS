package p000;

/* renamed from: blx */
/* compiled from: PG */
final class blx extends bmf {

    /* renamed from: a */
    private final String f3124a;

    /* renamed from: b */
    private final String f3125b;

    public blx(String str, String str2) {
        if (str != null) {
            this.f3124a = str;
            if (str2 != null) {
                this.f3125b = str2;
                return;
            }
            throw new NullPointerException("Null burstFilenameID");
        }
        throw new NullPointerException("Null bucketID");
    }

    /* renamed from: a */
    public final String mo2578a() {
        return this.f3124a;
    }

    /* renamed from: b */
    public final String mo2579b() {
        return this.f3125b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof bmf) {
            bmf bmf = (bmf) obj;
            return this.f3124a.equals(bmf.mo2578a()) && this.f3125b.equals(bmf.mo2579b());
        }
    }

    public final int hashCode() {
        return ((this.f3124a.hashCode() ^ 1000003) * 1000003) ^ this.f3125b.hashCode();
    }

    public final String toString() {
        String str = this.f3124a;
        String str2 = this.f3125b;
        StringBuilder sb = new StringBuilder(str.length() + 44 + str2.length());
        sb.append("BurstIdentifier{bucketID=");
        sb.append(str);
        sb.append(", burstFilenameID=");
        sb.append(str2);
        sb.append("}");
        return sb.toString();
    }
}
