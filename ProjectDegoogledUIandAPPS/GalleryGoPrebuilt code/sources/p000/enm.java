package p000;

import java.util.Arrays;

/* renamed from: enm */
/* compiled from: PG */
final class enm {

    /* renamed from: a */
    public final eln f8661a;

    /* renamed from: b */
    public final ejt f8662b;

    public /* synthetic */ enm(eln eln, ejt ejt) {
        this.f8661a = eln;
        this.f8662b = ejt;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof enm)) {
            return false;
        }
        enm enm = (enm) obj;
        if (!C0652xy.m16068a((Object) this.f8661a, (Object) enm.f8661a) || !C0652xy.m16068a((Object) this.f8662b, (Object) enm.f8662b)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f8661a, this.f8662b});
    }

    public final String toString() {
        eqo a = C0652xy.m16063a((Object) this);
        a.mo5163a("key", this.f8661a);
        a.mo5163a("feature", this.f8662b);
        return a.toString();
    }
}
