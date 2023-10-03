package p000;

import android.content.Intent;

/* renamed from: btj */
/* compiled from: PG */
public final class btj extends btn {

    /* renamed from: a */
    private final int f3541a;

    /* renamed from: b */
    private final int f3542b;

    /* renamed from: c */
    private final Intent f3543c;

    /* renamed from: d */
    private final int f3544d;

    public /* synthetic */ btj(int i, int i2, int i3, Intent intent) {
        this.f3541a = i;
        this.f3542b = i2;
        this.f3544d = i3;
        this.f3543c = intent;
    }

    /* renamed from: a */
    public final int mo2745a() {
        return this.f3541a;
    }

    /* renamed from: b */
    public final int mo2746b() {
        return this.f3542b;
    }

    /* renamed from: c */
    public final Intent mo2747c() {
        return this.f3543c;
    }

    /* renamed from: d */
    public final int mo2748d() {
        return this.f3544d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof btn) {
            btn btn = (btn) obj;
            if (this.f3541a == btn.mo2745a() && this.f3542b == btn.mo2746b()) {
                int i = this.f3544d;
                int d = btn.mo2748d();
                if (i != 0) {
                    return d == 1 && this.f3543c.equals(btn.mo2747c());
                }
                throw null;
            }
        }
    }

    public final int hashCode() {
        int i = (((this.f3541a ^ 1000003) * 1000003) ^ this.f3542b) * 1000003;
        if (this.f3544d != 0) {
            return ((i ^ 1) * 1000003) ^ this.f3543c.hashCode();
        }
        throw null;
    }

    public final String toString() {
        int i = this.f3541a;
        int i2 = this.f3542b;
        String str = this.f3544d != 1 ? "null" : "INSTALL_PHOTOS";
        String valueOf = String.valueOf(this.f3543c);
        StringBuilder sb = new StringBuilder(str.length() + 70 + String.valueOf(valueOf).length());
        sb.append("Promo{promoText=");
        sb.append(i);
        sb.append(", linkText=");
        sb.append(i2);
        sb.append(", type=");
        sb.append(str);
        sb.append(", linkIntent=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
