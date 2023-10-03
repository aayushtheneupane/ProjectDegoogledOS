package p000;

import java.io.Serializable;

/* renamed from: hpt */
/* compiled from: PG */
public final class hpt implements Serializable, hpr {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    public final Object f13233a;

    public hpt(Object obj) {
        this.f13233a = obj;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        return this.f13233a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof hpt) {
            return ife.m12891c(this.f13233a, ((hpt) obj).f13233a);
        }
        return false;
    }

    public final int hashCode() {
        Object obj = this.f13233a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f13233a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
        sb.append("Functions.constant(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
