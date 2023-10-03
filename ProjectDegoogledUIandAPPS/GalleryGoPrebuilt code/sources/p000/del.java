package p000;

import java.util.List;

/* renamed from: del */
/* compiled from: PG */
final class del extends dgb {

    /* renamed from: a */
    private final List f6391a;

    /* renamed from: b */
    private final List f6392b;

    /* renamed from: c */
    private final List f6393c;

    public /* synthetic */ del(List list, List list2, List list3) {
        this.f6391a = list;
        this.f6392b = list2;
        this.f6393c = list3;
    }

    /* renamed from: a */
    public final List mo4092a() {
        return this.f6391a;
    }

    /* renamed from: b */
    public final List mo4093b() {
        return this.f6392b;
    }

    /* renamed from: c */
    public final List mo4094c() {
        return this.f6393c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof dgb) {
            dgb dgb = (dgb) obj;
            return this.f6391a.equals(dgb.mo4092a()) && this.f6392b.equals(dgb.mo4093b()) && this.f6393c.equals(dgb.mo4094c());
        }
    }

    public final int hashCode() {
        return ((((this.f6391a.hashCode() ^ 1000003) * 1000003) ^ this.f6392b.hashCode()) * 1000003) ^ this.f6393c.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f6391a);
        String valueOf2 = String.valueOf(this.f6392b);
        String valueOf3 = String.valueOf(this.f6393c);
        int length = String.valueOf(valueOf).length();
        StringBuilder sb = new StringBuilder(length + 51 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append("SyncUpdate{newMedia=");
        sb.append(valueOf);
        sb.append(", updatedMedia=");
        sb.append(valueOf2);
        sb.append(", deletedMedia=");
        sb.append(valueOf3);
        sb.append("}");
        return sb.toString();
    }
}
