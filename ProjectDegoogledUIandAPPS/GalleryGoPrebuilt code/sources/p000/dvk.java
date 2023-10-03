package p000;

/* renamed from: dvk */
/* compiled from: PG */
public final class dvk extends dvn {

    /* renamed from: a */
    private final cxi f7455a;

    public dvk(cxi cxi) {
        this.f7455a = cxi;
    }

    /* renamed from: b */
    public final dwu mo4478b() {
        return dwu.MEDIA;
    }

    /* renamed from: e */
    public final cxi mo4490e() {
        return this.f7455a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof dwv) {
            dwv dwv = (dwv) obj;
            if (dwu.MEDIA != dwv.mo4478b() || !this.f7455a.equals(dwv.mo4490e())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        cxi cxi = this.f7455a;
        int i = cxi.f14173y;
        if (i != 0) {
            return i;
        }
        int a = ikp.f14397a.mo8876a((Object) cxi).mo8862a(cxi);
        cxi.f14173y = a;
        return a;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f7455a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 21);
        sb.append("PhotoGridItem{media=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
