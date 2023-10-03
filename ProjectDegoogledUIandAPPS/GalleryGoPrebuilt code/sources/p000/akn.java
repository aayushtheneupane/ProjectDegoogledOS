package p000;

/* renamed from: akn */
/* compiled from: PG */
public final class akn {

    /* renamed from: a */
    public String f692a;

    /* renamed from: b */
    public Long f693b;

    public akn(String str, long j) {
        this.f692a = str;
        this.f693b = Long.valueOf(j);
    }

    public akn(String str) {
        this(str, 0);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof akn) {
            akn akn = (akn) obj;
            if (this.f692a.equals(akn.f692a)) {
                Long l = this.f693b;
                if (l != null) {
                    return l.equals(akn.f693b);
                }
                return akn.f693b == null;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.f692a.hashCode() * 31;
        Long l = this.f693b;
        return hashCode + (l != null ? l.hashCode() : 0);
    }
}
