package p000;

import java.util.List;

/* renamed from: dvh */
/* compiled from: PG */
final class dvh extends dvn {

    /* renamed from: a */
    private final List f7452a;

    public dvh(List list) {
        this.f7452a = list;
    }

    /* renamed from: a */
    public final List mo4477a() {
        return this.f7452a;
    }

    /* renamed from: b */
    public final dwu mo4478b() {
        return dwu.CATEGORIES;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof dwv) {
            dwv dwv = (dwv) obj;
            if (dwu.CATEGORIES != dwv.mo4478b() || !this.f7452a.equals(dwv.mo4477a())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f7452a.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f7452a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 26);
        sb.append("PhotoGridItem{categories=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
