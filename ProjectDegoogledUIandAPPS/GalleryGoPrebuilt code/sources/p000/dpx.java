package p000;

import p003j$.util.Optional;

/* renamed from: dpx */
/* compiled from: PG */
final class dpx extends dqa {

    /* renamed from: a */
    private final Optional f7038a;

    /* renamed from: b */
    private final Optional f7039b;

    /* renamed from: c */
    private final Optional f7040c;

    /* renamed from: d */
    private final boolean f7041d;

    public /* synthetic */ dpx(Optional optional, Optional optional2, Optional optional3, boolean z) {
        this.f7038a = optional;
        this.f7039b = optional2;
        this.f7040c = optional3;
        this.f7041d = z;
    }

    /* renamed from: a */
    public final Optional mo4330a() {
        return this.f7038a;
    }

    /* renamed from: b */
    public final Optional mo4331b() {
        return this.f7039b;
    }

    /* renamed from: c */
    public final Optional mo4332c() {
        return this.f7040c;
    }

    /* renamed from: d */
    public final boolean mo4333d() {
        return this.f7041d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof dqa) {
            dqa dqa = (dqa) obj;
            return this.f7038a.equals(dqa.mo4330a()) && this.f7039b.equals(dqa.mo4331b()) && this.f7040c.equals(dqa.mo4332c()) && this.f7041d == dqa.mo4333d();
        }
    }

    public final int hashCode() {
        return ((((((this.f7038a.hashCode() ^ 1000003) * 1000003) ^ this.f7039b.hashCode()) * 1000003) ^ this.f7040c.hashCode()) * 1000003) ^ (!this.f7041d ? 1237 : 1231);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f7038a);
        String valueOf2 = String.valueOf(this.f7039b);
        String valueOf3 = String.valueOf(this.f7040c);
        boolean z = this.f7041d;
        int length = String.valueOf(valueOf).length();
        StringBuilder sb = new StringBuilder(length + 89 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append("OneUpFragmentData{media=");
        sb.append(valueOf);
        sb.append(", initialMedia=");
        sb.append(valueOf2);
        sb.append(", specialTypeData=");
        sb.append(valueOf3);
        sb.append(", initialMediaFetchFailed=");
        sb.append(z);
        sb.append("}");
        return sb.toString();
    }
}
