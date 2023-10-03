package p000;

import p003j$.util.Optional;

/* renamed from: cro */
/* compiled from: PG */
final class cro extends crq {

    /* renamed from: a */
    private final Optional f5498a;

    /* renamed from: b */
    private final boolean f5499b;

    /* renamed from: c */
    private final boolean f5500c;

    /* renamed from: d */
    private final int f5501d;

    /* renamed from: e */
    private final int f5502e;

    public /* synthetic */ cro(Optional optional, int i, boolean z, boolean z2, int i2) {
        this.f5498a = optional;
        this.f5501d = i;
        this.f5499b = z;
        this.f5500c = z2;
        this.f5502e = i2;
    }

    /* renamed from: a */
    public final Optional mo3781a() {
        return this.f5498a;
    }

    /* renamed from: b */
    public final boolean mo3782b() {
        return this.f5499b;
    }

    /* renamed from: c */
    public final boolean mo3783c() {
        return this.f5500c;
    }

    /* renamed from: d */
    public final int mo3784d() {
        return this.f5501d;
    }

    /* renamed from: e */
    public final int mo3785e() {
        return this.f5502e;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof crq) {
            crq crq = (crq) obj;
            if (this.f5498a.equals(crq.mo3781a())) {
                int i = this.f5501d;
                int d = crq.mo3784d();
                if (i == 0) {
                    throw null;
                } else if (i == d && this.f5499b == crq.mo3782b() && this.f5500c == crq.mo3783c()) {
                    int i2 = this.f5502e;
                    int e = crq.mo3785e();
                    if (i2 == 0) {
                        throw null;
                    } else if (i2 == e) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (this.f5498a.hashCode() ^ 1000003) * 1000003;
        int i = this.f5501d;
        if (i != 0) {
            int i2 = 1237;
            int i3 = (((hashCode ^ i) * 1000003) ^ (!this.f5499b ? 1237 : 1231)) * 1000003;
            if (this.f5500c) {
                i2 = 1231;
            }
            int i4 = (i3 ^ i2) * 1000003;
            int i5 = this.f5502e;
            if (i5 != 0) {
                return i4 ^ i5;
            }
            throw null;
        }
        throw null;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f5498a);
        int i = this.f5501d;
        String str = "null";
        String str2 = i != 1 ? i != 2 ? i != 3 ? i != 4 ? str : "DENIED" : "GRANTED" : "PENDING" : "NOT_REQUESTED";
        boolean z = this.f5499b;
        boolean z2 = this.f5500c;
        int i2 = this.f5502e;
        if (i2 != 0) {
            str = Integer.toString(i2 - 1);
        }
        String valueOf2 = String.valueOf(str);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 113 + str2.length() + String.valueOf(valueOf2).length());
        sb.append("HomeFragmentData{onboardingState=");
        sb.append(valueOf);
        sb.append(", permissionState=");
        sb.append(str2);
        sb.append(", photoGridHasContent=");
        sb.append(z);
        sb.append(", folderGridHasContent=");
        sb.append(z2);
        sb.append(", tab=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
