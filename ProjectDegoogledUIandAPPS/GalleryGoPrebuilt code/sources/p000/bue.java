package p000;

/* renamed from: bue */
/* compiled from: PG */
final class bue extends buf {

    /* renamed from: a */
    private final long f3612a;

    /* renamed from: b */
    private final long f3613b;

    /* renamed from: c */
    private final int f3614c;

    public bue(int i, long j, long j2) {
        if (i != 0) {
            this.f3614c = i;
            this.f3612a = j;
            this.f3613b = j2;
            return;
        }
        throw new NullPointerException("Null storageType");
    }

    /* renamed from: a */
    public final long mo2762a() {
        return this.f3612a;
    }

    /* renamed from: b */
    public final long mo2763b() {
        return this.f3613b;
    }

    /* renamed from: c */
    public final int mo2764c() {
        return this.f3614c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof buf) {
            buf buf = (buf) obj;
            return this.f3614c == buf.mo2764c() && this.f3612a == buf.mo2762a() && this.f3613b == buf.mo2763b();
        }
    }

    public final int hashCode() {
        int d = ggf.m10258d(this.f3614c);
        long j = this.f3612a;
        long j2 = this.f3613b;
        return ((((d ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2));
    }

    public final String toString() {
        String c = ggf.m10256c(this.f3614c);
        long j = this.f3612a;
        long j2 = this.f3613b;
        StringBuilder sb = new StringBuilder(c.length() + 102);
        sb.append("SingleDeviceFolderFragmentData{storageType=");
        sb.append(c);
        sb.append(", size=");
        sb.append(j);
        sb.append(", numItems=");
        sb.append(j2);
        sb.append("}");
        return sb.toString();
    }
}
