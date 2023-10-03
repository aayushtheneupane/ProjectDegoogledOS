package p000;

import java.util.concurrent.TimeUnit;

/* renamed from: gso */
/* compiled from: PG */
final class gso extends gst {

    /* renamed from: a */
    private final long f11977a;

    /* renamed from: b */
    private final TimeUnit f11978b;

    public gso(long j, TimeUnit timeUnit) {
        this.f11977a = j;
        if (timeUnit != null) {
            this.f11978b = timeUnit;
            return;
        }
        throw new NullPointerException("Null timeUnit");
    }

    /* renamed from: a */
    public final long mo7017a() {
        return this.f11977a;
    }

    /* renamed from: b */
    public final TimeUnit mo7018b() {
        return this.f11978b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof gst) {
            gst gst = (gst) obj;
            return this.f11977a == gst.mo7017a() && this.f11978b.equals(gst.mo7018b());
        }
    }

    public final int hashCode() {
        return this.f11978b.hashCode() ^ ((((int) this.f11977a) ^ 1000003) * 1000003);
    }

    public final String toString() {
        long j = this.f11977a;
        String valueOf = String.valueOf(this.f11978b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 54);
        sb.append("TimeUnitPair{duration=");
        sb.append(j);
        sb.append(", timeUnit=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
