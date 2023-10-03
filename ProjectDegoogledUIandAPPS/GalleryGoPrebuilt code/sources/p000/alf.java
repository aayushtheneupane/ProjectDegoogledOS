package p000;

/* renamed from: alf */
/* compiled from: PG */
public final class alf {

    /* renamed from: a */
    public String f710a;

    /* renamed from: b */
    public int f711b;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof alf) {
            alf alf = (alf) obj;
            if (this.f711b == alf.f711b) {
                return this.f710a.equals(alf.f710a);
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.f710a.hashCode() * 31) + gbz.m9997d(this.f711b);
    }
}
