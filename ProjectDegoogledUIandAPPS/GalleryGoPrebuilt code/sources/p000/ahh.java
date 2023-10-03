package p000;

/* renamed from: ahh */
/* compiled from: PG */
public final class ahh extends ihg {

    /* renamed from: a */
    public final ahf f491a = ahf.f487a;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f491a.equals(((ahh) obj).f491a);
    }

    public final int hashCode() {
        return (ahh.class.getName().hashCode() * 31) + this.f491a.hashCode();
    }

    public final String toString() {
        return "Failure {mOutputData=" + this.f491a + '}';
    }
}
