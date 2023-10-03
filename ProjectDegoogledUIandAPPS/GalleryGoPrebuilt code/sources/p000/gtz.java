package p000;

/* renamed from: gtz */
/* compiled from: PG */
final class gtz extends gve {

    /* renamed from: b */
    private final long f12059b;

    /* renamed from: c */
    private final gvc f12060c;

    /* renamed from: d */
    private final boolean f12061d;

    /* renamed from: e */
    private final hpy f12062e;

    /* renamed from: f */
    private final hpy f12063f;

    public gtz(long j, gvc gvc, boolean z, hpy hpy, hpy hpy2) {
        this.f12059b = j;
        if (gvc != null) {
            this.f12060c = gvc;
            this.f12061d = z;
            if (hpy != null) {
                this.f12062e = hpy;
                if (hpy2 != null) {
                    this.f12063f = hpy2;
                    return;
                }
                throw new NullPointerException("Null maybeInstanceData");
            }
            throw new NullPointerException("Null maybeTopicData");
        }
        throw new NullPointerException("Null callbacks");
    }

    /* renamed from: a */
    public final long mo7069a() {
        return this.f12059b;
    }

    /* renamed from: b */
    public final gvc mo7070b() {
        return this.f12060c;
    }

    /* renamed from: c */
    public final boolean mo7071c() {
        return this.f12061d;
    }

    /* renamed from: d */
    public final hpy mo7072d() {
        return this.f12062e;
    }

    /* renamed from: e */
    public final hpy mo7073e() {
        return this.f12063f;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof gve) {
            gve gve = (gve) obj;
            return this.f12059b == gve.mo7069a() && this.f12060c.equals(gve.mo7070b()) && this.f12061d == gve.mo7071c() && this.f12062e.equals(gve.mo7072d()) && this.f12063f.equals(gve.mo7073e());
        }
    }

    public final int hashCode() {
        int i;
        long j = this.f12059b;
        int hashCode = (((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.f12060c.hashCode()) * 1000003;
        if (!this.f12061d) {
            i = 1237;
        } else {
            i = 1231;
        }
        return this.f12063f.hashCode() ^ ((((hashCode ^ i) * 1000003) ^ this.f12062e.hashCode()) * 1000003);
    }

    public final String toString() {
        long j = this.f12059b;
        String valueOf = String.valueOf(this.f12060c);
        boolean z = this.f12061d;
        String valueOf2 = String.valueOf(this.f12062e);
        String valueOf3 = String.valueOf(this.f12063f);
        int length = String.valueOf(valueOf).length();
        StringBuilder sb = new StringBuilder(length + 130 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append("SubscriptionCallbacksState{index=");
        sb.append(j);
        sb.append(", callbacks=");
        sb.append(valueOf);
        sb.append(", openBackgroundFetch=");
        sb.append(z);
        sb.append(", maybeTopicData=");
        sb.append(valueOf2);
        sb.append(", maybeInstanceData=");
        sb.append(valueOf3);
        sb.append("}");
        return sb.toString();
    }
}
