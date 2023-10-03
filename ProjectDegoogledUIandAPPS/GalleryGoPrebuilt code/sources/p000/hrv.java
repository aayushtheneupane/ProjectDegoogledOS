package p000;

import java.io.Serializable;
import java.util.Comparator;

/* renamed from: hrv */
/* compiled from: PG */
final class hrv extends huv implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final Comparator f13332a;

    public hrv(Comparator comparator) {
        this.f13332a = (Comparator) ife.m12898e((Object) comparator);
    }

    public final int compare(Object obj, Object obj2) {
        return this.f13332a.compare(obj, obj2);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof hrv) {
            return this.f13332a.equals(((hrv) obj).f13332a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f13332a.hashCode();
    }

    public final String toString() {
        return this.f13332a.toString();
    }
}
