package p000;

/* renamed from: fsp */
/* compiled from: PG */
public final class fsp {

    /* renamed from: a */
    public final long f10538a;

    /* renamed from: b */
    public final long f10539b;

    public fsp(long j, long j2) {
        this.f10538a = j;
        this.f10539b = j2;
    }

    /* renamed from: a */
    public final double mo6158a() {
        double d = (double) this.f10538a;
        double d2 = (double) this.f10539b;
        Double.isNaN(d);
        Double.isNaN(d2);
        return d / d2;
    }

    public final int hashCode() {
        long j = this.f10538a;
        long j2 = this.f10539b;
        return ((((int) (j ^ (j >>> 32))) + 527) * 31) + ((int) (j2 ^ (j2 >>> 32)));
    }

    public fsp(fsp fsp) {
        this.f10538a = fsp.f10538a;
        this.f10539b = fsp.f10539b;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof fsp) {
            fsp fsp = (fsp) obj;
            return this.f10538a == fsp.f10538a && this.f10539b == fsp.f10539b;
        }
    }

    public final String toString() {
        long j = this.f10538a;
        long j2 = this.f10539b;
        StringBuilder sb = new StringBuilder(41);
        sb.append(j);
        sb.append("/");
        sb.append(j2);
        return sb.toString();
    }
}
