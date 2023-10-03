package p000;

/* renamed from: auq */
/* compiled from: PG */
final class auq implements auv {

    /* renamed from: a */
    public int f1721a;

    /* renamed from: b */
    public Class f1722b;

    /* renamed from: c */
    private final aur f1723c;

    public auq(aur aur) {
        this.f1723c = aur;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof auq) {
            auq auq = (auq) obj;
            if (this.f1721a == auq.f1721a && this.f1722b == auq.f1722b) {
                return true;
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.f1721a * 31;
        Class cls = this.f1722b;
        return i + (cls != null ? cls.hashCode() : 0);
    }

    /* renamed from: a */
    public final void mo1653a() {
        this.f1723c.mo1640a(this);
    }

    public final String toString() {
        int i = this.f1721a;
        String valueOf = String.valueOf(this.f1722b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
        sb.append("Key{size=");
        sb.append(i);
        sb.append("array=");
        sb.append(valueOf);
        sb.append('}');
        return sb.toString();
    }
}
