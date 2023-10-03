package p000;

import p003j$.util.Objects;

/* renamed from: iph */
/* compiled from: PG */
public final class iph {

    /* renamed from: a */
    private final Class f14622a;

    private iph(Class cls) {
        this.f14622a = cls;
    }

    /* renamed from: a */
    public static iph m14288a(Class cls) {
        return new iph((Class) ife.m12898e((Object) cls));
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof iph) && Objects.equals(this.f14622a, ((iph) obj).f14622a) && Objects.equals((Object) null, (Object) null);
        }
        return true;
    }

    public final int hashCode() {
        return ((Objects.hashCode(this.f14622a) ^ 1000003) * 1000003) ^ Objects.hashCode((Object) null);
    }

    public final String toString() {
        return this.f14622a.getCanonicalName();
    }
}
