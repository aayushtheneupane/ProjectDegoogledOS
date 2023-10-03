package p000;

/* renamed from: gtw */
/* compiled from: PG */
final class gtw extends gup {

    /* renamed from: a */
    private final long f12047a;

    public gtw(long j) {
        this.f12047a = j;
    }

    /* renamed from: a */
    public final long mo7048a() {
        return this.f12047a;
    }

    public final int hashCode() {
        long j = this.f12047a;
        return 1000003 ^ ((int) (j ^ (j >>> 32)));
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof gup) && this.f12047a == ((gup) obj).mo7048a();
        }
        return true;
    }

    public final String toString() {
        long j = this.f12047a;
        StringBuilder sb = new StringBuilder(46);
        sb.append("LoadTaskIdentifier{index=");
        sb.append(j);
        sb.append("}");
        return sb.toString();
    }
}
