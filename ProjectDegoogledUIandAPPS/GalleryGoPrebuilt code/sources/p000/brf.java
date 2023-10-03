package p000;

/* renamed from: brf */
/* compiled from: PG */
final class brf extends bri {

    /* renamed from: a */
    private final btl f3417a;

    public brf(btl btl) {
        this.f3417a = btl;
    }

    /* renamed from: a */
    public final btl mo2688a() {
        return this.f3417a;
    }

    /* renamed from: b */
    public final int mo2689b() {
        return 2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof bsb) {
            bsb bsb = (bsb) obj;
            if (bsb.mo2689b() != 2 || !this.f3417a.equals(bsb.mo2688a())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f3417a.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f3417a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 30);
        sb.append("FoldersGridItem{deviceFolder=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
