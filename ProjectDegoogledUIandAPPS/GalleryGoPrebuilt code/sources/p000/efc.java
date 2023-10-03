package p000;

import java.util.List;

/* renamed from: efc */
/* compiled from: PG */
final class efc extends efd {

    /* renamed from: a */
    private final List f8138a;

    /* renamed from: b */
    private final boolean f8139b;

    public efc(List list, boolean z) {
        if (list != null) {
            this.f8138a = list;
            this.f8139b = z;
            return;
        }
        throw new NullPointerException("Null apps");
    }

    /* renamed from: a */
    public final List mo4762a() {
        return this.f8138a;
    }

    /* renamed from: b */
    public final boolean mo4763b() {
        return this.f8139b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof efd) {
            efd efd = (efd) obj;
            return this.f8138a.equals(efd.mo4762a()) && this.f8139b == efd.mo4763b();
        }
    }

    public final int hashCode() {
        return ((this.f8138a.hashCode() ^ 1000003) * 1000003) ^ (!this.f8139b ? 1237 : 1231);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f8138a);
        boolean z = this.f8139b;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 56);
        sb.append("SharingFragmentData{apps=");
        sb.append(valueOf);
        sb.append(", compressionToggleState=");
        sb.append(z);
        sb.append("}");
        return sb.toString();
    }
}
