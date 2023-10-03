package p000;

import p003j$.util.Optional;

/* renamed from: bsv */
/* compiled from: PG */
final class bsv extends btb {

    /* renamed from: a */
    private final Optional f3502a;

    /* renamed from: b */
    private final Optional f3503b;

    public /* synthetic */ bsv(Optional optional, Optional optional2) {
        this.f3502a = optional;
        this.f3503b = optional2;
    }

    /* renamed from: a */
    public final Optional mo2725a() {
        return this.f3502a;
    }

    /* renamed from: b */
    public final Optional mo2726b() {
        return this.f3503b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof btb) {
            btb btb = (btb) obj;
            return this.f3502a.equals(btb.mo2725a()) && this.f3503b.equals(btb.mo2726b());
        }
    }

    public final int hashCode() {
        return ((this.f3502a.hashCode() ^ 1000003) * 1000003) ^ this.f3503b.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f3502a);
        String valueOf2 = String.valueOf(this.f3503b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 43 + String.valueOf(valueOf2).length());
        sb.append("DeviceFoldersFragmentData{folders=");
        sb.append(valueOf);
        sb.append(", promo=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
