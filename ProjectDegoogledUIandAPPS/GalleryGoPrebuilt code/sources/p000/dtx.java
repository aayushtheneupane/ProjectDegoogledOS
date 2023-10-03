package p000;

/* renamed from: dtx */
/* compiled from: PG */
public final class dtx extends dua {

    /* renamed from: a */
    private final ede f7369a;

    public dtx(ede ede, byte[] bArr) {
        this.f7369a = ede;
    }

    /* renamed from: a */
    public final int mo4432a() {
        return 2;
    }

    /* renamed from: b */
    public final ede mo4433b() {
        return this.f7369a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof dul) {
            dul dul = (dul) obj;
            if (dul.mo4432a() != 2 || !this.f7369a.equals(dul.mo4433b())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f7369a.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f7369a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
        sb.append("PeopleGridItem{footer=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
