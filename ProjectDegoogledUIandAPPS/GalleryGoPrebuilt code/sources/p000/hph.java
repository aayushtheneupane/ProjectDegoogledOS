package p000;

/* renamed from: hph */
/* compiled from: PG */
public final class hph extends hpy {

    /* renamed from: a */
    public static final hph f13219a = new hph();
    public static final long serialVersionUID = 0;

    private Object readResolve() {
        return f13219a;
    }

    /* renamed from: a */
    public final boolean mo7646a() {
        return false;
    }

    /* renamed from: c */
    public final Object mo7648c() {
        return null;
    }

    public final boolean equals(Object obj) {
        return obj == this;
    }

    public final int hashCode() {
        return 2040732332;
    }

    public final String toString() {
        return "Optional.absent()";
    }

    private hph() {
    }

    /* renamed from: b */
    public final Object mo7647b() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    /* renamed from: a */
    public final Object mo7645a(Object obj) {
        return ife.m12869b(obj, (Object) "use Optional.orNull() instead of Optional.or(null)");
    }
}
