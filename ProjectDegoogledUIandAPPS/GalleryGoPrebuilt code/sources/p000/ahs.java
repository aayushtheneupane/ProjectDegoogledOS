package p000;

import java.util.Set;
import java.util.UUID;

/* renamed from: ahs */
/* compiled from: PG */
public final class ahs {

    /* renamed from: a */
    public final UUID f499a;

    /* renamed from: b */
    public final alg f500b;

    /* renamed from: c */
    public final Set f501c;

    public ahs(ahk ahk) {
        this(ahk.f496a, ahk.f497b, ahk.f498c);
    }

    public ahs(ahp ahp) {
        this(ahp.f496a, ahp.f497b, ahp.f498c);
    }

    private ahs(UUID uuid, alg alg, Set set) {
        this.f499a = uuid;
        this.f500b = alg;
        this.f501c = set;
    }

    /* renamed from: a */
    public final String mo499a() {
        return this.f499a.toString();
    }
}
