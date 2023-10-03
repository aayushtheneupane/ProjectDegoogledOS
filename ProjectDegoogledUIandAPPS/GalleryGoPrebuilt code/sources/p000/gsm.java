package p000;

/* renamed from: gsm */
/* compiled from: PG */
public final class gsm extends gsv {

    /* renamed from: a */
    public final Class f11968a;

    /* renamed from: b */
    public final ahb f11969b;

    /* renamed from: c */
    public final gst f11970c;

    /* renamed from: d */
    public final ahf f11971d;

    /* renamed from: e */
    public final hpy f11972e;

    /* renamed from: f */
    public final hpy f11973f;

    /* renamed from: g */
    public final hto f11974g;

    public /* synthetic */ gsm(Class cls, ahb ahb, gst gst, ahf ahf, hpy hpy, hpy hpy2, hto hto) {
        this.f11968a = cls;
        this.f11969b = ahb;
        this.f11970c = gst;
        this.f11971d = ahf;
        this.f11972e = hpy;
        this.f11973f = hpy2;
        this.f11974g = hto;
    }

    /* renamed from: a */
    public final Class mo7002a() {
        return this.f11968a;
    }

    /* renamed from: b */
    public final ahb mo7003b() {
        return this.f11969b;
    }

    /* renamed from: c */
    public final gst mo7004c() {
        return this.f11970c;
    }

    /* renamed from: d */
    public final ahf mo7005d() {
        return this.f11971d;
    }

    /* renamed from: e */
    public final hpy mo7006e() {
        return this.f11972e;
    }

    /* renamed from: f */
    public final hpy mo7008f() {
        return this.f11973f;
    }

    /* renamed from: g */
    public final hto mo7009g() {
        return this.f11974g;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof gsv) {
            gsv gsv = (gsv) obj;
            return this.f11968a.equals(gsv.mo7002a()) && this.f11969b.equals(gsv.mo7003b()) && this.f11970c.equals(gsv.mo7004c()) && this.f11971d.equals(gsv.mo7005d()) && this.f11972e.equals(gsv.mo7006e()) && this.f11973f.equals(gsv.mo7008f()) && this.f11974g.equals(gsv.mo7009g());
        }
    }

    public final int hashCode() {
        return ((((((((((((this.f11968a.hashCode() ^ 1000003) * 1000003) ^ this.f11969b.hashCode()) * 1000003) ^ this.f11970c.hashCode()) * 1000003) ^ this.f11971d.hashCode()) * 1000003) ^ this.f11972e.hashCode()) * 1000003) ^ this.f11973f.hashCode()) * 1000003) ^ this.f11974g.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f11968a);
        String valueOf2 = String.valueOf(this.f11969b);
        String valueOf3 = String.valueOf(this.f11970c);
        String valueOf4 = String.valueOf(this.f11971d);
        String valueOf5 = String.valueOf(this.f11972e);
        String valueOf6 = String.valueOf(this.f11973f);
        String valueOf7 = String.valueOf(this.f11974g);
        int length = String.valueOf(valueOf).length();
        int length2 = String.valueOf(valueOf2).length();
        int length3 = String.valueOf(valueOf3).length();
        int length4 = String.valueOf(valueOf4).length();
        int length5 = String.valueOf(valueOf5).length();
        StringBuilder sb = new StringBuilder(length + 96 + length2 + length3 + length4 + length5 + String.valueOf(valueOf6).length() + String.valueOf(valueOf7).length());
        sb.append("TikTokWorkSpec{workerClass=");
        sb.append(valueOf);
        sb.append(", constraints=");
        sb.append(valueOf2);
        sb.append(", initialDelay=");
        sb.append(valueOf3);
        sb.append(", inputData=");
        sb.append(valueOf4);
        sb.append(", periodic=");
        sb.append(valueOf5);
        sb.append(", unique=");
        sb.append(valueOf6);
        sb.append(", tags=");
        sb.append(valueOf7);
        sb.append("}");
        return sb.toString();
    }
}
