package p000;

/* renamed from: brh */
/* compiled from: PG */
final class brh extends bri {

    /* renamed from: a */
    private final btn f3419a;

    public brh(btn btn) {
        this.f3419a = btn;
    }

    /* renamed from: b */
    public final int mo2689b() {
        return 3;
    }

    /* renamed from: d */
    public final btn mo2697d() {
        return this.f3419a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof bsb) {
            bsb bsb = (bsb) obj;
            if (bsb.mo2689b() != 3 || !this.f3419a.equals(bsb.mo2697d())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f3419a.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f3419a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
        sb.append("FoldersGridItem{promo=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
