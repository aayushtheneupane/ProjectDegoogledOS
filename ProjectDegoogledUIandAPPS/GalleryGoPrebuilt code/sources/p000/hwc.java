package p000;

/* renamed from: hwc */
/* compiled from: PG */
final class hwc {

    /* renamed from: a */
    private final hwg f13505a;

    /* renamed from: b */
    private final String f13506b;

    public /* synthetic */ hwc(hwg hwg, String str) {
        this.f13505a = (hwg) ife.m12827a((Object) hwg, "log site");
        this.f13506b = (String) ife.m12827a((Object) str, "log site key");
    }

    public final boolean equals(Object obj) {
        if (obj instanceof hwc) {
            hwc hwc = (hwc) obj;
            if (!this.f13505a.equals(hwc.f13505a) || !this.f13506b.equals(hwc.f13506b)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f13505a.hashCode() ^ this.f13506b.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f13505a);
        String str = this.f13506b;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 46 + str.length());
        sb.append("SpecializedLogSiteKey{ logSite=");
        sb.append(valueOf);
        sb.append(", extraKey='");
        sb.append(str);
        sb.append("' }");
        return sb.toString();
    }
}
