package p000;

/* renamed from: cy */
/* compiled from: PG */
final class C0083cy extends C0084cz {
    public static final long serialVersionUID = 7766999779862263523L;

    public C0083cy(C0086da daVar, C0086da daVar2) {
        super(daVar, daVar2);
    }

    /* renamed from: a */
    public final boolean mo3936a(C0087db dbVar) {
        return this.f6081a.mo3936a(dbVar) && this.f6082b.mo3936a(dbVar);
    }

    public final String toString() {
        String obj = this.f6081a.toString();
        String obj2 = this.f6082b.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 5 + String.valueOf(obj2).length());
        sb.append(obj);
        sb.append(" and ");
        sb.append(obj2);
        return sb.toString();
    }
}
