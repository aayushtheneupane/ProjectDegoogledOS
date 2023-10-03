package p000;

/* renamed from: gtv */
/* compiled from: PG */
final class gtv extends gul {

    /* renamed from: a */
    private final long f12046a;

    public gtv(long j) {
        this.f12046a = j;
    }

    /* renamed from: a */
    public final long mo7044a() {
        return this.f12046a;
    }

    public final int hashCode() {
        long j = this.f12046a;
        return 1000003 ^ ((int) (j ^ (j >>> 32)));
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof gul) && this.f12046a == ((gul) obj).mo7044a();
        }
        return true;
    }

    public final String toString() {
        long j = this.f12046a;
        StringBuilder sb = new StringBuilder(47);
        sb.append("FetchTaskIdentifier{index=");
        sb.append(j);
        sb.append("}");
        return sb.toString();
    }
}
