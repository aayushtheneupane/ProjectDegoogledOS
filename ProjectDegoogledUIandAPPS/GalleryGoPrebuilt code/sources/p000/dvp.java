package p000;

/* renamed from: dvp */
/* compiled from: PG */
final class dvp extends dxo {

    /* renamed from: a */
    private final boolean f7459a;

    /* renamed from: b */
    private final hto f7460b;

    public dvp(boolean z, hto hto) {
        this.f7459a = z;
        if (hto != null) {
            this.f7460b = hto;
            return;
        }
        throw new NullPointerException("Null selectedItems");
    }

    /* renamed from: a */
    public final boolean mo4506a() {
        return this.f7459a;
    }

    /* renamed from: b */
    public final hto mo4507b() {
        return this.f7460b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof dxo) {
            dxo dxo = (dxo) obj;
            return this.f7459a == dxo.mo4506a() && this.f7460b.equals(dxo.mo4507b());
        }
    }

    public final int hashCode() {
        return (((!this.f7459a ? 1237 : 1231) ^ 1000003) * 1000003) ^ this.f7460b.hashCode();
    }

    public final String toString() {
        boolean z = this.f7459a;
        String valueOf = String.valueOf(this.f7460b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 61);
        sb.append("PickFolderForSelectedItemsEvent{isMove=");
        sb.append(z);
        sb.append(", selectedItems=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
