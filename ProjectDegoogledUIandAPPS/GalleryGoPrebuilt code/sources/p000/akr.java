package p000;

/* renamed from: akr */
/* compiled from: PG */
public final class akr {

    /* renamed from: a */
    public final String f696a;

    /* renamed from: b */
    public final int f697b;

    public akr(String str, int i) {
        this.f696a = str;
        this.f697b = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof akr) {
            akr akr = (akr) obj;
            if (this.f697b == akr.f697b) {
                return this.f696a.equals(akr.f696a);
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.f696a.hashCode() * 31) + this.f697b;
    }
}
