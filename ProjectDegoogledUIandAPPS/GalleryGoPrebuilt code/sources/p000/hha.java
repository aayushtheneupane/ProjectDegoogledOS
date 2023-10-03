package p000;

/* renamed from: hha */
/* compiled from: PG */
public final class hha {

    /* renamed from: a */
    public final hjz f12725a;

    public hha(hjz hjz) {
        this.f12725a = hjz;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof hha) && this.f12725a.f12901b.equals(((hha) obj).f12725a.f12901b);
    }

    /* renamed from: a */
    public static hha m11482a(String str) {
        iir g = hjz.f12898c.mo8793g();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        hjz hjz = (hjz) g.f14318b;
        str.getClass();
        hjz.f12900a |= 1;
        hjz.f12901b = str;
        return new hha((hjz) g.mo8770g());
    }

    public final int hashCode() {
        return this.f12725a.f12901b.hashCode();
    }
}
