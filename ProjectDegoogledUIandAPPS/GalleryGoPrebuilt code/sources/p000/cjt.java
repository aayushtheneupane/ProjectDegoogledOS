package p000;

/* renamed from: cjt */
/* compiled from: PG */
final class cjt extends cjw {

    /* renamed from: a */
    private final cxe f4521a;

    public cjt(cxe cxe) {
        if (cxe != null) {
            this.f4521a = cxe;
            return;
        }
        throw new NullPointerException("Null updatedFolder");
    }

    /* renamed from: a */
    public final cxe mo3186a() {
        return this.f4521a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof cjw) {
            return this.f4521a.equals(((cjw) obj).mo3186a());
        }
        return false;
    }

    public final int hashCode() {
        cxe cxe = this.f4521a;
        int i = cxe.f14173y;
        if (i == 0) {
            i = ikp.f14397a.mo8876a((Object) cxe).mo8862a(cxe);
            cxe.f14173y = i;
        }
        return 1000003 ^ i;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f4521a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 34);
        sb.append("FolderUpdatedEvent{updatedFolder=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
