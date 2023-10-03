package p000;

import java.util.Arrays;

/* renamed from: fda */
/* compiled from: PG */
final class fda extends fdc {

    /* renamed from: a */
    public final String f9294a;

    /* renamed from: b */
    public final ikf f9295b;

    public /* synthetic */ fda(String str, ikf ikf) {
        this.f9294a = str;
        this.f9295b = ikf;
    }

    /* renamed from: a */
    public final String mo5497a() {
        return this.f9294a;
    }

    /* renamed from: b */
    public final ikf mo5498b() {
        return this.f9295b;
    }

    /* renamed from: c */
    public final void mo5499c() {
    }

    /* renamed from: d */
    public final void mo5500d() {
    }

    /* renamed from: e */
    public final void mo5501e() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof fdc) {
            fdc fdc = (fdc) obj;
            if (this.f9294a.equals(fdc.mo5497a()) && this.f9295b.equals(fdc.mo5498b())) {
                fdc.mo5501e();
                fdc.mo5499c();
                if (fdc instanceof fda) {
                    fda fda = (fda) fdc;
                } else {
                    fdc.mo5500d();
                }
                if (Arrays.equals((int[]) null, (int[]) null)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.f9294a.hashCode() ^ 1000003) * 1000003) ^ this.f9295b.hashCode()) * 583896283) ^ Arrays.hashCode((int[]) null);
    }

    public final String toString() {
        String str = this.f9294a;
        String valueOf = String.valueOf(this.f9295b);
        String valueOf2 = String.valueOf((Object) null);
        String valueOf3 = String.valueOf((Object) null);
        String arrays = Arrays.toString((int[]) null);
        int length = String.valueOf(str).length();
        int length2 = String.valueOf(valueOf).length();
        int length3 = String.valueOf(valueOf2).length();
        StringBuilder sb = new StringBuilder(length + 79 + length2 + length3 + String.valueOf(valueOf3).length() + String.valueOf(arrays).length());
        sb.append("ClearcutData{logSource=");
        sb.append(str);
        sb.append(", message=");
        sb.append(valueOf);
        sb.append(", visualElements=");
        sb.append(valueOf2);
        sb.append(", eventCode=");
        sb.append(valueOf3);
        sb.append(", experimentIds=");
        sb.append(arrays);
        sb.append("}");
        return sb.toString();
    }
}
