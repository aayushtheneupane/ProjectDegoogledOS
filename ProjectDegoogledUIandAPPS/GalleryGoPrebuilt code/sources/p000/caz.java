package p000;

import android.graphics.Bitmap;

/* renamed from: caz */
/* compiled from: PG */
final class caz extends cbd {

    /* renamed from: a */
    public final Bitmap f3999a;

    /* renamed from: b */
    public final car f4000b;

    /* renamed from: c */
    public final boolean f4001c;

    public /* synthetic */ caz(Bitmap bitmap, car car, boolean z) {
        this.f3999a = bitmap;
        this.f4000b = car;
        this.f4001c = z;
    }

    /* renamed from: a */
    public final Bitmap mo2972a() {
        return this.f3999a;
    }

    /* renamed from: b */
    public final car mo2973b() {
        return this.f4000b;
    }

    /* renamed from: c */
    public final boolean mo2974c() {
        return this.f4001c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof cbd) {
            cbd cbd = (cbd) obj;
            return this.f3999a.equals(cbd.mo2972a()) && this.f4000b.equals(cbd.mo2973b()) && this.f4001c == cbd.mo2974c();
        }
    }

    public final int hashCode() {
        return ((((this.f3999a.hashCode() ^ 1000003) * 1000003) ^ this.f4000b.hashCode()) * 1000003) ^ (!this.f4001c ? 1237 : 1231);
    }

    /* renamed from: d */
    public final cbc mo2975d() {
        return new cbc((cbd) this);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f3999a);
        String valueOf2 = String.valueOf(this.f4000b);
        boolean z = this.f4001c;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 44 + String.valueOf(valueOf2).length());
        sb.append("PresetItem{bitmap=");
        sb.append(valueOf);
        sb.append(", preset=");
        sb.append(valueOf2);
        sb.append(", selected=");
        sb.append(z);
        sb.append("}");
        return sb.toString();
    }
}
