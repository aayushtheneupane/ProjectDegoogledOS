package p000;

/* renamed from: iii */
/* compiled from: PG */
final class iii {

    /* renamed from: a */
    private final Object f14245a;

    /* renamed from: b */
    private final int f14246b;

    public iii(Object obj, int i) {
        this.f14245a = obj;
        this.f14246b = i;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof iii) {
            iii iii = (iii) obj;
            if (this.f14245a == iii.f14245a && this.f14246b == iii.f14246b) {
                return true;
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.f14245a) * 65535) + this.f14246b;
    }
}
