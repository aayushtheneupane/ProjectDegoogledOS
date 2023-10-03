package p000;

/* renamed from: dvo */
/* compiled from: PG */
public final class dvo extends dwd {

    /* renamed from: a */
    private final hto f7458a;

    public dvo(hto hto) {
        if (hto != null) {
            this.f7458a = hto;
            return;
        }
        throw new NullPointerException("Null selectedItems");
    }

    /* renamed from: a */
    public final hto mo4502a() {
        return this.f7458a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof dwd) {
            return this.f7458a.equals(((dwd) obj).mo4502a());
        }
        return false;
    }

    public final int hashCode() {
        return this.f7458a.hashCode() ^ 1000003;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f7458a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 43);
        sb.append("MultiplePhotosSelectedEvent{selectedItems=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
