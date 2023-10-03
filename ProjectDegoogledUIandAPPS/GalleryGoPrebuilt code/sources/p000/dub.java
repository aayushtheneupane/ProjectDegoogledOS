package p000;

/* renamed from: dub */
/* compiled from: PG */
public final class dub implements hoi {

    /* renamed from: a */
    public final hto f7382a;

    public dub(hto hto) {
        this.f7382a = hto;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof dub) {
            return this.f7382a.equals(((dub) obj).f7382a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f7382a.hashCode();
    }

    public final String toString() {
        return String.format("MultiplePeopleSelectedEvent[%s]", new Object[]{this.f7382a});
    }
}
