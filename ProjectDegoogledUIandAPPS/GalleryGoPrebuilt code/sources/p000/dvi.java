package p000;

/* renamed from: dvi */
/* compiled from: PG */
public final class dvi extends dvn {

    /* renamed from: a */
    private final dvs f7453a;

    public dvi(dvs dvs) {
        this.f7453a = dvs;
    }

    /* renamed from: b */
    public final dwu mo4478b() {
        return dwu.HEADER;
    }

    /* renamed from: c */
    public final dvs mo4482c() {
        return this.f7453a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof dwv) {
            dwv dwv = (dwv) obj;
            if (dwu.HEADER != dwv.mo4478b() || !this.f7453a.equals(dwv.mo4482c())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f7453a.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f7453a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
        sb.append("PhotoGridItem{header=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
