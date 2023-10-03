package p000;

import android.content.Intent;

/* renamed from: dzd */
/* compiled from: PG */
final class dzd extends dzf {

    /* renamed from: a */
    private final int f7700a;

    /* renamed from: b */
    private final int f7701b;

    /* renamed from: c */
    private final int f7702c;

    /* renamed from: d */
    private final Intent f7703d;

    /* renamed from: e */
    private final int f7704e;

    public /* synthetic */ dzd(int i, int i2, int i3, int i4, Intent intent) {
        this.f7700a = i;
        this.f7701b = i2;
        this.f7702c = i3;
        this.f7704e = i4;
        this.f7703d = intent;
    }

    /* renamed from: a */
    public final int mo4605a() {
        return this.f7700a;
    }

    /* renamed from: b */
    public final int mo4606b() {
        return this.f7701b;
    }

    /* renamed from: c */
    public final int mo4607c() {
        return this.f7702c;
    }

    /* renamed from: d */
    public final Intent mo4608d() {
        return this.f7703d;
    }

    /* renamed from: e */
    public final int mo4609e() {
        return this.f7704e;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof dzf) {
            dzf dzf = (dzf) obj;
            if (this.f7700a == dzf.mo4605a() && this.f7701b == dzf.mo4606b() && this.f7702c == dzf.mo4607c()) {
                int i = this.f7704e;
                int e = dzf.mo4609e();
                if (i != 0) {
                    return e == 1 && this.f7703d.equals(dzf.mo4608d());
                }
                throw null;
            }
        }
    }

    public final int hashCode() {
        int i = (((((this.f7700a ^ 1000003) * 1000003) ^ this.f7701b) * 1000003) ^ this.f7702c) * 1000003;
        if (this.f7704e != 0) {
            return ((i ^ 1) * 1000003) ^ this.f7703d.hashCode();
        }
        throw null;
    }

    public final String toString() {
        int i = this.f7700a;
        int i2 = this.f7701b;
        int i3 = this.f7702c;
        String str = this.f7704e != 1 ? "null" : "OPEN_PHOTOS";
        String valueOf = String.valueOf(this.f7703d);
        StringBuilder sb = new StringBuilder(str.length() + 108 + String.valueOf(valueOf).length());
        sb.append("Promo{promoHeaderText=");
        sb.append(i);
        sb.append(", promoSubHeaderText=");
        sb.append(i2);
        sb.append(", linkText=");
        sb.append(i3);
        sb.append(", type=");
        sb.append(str);
        sb.append(", linkIntent=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
