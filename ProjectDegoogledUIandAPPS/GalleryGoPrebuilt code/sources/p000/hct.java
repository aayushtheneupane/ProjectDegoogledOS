package p000;

/* renamed from: hct */
/* compiled from: PG */
final class hct extends hdg {

    /* renamed from: a */
    private final long f12489a;

    /* renamed from: b */
    private final long f12490b;

    public hct(long j, long j2) {
        this.f12489a = j;
        this.f12490b = j2;
    }

    /* renamed from: a */
    public final long mo7288a() {
        return this.f12489a;
    }

    /* renamed from: b */
    public final long mo7289b() {
        return this.f12490b;
    }

    public final int hashCode() {
        long j = this.f12489a;
        return ((int) this.f12490b) ^ ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof hdg) {
            hdg hdg = (hdg) obj;
            return this.f12489a == hdg.mo7288a() && this.f12490b == hdg.mo7289b();
        }
    }

    public final String toString() {
        long j = this.f12489a;
        long j2 = this.f12490b;
        StringBuilder sb = new StringBuilder(74);
        sb.append("LogReport{timestampMillis=");
        sb.append(j);
        sb.append(", size=");
        sb.append(j2);
        sb.append("}");
        return sb.toString();
    }
}
