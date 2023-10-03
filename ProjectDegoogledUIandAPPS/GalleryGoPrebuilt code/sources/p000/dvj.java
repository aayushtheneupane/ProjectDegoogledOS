package p000;

/* renamed from: dvj */
/* compiled from: PG */
final class dvj extends dvn {

    /* renamed from: a */
    private final Object f7454a;

    public dvj(Object obj) {
        this.f7454a = obj;
    }

    /* renamed from: b */
    public final dwu mo4478b() {
        return dwu.HEADER_PLACEHOLDER;
    }

    /* renamed from: d */
    public final Object mo4486d() {
        return this.f7454a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof dwv) {
            dwv dwv = (dwv) obj;
            if (dwu.HEADER_PLACEHOLDER != dwv.mo4478b() || !this.f7454a.equals(dwv.mo4486d())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f7454a.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f7454a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 33);
        sb.append("PhotoGridItem{headerplaceholder=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
