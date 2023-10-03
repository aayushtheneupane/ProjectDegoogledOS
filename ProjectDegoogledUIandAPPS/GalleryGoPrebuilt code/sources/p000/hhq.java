package p000;

import java.util.Set;

/* renamed from: hhq */
/* compiled from: PG */
final class hhq extends hjj {

    /* renamed from: a */
    private final Set f12753a;

    /* renamed from: b */
    private final long f12754b;

    /* renamed from: c */
    private final hpy f12755c;

    public hhq(Set set, long j, hpy hpy) {
        this.f12753a = set;
        this.f12754b = j;
        if (hpy != null) {
            this.f12755c = hpy;
            return;
        }
        throw new NullPointerException("Null deadlineToIgnoreOptionalConstraints");
    }

    /* renamed from: a */
    public final Set mo7449a() {
        return this.f12753a;
    }

    /* renamed from: b */
    public final long mo7450b() {
        return this.f12754b;
    }

    /* renamed from: c */
    public final hpy mo7451c() {
        return this.f12755c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof hjj) {
            hjj hjj = (hjj) obj;
            return this.f12753a.equals(hjj.mo7449a()) && this.f12754b == hjj.mo7450b() && this.f12755c.equals(hjj.mo7451c());
        }
    }

    public final int hashCode() {
        int hashCode = this.f12753a.hashCode();
        long j = this.f12754b;
        return ((((hashCode ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.f12755c.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f12753a);
        long j = this.f12754b;
        String valueOf2 = String.valueOf(this.f12755c);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 122 + String.valueOf(valueOf2).length());
        sb.append("SyncSchedule{constraints=");
        sb.append(valueOf);
        sb.append(", minLatencyBeforeCheckingConstraints=");
        sb.append(j);
        sb.append(", deadlineToIgnoreOptionalConstraints=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
