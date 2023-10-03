package p000;

/* renamed from: hgt */
/* compiled from: PG */
final class hgt extends hgy {

    /* renamed from: a */
    private final hgz f12709a;

    /* renamed from: b */
    private final long f12710b;

    public hgt(hgz hgz, long j) {
        if (hgz != null) {
            this.f12709a = hgz;
            this.f12710b = j;
            return;
        }
        throw new NullPointerException("Null constraintType");
    }

    /* renamed from: a */
    public final hgz mo7419a() {
        return this.f12709a;
    }

    /* renamed from: b */
    public final long mo7420b() {
        return this.f12710b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof hgy) {
            hgy hgy = (hgy) obj;
            return this.f12709a.equals(hgy.mo7419a()) && this.f12710b == hgy.mo7420b();
        }
    }

    public final int hashCode() {
        int hashCode = this.f12709a.hashCode();
        long j = this.f12710b;
        return ((hashCode ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f12709a);
        long j = this.f12710b;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 70);
        sb.append("SyncConstraint{constraintType=");
        sb.append(valueOf);
        sb.append(", applicablePeriod=");
        sb.append(j);
        sb.append("}");
        return sb.toString();
    }
}
