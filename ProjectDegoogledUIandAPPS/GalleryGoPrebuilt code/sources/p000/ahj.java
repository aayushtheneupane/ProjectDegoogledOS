package p000;

/* renamed from: ahj */
/* compiled from: PG */
public final class ahj extends ihg {

    /* renamed from: a */
    public final ahf f492a;

    public ahj() {
        this(ahf.f487a);
    }

    public ahj(ahf ahf) {
        this.f492a = ahf;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f492a.equals(((ahj) obj).f492a);
    }

    public final int hashCode() {
        return (ahj.class.getName().hashCode() * 31) + this.f492a.hashCode();
    }

    public final String toString() {
        return "Success {mOutputData=" + this.f492a + '}';
    }
}
