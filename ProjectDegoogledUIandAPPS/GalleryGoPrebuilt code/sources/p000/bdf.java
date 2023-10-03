package p000;

/* renamed from: bdf */
/* compiled from: PG */
final class bdf implements bdd {

    /* renamed from: a */
    private final /* synthetic */ bdg f2081a;

    public bdf(bdg bdg) {
        this.f2081a = bdg;
    }

    public final String toString() {
        String obj = super.toString();
        String valueOf = String.valueOf(this.f2081a);
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 11 + String.valueOf(valueOf).length());
        sb.append(obj);
        sb.append("{fragment=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
