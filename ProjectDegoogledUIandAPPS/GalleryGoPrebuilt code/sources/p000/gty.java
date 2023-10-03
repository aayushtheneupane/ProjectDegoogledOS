package p000;

/* renamed from: gty */
/* compiled from: PG */
final class gty extends gva {

    /* renamed from: a */
    public final gud f12053a;

    /* renamed from: b */
    public final long f12054b;

    /* renamed from: c */
    public final gul f12055c;

    /* renamed from: d */
    public final gup f12056d;

    /* renamed from: e */
    public final int f12057e;

    /* renamed from: f */
    public final long f12058f;

    public gty(gud gud, long j, gul gul, gup gup, int i, long j2) {
        if (gud != null) {
            this.f12053a = gud;
            this.f12054b = j;
            this.f12055c = gul;
            this.f12056d = gup;
            this.f12057e = i;
            this.f12058f = j2;
            return;
        }
        throw new NullPointerException("Null dataSource");
    }

    /* renamed from: a */
    public final gud mo7060a() {
        return this.f12053a;
    }

    /* renamed from: b */
    public final long mo7061b() {
        return this.f12054b;
    }

    /* renamed from: c */
    public final gul mo7062c() {
        return this.f12055c;
    }

    /* renamed from: d */
    public final gup mo7063d() {
        return this.f12056d;
    }

    /* renamed from: e */
    public final int mo7064e() {
        return this.f12057e;
    }

    /* renamed from: f */
    public final long mo7066f() {
        return this.f12058f;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof gva) {
            gva gva = (gva) obj;
            return this.f12053a.equals(gva.mo7060a()) && this.f12054b == gva.mo7061b() && this.f12055c.equals(gva.mo7062c()) && this.f12056d.equals(gva.mo7063d()) && this.f12057e == gva.mo7064e() && this.f12058f == gva.mo7066f();
        }
    }

    public final int hashCode() {
        int hashCode = this.f12053a.hashCode();
        long j = this.f12054b;
        int hashCode2 = this.f12055c.hashCode();
        int hashCode3 = this.f12056d.hashCode();
        int i = this.f12057e;
        long j2 = this.f12058f;
        return ((((((((((hashCode ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ hashCode2) * 1000003) ^ hashCode3) * 1000003) ^ i) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2));
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f12053a);
        long j = this.f12054b;
        String valueOf2 = String.valueOf(this.f12055c);
        String valueOf3 = String.valueOf(this.f12056d);
        int i = this.f12057e;
        long j2 = this.f12058f;
        int length = String.valueOf(valueOf).length();
        StringBuilder sb = new StringBuilder(length + 173 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append("SubscribeSequenceState{dataSource=");
        sb.append(valueOf);
        sb.append(", index=");
        sb.append(j);
        sb.append(", fetchTaskIdentifier=");
        sb.append(valueOf2);
        sb.append(", loadTaskIdentifier=");
        sb.append(valueOf3);
        sb.append(", loadAttempts=");
        sb.append(i);
        sb.append(", epochTimeAtStartMs=");
        sb.append(j2);
        sb.append("}");
        return sb.toString();
    }
}
