package p000;

import p003j$.util.Optional;

/* renamed from: dyh */
/* compiled from: PG */
final class dyh extends dyu {

    /* renamed from: a */
    private final Optional f7644a;

    /* renamed from: b */
    private final Optional f7645b;

    /* renamed from: c */
    private final Optional f7646c;

    /* renamed from: d */
    private final Optional f7647d;

    /* renamed from: e */
    private final Optional f7648e;

    /* renamed from: f */
    private final Boolean f7649f;

    public /* synthetic */ dyh(Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, Boolean bool) {
        this.f7644a = optional;
        this.f7645b = optional2;
        this.f7646c = optional3;
        this.f7647d = optional4;
        this.f7648e = optional5;
        this.f7649f = bool;
    }

    /* renamed from: a */
    public final Optional mo4572a() {
        return this.f7644a;
    }

    /* renamed from: b */
    public final Optional mo4573b() {
        return this.f7645b;
    }

    /* renamed from: c */
    public final Optional mo4574c() {
        return this.f7646c;
    }

    /* renamed from: d */
    public final Optional mo4575d() {
        return this.f7647d;
    }

    /* renamed from: e */
    public final Optional mo4576e() {
        return this.f7648e;
    }

    /* renamed from: f */
    public final Boolean mo4578f() {
        return this.f7649f;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof dyu) {
            dyu dyu = (dyu) obj;
            return this.f7644a.equals(dyu.mo4572a()) && this.f7645b.equals(dyu.mo4573b()) && this.f7646c.equals(dyu.mo4574c()) && this.f7647d.equals(dyu.mo4575d()) && this.f7648e.equals(dyu.mo4576e()) && this.f7649f.equals(dyu.mo4578f());
        }
    }

    public final int hashCode() {
        return ((((((((((this.f7644a.hashCode() ^ 1000003) * 1000003) ^ this.f7645b.hashCode()) * 1000003) ^ this.f7646c.hashCode()) * 1000003) ^ this.f7647d.hashCode()) * 1000003) ^ this.f7648e.hashCode()) * 1000003) ^ this.f7649f.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f7644a);
        String valueOf2 = String.valueOf(this.f7645b);
        String valueOf3 = String.valueOf(this.f7646c);
        String valueOf4 = String.valueOf(this.f7647d);
        String valueOf5 = String.valueOf(this.f7648e);
        String valueOf6 = String.valueOf(this.f7649f);
        int length = String.valueOf(valueOf).length();
        int length2 = String.valueOf(valueOf2).length();
        int length3 = String.valueOf(valueOf3).length();
        int length4 = String.valueOf(valueOf4).length();
        StringBuilder sb = new StringBuilder(length + 108 + length2 + length3 + length4 + String.valueOf(valueOf5).length() + String.valueOf(valueOf6).length());
        sb.append("PhotoGridFragmentData{categories=");
        sb.append(valueOf);
        sb.append(", gridItems=");
        sb.append(valueOf2);
        sb.append(", promo=");
        sb.append(valueOf3);
        sb.append(", specialTypeIdToData=");
        sb.append(valueOf4);
        sb.append(", selectedMedia=");
        sb.append(valueOf5);
        sb.append(", syncCompleted=");
        sb.append(valueOf6);
        sb.append("}");
        return sb.toString();
    }
}
