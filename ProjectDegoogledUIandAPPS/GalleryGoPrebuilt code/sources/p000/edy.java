package p000;

/* renamed from: edy */
/* compiled from: PG */
final class edy extends edw {

    /* renamed from: a */
    public final String f8071a;

    /* renamed from: b */
    public final String f8072b;

    /* renamed from: c */
    public final String f8073c;

    /* renamed from: d */
    public final int f8074d;

    /* renamed from: e */
    public final long f8075e;

    /* renamed from: f */
    public final boolean f8076f;

    /* renamed from: g */
    public final boolean f8077g;

    public /* synthetic */ edy(String str, String str2, String str3, int i, long j, boolean z, boolean z2) {
        this.f8071a = str;
        this.f8072b = str2;
        this.f8073c = str3;
        this.f8074d = i;
        this.f8075e = j;
        this.f8076f = z;
        this.f8077g = z2;
    }

    /* renamed from: c */
    public final String mo4729c() {
        return this.f8071a;
    }

    /* renamed from: d */
    public final String mo4730d() {
        return this.f8072b;
    }

    /* renamed from: e */
    public final String mo4731e() {
        return this.f8073c;
    }

    /* renamed from: f */
    public final int mo4732f() {
        return this.f8074d;
    }

    /* renamed from: g */
    public final long mo4733g() {
        return this.f8075e;
    }

    /* renamed from: h */
    public final boolean mo4734h() {
        return this.f8076f;
    }

    /* renamed from: i */
    public final boolean mo4735i() {
        return this.f8077g;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof edw) {
            edw edw = (edw) obj;
            return this.f8071a.equals(edw.mo4729c()) && this.f8072b.equals(edw.mo4730d()) && this.f8073c.equals(edw.mo4731e()) && this.f8074d == edw.mo4732f() && this.f8075e == edw.mo4733g() && this.f8076f == edw.mo4734h() && this.f8077g == edw.mo4735i();
        }
    }

    public final int hashCode() {
        int hashCode = this.f8071a.hashCode();
        int hashCode2 = this.f8072b.hashCode();
        int hashCode3 = this.f8073c.hashCode();
        int i = this.f8074d;
        long j = this.f8075e;
        int i2 = (((((((((hashCode ^ 1000003) * 1000003) ^ hashCode2) * 1000003) ^ hashCode3) * 1000003) ^ i) * 1000003) ^ ((int) ((j >>> 32) ^ j))) * 1000003;
        int i3 = 1237;
        int i4 = (i2 ^ (!this.f8076f ? 1237 : 1231)) * 1000003;
        if (this.f8077g) {
            i3 = 1231;
        }
        return i4 ^ i3;
    }

    /* renamed from: k */
    public final edv mo4736k() {
        return new edv((edw) this);
    }

    public final String toString() {
        String str = this.f8071a;
        String str2 = this.f8072b;
        String str3 = this.f8073c;
        int i = this.f8074d;
        long j = this.f8075e;
        boolean z = this.f8076f;
        boolean z2 = this.f8077g;
        int length = String.valueOf(str).length();
        StringBuilder sb = new StringBuilder(length + 150 + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append("AppShare{activityClassName=");
        sb.append(str);
        sb.append(", packageName=");
        sb.append(str2);
        sb.append(", label=");
        sb.append(str3);
        sb.append(", numShares=");
        sb.append(i);
        sb.append(", lastShare=");
        sb.append(j);
        sb.append(", supportsMultiple=");
        sb.append(z);
        sb.append(", supportsVideo=");
        sb.append(z2);
        sb.append("}");
        return sb.toString();
    }
}
