package p000;

import java.util.Map;

/* renamed from: hgs */
/* compiled from: PG */
final class hgs extends hgw {

    /* renamed from: a */
    private final long f12706a;

    /* renamed from: b */
    private final long f12707b;

    /* renamed from: c */
    private final Map f12708c;

    public hgs(long j, long j2, Map map) {
        this.f12706a = j;
        this.f12707b = j2;
        if (map != null) {
            this.f12708c = map;
            return;
        }
        throw new NullPointerException("Null constraints");
    }

    /* renamed from: a */
    public final long mo7413a() {
        return this.f12706a;
    }

    /* renamed from: b */
    public final long mo7414b() {
        return this.f12707b;
    }

    /* renamed from: c */
    public final Map mo7415c() {
        return this.f12708c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof hgw) {
            hgw hgw = (hgw) obj;
            return this.f12706a == hgw.mo7413a() && this.f12707b == hgw.mo7414b() && this.f12708c.equals(hgw.mo7415c());
        }
    }

    public final int hashCode() {
        long j = this.f12706a;
        long j2 = this.f12707b;
        return this.f12708c.hashCode() ^ ((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003);
    }

    public final String toString() {
        long j = this.f12706a;
        long j2 = this.f12707b;
        String valueOf = String.valueOf(this.f12708c);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 92);
        sb.append("SyncConfig{minSyncInterval=");
        sb.append(j);
        sb.append(", timeout=");
        sb.append(j2);
        sb.append(", constraints=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
