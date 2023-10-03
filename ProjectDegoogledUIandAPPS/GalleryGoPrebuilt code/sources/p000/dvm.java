package p000;

/* renamed from: dvm */
/* compiled from: PG */
final class dvm extends dvn {

    /* renamed from: a */
    private final dzf f7457a;

    public dvm(dzf dzf) {
        this.f7457a = dzf;
    }

    /* renamed from: b */
    public final dwu mo4478b() {
        return dwu.PROMO;
    }

    /* renamed from: g */
    public final dzf mo4499g() {
        return this.f7457a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof dwv) {
            dwv dwv = (dwv) obj;
            if (dwu.PROMO != dwv.mo4478b() || !this.f7457a.equals(dwv.mo4499g())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f7457a.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f7457a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 21);
        sb.append("PhotoGridItem{promo=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
