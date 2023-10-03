package p000;

/* renamed from: hqc */
/* compiled from: PG */
public final class hqc extends hpy {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    public final Object f13250a;

    public hqc(Object obj) {
        this.f13250a = obj;
    }

    /* renamed from: a */
    public final boolean mo7646a() {
        return true;
    }

    /* renamed from: b */
    public final Object mo7647b() {
        return this.f13250a;
    }

    /* renamed from: c */
    public final Object mo7648c() {
        return this.f13250a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof hqc) {
            return this.f13250a.equals(((hqc) obj).f13250a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f13250a.hashCode() + 1502476572;
    }

    /* renamed from: a */
    public final Object mo7645a(Object obj) {
        ife.m12869b(obj, (Object) "use Optional.orNull() instead of Optional.or(null)");
        return this.f13250a;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f13250a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 13);
        sb.append("Optional.of(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
