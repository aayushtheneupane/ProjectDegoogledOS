package p000;

/* renamed from: de */
/* compiled from: PG */
final class C0090de extends C0084cz {
    public static final long serialVersionUID = 1405488568664762222L;

    public C0090de(C0086da daVar, C0086da daVar2) {
        super(daVar, daVar2);
    }

    /* renamed from: a */
    public final boolean mo3936a(C0087db dbVar) {
        return this.f6081a.mo3936a(dbVar) || this.f6082b.mo3936a(dbVar);
    }

    public final String toString() {
        String obj = this.f6081a.toString();
        String obj2 = this.f6082b.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 4 + String.valueOf(obj2).length());
        sb.append(obj);
        sb.append(" or ");
        sb.append(obj2);
        return sb.toString();
    }
}
