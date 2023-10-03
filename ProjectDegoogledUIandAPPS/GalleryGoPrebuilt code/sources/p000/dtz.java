package p000;

import p003j$.util.Optional;

/* renamed from: dtz */
/* compiled from: PG */
final class dtz extends dua {

    /* renamed from: a */
    private final Optional f7371a;

    public dtz(Optional optional) {
        this.f7371a = optional;
    }

    /* renamed from: a */
    public final int mo4432a() {
        return 3;
    }

    /* renamed from: d */
    public final Optional mo4441d() {
        return this.f7371a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof dul) {
            dul dul = (dul) obj;
            if (dul.mo4432a() != 3 || !this.f7371a.equals(dul.mo4441d())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f7371a.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f7371a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 28);
        sb.append("PeopleGridItem{placeholder=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
