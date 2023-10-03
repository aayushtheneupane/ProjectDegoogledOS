package p000;

/* renamed from: ckv */
/* compiled from: PG */
final class ckv extends ckm {

    /* renamed from: a */
    private final hto f4588a;

    /* renamed from: b */
    private final int f4589b;

    public ckv(int i, hto hto) {
        this.f4589b = i;
        if (hto != null) {
            this.f4588a = hto;
            return;
        }
        throw new NullPointerException("Null selectedItems");
    }

    /* renamed from: a */
    public final hto mo3209a() {
        return this.f4588a;
    }

    /* renamed from: b */
    public final int mo3210b() {
        return this.f4589b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ckm) {
            ckm ckm = (ckm) obj;
            return this.f4589b == ckm.mo3210b() && this.f4588a.equals(ckm.mo3209a());
        }
    }

    public final int hashCode() {
        return ((this.f4589b ^ 1000003) * 1000003) ^ this.f4588a.hashCode();
    }

    public final String toString() {
        String str = this.f4589b != 1 ? "COPY" : "MOVE";
        String valueOf = String.valueOf(this.f4588a);
        StringBuilder sb = new StringBuilder(str.length() + 49 + String.valueOf(valueOf).length());
        sb.append("AddItemsToFolderEvent{operation=");
        sb.append(str);
        sb.append(", selectedItems=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
