package p000;

import java.util.List;

/* renamed from: hft */
/* compiled from: PG */
final class hft extends hfv {

    /* renamed from: a */
    private final String f12668a;

    /* renamed from: b */
    private final ikf f12669b;

    /* renamed from: c */
    private final hfs f12670c;

    /* renamed from: d */
    private final hso f12671d;

    /* renamed from: e */
    private final fyx f12672e;

    /* renamed from: f */
    private final iij f12673f;

    public /* synthetic */ hft(String str, ikf ikf, hfs hfs, hso hso, fyx fyx, iij iij) {
        this.f12668a = str;
        this.f12669b = ikf;
        this.f12670c = hfs;
        this.f12671d = hso;
        this.f12672e = fyx;
        this.f12673f = iij;
    }

    /* renamed from: a */
    public final String mo7373a() {
        return this.f12668a;
    }

    /* renamed from: b */
    public final ikf mo7374b() {
        return this.f12669b;
    }

    /* renamed from: c */
    public final hfs mo7375c() {
        return this.f12670c;
    }

    /* renamed from: d */
    public final hso mo7376d() {
        return this.f12671d;
    }

    /* renamed from: e */
    public final fyx mo7377e() {
        return this.f12672e;
    }

    /* renamed from: f */
    public final iij mo7379f() {
        return this.f12673f;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof hfv) {
            hfv hfv = (hfv) obj;
            return this.f12668a.equals(hfv.mo7373a()) && this.f12669b.equals(hfv.mo7374b()) && this.f12670c.equals(hfv.mo7375c()) && ife.m12856a((List) this.f12671d, (Object) hfv.mo7376d()) && this.f12672e.equals(hfv.mo7377e()) && this.f12673f.equals(hfv.mo7379f());
        }
    }

    public final int hashCode() {
        return ((((((((((this.f12668a.hashCode() ^ 1000003) * 1000003) ^ this.f12669b.hashCode()) * 1000003) ^ this.f12670c.hashCode()) * 1000003) ^ this.f12671d.hashCode()) * 1000003) ^ this.f12672e.hashCode()) * 1000003) ^ this.f12673f.hashCode();
    }

    public final String toString() {
        String str = this.f12668a;
        String valueOf = String.valueOf(this.f12669b);
        String valueOf2 = String.valueOf(this.f12670c);
        String valueOf3 = String.valueOf(this.f12671d);
        String valueOf4 = String.valueOf(this.f12672e);
        String valueOf5 = String.valueOf(this.f12673f);
        int length = String.valueOf(str).length();
        int length2 = String.valueOf(valueOf).length();
        int length3 = String.valueOf(valueOf2).length();
        int length4 = String.valueOf(valueOf3).length();
        StringBuilder sb = new StringBuilder(length + 89 + length2 + length3 + length4 + String.valueOf(valueOf4).length() + String.valueOf(valueOf5).length());
        sb.append("ProtoDataStoreConfig{name=");
        sb.append(str);
        sb.append(", schema=");
        sb.append(valueOf);
        sb.append(", storage=");
        sb.append(valueOf2);
        sb.append(", migrations=");
        sb.append(valueOf3);
        sb.append(", handler=");
        sb.append(valueOf4);
        sb.append(", extensionRegistry=");
        sb.append(valueOf5);
        sb.append("}");
        return sb.toString();
    }
}
