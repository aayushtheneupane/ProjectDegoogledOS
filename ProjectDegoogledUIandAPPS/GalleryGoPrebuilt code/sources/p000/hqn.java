package p000;

import java.io.Serializable;
import java.util.Arrays;

/* renamed from: hqn */
/* compiled from: PG */
public final class hqn implements Serializable, hqk {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    public final Object f13267a;

    public hqn(Object obj) {
        this.f13267a = obj;
    }

    /* renamed from: a */
    public final Object mo2652a() {
        return this.f13267a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof hqn) {
            return ife.m12891c(this.f13267a, ((hqn) obj).f13267a);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f13267a});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f13267a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
        sb.append("Suppliers.ofInstance(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
