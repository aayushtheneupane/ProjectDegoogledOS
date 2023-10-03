package p000;

/* renamed from: hsa */
/* compiled from: PG */
public abstract class hsa implements Iterable {

    /* renamed from: a */
    private final hpy f13337a;

    protected hsa() {
        this.f13337a = hph.f13219a;
    }

    public hsa(Iterable iterable) {
        ife.m12898e((Object) iterable);
        this.f13337a = hpy.m11894c(this == iterable ? null : iterable);
    }

    /* renamed from: a */
    public static hsa m11995a(Iterable iterable) {
        if (!(iterable instanceof hsa)) {
            return new hrx(iterable, iterable);
        }
        return (hsa) iterable;
    }

    /* renamed from: a */
    public final Iterable mo7868a() {
        return (Iterable) this.f13337a.mo7645a(this);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        boolean z = true;
        for (Object append : mo7868a()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(append);
            z = false;
        }
        sb.append(']');
        return sb.toString();
    }
}
