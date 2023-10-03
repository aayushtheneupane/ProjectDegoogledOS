package p000;

/* renamed from: gtx */
/* compiled from: PG */
final class gtx extends guz {

    /* renamed from: a */
    public final gud f12048a;

    /* renamed from: b */
    public final gvv f12049b;

    /* renamed from: c */
    public final long f12050c;

    /* renamed from: d */
    public final gva f12051d;

    /* renamed from: e */
    public final int f12052e;

    public gtx(gud gud, gvv gvv, long j, int i, gva gva) {
        if (gud != null) {
            this.f12048a = gud;
            if (gvv != null) {
                this.f12049b = gvv;
                this.f12050c = j;
                this.f12052e = i;
                this.f12051d = gva;
                return;
            }
            throw new NullPointerException("Null tolerance");
        }
        throw new NullPointerException("Null dataSource");
    }

    /* renamed from: a */
    public final gud mo7052a() {
        return this.f12048a;
    }

    /* renamed from: b */
    public final gvv mo7053b() {
        return this.f12049b;
    }

    /* renamed from: c */
    public final long mo7054c() {
        return this.f12050c;
    }

    /* renamed from: d */
    public final gva mo7055d() {
        return this.f12051d;
    }

    /* renamed from: e */
    public final int mo7056e() {
        return this.f12052e;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof guz) {
            guz guz = (guz) obj;
            return this.f12048a.equals(guz.mo7052a()) && this.f12049b.equals(guz.mo7053b()) && this.f12050c == guz.mo7054c() && this.f12052e == guz.mo7056e() && this.f12051d.equals(guz.mo7055d());
        }
    }

    public final int hashCode() {
        int hashCode = this.f12048a.hashCode();
        int hashCode2 = this.f12049b.hashCode();
        long j = this.f12050c;
        return ((((((((hashCode ^ 1000003) * 1000003) ^ hashCode2) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.f12052e) * 1000003) ^ this.f12051d.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f12048a);
        String valueOf2 = String.valueOf(this.f12049b);
        long j = this.f12050c;
        int i = this.f12052e;
        String str = i != 1 ? i != 2 ? "SUBSCRIBE" : "FORCE_REFRESH" : "UNDEFINED";
        String valueOf3 = String.valueOf(this.f12051d);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 116 + String.valueOf(valueOf2).length() + str.length() + String.valueOf(valueOf3).length());
        sb.append("SubscribeCallState{dataSource=");
        sb.append(valueOf);
        sb.append(", tolerance=");
        sb.append(valueOf2);
        sb.append(", index=");
        sb.append(j);
        sb.append(", subscribeCallType=");
        sb.append(str);
        sb.append(", subscribeSequenceState=");
        sb.append(valueOf3);
        sb.append("}");
        return sb.toString();
    }
}
