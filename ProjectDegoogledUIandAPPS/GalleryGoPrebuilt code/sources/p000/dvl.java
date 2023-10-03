package p000;

/* renamed from: dvl */
/* compiled from: PG */
final class dvl extends dvn {

    /* renamed from: a */
    private final Object f7456a;

    public dvl(Object obj) {
        this.f7456a = obj;
    }

    /* renamed from: b */
    public final dwu mo4478b() {
        return dwu.MEDIA_PLACEHOLDER;
    }

    /* renamed from: f */
    public final Object mo4495f() {
        return this.f7456a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof dwv) {
            dwv dwv = (dwv) obj;
            if (dwu.MEDIA_PLACEHOLDER != dwv.mo4478b() || !this.f7456a.equals(dwv.mo4495f())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f7456a.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f7456a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 32);
        sb.append("PhotoGridItem{mediaplaceholder=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
