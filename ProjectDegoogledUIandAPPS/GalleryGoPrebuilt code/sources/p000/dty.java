package p000;

/* renamed from: dty */
/* compiled from: PG */
public final class dty extends dua {

    /* renamed from: a */
    private final cia f7370a;

    public dty(cia cia) {
        this.f7370a = cia;
    }

    /* renamed from: a */
    public final int mo4432a() {
        return 1;
    }

    /* renamed from: c */
    public final cia mo4437c() {
        return this.f7370a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof dul) {
            dul dul = (dul) obj;
            if (dul.mo4432a() != 1 || !this.f7370a.equals(dul.mo4437c())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f7370a.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f7370a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
        sb.append("PeopleGridItem{person=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
