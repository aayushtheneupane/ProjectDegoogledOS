package p000;

/* renamed from: gms */
/* compiled from: PG */
final /* synthetic */ class C0181gms implements hpr {

    /* renamed from: a */
    private final String f11634a;

    public C0181gms(String str) {
        this.f11634a = str;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        boolean z;
        String str = this.f11634a;
        str.getClass();
        ijy ijy = ((gni) obj).f11682a;
        if (ijy.containsKey(str)) {
            z = ((Boolean) ijy.get(str)).booleanValue();
        } else {
            z = false;
        }
        return Boolean.valueOf(!z);
    }
}
