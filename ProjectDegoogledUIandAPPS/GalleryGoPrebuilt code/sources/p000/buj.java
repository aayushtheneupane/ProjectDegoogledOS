package p000;

import p003j$.util.Optional;

/* renamed from: buj */
/* compiled from: PG */
final class buj extends bwp {

    /* renamed from: a */
    private final boolean f3622a;

    /* renamed from: b */
    private final Optional f3623b;

    public buj(boolean z, Optional optional) {
        this.f3622a = z;
        if (optional != null) {
            this.f3623b = optional;
            return;
        }
        throw new NullPointerException("Null media");
    }

    /* renamed from: a */
    public final boolean mo2768a() {
        return this.f3622a;
    }

    /* renamed from: b */
    public final Optional mo2769b() {
        return this.f3623b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof bwp) {
            bwp bwp = (bwp) obj;
            return this.f3622a == bwp.mo2768a() && this.f3623b.equals(bwp.mo2769b());
        }
    }

    public final int hashCode() {
        return (((!this.f3622a ? 1237 : 1231) ^ 1000003) * 1000003) ^ this.f3623b.hashCode();
    }

    public final String toString() {
        boolean z = this.f3622a;
        String valueOf = String.valueOf(this.f3623b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 48);
        sb.append("ImageEditingFinishedEvent{success=");
        sb.append(z);
        sb.append(", media=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
