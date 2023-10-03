package p000;

/* renamed from: bcy */
/* compiled from: PG */
final class bcy implements bdd {

    /* renamed from: a */
    private final /* synthetic */ bcz f2066a;

    public bcy(bcz bcz) {
        this.f2066a = bcz;
    }

    public final String toString() {
        String obj = super.toString();
        String valueOf = String.valueOf(this.f2066a);
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 11 + String.valueOf(valueOf).length());
        sb.append(obj);
        sb.append("{fragment=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
