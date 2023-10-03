package p000;

/* renamed from: ioo */
/* compiled from: PG */
public final class ioo extends iop {

    /* renamed from: a */
    private final Object f14604a;

    public /* synthetic */ ioo(Object obj) {
        super((byte[]) null);
        this.f14604a = obj;
    }

    /* renamed from: a */
    public final Object mo9037a() {
        return this.f14604a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ioo) {
            return ife.m12891c(this.f14604a, ((ioo) obj).f14604a);
        }
        return false;
    }

    public final int hashCode() {
        Object obj = this.f14604a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f14604a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 10);
        sb.append("Produced[");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }
}
