package p000;

/* renamed from: brg */
/* compiled from: PG */
final class brg extends bri {

    /* renamed from: a */
    private final String f3418a;

    public brg(String str) {
        this.f3418a = str;
    }

    /* renamed from: b */
    public final int mo2689b() {
        return 1;
    }

    /* renamed from: c */
    public final String mo2693c() {
        return this.f3418a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof bsb) {
            bsb bsb = (bsb) obj;
            if (bsb.mo2689b() != 1 || !this.f3418a.equals(bsb.mo2693c())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f3418a.hashCode();
    }

    public final String toString() {
        String str = this.f3418a;
        StringBuilder sb = new StringBuilder(str.length() + 27);
        sb.append("FoldersGridItem{newFolder=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }
}
