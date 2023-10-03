package p000;

/* renamed from: hea */
/* compiled from: PG */
public final class hea extends hed {

    /* renamed from: a */
    public final String f12565a;

    /* renamed from: b */
    public final hsu f12566b;

    /* renamed from: c */
    public final boolean f12567c;

    public /* synthetic */ hea(String str, hsu hsu, boolean z) {
        this.f12565a = str;
        this.f12566b = hsu;
        this.f12567c = z;
    }

    /* renamed from: a */
    public final String mo7324a() {
        return this.f12565a;
    }

    /* renamed from: b */
    public final hsu mo7325b() {
        return this.f12566b;
    }

    /* renamed from: c */
    public final boolean mo7326c() {
        return this.f12567c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof hed) {
            hed hed = (hed) obj;
            return this.f12565a.equals(hed.mo7324a()) && this.f12566b.equals(hed.mo7325b()) && this.f12567c == hed.mo7326c();
        }
    }

    public final int hashCode() {
        return ((((this.f12565a.hashCode() ^ 1000003) * 1000003) ^ this.f12566b.hashCode()) * 1000003) ^ (!this.f12567c ? 1237 : 1231);
    }

    public final String toString() {
        String str = this.f12565a;
        String valueOf = String.valueOf(this.f12566b);
        boolean z = this.f12567c;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 60 + String.valueOf(valueOf).length());
        sb.append("FeedbackOptions{categoryTag=");
        sb.append(str);
        sb.append(", psd=");
        sb.append(valueOf);
        sb.append(", includeScreenshot=");
        sb.append(z);
        sb.append("}");
        return sb.toString();
    }
}
