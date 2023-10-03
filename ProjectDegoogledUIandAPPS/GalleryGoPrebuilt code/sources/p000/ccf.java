package p000;

import p003j$.util.Optional;

/* renamed from: ccf */
/* compiled from: PG */
final class ccf extends cco {

    /* renamed from: a */
    public final Optional f4046a;

    /* renamed from: b */
    public final Optional f4047b;

    /* renamed from: c */
    private final Optional f4048c;

    public /* synthetic */ ccf(Optional optional, Optional optional2, Optional optional3) {
        this.f4046a = optional;
        this.f4048c = optional2;
        this.f4047b = optional3;
    }

    /* renamed from: a */
    public final Optional mo3024a() {
        return this.f4046a;
    }

    /* renamed from: b */
    public final Optional mo3025b() {
        return this.f4048c;
    }

    /* renamed from: c */
    public final Optional mo3026c() {
        return this.f4047b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof cco) {
            cco cco = (cco) obj;
            return this.f4046a.equals(cco.mo3024a()) && this.f4048c.equals(cco.mo3025b()) && this.f4047b.equals(cco.mo3026c());
        }
    }

    public final int hashCode() {
        return ((((this.f4046a.hashCode() ^ 1000003) * 1000003) ^ this.f4048c.hashCode()) * 1000003) ^ this.f4047b.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f4046a);
        String valueOf2 = String.valueOf(this.f4048c);
        String valueOf3 = String.valueOf(this.f4047b);
        int length = String.valueOf(valueOf).length();
        StringBuilder sb = new StringBuilder(length + 63 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append("VideoEditorFragmentData{media=");
        sb.append(valueOf);
        sb.append(", thumbnails=");
        sb.append(valueOf2);
        sb.append(", isVideoSupported=");
        sb.append(valueOf3);
        sb.append("}");
        return sb.toString();
    }
}
