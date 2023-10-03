package p000;

/* renamed from: fdu */
/* compiled from: PG */
public final class fdu {

    /* renamed from: a */
    public final fdw f9331a;

    private fdu(fdw fdw) {
        this.f9331a = fdw;
    }

    /* renamed from: a */
    public static fdu m8653a() {
        return new fdu((fdw) new fdt(iay.TAP).f9330a.mo8770g());
    }

    public final String toString() {
        Object[] objArr = new Object[2];
        iay a = iay.m12593a(this.f9331a.f9336b);
        if (a == null) {
            a = iay.UNASSIGNED_USER_ACTION_ID;
        }
        objArr[0] = a.name();
        objArr[1] = this.f9331a;
        return String.format("Action: %s; Full: %s", objArr);
    }
}
