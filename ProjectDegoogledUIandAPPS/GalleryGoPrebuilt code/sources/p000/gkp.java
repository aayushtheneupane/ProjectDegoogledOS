package p000;

/* renamed from: gkp */
/* compiled from: PG */
public final class gkp extends gkn {

    /* renamed from: a */
    public final int f11548a;

    public gkp(int i) {
        this.f11548a = i;
    }

    /* renamed from: a */
    public final int mo6807a() {
        return this.f11548a;
    }

    public final int hashCode() {
        return this.f11548a ^ 1000003;
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof gkn) && this.f11548a == ((gkn) obj).mo6807a();
        }
        return true;
    }

    public final String toString() {
        int i = this.f11548a;
        StringBuilder sb = new StringBuilder(25);
        sb.append("AccountId{id=");
        sb.append(i);
        sb.append("}");
        return sb.toString();
    }
}
