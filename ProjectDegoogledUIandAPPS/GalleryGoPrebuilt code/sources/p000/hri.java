package p000;

import java.util.Map;
import java.util.Set;

/* renamed from: hri */
/* compiled from: PG */
abstract class hri implements hum {

    /* renamed from: a */
    private transient Set f13304a;

    /* renamed from: b */
    private transient Map f13305b;

    /* renamed from: b */
    public abstract Set mo7763b();

    /* renamed from: c */
    public abstract Map mo7764c();

    /* renamed from: e */
    public Map mo7787e() {
        Map map = this.f13305b;
        if (map != null) {
            return map;
        }
        Map c = mo7764c();
        this.f13305b = c;
        return c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof hum)) {
            return false;
        }
        return mo7787e().equals(((hum) obj).mo7787e());
    }

    public final int hashCode() {
        return mo7787e().hashCode();
    }

    /* renamed from: d */
    public final Set mo7786d() {
        Set set = this.f13304a;
        if (set != null) {
            return set;
        }
        Set b = mo7763b();
        this.f13304a = b;
        return b;
    }

    public final String toString() {
        return mo7787e().toString();
    }
}
